package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.villagecraft.VillageCraft;
import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.entity.goal.VillagerGoalBase.BlockFilter;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.block.Block;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.server.ServerWorld;

public class HostileAttackVillageCenter extends Goal {
	
	private CreatureEntity mob;
	private PointOfInterestManager poiManager;
	protected double maxScanRange = 250D;
	protected BlockPos targetPos;
	protected int LastTick = 25;
	protected int LastCheck = 100;
	private Path path;
	private boolean daytime;
	
	public HostileAttackVillageCenter(CreatureEntity entity, boolean daytime) { 
		super();
		this.mob = entity;
		ServerWorld world = (ServerWorld) entity.getEntityWorld();
		poiManager = world.getPointOfInterestManager();
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		this.daytime = daytime;
	}
	
   /**
    * Returns whether an in-progress EntityAIBase should continue executing
    */
    public boolean shouldContinueExecuting() {
      if (this.mob.getNavigator().noPath()) {
         return false;
      } else if (this.closestTargetPos == null) {
    	  return false;
      } else {
        boolean status = (this.getBlockPosDistance(this.mob.getPosition(), targetPos) < this.maxScanRange);
        return status;
      }
    }
    
    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
       this.mob.getNavigator().setPath(this.path, 2);
       
       LastTick--;
       if (LastTick < 1) {
    	   if (this.targetPos != null) {
       		   this.mob.getNavigator().tryMoveToXYZ(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 2);
       	   }
    	   LastTick = 5;
       }
       
       if (this.targetPos != null) {
	       if (this.getBlockPosDistance(mob.getPosition(), targetPos) < 4) {
			mob.getEntityWorld().getBlockState(this.targetPos);
			mob.getEntityWorld().setBlockState(targetPos, net.minecraft.block.AirBlock.getStateById(0));
			this.targetPos = null;
			this.path = null;
	   	   }
       }
       
    }
    
    // Returns if the block is a village center.
    protected boolean isVillageCenter(Block block) {
    	return (block instanceof BlockVillageCenter);
    }
    
    protected boolean isVillageCenter(BlockPos pos) {
    	return this.isVillageCenter(this.mob.world.getBlockState(pos).getBlock());
    }
	
	@Override
	public boolean shouldExecute() {
		this.LastCheck--;
		this.LastTick--;
		
		if (this.targetPos != null) {
			if (!(this.isVillageCenter(this.targetPos))) {
				this.targetPos = null;
				this.path = null;
			}
			else if (this.getBlockPosDistance(mob.getPosition(), targetPos) < 4) {
				mob.getEntityWorld().getBlockState(this.targetPos);
				mob.getEntityWorld().setBlockState(targetPos, net.minecraft.block.AirBlock.getStateById(0));
				this.targetPos = null;
				this.path = null;
	   	   }
	     }
		
		if ( 
			(this.targetPos == null || mob.getEntityWorld().getBlockState(this.targetPos).getBlock() != ModBlocks.BLOCK_VILLAGE_CENTER.get().getBlock())
		) {
			BlockPos target = this.findClosestBlock(ModVillagerProfessions.VILLAGE_CENTER.get(), PointOfInterestManager.Status.ANY, this.getEntityBlockPos(this.mob));
			if (target != null && this.getBlockPosDistance(target, this.mob.getPosition()) < this.maxScanRange ) {
				
				targetPos = target;
				GroundPathNavigator groundpathnavigator = (GroundPathNavigator)this.mob.getNavigator();
				boolean flag = groundpathnavigator.getEnterDoors();
                groundpathnavigator.setBreakDoors(true);
                this.path = groundpathnavigator.getPathToPos(this.targetPos, 0);
                groundpathnavigator.setBreakDoors(flag);
				return true;
			}
		}
		
		
		
		// TODO Auto-generated method stub
		return (
				((!this.mob.world.isDaytime() && !this.daytime) || (this.mob.world.isDaytime() && this.daytime) ) && 
				this.targetPos != null
				);
	}
	
	/**
	 * return a block position object for a entity.
	 */
	protected BlockPos getEntityBlockPos(Entity entity) {
		return new BlockPos(entity.getPosX(),entity.getPosY(), entity.getPosZ());
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
	
	/**
	 * Provides a block filter
	 */
	public class BlockFilter implements Predicate<BlockPos> { 
		public boolean test(BlockPos t) {
			return true;
		} 
	}
	
	// Returns the distances to a block, given two block pos;
	protected double getBlockPosDistance(BlockPos a, BlockPos b) {
		return a.distanceSq(b.getX(), b.getY(), b.getZ(), true);
	}
	
	protected BlockPos getVillagerBlockPos() { 
		if (this.mob != null) {
			return new BlockPos(this.mob.getPosX(), this.mob.getPosY(), this.mob.getPosZ());
		}
		return null;
	}

}
