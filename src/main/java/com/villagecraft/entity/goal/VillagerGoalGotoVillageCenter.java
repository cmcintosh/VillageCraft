package com.villagecraft.entity.goal;

import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.core.BlockPos;

public class VillagerGoalGotoVillageCenter extends VillagerGoalGoToBlock {
	
	public VillagerGoalGotoVillageCenter(Villager entity) {
		super(entity, ModBlocks.VILLAGE_CENTER.get(), true, 10);
		// TODO: POI system changed - reimplement poiType
	}
	
	@Override
	public boolean canUse() {
		// TODO: Reimplement profession check for 1.20.2
		// - TRADESMAN profession may not be registered yet
		return false;
	}
	
	@Override
	public void tick() { 
		// TODO: Reimplement center calculation
		super.tick();
	}
}