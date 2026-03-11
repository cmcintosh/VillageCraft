package com.villagecraft.entity.goal;

import java.util.List;
import java.util.Optional;

import com.villagecraft.VillageCraft;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;

/**
 * VillagerGoalSleep implements sleeping behavior for villagers.
 * 
 * Sleep schedule:
 * - Night: 18000-6000 ticks (6pm - 6am)
 * - Villagers seek out beds during night
 * - Villagers wake up at day start
 * 
 * Sleep behavior:
 * 1. During night, villager seeks nearest bed
 * 2. Pathfinds to bed location
 * 3. Enters bed (plays lie-down animation)
 * 4. Sleeps until morning
 * 5. Wakes up and resumes normal behavior
 * 
 * If no bed is available:
 * - Villager will wander near their home/job site
 * - May stand idle and wait for day
 * - Will eventually fall asleep standing (if very tired)
 * 
 * @author VillageCraft Team
 * @since 1.0
 * @see VillagerGoalBase
 */
public class VillagerGoalSleep extends VillagerGoalBase {
	
	// Sleep timing
	protected static final long NIGHT_START = 18000L; // 6 PM
	protected static final long NIGHT_END = 6000L;   // 6 AM
	protected static final int TICKS_PER_DAY = 24000;
	
	// Sleep search range
	protected static final double BED_SEARCH_RANGE = 100.0D;
	protected static final double BED_ACCEPT_RANGE = 2.5D;
	
	// Sleep state
	protected SleepState sleepState = SleepState.AWAKE;
	protected BlockPos targetBedPos;
	protected boolean isInBed = false;
	protected int sleepTicks = 0;
	protected int pathingCooldown = 0;
	
	/**
	 * Sleep state machine
	 */
	public enum SleepState {
		AWAKE,        // Not sleeping, daytime
		SEEKING_BED,  // Looking for a bed
		PATHING,      // Moving to bed
		GETTING_IN,   // Starting to enter bed
		SLEEPING,     // In bed, sleeping
		WAKING_UP     // Getting out of bed
	}
	
	/**
	 * Creates a new sleep goal for the villager
	 * @param entity The villager entity
	 */
	public VillagerGoalSleep(VillagerEntity entity) {
		super(entity);
	}
	
	/**
	 * Checks if the villager should try to sleep
	 */
	@Override
	public boolean shouldExecute() {
		// Only sleep at night
		if (!isNightTime()) {
			return false;
		}
		
		// Don't sleep if critically hungry (eat first)
		if (isCriticallyHungry()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Continue sleeping if still night
	 */
	@Override
	public boolean shouldContinueExecuting() {
		// Keep sleeping while it's night
		// Even if we're not in a bed, we should try to find one
		return isNightTime() || this.sleepState == SleepState.SLEEPING;
	}
	
	/**
	 * Called when sleep goal starts
	 */
	@Override
	public void startExecuting() {
		super.startExecuting();
		this.transitionToState(VillagerState.SLEEPING);
		this.sleepState = SleepState.SEEKING_BED;
		this.targetBedPos = null;
		this.isInBed = false;
		this.sleepTicks = 0;
		
		VillageCraft.LOGGER.debug("Villager " + this.villager.getUniqueID() + 
			" is starting to sleep (night time)");
	}
	
	/**
	 * Called when sleep goal stops
	 */
	@Override
	public void resetTask() {
		super.resetTask();
		
		// Wake up from bed if sleeping
		if (this.isInBed) {
			wakeUpFromBed();
		}
		
		this.sleepState = SleepState.AWAKE;
		this.targetBedPos = null;
		this.isInBed = false;
		this.sleepTicks = 0;
		
		VillageCraft.LOGGER.debug("Villager " + this.villager.getUniqueID() + 
			" finished sleeping");
	}
	
	/**
	 * Main sleep tick behavior
	 */
	@Override
	protected void tickSleeping() {
		// Check if it's now day time (wake up)
		if (isDayTime() && (this.sleepState == SleepState.SLEEPING || this.isInBed)) {
			wakeUpFromBed();
			return;
		}
		
		// Handle pathing cooldown
		if (this.pathingCooldown > 0) {
			this.pathingCooldown--;
			return;
		}
		
		switch (this.sleepState) {
			case SEEKING_BED:
				findBed();
				break;
			case PATHING:
				pathToBed();
				break;
			case GETTING_IN:
				enterBed();
				break;
			case SLEEPING:
				continueSleeping();
				break;
			case WAKING_UP:
				wakeUpFromBed();
				break;
			case AWAKE:
			default:
				// Should be sleeping, restart search
				this.sleepState = SleepState.SEEKING_BED;
				break;
		}
	}
	
	/**
	 * Find a bed to sleep in
	 */
	protected void findBed() {
		// Don't search too frequently
		if (this.ticksToNextRun > 0) {
			return;
		}
		this.ticksToNextRun = 20; // Check every second
		
		// Try to find a bed POI
		BlockPos bedPos = findNearestBed();
		
		if (bedPos != null) {
			// Check if the bed is valid
			if (isBedValid(bedPos)) {
				this.targetBedPos = bedPos;
				this.sleepState = SleepState.PATHING;
				VillageCraft.LOGGER.debug("Villager found bed at " + bedPos);
			} else {
				// Bed is occupied or invalid, try again later
				this.pathingCooldown = 100;
			}
		} else {
			// No bed found, wait and wander
			wanderNearHome();
		}
	}
	
	/**
	 * Find the nearest bed using POI system
	 * @return BlockPos of bed or null if none found
	 */
	protected BlockPos findNearestBed() {
		// Use the villager's home memory if available
		Optional<GlobalPos> home = this.villager.getBrain().getMemory(MemoryModuleType.HOME);
		
		if (home.isPresent()) {
			GlobalPos homePos = home.get();
			BlockPos bed = homePos.getPos();
			if (isBedBlock(bed)) {
				return bed;
			}
		}
		
		// Otherwise search for any bed POI
		BlockPos searchCenter = this.getVillagerBlockPos();
		
		// Look for a bed using POI
		return this.findClosestBlock(
			PointOfInterestType.HOME,
			PointOfInterestManager.Status.ANY,
			searchCenter
		);
	}
	
	/**
	 * Check if a position has a valid bed
	 * @param pos BlockPos to check
	 * @return true if it's a bed
	 */
	protected boolean isBedBlock(BlockPos pos) {
		if (pos == null) return false;
		
		BlockState state = this.villager.world.getBlockState(pos);
		return state.getBlock() instanceof BedBlock;
	}
	
	/**
	 * Check if a bed is valid (not occupied, properly placed)
	 * @param pos BlockPos of the bed
	 * @return true if villager can sleep there
	 */
	protected boolean isBedValid(BlockPos pos) {
		if (!isBedBlock(pos)) {
			return false;
		}
		
		BlockState state = this.villager.world.getBlockState(pos);
		BedBlock bed = (BedBlock) state.getBlock();
		
		// Check if bed is already occupied
		// In vanilla, this would check world.getBlockState(pos).get(BedBlock.OCCUPIED)
		// For now, we just check if it's a bed
		return true;
	}
	
	/**
	 * Navigate to the target bed
	 */
	protected void pathToBed() {
		if (this.targetBedPos == null) {
			this.sleepState = SleepState.SEEKING_BED;
			return;
		}
		
		double distance = this.getBlockPosDistance(
			this.getVillagerBlockPos(),
			this.targetBedPos
		);
		
		if (distance < BED_ACCEPT_RANGE) {
			// At the bed, try to enter it
			this.sleepState = SleepState.GETTING_IN;
		} else {
			// Navigate to bed
			if (this.villager.getNavigator().noPath()) {
				boolean pathFound = this.villager.getNavigator().tryMoveToXYZ(
					this.targetBedPos.getX(),
					this.targetBedPos.getY(),
					this.targetBedPos.getZ(),
					this.villager.getAIMoveSpeed()
				);
				
				if (!pathFound) {
					// Can't path to bed, try another
					this.sleepState = SleepState.SEEKING_BED;
					this.targetBedPos = null;
					this.pathingCooldown = 100;
				}
			}
		}
	}
	
	/**
	 * Enter the bed
	 */
	protected void enterBed() {
		if (this.targetBedPos == null || !isBedBlock(this.targetBedPos)) {
			this.sleepState = SleepState.SEEKING_BED;
			return;
		}
		
		// Check if we're close enough
		double distance = this.getBlockPosDistance(
			this.getVillagerBlockPos(),
			this.targetBedPos
		);
		
		if (distance > BED_ACCEPT_RANGE) {
			// Move closer
			this.sleepState = SleepState.PATHING;
			return;
		}
		
		// Try to sleep in the bed
		// In vanilla, this would use bed.trySleep or similar
		// For now, we simulate sleeping
		this.isInBed = true;
		this.sleepState = SleepState.SLEEPING;
		
		// Stop moving
		this.villager.getNavigator().clearPath();
		
		// Set the villager's position to the bed
		BlockPos bedPos = this.targetBedPos;
		this.villager.setPosition(
			bedPos.getX() + 0.5D,
			bedPos.getY() + 0.5D,
			bedPos.getZ() + 0.5D
		);
		
		// Mark villager as sleeping
		this.villager.setSleeping(true);
		
		VillageCraft.LOGGER.debug("Villager entered bed at " + bedPos);
	}
	
	/**
	 * Continue sleeping in the bed
	 */
	protected void continueSleeping() {
		if (!this.isInBed) {
			this.sleepState = SleepState.SEEKING_BED;
			return;
		}
		
		// Keep the villager at the bed position
		if (this.targetBedPos != null) {
			Vector3d bedCenter = new Vector3d(
				this.targetBedPos.getX() + 0.5D,
				this.targetBedPos.getY() + 0.5D,
				this.targetBedPos.getZ() + 0.5D
			);
			
			// Keep villager at bed (prevent movement)
			this.villager.setMotion(0, 0, 0);
		}
		
		this.sleepTicks++;
		
		// Check if it's day time (wake up)
		if (isDayTime()) {
			wakeUpFromBed();
		}
	}
	
	/**
	 * Wake up from the bed
	 */
	protected void wakeUpFromBed() {
		if (!this.isInBed) {
			this.sleepState = SleepState.AWAKE;
			return;
		}
		
		// Clear sleeping flag
		this.villager.setSleeping(false);
		this.isInBed = false;
		
		// Move the villager slightly away from the bed
		if (this.targetBedPos != null) {
			// Find a safe spot to stand
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					if (x == 0 && z == 0) continue;
					
					BlockPos checkPos = this.targetBedPos.add(x, 0, z);
					if (this.villager.world.isAirBlock(checkPos) && 
						this.villager.world.isAirBlock(checkPos.up())) {
						this.villager.setPosition(
							checkPos.getX() + 0.5D,
							checkPos.getY(),
							checkPos.getZ() + 0.5D
						);
						break;
					}
				}
			}
		}
		
		this.sleepState = SleepState.AWAKE;
		this.sleepTicks = 0;
		
		VillageCraft.LOGGER.debug("Villager woke up from bed");
	}
	
	/**
	 * Wander near home or current position when no bed is available
	 */
	protected void wanderNearHome() {
		// If we don't have a navigator path, pick a random nearby spot
		if (this.villager.getNavigator().noPath() && this.villager.getRNG().nextInt(100) == 0) {
			// Look for home position
			Optional<GlobalPos> home = this.villager.getBrain().getMemory(MemoryModuleType.HOME);
			BlockPos center = home.map(GlobalPos::getPos).orElseGet(this::getVillagerBlockPos);
			
			// Pick a random position within 10 blocks
			int x = center.getX() + (this.villager.getRNG().nextInt(20) - 10);
			int z = center.getZ() + (this.villager.getRNG().nextInt(20) - 10);
			
			int y = center.getY();
			// Find floor level
			for (int i = 0; i < 5 && y > 0; i++) {
				if (this.villager.world.isAirBlock(new BlockPos(x, y, z))) {
					if (!this.villager.world.isAirBlock(new BlockPos(x, y - 1, z))) {
						break;
					}
				} else {
					y++;
				}
				y--;
			}
			
			this.villager.getNavigator().tryMoveToXYZ(x, y, z, this.villager.getAIMoveSpeed() * 0.5);
		}
		
		// Stop and stand still most of the time at night
		if (this.villager.getNavigator().noPath() && this.villager.getRNG().nextInt(20) == 0) {
			// Occasional "sleeping while standing" animation
			this.villager.setMotion(0, this.villager.getMotion().getY(), 0);
		}
	}
}
