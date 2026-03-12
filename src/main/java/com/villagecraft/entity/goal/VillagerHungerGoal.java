package com.villagecraft.entity.goal;

import com.villagecraft.VillageCraft;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.item.ItemEntity;
import java.util.EnumSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

/**
 * Goal that handles villager hunger mechanics.
 * Updated for NeoForge 1.20.2
 */
public class VillagerHungerGoal extends VillagerGoalBase {
	
	protected CompoundTag extraVillagerData;
	protected int lastHungerTick = 0;
	protected int maxHungerTicks = 24000 / 10; // Roughly every 1/10th of a day
	protected int hungerLevel = 4;
	protected int theftHungerLevel = 2;
	protected int starvationLevel = 1;
	protected int maxHonorTheft = -4;
	protected int currentHunger = 0; // Current hunger value
	protected int maxHunger = 20; // Full hunger
	protected int hungryThreshold = 10; // Below this is considered hungry
	
	public VillagerHungerGoal(Villager entity) { 
		super(entity);
		extraVillagerData = new CompoundTag();
		// This goal requires movement and looking capabilities
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		// Tick the hunger counter
		lastHungerTick++;
		if (lastHungerTick >= maxHungerTicks) {
			lastHungerTick = 0;
			// Reduce hunger periodically
			if (currentHunger > 0) {
				currentHunger--;
			}
			return currentHunger <= hungryThreshold;
		}
		return false;
	}
	
	@Override
	public boolean canContinueToUse() {
		return currentHunger <= hungryThreshold + 2; // Keep going until satisfactorily fed
	}
	
	@Override
	public void start() {
		VillageCraft.LOGGER.debug("Villager " + villager.getUUID() + " is looking for food");
	}
	
	@Override
	public void stop() {
		// Goal stopped, villager has eaten or gave up
		VillageCraft.LOGGER.debug("Villager " + villager.getUUID() + " stopped hunger goal");
	}
	
	@Override
	public void tick() { 
		// Look for food in inventory first
		// 1.20.2: Get inventory via getInventory()
		if (villager.getInventory() != null) {
			// Check for food items in inventory
			for (int i = 0; i < villager.getInventory().getContainerSize(); i++) {
				if (villager.getInventory().getItem(i).isEdible()) {
					// Eat the food
					int nutrition = villager.getInventory().getItem(i).getItem().getFoodProperties().getNutrition();
					villager.getInventory().removeItem(i, 1);
					currentHunger = Math.min(currentHunger + nutrition, maxHunger);
					
					// Use proper eat animation/effects
					if (!villager.level().isClientSide()) {
						// Play eating sound and particles
						villager.level().broadcastEntityEvent(villager, (byte) 103); // Eat particles
					}
					
					VillageCraft.LOGGER.debug("Villager " + villager.getUUID() + " ate food, hunger: " + currentHunger);
					return; // Goal complete
				}
			}
		}
		
		// If no food in inventory, seek food nearby
		if (!villager.level().isClientSide()) {
			ServerLevel serverLevel = (ServerLevel) villager.level();
			BlockPos pos = villager.blockPosition();
			
			// Look for food items on the ground nearby
			for (ItemEntity itemEntity : serverLevel.getEntitiesOfClass(
					ItemEntity.class, 
					villager.getBoundingBox().inflate(5.0), 
					(entity) -> entity.getItem().isEdible())) {
				
				if (villager.distanceToSqr(itemEntity) < 2.0) {
					// Pick up food
					villager.getInventory().addItem(itemEntity.getItem().split(1));
					break;
				} else {
					// Move toward food
					villager.getNavigation().moveTo(itemEntity, 0.5);
				}
			}
		}
		
		// Handle starvation if hunger reaches zero
		if (currentHunger <= starvationLevel) {
			handleStarvation();
		}
	}
	
	/**
	 * Handle starvation - apply damage or bad effects
	 */
	protected void handleStarvation() {
		if (!villager.level().isClientSide() && villager.tickCount % 80 == 0) {
			// Apply damage on starvation
			villager.hurt(villager.damageSources().starve(), 1.0F);
		}
	}
	
	/**
	 * Check if villager is hungry
	 */
	public boolean isHungry() {
		return currentHunger <= hungryThreshold;
	}
	
	/**
	 * Get current hunger level
	 */
	public int getHungerLevel() {
		return currentHunger;
	}
	
	/**
	 * Set hunger level (for debugging or admin commands)
	 */
	public void setHungerLevel(int hunger) {
		this.currentHunger = Math.min(hunger, maxHunger);
	}
	
	/**
	 * Feed the villager
	 */
	public void feed(int amount) {
		this.currentHunger = Math.min(this.currentHunger + amount, maxHunger);
	}
	
	/**
	 * Save hunger data to NBT
	 */
	public void addAdditionalSaveData(CompoundTag tag) {
		tag.putInt("HungerLevel", this.currentHunger);
		tag.putInt("LastHungerTick", this.lastHungerTick);
	}
	
	/**
	 * Load hunger data from NBT
	 */
	public void readAdditionalSaveData(CompoundTag tag) {
		this.currentHunger = tag.getInt("HungerLevel");
		this.lastHungerTick = tag.getInt("LastHungerTick");
	}
}