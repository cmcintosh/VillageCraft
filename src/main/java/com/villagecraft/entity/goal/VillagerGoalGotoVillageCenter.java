package com.villagecraft.entity.goal;

import java.util.function.Predicate;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.util.BlockScanner;
import com.villagecraft.util.VillageCenterBlockScanner;

import net.minecraft.world.level.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vector3i;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class VillagerGoalGotoVillageCenter extends VillagerGoalGoToBlock {
	
	public VillagerGoalGotoVillageCenter(VillagerEntity entity) {
		super(entity, ModBlocks.BLOCK_VILLAGE_CENTER.get(), true, 10);
		this.poiType = ModVillagerProfessions.VILLAGE_CENTER.get();
	}
	
	@Override
	public boolean shouldExecute() {
		if (this.villager.getVillagerData().getProfession() != ModVillagerProfessions.TRADESMAN.get()) { 
			return false;
		}
		return super.shouldExecute();
	}
	
	@Override
	public void tick() { 
		this.center = new BlockPos(villager.getPositionVec().getX(), villager.getPositionVec().getY(), villager.getPositionVec().getZ());
		super.tick();
	}
}
