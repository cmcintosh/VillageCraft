package com.villagecraft.entity;

import com.villagecraft.data.VillageCraftVillagerData;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;

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
		VillagerProfession profession = this.getVillagerData().getProfession();
		// Simplified for now - full implementation needs new Activity/behavior packages 
	}

}