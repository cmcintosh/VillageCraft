package com.villagecraft.entity.goal;

import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.VillageCraft;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.core.BlockPos;

import java.util.EnumSet;

/**
 * Goal for villagers to go to the Village Center.
 * Used for various town activities and gatherings.
 * Updated for NeoForge 1.20.2
 */
public class VillagerGoalGotoVillageCenter extends VillagerGoalGoToBlock {
	
	private int socializeTicks = 0;
	private static final int SOCIALIZE_DURATION = 200; // 10 seconds of socializing
	
	public VillagerGoalGotoVillageCenter(Villager entity) {
		super(entity, ModBlocks.BLOCK_VILLAGE_CENTER.get(), true, 10.0);
		// Villagers socialize when they reach the center
		this.setMaxStayTicks(SOCIALIZE_DURATION);
	}
	
	@Override
	public boolean canUse() {
		// Only certain professions go to the village center
		VillagerProfession profession = villager.getVillagerData().getProfession();
		
		// Most villagers occasionally go to the center
		if (profession == VillagerProfession.NITWIT || 
		    profession == VillagerProfession.NONE) {
			return false; // These stay away
		}
		
		// Check cooldown to prevent constant attempts
		if (villager.tickCount % 100 != 0) {
			return false;
		}
		
		// Find nearest village center
		if (targetBlockPos == null) {
			targetBlockPos = findNearestBlock();
		}
		
		return targetBlockPos != null;
	}
	
	@Override
	public boolean canContinueToUse() {
		// Continue if we haven't socialized enough
		if (socializeTicks < SOCIALIZE_DURATION) {
			return super.canContinueToUse();
		}
		return false;
	}
	
	@Override
	public void start() {
		super.start();
		socializeTicks = 0;
		VillageCraft.LOGGER.debug("Villager {} is going to the village center", villager.getUUID());
	}
	
	@Override
	protected void onReachBlock() {
		super.onReachBlock();
		VillageCraft.LOGGER.debug("Villager {} reached village center and is socializing", villager.getUUID());
		
		// Spawn social particles
		if (!villager.level().isClientSide()) {
			for (int i = 0; i < 3; i++) {
				double x = villager.getX() + (villager.getRandom().nextDouble() - 0.5) * 0.5;
				double y = villager.getY() + villager.getEyeHeight() + 0.5;
				double z = villager.getZ() + (villager.getRandom().nextDouble() - 0.5) * 0.5;
				villager.level().addParticle(
					net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
					x, y, z, 0, 0.1, 0
				);
			}
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		
		// Count socializing time when in range
		if (reachedBlock) {
			socializeTicks++;
			
			// Occasionally look around while socializing
			if (villager.tickCount % 20 == 0) {
				double randomX = villager.getX() + (villager.getRandom().nextDouble() - 0.5) * 10;
				double randomZ = villager.getZ() + (villager.getRandom().nextDouble() - 0.5) * 10;
				villager.getLookControl().setLookAt(randomX, villager.getY(), randomZ);
			}
		}
	}
	
	@Override
	public void stop() {
		super.stop();
		socializeTicks = 0;
		VillageCraft.LOGGER.debug("Villager {} finished socializing at village center", villager.getUUID());
	}
}
