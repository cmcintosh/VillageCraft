package com.villagecraft.entity.goal;

import java.util.function.Predicate;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class VillagerGoalLocateBlock extends VillagerGoalBase {
	
	protected BlockPos targetBlockPos;
	protected Block targetBlock;
	protected BlockPos center;
	
	protected PointOfInterestType poiType;
	
	protected int lastTick = 0;
	protected int cooldownTicks = 10;

	public VillagerGoalLocateBlock(VillagerEntity entity, Block block, BlockPos center) {
		super(entity);
		this.targetBlock = block;
		this.center = center;
		poiType = new PointOfInterestType(block.toString(), ModVillagerProfessions.getAllStates(block), 1, 1);
	}
	
	public boolean shouldExecute() { 
		
		if (this.targetBlockPos != null) {
			World world = this.villager.world;
			if ( world.getBlockState(this.targetBlockPos).getBlock() != this.targetBlock ) {
				return true;
			}	
		}
		
		if (lastTick == 0) {
			lastTick = cooldownTicks;
			return true;
		}
		lastTick--;
		return false;
	}
	
	@Override
	public void tick()
	{
		super.tick();
		
		if (this.targetBlockPos == null) { 
			this.targetBlockPos = this.findClosestBlock(this.poiType, PointOfInterestManager.Status.ANY, center);
		} else {
			World world = this.villager.world;
			if ( world.getBlockState(this.targetBlockPos).getBlock() != this.targetBlock ) {
				this.targetBlockPos = null;
				this.lastClosest = this.maxScanRange;
			}	
		}
	}
	

	public void resetTask() { 
		super.resetTask();
		this.targetBlockPos = null;
	}
	
	public class GoodBlock implements Predicate<BlockPos> {

		@Override
		public boolean test(BlockPos t) {
			return true;
		} 
		
	}
}
