package com.villagecraft.entity.goal;

import java.util.function.Predicate;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.world.level.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.core.BlockPos;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class VillagerGoalLocateBlock extends VillagerGoalBase {
	
	protected BlockPos targetBlockPos;
	protected Block targetBlock;
	protected BlockPos center;
	
	protected PoiType poiType;
	
	protected int lastTick = 0;
	protected int cooldownTicks = 10;

	public VillagerGoalLocateBlock(VillagerEntity entity, Block block, BlockPos center) {
		super(entity);
		this.targetBlock = block;
		this.center = center;
		poiType = new PoiType(block.toString(), ModVillagerProfessions.getAllStates(block), 1, 1);
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
