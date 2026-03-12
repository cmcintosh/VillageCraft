package com.villagecraft.entity.goal;

import com.villagecraft.VillageCraft;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import java.util.EnumSet;

/**
 * Goal for villagers to locate entities nearby.
 * Updated for NeoForge 1.20.2
 */
public class VillagerGoalLocateEntity extends Goal {
	
	protected Villager villager;
	protected int scanRange = 32; // Default scan range
	protected int scanInterval = 20; // Ticks between scans
	protected int ticksSinceScan = 0;
	
	public VillagerGoalLocateEntity(Villager entity) { 
		super();
		this.villager = entity;
		// Allow looking
		this.setFlags(EnumSet.of(Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		ticksSinceScan++;
		if (ticksSinceScan >= scanInterval) {
			ticksSinceScan = 0;
			return true;
		}
		return false;
	}
	
	@Override
	public void tick() {
		if (villager == null || villager.level().isClientSide()) return;
		
		// Locate entities in range
		BlockPos pos = getVillagerBlockPos();
		if (pos != null) {
			// Scan for nearby entities
			villager.level().getEntities(villager, villager.getBoundingBox().inflate(scanRange), 
				entity -> entity.isAlive() && entity != villager)
				.forEach(entity -> {
					// Entity found - log or process
					VillageCraft.LOGGER.debug("Villager located entity: " + entity.getName().getString());
				});
		}
	}
	
	/**
	 * Get the villager's current block position.
	 * Updated for 1.20.2: Use blockPosition() method
	 */
	protected BlockPos getVillagerBlockPos() { 
		if (this.villager != null) {
			return this.villager.blockPosition();
		}
		return null;
	}
	
	/**
	 * Get the villager's position as Vec3
	 */
	protected Vec3 getVillagerPosition() {
		if (this.villager != null) {
			return this.villager.position();
		}
		return Vec3.ZERO;
	}

	public int getScanRange() {
		return scanRange;
	}

	public void setScanRange(int range) {
		this.scanRange = range;
	}
}