package com.villagecraft.entity.goal;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.core.BlockPos;

public abstract class VillagerGoalGoToBlock extends VillagerGoalLocateBlock {
	
	protected boolean stayInRange;
	protected boolean reachedBlock = false;
	protected double targetRange;
	protected int walkSpeed = 1;
	
	public VillagerGoalGoToBlock(Villager entity, Block block, boolean stayInRange, double targetRange) {
		super(entity, block, new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ()));
		this.stayInRange = stayInRange;
		this.targetRange = targetRange;
	}
	
	@Override
	public boolean canUse() { 
		// Simplified for 1.20.2 - navigation API changed
		return false;
	}
	
	@Override
	public void tick()
	{
		super.tick();
		// TODO: Reimplement navigation for 1.20.2
	}
	
}