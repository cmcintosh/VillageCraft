package com.villagecraft.entity.goal;

import java.util.EnumSet;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.core.BlockPos;
import com.villagecraft.VillageCraft;

/**
 * Abstract goal for villagers to go to a specific block.
 * Extends VillagerGoalLocateBlock with range and stay behaviors.
 * Updated for NeoForge 1.20.2
 */
public abstract class VillagerGoalGoToBlock extends VillagerGoalLocateBlock {
	
	protected boolean stayInRange;
	protected boolean reachedBlock = false;
	protected double targetRange;
	protected double walkSpeed = 0.6;
	protected int stayTicks = 0;
	protected int maxStayTicks = 100; // Stay for 5 seconds max
	
	public VillagerGoalGoToBlock(Villager entity, Block block, boolean stayInRange, double targetRange) {
		super(entity, block, entity.blockPosition());
		this.stayInRange = stayInRange;
		this.targetRange = targetRange;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}
	
	public VillagerGoalGoToBlock(Villager entity, Block block, boolean stayInRange, double targetRange, double speed) {
		this(entity, block, stayInRange, targetRange);
		this.walkSpeed = speed;
	}
	
	@Override
	public boolean canUse() { 
		// Check if we should try to go to the block
		if (targetBlockPos == null) {
			targetBlockPos = findNearestBlock();
		}
		
		// Only use this goal if we found a valid target
		return targetBlockPos != null;
	}
	
	@Override
	public boolean canContinueToUse() {
		// Continue if we have a target
		if (targetBlockPos == null) {
			return false;
		}
		
		// Check if block still exists
		if (!villager.level().getBlockState(targetBlockPos).is(targetBlock)) {
			return false;
		}
		
		double distSq = villager.distanceToSqr(
			targetBlockPos.getX() + 0.5,
			targetBlockPos.getY() + 0.5,
			targetBlockPos.getZ() + 0.5
		);
		
		// If staying in range, continue as long as we're within range
		if (stayInRange) {
			return distSq <= targetRange * targetRange;
		}
		
		// If not staying in range, we're done when we reach the block
		// Allow a bit of slack (1 block radius)
		return !reachedBlock || distSq > 1.0;
	}
	
	@Override
	public void start() {
		super.start();
		reachedBlock = false;
		stayTicks = 0;
		VillageCraft.LOGGER.debug("Villager {} started going to {} block at {}", 
			villager.getUUID(), targetBlock, targetBlockPos);
	}
	
	@Override
	public void tick() {
		if (targetBlockPos == null) {
			return;
		}
		
		double distSq = villager.distanceToSqr(
			targetBlockPos.getX() + 0.5,
			targetBlockPos.getY() + 0.5,
			targetBlockPos.getZ() + 0.5
		);
		
		// Check if we're within range
		if (distSq <= targetRange * targetRange) {
			if (!reachedBlock) {
				reachedBlock = true;
				onReachBlock();
			}
			
			// Handle staying in range
			if (stayInRange) {
				stayTicks++;
				if (stayTicks > maxStayTicks) {
					// Done staying, stop the goal
					return;
				}
				// Look at the block while staying
				villager.getLookControl().setLookAt(
					targetBlockPos.getX() + 0.5,
					targetBlockPos.getY() + 0.5,
					targetBlockPos.getZ() + 0.5
				);
			}
		} else {
			// Navigate to the block
			villager.getNavigation().moveTo(
				targetBlockPos.getX() + 0.5,
				targetBlockPos.getY(),
				targetBlockPos.getZ() + 0.5,
				walkSpeed
			);
			
			// Look at the block
			villager.getLookControl().setLookAt(
				targetBlockPos.getX() + 0.5,
				targetBlockPos.getY() + 0.5,
				targetBlockPos.getZ() + 0.5
			);
		}
		
		super.tick();
	}
	
	@Override
	public void stop() {
		super.stop();
		reachedBlock = false;
		stayTicks = 0;
		VillageCraft.LOGGER.debug("Villager {} stopped going to block", villager.getUUID());
	}
	
	/**
	 * Called when the villager reaches the target block
	 * Override for custom behavior
	 */
	protected void onReachBlock() {
		VillageCraft.LOGGER.debug("Villager {} reached target block at {}", 
			villager.getUUID(), targetBlockPos);
	}
	
	/**
	 * Check if we've reached the block
	 */
	public boolean hasReachedBlock() {
		return reachedBlock;
	}
	
	/**
	 * Set maximum time to stay in range (in ticks)
	 */
	public void setMaxStayTicks(int ticks) {
		this.maxStayTicks = ticks;
	}
}