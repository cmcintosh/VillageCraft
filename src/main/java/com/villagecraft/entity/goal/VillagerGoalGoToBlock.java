package com.villagecraft.entity.goal;

import com.villagecraft.VillageCraft;

import net.minecraft.block.Block;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.world.World;

public abstract class VillagerGoalGoToBlock extends VillagerGoalLocateBlock {
	
	protected boolean stayInRange;
	protected boolean reachedBlock = false;
	protected double targetRange;
	protected int walkSpeed = 1;
	
	public VillagerGoalGoToBlock(VillagerEntity entity, Block block, boolean StayInRange, double targetRange) {
		super(entity, block, new BlockPos(entity.getPositionVec().getX(), entity.getPositionVec().getY(), entity.getPositionVec().getZ()));
		this.stayInRange = StayInRange;
		this.targetRange = targetRange;
	}
	
	@Override
	public boolean shouldExecute() { 
		if (!super.shouldExecute()) {
			return false;
		}
		
		if (this.targetBlockPos != null) {
			if (!inRange() ) {
				if (this.reachedBlock && this.stayInRange) { 
					return true;
				} else if (this.reachedBlock && !this.stayInRange) {
					return false;
				}
				return true;
			}
			
			return false;	
		}
		return true;
	}
	
	public boolean inRange() {
		Vector3i vbp = new Vector3i(this.villager.getPositionVec().x, this.villager.getPositionVec().y, this.villager.getPositionVec().z);
		return (this.targetBlockPos.distanceSq(vbp) < this.targetRange);
	}
	
	public void tick()
	{
		super.tick();
		if (this.targetBlockPos != null) {
			villager.getNavigator().tryMoveToXYZ(this.targetBlockPos.getX(), this.targetBlockPos.getY(), this.targetBlockPos.getZ(), this.villager.getAIMoveSpeed());
		}
	}
	
}
