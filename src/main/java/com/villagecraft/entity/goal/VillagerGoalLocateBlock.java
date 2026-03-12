package com.villagecraft.entity.goal;

import java.util.function.Predicate;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;

public class VillagerGoalLocateBlock extends VillagerGoalBase {
	
	protected BlockPos targetBlockPos;
	protected Block targetBlock;
	protected BlockPos center;
	
	protected int lastTick = 0;
	protected int cooldownTicks = 10;

	public VillagerGoalLocateBlock(Villager entity, Block block, BlockPos center) {
		super(entity);
		this.targetBlock = block;
		this.center = center;
	}
	
	@Override
	public boolean canUse() { 
		// Simplified for 1.20.2 - POI system changed
		return false;
	}
	
	@Override
	public void tick()
	{
		super.tick();
		// TODO: Reimplement POI lookup for 1.20.2
	}
	
	@Override
	public void stop() { 
		super.stop();
		this.targetBlockPos = null;
	}
}