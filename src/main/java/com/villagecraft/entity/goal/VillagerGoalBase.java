package com.villagecraft.entity.goal;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.villagecraft.VillageCraft;
import com.villagecraft.capabilities.CapabilityVillagerAttribute;
import com.villagecraft.capabilities.IVillagerAttribute;
import com.villagecraft.capabilities.IVillagerHonor;
import com.villagecraft.capabilities.IVillagerHunger;
import com.villagecraft.capabilities.VillagerHungerAttribute;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.item.profession_tokens.ItemProfessionToken;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;

/**
 * Base goal class for VillageCraft villager AI.
 * Implements a state machine for villager behaviors:
 * IDLE → WORKING → EATING → SLEEPING
 * 
 * Work schedule:
 * - Day (6000-18000 ticks): Work priority
 * - Night (18000-6000 ticks): Sleep priority
 * - Hunger < 30%: Interrupt to eat
 * 
 * @author VillageCraft Team
 * @since 1.0
 */
public class VillagerGoalBase extends Goal {
	
	protected VillagerEntity villager;
	protected PointOfInterestManager poiManager;
	
	// Related to tick/cooldowns
	protected int ticksToNextRun = 0;
	protected int cooldownTicks = 20;
	
	// Related to searching
	protected double maxScanRange = 100D;
	
	// Protected variables related to tasks
	protected LivingEntity targetLivingEntity;
	protected Block targetBlockType;
	protected BlockPos targetPosition;
	
	protected CompoundNBT extraVillagerData;
	protected ItemProfessionToken token;
	
	protected IVillagerHunger hunger;
	protected IVillagerHonor honor;
	
	/**
	 * Villager state machine states
	 */
	public enum VillagerState {
		IDLE,
		WORKING,
		EATING,
		SLEEPING
	}
	
	// Current state of the villager
	protected VillagerState currentState = VillagerState.IDLE;
	protected VillagerState previousState = VillagerState.IDLE;
	
	// State priorities (higher = more important)
	protected static final int PRIORITY_STARVING = 100;   // Critical hunger
	protected static final int PRIORITY_HUNGRY = 80;      // Needs to eat
	protected static final int PRIORITY_WORK = 60;        // Work during day
	protected static final int PRIORITY_SLEEP = 70;       // Sleep at night
	protected static final int PRIORITY_IDLE = 10;        // Default
	
	// Time constants (Minecraft world time)
	protected static final long DAY_START = 6000L;        // 6 AM
	protected static final long DAY_END = 18000L;         // 6 PM (sunset)
	
	// Hunger thresholds
	protected static final int HUNGER_CRITICAL = 4;       // Starving
	protected static final int HUNGER_LOW = 12;           // 30% of 40 max
	
	// Animation state tracking
	protected int remainingAnimationTicks = 0;
	
	/**
	 * Constructs the base villager goal with state machine initialization.
	 * @param entity The villager entity
	 */
	public VillagerGoalBase(VillagerEntity entity) { 
		super();
		villager = entity;
		ServerWorld world = (ServerWorld) entity.getEntityWorld();
		poiManager = world.getPointOfInterestManager();
		extraVillagerData = new CompoundNBT();
		
		// Initialize state to IDLE
		this.currentState = VillagerState.IDLE;
		this.previousState = VillagerState.IDLE;
	
	}
	
	/**
	 * Gets the current state of the villager
	 * @return Current VillagerState
	 */
	public VillagerState getCurrentState() {
		return this.currentState;
	}
	
	/**
	 * Transitions to a new state
	 * @param newState The state to transition to
	 */
	protected void transitionToState(VillagerState newState) {
		if (this.currentState != newState) {
			this.previousState = this.currentState;
			this.currentState = newState;
			this.onStateChange(this.previousState, newState);
		}
	}
	
	/**
	 * Called when the villager changes states
	 * Override in subclasses to handle state entry/exit
	 * @param oldState The previous state
	 * @param newState The new state
	 */
	protected void onStateChange(VillagerState oldState, VillagerState newState) {
		// Reset animation ticks on state change
		this.remainingAnimationTicks = 0;
		
		// Log state change for debugging
		VillageCraft.LOGGER.debug("Villager " + this.villager.getUniqueID() + 
			" changed state from " + oldState + " to " + newState);
	}
	
	/**
	 * Returns true if it's currently day time (6am - 6pm)
	 * @return true during day hours
	 */
	protected boolean isDayTime() {
		long worldTime = this.villager.world.getDayTime() % 24000L;
		return worldTime >= DAY_START && worldTime < DAY_END;
	}
	
	/**
	 * Returns true if it's currently night time (6pm - 6am)
	 * @return true during night hours
	 */
	protected boolean isNightTime() {
		return !isDayTime();
	}
	
	/**
	 * Gets the current hunger level of the villager
	 * @return Current hunger value, or -1 if capability not available
	 */
	protected int getCurrentHunger() {
		final int[] hungerValue = {-1};
		this.villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HUNGER).ifPresent(h -> {
			hungerValue[0] = h.getValue();
		});
		return hungerValue[0];
	}
	
	/**
	 * Checks if the villager is critically hungry (needs to eat immediately)
	 * @return true if hunger is at critical level
	 */
	protected boolean isCriticallyHungry() {
		int hunger = getCurrentHunger();
		return hunger >= 0 && hunger <= HUNGER_CRITICAL;
	}
	
	/**
	 * Checks if the villager is hungry (should try to eat)
	 * @return true if hunger is below threshold
	 */
	protected boolean isHungry() {
		int hunger = getCurrentHunger();
		return hunger >= 0 && hunger <= HUNGER_LOW;
	}
	
	/**
	 * Sets the villager to use a specific animation
	 * @param animationTicks Number of ticks to play animation
	 */
	protected void setAnimation(int animationTicks) {
		this.remainingAnimationTicks = animationTicks;
	}
	
	/**
	 * Plays the eating animation/sound for the villager
	 */
	protected void playEatingAnimation() {
		this.villager.setActiveHand(Hand.MAIN_HAND);
		this.setAnimation(32); // Standard eating animation lasts ~32 ticks
	}
	
	/**
	 * Plays the working animation for the villager
	 */
	protected void playWorkingAnimation() {
		// Use swing animation as work animation
		this.villager.swingArm(this.villager.getActiveHand());
		this.setAnimation(20);
	}
	
	/**
	 * Updates animation state - call in tick()
	 */
	protected void updateAnimation() {
		if (this.remainingAnimationTicks > 0) {
			this.remainingAnimationTicks--;
		}
	}
	
	
	
	protected boolean hasProfessionToken() { 
		
//		this.villager.getVillagerInventory().
		return false;
	}
	

	@Override
	public boolean shouldExecute() {
		// Goals are always eligible to run, but may do nothing based on state
		// The state machine controls what actions to take
		return true;
	}

	/**
	 * Continue executing if we're still active
	 */
	@Override
	public boolean shouldContinueExecuting() {
		return true;
	}
	
	protected BlockPos getVillagerBlockPos() { 
		if (this.villager != null) {
			return new BlockPos(this.villager.getPosX(), this.villager.getPosY(), this.villager.getPosZ());
		}
		return null;
	}
	
	/**
	 * Selects the next state based on villager conditions and time of day.
	 * Implements priority-based state selection:
	 * 1. Critical Hunger (starving) - highest priority
	 * 2. Hunger (low) - eat if possible
	 * 3. Night time - sleep
	 * 4. Day time - work
	 * 5. Idle - default
	 */
	protected void selectNextState() {
		int hunger = getCurrentHunger();
		boolean night = isNightTime();
		boolean day = isDayTime();
		
		// Priority 1: Critical hunger - must eat now
		if (hunger >= 0 && hunger <= HUNGER_CRITICAL) {
			this.transitionToState(VillagerState.EATING);
			return;
		}
		
		// Priority 2: Night time - sleep (unless hungry)
		if (night) {
			if (hunger >= 0 && hunger <= HUNGER_LOW) {
				this.transitionToState(VillagerState.EATING);
			} else {
				this.transitionToState(VillagerState.SLEEPING);
			}
			return;
		}
		
		// Priority 3: Day time - work or eat
		if (day) {
			if (hunger >= 0 && hunger <= HUNGER_LOW) {
				this.transitionToState(VillagerState.EATING);
			} else {
				this.transitionToState(VillagerState.WORKING);
			}
			return;
		}
		
		// Default: Idle
		this.transitionToState(VillagerState.IDLE);
	}
	
	/**
	 * Main tick method that handles state transitions and updates.
	 * This is called every tick to update villager behavior.
	 */
	@Override
	public void tick() { 
		// Update animation state
		this.updateAnimation();
		
		// Re-evaluate state based on conditions (every 20 ticks for performance)
		if (this.ticksToNextRun-- <= 0) {
			this.ticksToNextRun = this.cooldownTicks;
			this.selectNextState();
		}
		
		// Execute behavior based on current state
		switch (this.currentState) {
			case EATING:
				tickEating();
				break;
			case WORKING:
				tickWorking();
				break;
			case SLEEPING:
				tickSleeping();
				break;
			case IDLE:
			default:
				tickIdle();
				break;
		}
	}
	
	/**
	 * Called when in EATING state.
	 * Subclasses should override to implement eating behavior.
	 * Default implementation attempts to find and eat food.
	 */
	protected void tickEating() {
		// Default eating behavior - play eating animation
		if (this.remainingAnimationTicks <= 0) {
			this.playEatingAnimation();
		}
	}
	
	/**
	 * Called when in WORKING state.
	 * Subclasses should override to implement profession-specific work.
	 * Default implementation plays working animation and wanders.
	 */
	protected void tickWorking() {
		// Default work behavior - play working animation occasionally
		if (this.remainingAnimationTicks <= 0 && this.villager.getRNG().nextInt(100) == 0) {
			this.playWorkingAnimation();
		}
		
		// Occasionally move to work location
		if (this.villager.getNavigator().noPath() && this.villager.getRNG().nextInt(100) == 0) {
			this.moveToWorkLocation();
		}
	}
	
	/**
	 * Called when in SLEEPING state.
	 * Subclasses can override to customize sleeping behavior.
	 * Default implementation finds and goes to bed.
	 */
	protected void tickSleeping() {
		// Default sleeping behavior - handled by villager brain
		// Subclasses may want to navigate to a bed
	}
	
	/**
	 * Called when in IDLE state.
	 * Subclasses should override for custom idle behavior.
	 */
	protected void tickIdle() {
		// Default idle behavior - do nothing
	}
	
	/**
	 * Moves the villager to their work location.
	 * Called during work state to bring villager to their job site.
	 */
	protected void moveToWorkLocation() {
		// Try to navigate to job site if present
		this.villager.getBrain().getMemory(MemoryModuleType.JOB_SITE).ifPresent(pos -> {
			if (this.getBlockPosDistance(this.getVillagerBlockPos(), pos.getPos()) > 3.0D) {
				this.villager.getNavigator().tryMoveToXYZ(
					pos.getPos().getX(), 
					pos.getPos().getY(), 
					pos.getPos().getZ(), 
					this.villager.getAIMoveSpeed()
				);
			}
		});
	}
	
	
	/**
	 * return a block position object for a entity.
	 */
	protected BlockPos getEntityBlockPos(Entity entity) {
		return new BlockPos(entity.getPosX(),entity.getPosY(), entity.getPosZ());
	}
	
	/**
	 * return a block position object for a entity.
	 */
	protected BlockPos getEntityPrevBlockPos(Entity entity) {
		return new BlockPos(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
	}
	
	
	/**
	 * Heal target living entity
	 */
	protected void healTargetLivingEntity(float amount) { 
		this.targetLivingEntity.heal(amount);
	}
	
	/**
	 * Attack target living entity
	 */
	protected void attackTargetLivingEntity(LivingEntity targetEntity) { 
		this.villager.setAttackTarget(targetEntity);
		this.villager.attackEntityAsMob(targetEntity);
	}
	
	/**
	 * Follow target living entity
	 */
	protected double targetLivingEntityRange;
	protected double maxFollowRange = 10;
	protected void followTargetLivingEntity(LivingEntity targetEntity) {
		targetLivingEntityRange = this.getBlockPosDistance(this.getVillagerBlockPos(), this.getEntityBlockPos(targetEntity));
		if (targetLivingEntityRange > maxFollowRange) {
			villager.getNavigator().tryMoveToEntityLiving(targetEntity, villager.getAIMoveSpeed());	
		}
		
	}
	
	/**
	 * Goto target living entity
	 */
	protected void gotoTargetLivingEntity(LivingEntity targetEntity) { 
		villager.getNavigator().tryMoveToEntityLiving(targetEntity, villager.getAIMoveSpeed());
	}
	
	/**
	 * In Melee range of target living entity
	 */
	protected boolean inMeleeRangeTargetLivingEntity(Entity targetEntity) { 
		double range = this.getBlockPosDistance(this.getVillagerBlockPos(), this.getEntityBlockPos(targetEntity));
		return (range < 3);
	}
	
	/**
	 * Locate a entity of type
	 */
	protected List findLivingEntitiesWithinAABB(Class entityType) { 
		List<IronGolemEntity> list = this.villager.world
				.getEntitiesWithinAABB(entityType, 
						this.villager.getBoundingBox().grow(maxScanRange)
				);
		return list;
	}
	
	/**
	 * @Section Block Related
	 */
	
	// Returns the distances to a block, given two block pos;
	protected double getBlockPosDistance(BlockPos a, BlockPos b) {
		return a.distanceSq(b.getX(), b.getY(), b.getZ(), true);
	}
	
	// Return all blocks of a Poi type, and meet our block filters.
	protected Stream<BlockPos> findAllBlocks(PointOfInterestType poi, PointOfInterestManager.Status status) {
		return poiManager.findAll(poi.getPredicate(), new BlockFilter(), this.getVillagerBlockPos(), (int) this.maxScanRange, status);	
	}
	
	// Return the closest block of a Poi type.
	protected BlockPos closestTargetPos;
	protected double lastClosest;
	protected BlockPos findClosestBlock(PointOfInterestType poi, PointOfInterestManager.Status status, BlockPos center) { 
		Stream<BlockPos> blocks = this.findAllBlocks(poi, status);
		BlockPos min = null;
		blocks.forEach(b -> {
			double thisDistance = this.getBlockPosDistance(center, b);
			if (closestTargetPos == null) {
				closestTargetPos = b;
				lastClosest = thisDistance;
			}
			else if (thisDistance < lastClosest) {
				closestTargetPos = b;
				lastClosest = thisDistance;
			}
		});
		min = closestTargetPos;
		closestTargetPos = null;
		lastClosest = 0;
		return min;
	}
	
	
	public class BlockFilter implements Predicate<BlockPos> { 
		public boolean test(BlockPos t) {
			return true;
		} 
	}
	
	/**
	 * Returns the Hunger capability for a villager.
	 * @return
	 */
	public LazyOptional<IVillagerHunger> getHunger() {
		return this.villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HUNGER);
	}
	
	/**
	 * Returns the Honor capability for a villager.
	 */
	public LazyOptional<IVillagerHonor> getHonor() { 
		return this.villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HONOR); 
	}
	
	public void attackVillagerEntity(VillagerEntity entity, int amount) { 
		entity.setShakeHeadTicks(5);
		entity.performHurtAnimation();
		ResourceLocation location = new ResourceLocation("vcm", "villager_grunt");
		SoundEvent event = new SoundEvent(location);
		entity.playSound(event, 100, 1);
		entity.setHealth((float) (entity.getHealth() - amount));
		
	}
}
