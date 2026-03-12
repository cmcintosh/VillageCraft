package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.core.Vec3i;

import com.villagecraft.VillageCraft;

/**
 * Goal for villagers to locate specific blocks in the world.
 * Used for finding job sites, beds, or other important blocks.
 * Updated for NeoForge 1.20.2
 */
public class VillagerGoalLocateBlock extends VillagerGoalBase {
	
	protected BlockPos targetBlockPos;
	protected Block targetBlock;
	protected BlockPos center;
	protected int searchRadius = 32;
	
	protected int lastTick = 0;
	protected int cooldownTicks = 20; // Check every second
	protected int searchYRange = 4; // Vertical search range
	
	private int giveUpTimer = 0;
	private static final int MAX_GIVE_UP_TICKS = 600; // 30 seconds

	public VillagerGoalLocateBlock(Villager entity, Block block, BlockPos center) {
		super(entity);
		this.targetBlock = block;
		this.center = center;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}
	
	/**
	 * Alternative constructor with custom search radius
	 */
	public VillagerGoalLocateBlock(Villager entity, Block block, BlockPos center, int radius) {
		this(entity, block, center);
		this.searchRadius = radius;
	}
	
	@Override
	public boolean canUse() { 
		// Only run if we don't have a target and cooldown has passed
		if (++lastTick < cooldownTicks) {
			return false;
		}
		lastTick = 0;
		
		// Don't run if already at a job site or has target
		if (targetBlockPos != null) {
			return false;
		}
		
		// Look for the target block
		targetBlockPos = findNearestBlock();
		
		return targetBlockPos != null;
	}
	
	@Override
	public boolean canContinueToUse() {
		// Continue if we have a valid target that's still there
		if (targetBlockPos == null) {
			return false;
		}
		
		// Check if block still exists
		if (!villager.level().getBlockState(targetBlockPos).is(targetBlock)) {
			return false;
		}
		
		// Give up if taking too long
		if (giveUpTimer > MAX_GIVE_UP_TICKS) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void start() {
		super.start();
		giveUpTimer = 0;
		VillageCraft.LOGGER.debug("Villager {} started looking for {} block", 
			villager.getUUID(), targetBlock);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (targetBlockPos == null) {
			return;
		}
		
		giveUpTimer++;
		
		// Move toward the target block
		// 1.20.2: Calculate center position manually
		double centerX = targetBlockPos.getX() + 0.5;
		double centerY = targetBlockPos.getY() + 0.5;
		double centerZ = targetBlockPos.getZ() + 0.5;
		double distanceSq = villager.distanceToSqr(centerX, centerY, centerZ);
		
		// If close enough, we're done
		if (distanceSq < 4.0) { // Within 2 blocks
			VillageCraft.LOGGER.debug("Villager {} reached target block at {}", 
				villager.getUUID(), targetBlockPos);
			targetBlockPos = null; // Clear target
			return;
		}
		
		// Navigate to the block
		villager.getNavigation().moveTo(
			targetBlockPos.getX(), 
			targetBlockPos.getY(), 
			targetBlockPos.getZ(), 
			0.6
		);
		
		// Look at the block
		villager.getLookControl().setLookAt(
			targetBlockPos.getX() + 0.5,
			targetBlockPos.getY() + 0.5, 
			targetBlockPos.getZ() + 0.5
		);
	}
	
	@Override
	public void stop() { 
		super.stop();
		VillageCraft.LOGGER.debug("Villager {} stopped looking for block", villager.getUUID());
		this.targetBlockPos = null;
		this.giveUpTimer = 0;
	}
	
	/**
	 * Find the nearest block of the target type
	 */
	protected BlockPos findNearestBlock() {
		Level level = villager.level();
		BlockPos villagerPos = villager.blockPosition();
		BlockPos searchCenter = (center != null) ? center : villagerPos;
		
		BlockPos nearest = null;
		double nearestDist = Double.MAX_VALUE;
		
		// Spiral search pattern for efficiency
		for (int y = -searchYRange; y <= searchYRange; y++) {
			for (int x = -searchRadius; x <= searchRadius; x++) {
				for (int z = -searchRadius; z <= searchRadius; z++) {
					BlockPos pos = searchCenter.offset(x, y, z);
					
					if (level.getBlockState(pos).is(targetBlock)) {
						double dist = pos.distSqr(villagerPos);
						if (dist < nearestDist) {
							nearestDist = dist;
							nearest = pos;
						}
					}
				}
			}
		}
		
		return nearest;
	}
	
	/**
	 * Get the found target position
	 */
	public BlockPos getTargetBlockPos() {
		return targetBlockPos;
	}
	
	/**
	 * Set a new search center
	 */
	public void setCenter(BlockPos center) {
		this.center = center;
	}
	
	/**
	 * Set search radius
	 */
	public void setSearchRadius(int radius) {
		this.searchRadius = radius;
	}
}