package com.villagecraft.capabilities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * Villager Hunger Attribute
 * Tracks hunger level with activity-based decay and starvation mechanics.
 */
public class VillagerHungerAttribute implements IVillagerHunger {
	
	private String name = "hunger";
	private String label = "Hunger";
	private int attributeValue = 20; // Max hunger
	
	// Configuration constants
	public static final int MAX_HUNGER = 20;
	public static final int STARVATION_THRESHOLD = 0;
	public static final int STARVATION_DAMAGE_TICKS = 100; // 5 seconds at 20tps
	public static final float STARVATION_DAMAGE_AMOUNT = 0.5f; // Half heart
	public static final int PARTICLE_TICKS = 40; // Show particles every 2 seconds
	
	// Activity multipliers for hunger decay
	public static final double IDLE_DECAY = 0.005; // Minimal decay when idle
	public static final double WALKING_DECAY = 0.05; // Standard movement
	public static final double WORKING_DECAY = 0.10; // Performing tasks
	public static final double COMBAT_DECAY = 0.15; // Fighting/running
	
	// Internal tracking
	private int starvationTicks = 0;
	private int particleTicks = 0;
	private double hungerAccumulator = 20.0; // Use double for fractional decay
	
	@Override
	public String getName() { return this.name; }

	@Override
	public String getLabel() { return this.label; }

	@Override
	public int getValue() { return this.attributeValue; }

	@Override
	public void setValue(int value) { 
		this.attributeValue = Math.min(Math.max(value, 0), MAX_HUNGER);
		this.hungerAccumulator = this.attributeValue;
	}
	
	/**
	 * Get current hunger level
	 */
	public double getHungerLevel() {
		return this.hungerAccumulator;
	}
	
	/**
	 * Decay hunger based on activity level
	 * Called each tick on the villager
	 */
	public void tick(Villager villager, ActivityLevel activity) {
		if (villager.level().isClientSide()) return;
		
		double decayRate = switch (activity) {
			case IDLE -> IDLE_DECAY;
			case WALKING -> WALKING_DECAY;
			case WORKING -> WORKING_DECAY;
			case COMBAT -> COMBAT_DECAY;
		};
		
		// Apply decay
		this.hungerAccumulator = Math.max(0, this.hungerAccumulator - decayRate);
		this.attributeValue = (int) this.hungerAccumulator;
		
		// Handle starvation
		if (this.attributeValue <= STARVATION_THRESHOLD) {
			this.starvationTicks++;
			
			// Show starvation particles
			this.particleTicks++;
			if (this.particleTicks >= PARTICLE_TICKS) {
				this.particleTicks = 0;
				showStarvationParticles(villager);
			}
			
			// Apply starvation damage
			if (this.starvationTicks >= STARVATION_DAMAGE_TICKS) {
				this.starvationTicks = 0;
				villager.hurt(villager.damageSources().starve(), STARVATION_DAMAGE_AMOUNT);
			}
		} else {
			// Reset starvation timer when hunger > 0
			this.starvationTicks = 0;
		}
	}
	
	/**
	 * Restore hunger when eating food
	 * All food items restore 1-4 hunger based on their nourishment
	 */
	public void eatFood(Item foodItem) {
		int restoreAmount = getFoodRestoreAmount(foodItem);
		this.hungerAccumulator = Math.min(MAX_HUNGER, this.hungerAccumulator + restoreAmount);
		this.attributeValue = (int) this.hungerAccumulator;
	}
	
	/**
	 * Get hunger restoration amount for a food item
	 * Returns 1-4 based on typical food values
	 */
	private int getFoodRestoreAmount(Item foodItem) {
		// High nourishment foods (steak, golden carrots)
		if (foodItem == Items.GOLDEN_CARROT || foodItem == Items.COOKED_BEEF || 
		    foodItem == Items.COOKED_PORKCHOP || foodItem == Items.CAKE) {
			return 4;
		}
		// Medium nourishment (cooked chicken, cooked mutton, bread)
		if (foodItem == Items.COOKED_CHICKEN || foodItem == Items.COOKED_MUTTON ||
		    foodItem == Items.BREAD || foodItem == Items.COOKED_SALMON ||
		    foodItem == Items.COOKED_COD || foodItem == Items.PUMPKIN_PIE) {
			return 3;
		}
		// Low nourishment (raw foods, fruit)
		if (foodItem == Items.APPLE || foodItem == Items.MELON_SLICE ||
		    foodItem == Items.CARROT || foodItem == Items.POTATO ||
		    foodItem == Items.BEETROOT || foodItem == Items.SWEET_BERRIES) {
			return 2;
		}
		// Minimal (seeds, sugar, etc.) - still restores something
		if (foodItem.isEdible()) {
			return 1;
		}
		// Non-food items restore nothing
		return 0;
	}
	
	/**
	 * Check if item is edible by villagers
	 */
	public static boolean isVillagerFood(Item item) {
		return item.isEdible();
	}
	
	/**
	 * Show starvation particles above the villager
	 */
	private void showStarvationParticles(Villager villager) {
		if (!(villager.level() instanceof ServerLevel serverLevel)) return;
		
		// Black smoke particles to indicate starvation
		for (int i = 0; i < 3; i++) {
			serverLevel.sendParticles(
				ParticleTypes.SMOKE,
				villager.getX() + (villager.getRandom().nextDouble() - 0.5) * 0.5,
				villager.getY() + villager.getEyeHeight() + 0.2,
				villager.getZ() + (villager.getRandom().nextDouble() - 0.5) * 0.5,
				1, // count
				0, 0, 0, // velocity
				0.1 // speed
			);
		}
	}
	
	/**
	 * Get starvation status
	 */
	public boolean isStarving() {
		return this.attributeValue <= STARVATION_THRESHOLD;
	}
	
	/**
	 * Get hunger percentage (0.0 - 1.0)
	 */
	public double getHungerPercent() {
		return this.attributeValue / (double) MAX_HUNGER;
	}
	
	/**
	 * Activity levels for hunger decay calculation
	 */
	public enum ActivityLevel {
		IDLE,      // Standing still
		WALKING,   // Moving around
		WORKING,   // Performing profession tasks
		COMBAT     // Fighting or fleeing
	}
}