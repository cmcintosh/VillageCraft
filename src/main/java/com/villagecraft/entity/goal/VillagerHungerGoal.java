package com.villagecraft.entity.goal;

import java.util.List;

import com.villagecraft.VillageCraft;
import com.villagecraft.capabilities.CapabilityVillagerAttribute;
import com.villagecraft.capabilities.IVillagerHonor;
import com.villagecraft.capabilities.IVillagerHunger;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.PointOfInterestManager.Status;
import net.minecraft.world.server.ServerWorld;

public class VillagerHungerGoal extends VillagerGoalBase {
	
	protected VillagerEntity villager;
	protected CompoundNBT extraVillagerData;
	
	/**
	 * @Section
	 * Attribute related
	 */
	
	/**
	 * Configurable variables ... @TODO
	 */
	protected int lastHungerTick = 0;
	protected int maxHungerTicks = 24000 / 10; 
	protected int hungerLevel = 4;
	protected int theftHungerLevel = 2;
	protected int starvationLevel = 1;
	protected int maxHonorTheft = -4;
	
	public VillagerHungerGoal(VillagerEntity entity) { 
		super(entity);
		villager = entity;
		extraVillagerData = new CompoundNBT();
	}

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		lastHungerTick++;
		if (lastHungerTick == maxHungerTicks) {
			lastHungerTick = 0;
			return true;
		}
		return false;
	}
	
	/*
	 * Check for hunger and/or try to eat.
	 * Eating actions based on the villager's honor level.
	 */
	public void tick() { 
		this.villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HUNGER).ifPresent(h -> {
			
			if (h.getName() == "hunger") {
					int currentHunger = h.getValue();
					if (currentHunger > 0) {
						currentHunger--;
						h.setValue(currentHunger);
					}
					
					if (currentHunger < this.hungerLevel) {
						this.eat(h);
					}
					
					if (currentHunger < this.starvationLevel) {
						starving();
					}
					
				}
		});
	}
	
	/**
	 * If a villager cant entity deal damage.
	 */
	public void starving() { 
		villager.setShakeHeadTicks(5);
		ResourceLocation location = new ResourceLocation("vcm", "villager_grunt");
		SoundEvent event = new SoundEvent(location);
		villager.playSound(event, 100, 1);
		// @TODO: Configurable on/off
//		villager.setHealth((float) (villager.getHealth() - 1));
	}
	
	/**
	 * Try to eat food.
	 */
	boolean hasAte = false;
	boolean hasCheckedInventory = false;
	boolean hasCheckedGround = false;
	boolean hasCheckedPOI = false;
	boolean hasCheckedChests = false;
	ItemStack foundFood = null;
	Block foundFoodBlock = null;
	BlockPos targetFoodBlock = null;
	protected boolean foundFoodOnGround = false;
	protected ItemEntity foodItem;
	protected ChestTileEntity targetChest = null;
	
	protected void eat(IVillagerHunger h) { 
		eatFromInventory(h);
		
		eatFromChest(h);
		
		eatFromGround(h);
		
		if (h.getValue() <= this.theftHungerLevel) {
			stealFromVillager(h);
		}
	}
	
	/**
	 * Try and still food from another villager
	 */
	protected void stealFromVillager(IVillagerHunger h) {
		if (this.targetLivingEntity != null) {
			if (villager.getDistance(this.targetLivingEntity) > 2) {
				villager.getNavigator().tryMoveToEntityLiving(this.targetLivingEntity, villager.getAIMoveSpeed());
			}
			else {
				VillageCraft.LOGGER.debug("Attacking target");
				VillagerEntity targetVillager = (VillagerEntity) this.targetLivingEntity;
				this.attackVillagerEntity(targetVillager, 1);
				this.foundFood = this.getFoodInInventory(targetVillager);
				this.eatFoodItem(h);
			}
		} else {
			this.findLivingEntitiesWithinAABB(VillagerEntity.class).forEach(c -> { 
				if ( ((VillagerEntity) c) != this.villager) {
					// Check if this villager has food.
					if (this.hasFoodInInventory((VillagerEntity) c)) {
						this.targetLivingEntity = (VillagerEntity) c;
						return;
					}
				}
			});
		}
	}
	
	/**
	 * Get food from inventory
	 */
	protected void eatFromInventory(IVillagerHunger h) { 
		if (this.hasFoodInInventory(villager)) {
			ItemStack food = this.getFoodInInventory(villager);
			this.eatFoodItem(h);
		}
	}
	
	/**
	 * East food from a chest
	 */
	protected void eatFromChest(IVillagerHunger h) {
		if (foodInChest()) {
			if (this.getBlockPosDistance(villager.getPosition(), this.targetFoodBlock) >= 2) {
				VillageCraft.LOGGER.debug("moving to chest");
				villager.getNavigator().tryMoveToXYZ(this.targetFoodBlock.getX(), this.targetFoodBlock.getY(), this.targetFoodBlock.getZ(), villager.getAIMoveSpeed() * 2);	
			} 
			
			if (this.targetChest != null){
				// get item from container and eat.
				for (int i = 0; i < this.targetChest.getSizeInventory(); i++) {
					ItemStack check = this.targetChest.getStackInSlot(i);
					if (check.getItem().isFood()) {
						this.foundFood = check;
						this.eatFoodItem(h);
						return;
					}
				}
			}
		}
	}
	
	/**
	 * Eat food from ground.
	 */
	protected void eatFromGround(IVillagerHunger h) {
		if (this.foodIsOnGround()) {
			if (this.foodItem != null) {
				float distancetoFood = villager.getDistance(this.foodItem); 
				if (distancetoFood >= 2)
				{
					villager.getNavigator().tryMoveToXYZ(this.foodItem.getPosX(), this.foodItem.getPosY(), this.foodItem.getPosZ(), villager.getAIMoveSpeed() * 2);	
				} else {
					villager.getVillagerInventory().addItem(this.foodItem.getItem().getStack());
					this.eatFoodItem(h);	
				}
			}
		}
	}
	
	/**
	 * Eats a food item.
	 * @param h
	 */
	protected void eatFoodItem(IVillagerHunger h) {
		if (this.foundFood == null) { return; }
		if (this.foundFood.getItem() == null) { return; }
		int healing = this.foundFood.getItem().getFood().getHealing();
		int foodAmount = (int) ( healing * this.foundFood.getItem().getFood().getSaturation() ) * 2;
		h.setValue(foodAmount + h.getValue());
		villager.heal(healing);
		if (this.foundFood != null) {
			this.foundFood.getItem().getFood().getEffects().forEach((e) -> {
				if (e != null) {
					villager.addPotionEffect(e.getFirst());
				}
			});
		}
		this.foundFood.setCount(this.foundFood.getCount() -1);
		this.foodItem = null;
		this.foundFoodBlock = null;
		this.targetChest = null;

		VillageCraft.LOGGER.debug("Ate Food, new hunger level is: " + h.getValue());
	}
	
	protected boolean hasFoodInInventory(VillagerEntity enitity) { 
		ItemStack food = this.getFoodInInventory(enitity);
		if (food != null ) {
			this.foundFood = food;
			return true;
		}
		return false;
	}
	
	/*
	 * Returns food items from inventory.
	 */
	protected ItemStack getFoodInInventory(VillagerEntity enitity) { 
		ItemStack food = null;
		Inventory villagerInventory = enitity.getVillagerInventory();
		for (int i = 0; i < villagerInventory.getSizeInventory(); i++) {
			ItemStack check = villagerInventory.getStackInSlot(i);
			if (check.isFood()) {
				return check;
			}
		}
		return food;
	}
	
	/**
	 * Locate food on ground.
	 */
	
	
	protected boolean foodIsOnGround() {
		ItemStack food = this.getClosestFoodOnGround();
		return this.foundFoodOnGround;
	}
	
	// Looks for foods on the ground
	protected ItemStack getClosestFoodOnGround() { 
		ItemStack food = null;
		List<ItemEntity> list = villager.getEntityWorld().getEntitiesWithinAABB(ItemEntity.class, this.villager.getBoundingBox().grow((double)100.0D));
		if (!list.isEmpty()) {
			for(ItemEntity itemEntity : list) {
				if (itemEntity.getItem().isFood()) {
					this.foundFood = itemEntity.getItem().getStack();
					this.foodItem = itemEntity;
					this.foundFoodOnGround = true;
					return itemEntity.getItem().getStack();
				}
			}
		} 
		return food;
	}
	 
	/**
	 * Locate food in a chest?
	 * maybe check the inventory of our workshop for food. or look just for closest chest if our honor is low.
	 */
	protected boolean foodInChest() { 
		ItemStack food = null;
		BlockPos targetBlock = this.findClosestBlock(ModVillagerProfessions.CHEST.get(), Status.ANY, this.getVillagerBlockPos());
		ChestTileEntity chest = (ChestTileEntity) this.villager.world.getTileEntity(targetBlock);
		
		for (int i = 0; i < chest.getSizeInventory(); i++) {
			ItemStack check = chest.getStackInSlot(i);
			if (check.isFood()) {
				this.targetFoodBlock = targetBlock;
				this.targetChest = chest;
				return true;
			}
		}
		
		return false;
	}
	
}
