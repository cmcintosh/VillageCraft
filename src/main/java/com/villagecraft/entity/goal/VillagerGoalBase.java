package com.villagecraft.entity.goal;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.item.profession_tokens.ItemProfessionToken;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.server.ServerWorld;

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
	
	public VillagerGoalBase(VillagerEntity entity) { 
		super();
		villager = entity;
		ServerWorld world = (ServerWorld) entity.getEntityWorld();
		poiManager = world.getPointOfInterestManager();
		extraVillagerData = new CompoundNBT();
		
	}
	
	
	
	protected boolean hasProfessionToken() { 
		
//		this.villager.getVillagerInventory().
		return false;
	}
	

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		return true;
	}
	
	protected BlockPos getVillagerBlockPos() { 
		if (this.villager != null) {
			return new BlockPos(this.villager.getPosX(), this.villager.getPosY(), this.villager.getPosZ());
		}
		return null;
	}
	
	/**
	 * Tick here
	 */
	public void tick() { 
		villager.swingArm(Hand.MAIN_HAND);
		villager.setCanPickUpLoot(true);
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
}
