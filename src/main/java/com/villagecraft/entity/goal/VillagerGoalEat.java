package com.villagecraft.entity.goal;

import java.util.List;

import com.villagecraft.VillageCraft;
import com.villagecraft.capabilities.CapabilityVillagerAttribute;
import com.villagecraft.capabilities.IVillagerHunger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;

/**
 * VillagerGoalEat implements eating behavior for villagers.
 * 
 * When a villager is hungry, they will:
 * 1. Try to eat food from their own inventory
 * 2. Look for food in nearby chests (village stockpile)
 * 3. Look for food items on the ground
 * 4. If starving, may attempt to steal from other villagers
 * 
 * Hunger thresholds:
 * - < 30% (12/40): Will eat if food available
 * - < 10% (4/40): Critically hungry, will steal if necessary
 * 
 * Eating restores hunger and provides healing.
 * 
 * @author VillageCraft Team
 * @since 1.0
 * @see VillagerHungerGoal
 * @see VillagerGoalBase
 */
public class VillagerGoalEat extends VillagerGoalBase {
	
	// Hunger thresholds
	protected static final int HUNGER_MAX = 40;
	protected static final int HUNGER_THRESHOLD_LOW = 12;      // 30% - will eat
	protected static final int HUNGER_THRESHOLD_CRITICAL = 4; // 10% - will steal
	
	// Eating timing
	protected static final int EATING_ANIMATION_TICKS = 32;
	protected static final int EATING_COOLDOWN = 20; // 1 second between eat checks
	
	// Search ranges
	protected static final double CHEST_SEARCH_RANGE = 50.0D;
	protected static final double GROUND_FOOD_RANGE = 20.0D;
	protected static final double STEAL_RANGE = 10.0D;
	
	// Current eating state
	protected EatingState eatingState = EatingState.IDLE;
	protected BlockPos targetFoodLocation;
	protected ItemStack foodToEat;
	protected ItemEntity targetFoodItem;
	protected VillagerEntity targetVillagerToStealFrom;
	protected ChestTileEntity targetChest;
	
	protected int eatingCooldown = 0;
	protected int eatingProgress = 0;
	
	/**
	 * Eating state machine
	 */
	public enum EatingState {
		IDLE,              // Not eating
		FINDING_FOOD,      // Searching for food
		MOVING_TO_FOOD,    // Moving to food location
		EATING             // Currently eating
	}
	
	/**
	 * Creates a new eat goal for the villager
	 * @param entity The villager entity
	 */
	public VillagerGoalEat(VillagerEntity entity) {
		super(entity);
	}
	
	/**
	 * Checks if the villager should try to eat
	 */
	@Override
	public boolean shouldExecute() {
		// Don't eat if not hungry
		if (!isHungry()) {
			return false;
		}
		
		// Check cooldown
		if (eatingCooldown > 0) {
			eatingCooldown--;
			return false;
		}
		
		return true;
	}
	
	/**
	 * Continue eating if still hungry and have food
	 */
	@Override
	public boolean shouldContinueExecuting() {
		// Stop if no longer hungry (above threshold)
		int hunger = getCurrentHunger();
		if (hunger > HUNGER_THRESHOLD_LOW) {
			return false;
		}
		
		// Continue if we have food to eat or are in the process of eating
		return this.eatingState != EatingState.IDLE || isHungry();
	}
	
	/**
	 * Called when eating starts
	 */
	@Override
	public void startExecuting() {
		super.startExecuting();
		this.transitionToState(VillagerState.EATING);
		this.eatingState = EatingState.FINDING_FOOD;
		this.targetFoodLocation = null;
		this.foodToEat = null;
		this.targetFoodItem = null;
		this.targetChest = null;
		
		VillageCraft.LOGGER.debug("Villager " + this.villager.getUniqueID() + 
			" starting to eat (hunger: " + getCurrentHunger() + ")");
	}
	
	/**
	 * Called when eating stops
	 */
	@Override
	public void resetTask() {
		super.resetTask();
		this.eatingState = EatingState.IDLE;
		this.targetFoodLocation = null;
		this.foodToEat = null;
		this.targetFoodItem = null;
		this.targetChest = null;
		this.targetVillagerToStealFrom = null;
		this.eatingProgress = 0;
	}
	
	/**
	 * Main eating tick behavior
	 */
	@Override
	protected void tickEating() {
		if (eatingCooldown > 0) {
			eatingCooldown--;
			return;
		}
		
		switch (this.eatingState) {
			case FINDING_FOOD:
				findAndEatFood();
				break;
			case MOVING_TO_FOOD:
				moveToFood();
				break;
			case EATING:
				performEating();
				break;
			case IDLE:
			default:
				// Should not reach here, but reset just in case
				this.eatingState = EatingState.FINDING_FOOD;
				break;
		}
	}
	
	/**
	 * Find food source and initiate eating
	 * Priority:
	 * 1. Inventory
	 * 2. Chests
	 * 3. Ground items
	 * 4. Steal from other villagers
	 */
	protected void findAndEatFood() {
		// Priority 1: Check own inventory first
		if (hasFoodInInventory()) {
			ItemStack food = getFoodFromInventory();
			if (food != null && !food.isEmpty()) {
				this.foodToEat = food;
				this.eatingState = EatingState.EATING;
				VillageCraft.LOGGER.debug("Found food in inventory");
				return;
			}
		}
		
		// Priority 2: Look in nearby chests (village stockpile)
		if (findFoodInChest()) {
			this.eatingState = EatingState.MOVING_TO_FOOD;
			VillageCraft.LOGGER.debug("Going to chest for food");
			return;
		}
		
		// Priority 3: Look for food on the ground
		if (findFoodOnGround()) {
			this.eatingState = EatingState.MOVING_TO_FOOD;
			VillageCraft.LOGGER.debug("Going to ground food");
			return;
		}
		
		// Priority 4: If starving, try to steal
		if (isCriticallyHungry()) {
			if (findVillagerToStealFrom()) {
				this.eatingState = EatingState.MOVING_TO_FOOD;
				VillageCraft.LOGGER.debug("Going to steal food from villager");
				return;
			}
		}
		
		// No food found - stay hungry and wait
		VillageCraft.LOGGER.debug("No food found, villager remains hungry");
		this.eatingCooldown = EATING_COOLDOWN * 5; // Wait longer before trying again
	}
	
	/**
	 * Move to the food location
	 */
	protected void moveToFood() {
		if (this.targetFoodLocation == null) {
			this.eatingState = EatingState.FINDING_FOOD;
			return;
		}
		
		double distance = this.getBlockPosDistance(
			this.getVillagerBlockPos(), 
			this.targetFoodLocation
		);
		
		if (distance < 2.0D) {
			// At the food location
			if (this.targetFoodItem != null) {
				// Pick up the food from ground
				pickUpGroundFood();
			} else if (this.targetChest != null) {
				// Get food from chest
				getFoodFromChest();
			} else if (this.targetVillagerToStealFrom != null) {
				// Steal from villager
				stealFromVillager();
			}
			
			// Now eat if we have food
			if (this.foodToEat != null && !this.foodToEat.isEmpty()) {
				this.eatingState = EatingState.EATING;
			} else {
				// Failed to get food, try again
				this.eatingState = EatingState.FINDING_FOOD;
				this.targetFoodLocation = null;
			}
		} else {
			// Navigate to food
			if (this.villager.getNavigator().noPath()) {
				this.villager.getNavigator().tryMoveToXYZ(
					this.targetFoodLocation.getX(),
					this.targetFoodLocation.getY(),
					this.targetFoodLocation.getZ(),
					this.villager.getAIMoveSpeed() * 1.5 // Move faster when hungry
				);
			}
		}
	}
	
	/**
	 * Perform the eating animation and restore hunger
	 */
	protected void performEating() {
		if (this.foodToEat == null || this.foodToEat.isEmpty()) {
			this.eatingState = EatingState.FINDING_FOOD;
			return;
		}
		
		// Start eating animation
		if (this.eatingProgress == 0) {
			this.villager.setActiveHand(Hand.MAIN_HAND);
			this.playEatingAnimation();
			VillageCraft.LOGGER.debug("Villager started eating");
		}
		
		this.eatingProgress++;
		
		// Complete eating after animation
		if (this.eatingProgress >= EATING_ANIMATION_TICKS) {
			completeEating();
		}
	}
	
	/**
	 * Complete eating and restore hunger/health
	 */
	protected void completeEating() {
		if (this.foodToEat == null || this.foodToEat.isEmpty()) {
			this.eatingState = EatingState.IDLE;
			return;
		}
		
		// Get food stats
		if (this.foodToEat.getItem().isFood()) {
			int healAmount = this.foodToEat.getItem().getFood().getHealing();
			float saturation = this.foodToEat.getItem().getFood().getSaturation();
			int hungerRestore = (int)(healAmount * saturation * 2);
			
			// Update hunger capability
			this.villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HUNGER).ifPresent(h -> {
				int newHunger = h.getValue() + hungerRestore;
				if (newHunger > HUNGER_MAX) newHunger = HUNGER_MAX;
				h.setValue(newHunger);
				
				VillageCraft.LOGGER.debug("Villager ate food, hunger now " + newHunger);
			});
			
			// Heal the villager
			this.villager.heal(healAmount);
			
			// Apply food effects
			this.foodToEat.getItem().getFood().getEffects().forEach(effect -> {
				if (effect.getFirst() != null) {
					this.villager.addPotionEffect(effect.getFirst());
				}
			});
			
			// Play eating sound
			this.villager.world.playSound(
				null,
				this.villager.getPosition(),
				SoundEvents.ENTITY_GENERIC_EAT,
				SoundCategory.NEUTRAL,
				1.0F,
				1.0F + (this.villager.getRNG().nextFloat() - this.villager.getRNG().nextFloat()) * 0.4F
			);
			
			// Consume food
			this.foodToEat.shrink(1);
		}
		
		// Reset state
		this.eatingProgress = 0;
		this.foodToEat = null;
		this.eatingState = EatingState.IDLE;
		this.eatingCooldown = EATING_COOLDOWN;
		
		// Check if still hungry
		if (isHungry()) {
			this.eatingState = EatingState.FINDING_FOOD;
		}
	}
	
	/**
	 * Check if the villager has food in their inventory
	 * @return true if food is available
	 */
	protected boolean hasFoodInInventory() {
		Inventory inv = this.villager.getVillagerInventory();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (isFoodItem(stack)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get food item from inventory
	 * @return ItemStack containing food, or ItemStack.EMPTY if none
	 */
	protected ItemStack getFoodFromInventory() {
		Inventory inv = this.villager.getVillagerInventory();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (isFoodItem(stack)) {
				return stack;
			}
		}
		return ItemStack.EMPTY;
	}
	
	/**
	 * Check if an item stack is food
	 * @param stack ItemStack to check
	 * @return true if it's a food item and not rotten (unless starving)
	 */
	protected boolean isFoodItem(ItemStack stack) {
		if (stack == null || stack.isEmpty()) {
			return false;
		}
		return stack.getItem().isFood();
	}
	
	/**
	 * Find food in a nearby chest
	 * @return true if food was found and target set
	 */
	protected boolean findFoodInChest() {
		// Look for chest POI
		BlockPos chestPos = this.findClosestBlock(
			PointOfInterestType.FISHERMAN, // Use fisherman as generic chest/campfire
			PointOfInterestManager.Status.ANY,
			this.getVillagerBlockPos()
		);
		
		if (chestPos == null) {
			return false;
		}
		
		// Check if it's actually a chest
		TileEntity te = this.villager.world.getTileEntity(chestPos);
		if (!(te instanceof ChestTileEntity)) {
			return false;
		}
		
		ChestTileEntity chest = (ChestTileEntity) te;
		
		// Check for food in chest
		for (int i = 0; i < chest.getSizeInventory(); i++) {
			ItemStack stack = chest.getStackInSlot(i);
			if (isFoodItem(stack)) {
				this.targetFoodLocation = chestPos;
				this.targetChest = chest;
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Get food from the targeted chest
	 */
	protected void getFoodFromChest() {
		if (this.targetChest == null) {
			return;
		}
		
		for (int i = 0; i < this.targetChest.getSizeInventory(); i++) {
			ItemStack stack = this.targetChest.getStackInSlot(i);
			if (isFoodItem(stack)) {
				// Take one food item
				this.foodToEat = stack.split(1);
				
				// If stack is now empty, clear the slot
				if (stack.isEmpty()) {
					this.targetChest.setInventorySlotContents(i, ItemStack.EMPTY);
				}
				
				VillageCraft.LOGGER.debug("Villager took food from chest");
				return;
			}
		}
		
		// No food found in chest
		this.targetChest = null;
		this.targetFoodLocation = null;
	}
	
	/**
	 * Find food item on the ground
	 * @return true if food was found on ground
	 */
	protected boolean findFoodOnGround() {
		List<ItemEntity> items = this.villager.world.getEntitiesWithinAABB(
			ItemEntity.class,
			this.villager.getBoundingBox().grow(GROUND_FOOD_RANGE)
		);
		
		for (ItemEntity item : items) {
			if (isFoodItem(item.getItem())) {
				this.targetFoodLocation = new BlockPos(item.getPositionVec());
				this.targetFoodItem = item;
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Pick up food item from ground
	 */
	protected void pickUpGroundFood() {
		if (this.targetFoodItem == null || !this.targetFoodItem.isAlive()) {
			this.targetFoodItem = null;
			return;
		}
		
		// Pick up the item
		ItemStack food = this.targetFoodItem.getItem();
		if (isFoodItem(food)) {
			// Add to inventory or consume directly
			this.villager.getVillagerInventory().addItem(food);
			this.foodToEat = food;
			
			// Remove the entity
			this.targetFoodItem.remove();
			
			VillageCraft.LOGGER.debug("Villager picked up food from ground");
		}
		
		this.targetFoodItem = null;
	}
	
	/**
	 * Find another villager to steal food from
	 * @return true if a target was found
	 */
	protected boolean findVillagerToStealFrom() {
		List<VillagerEntity> villagers = this.villager.world.getEntitiesWithinAABB(
			VillagerEntity.class,
			this.villager.getBoundingBox().grow(STEAL_RANGE)
		);
		
		for (VillagerEntity other : villagers) {
			// Don't steal from self
			if (other == this.villager) {
				continue;
			}
			
			// Check if they have food
			Inventory inv = other.getVillagerInventory();
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack stack = inv.getStackInSlot(i);
				if (isFoodItem(stack)) {
					this.targetVillagerToStealFrom = other;
					this.targetFoodLocation = other.getPosition();
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Steal food from another villager
	 */
	protected void stealFromVillager() {
		if (this.targetVillagerToStealFrom == null || !this.targetVillagerToStealFrom.isAlive()) {
			this.targetVillagerToStealFrom = null;
			return;
		}
		
		// Make them angry at us
		this.targetVillagerToStealFrom.setRevengeTarget(this.villager);
		
		// Take their food
		Inventory inv = this.targetVillagerToStealFrom.getVillagerInventory();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (isFoodItem(stack)) {
				// Steal one item
				this.foodToEat = stack.split(1);
				
				// If stack empty, clear it
				if (stack.isEmpty()) {
					inv.setInventorySlotContents(i, ItemStack.EMPTY);
				}
				
				// Play angry/thief sound
				this.villager.world.playSound(
					null,
					this.villager.getPosition(),
					SoundEvents.ENTITY_VILLAGER_NO,
					SoundCategory.NEUTRAL,
					1.0F,
					1.0F
				);
				
				VillageCraft.LOGGER.debug("Villager stole food from another villager!");
				break;
			}
		}
		
		this.targetVillagerToStealFrom = null;
	}
}
