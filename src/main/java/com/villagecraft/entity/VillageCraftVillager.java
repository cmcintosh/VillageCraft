package com.villagecraft.entity;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.villagecraft.data.VillageCraftVillagerData;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleStatus;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class VillageCraftVillager extends Villager {
	
	protected VillageCraftVillagerData villageData;

	public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, VillagerType villagerType) {
		super(type, worldIn, villagerType);
	}
	
	public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, VillagerType villagerType, VillageCraftVillagerData data) {
		super(type, worldIn, villagerType);
		this.villageData = data;
	}
	
	// TODO: Reimplement initBrain for 1.20.2 - Brain API changed significantly
	private void initBrain(Brain<Villager> villagerBrain) {
		// Brain API completely changed in 1.20.2
		// Schedule, Activity registration moved to different packages
		// func_233700_a_ replaced with addActivityAndRemoveMemories()
		VillagerProfession profession = this.getVillagerData().getProfession();
		
		// Simplified for now - full implementation needs new Activity/behavior packages 
	}

}