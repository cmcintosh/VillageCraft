package com.villagecraft.entity;

import com.villagecraft.VillageCraft;
import com.villagecraft.data.VillageCraftVillagerData;
import com.villagecraft.entity.goal.VillagerGoalBase;
import com.villagecraft.entity.goal.VillagerHungerGoal;
import com.villagecraft.entity.goal.VillagerGoalGotoVillageCenter;
import com.villagecraft.entity.goal.HealGolemGoal;
import com.villagecraft.entity.goal.WanderBardPerformGoal;
import com.villagecraft.entity.goal.WanderSingerPerformGoal;
import com.villagecraft.entity.goal.WanderDrummerPerformGoal;
import com.villagecraft.entity.goal.BassistPerformGoal;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;
import java.util.EnumSet;

/**
 * VillageCraft Villager - Enhanced villager with custom behaviors.
 * Updated for NeoForge 1.20.2
 */
public class VillageCraftVillager extends Villager {
	
	protected VillageCraftVillagerData villageData;
	private boolean goalsRegistered = false;

	public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, VillagerType villagerType) {
		super(type, worldIn, villagerType);
		initVillageCraftVillager();
	}
	
	public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, VillagerType villagerType, VillageCraftVillagerData data) {
		super(type, worldIn, villagerType);
		this.villageData = data;
		initVillageCraftVillager();
	}
	
	/**
	 * Initialize VillageCraft-specific villager behavior
	 */
	private void initVillageCraftVillager() {
		// VillageCraft villagers have enhanced needs
		// These are initialized but the actual ticking is handled by event handlers
		VillageCraft.LOGGER.debug("VillageCraftVillager initialized");
	}
	
	/**
	 * NeoForge 1.20.2: Custom brain initialization.
	 * Called after the vanilla brain is set up.
	 */
	@Override
	protected void registerGoals() {
		super.registerGoals();
		
		// Only add custom goals once
		if (goalsRegistered) {
			return;
		}
		goalsRegistered = true;
		
		// Add VillageCraft custom goals
		// Note: Priority determines execution order (lower = higher priority)
		
		// Priority 0: Hunger management - most important for survival
		// Already handled by VillagerHungerGoal in the event handler
		
		// Priority 1: Heal golems if clerics
		this.goalSelector.addGoal(1, new HealGolemGoal(this));
		
		// Priority 3: Go to village center occasionally
		// Only for employed villagers who want to socialize
		if (this.getVillagerData().getProfession() != VillagerProfession.NITWIT &&
		    this.getVillagerData().getProfession() != VillagerProfession.NONE) {
			this.goalSelector.addGoal(3, new VillagerGoalGotoVillageCenter(this));
		}
		
		// Priority 4: Musical performance goals (profession-specific)
		// Musicians have unique performance behaviors
		if (this.getVillagerData().getProfession() == ModVillagerProfessions.BARD.get()) {
			this.goalSelector.addGoal(4, new WanderBardPerformGoal(this));
		} else if (this.getVillagerData().getProfession() == ModVillagerProfessions.SINGER.get()) {
			this.goalSelector.addGoal(4, new WanderSingerPerformGoal(this));
		} else if (this.getVillagerData().getProfession() == ModVillagerProfessions.DRUMMER.get()) {
			this.goalSelector.addGoal(4, new WanderDrummerPerformGoal(this));
		} else if (this.getVillagerData().getProfession() == ModVillagerProfessions.BASSIST.get()) {
			this.goalSelector.addGoal(4, new BassistPerformGoal(this));
		} else if (this.getVillagerData().getProfession() != VillagerProfession.NITWIT &&
		           this.getVillagerData().getProfession() != VillagerProfession.NONE) {
			// Non-musicians can still perform basic bard style (representing village morale)
			this.goalSelector.addGoal(4, new WanderBardPerformGoal(this));
		}
		
		VillageCraft.LOGGER.debug("VillageCraftVillager registered custom goals for profession {}", 
			this.getVillagerData().getProfession());
	}
	
	/**
	 * Getter for VillageCraft data
	 */
	public VillageCraftVillagerData getVillageData() {
		return this.villageData;
	}
	
	/**
	 * Setter for VillageCraft data
	 */
	public void setVillageData(VillageCraftVillagerData data) {
		this.villageData = data;
	}
	
	/**
	 * Check if this villager has associated VillageCraft data
	 */
	public boolean hasVillageData() {
		return this.villageData != null;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		// VillageCraft-specific tick logic
		if (!this.level().isClientSide() && this.villageData != null) {
			// Update village data reference periodically
			if (this.tickCount % 100 == 0) {
				updateVillageData();
			}
		}
	}
	
	/**
	 * Update village data from the world storage
	 */
	private void updateVillageData() {
		// This would sync with VillageCraftData if needed
		// For now, data is managed through capabilities and events
	}
}
