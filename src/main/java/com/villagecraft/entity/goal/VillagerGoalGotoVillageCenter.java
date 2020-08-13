package com.villagecraft.entity.goal;

import java.util.function.Predicate;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.util.BlockScanner;
import com.villagecraft.util.VillageCenterBlockScanner;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

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
