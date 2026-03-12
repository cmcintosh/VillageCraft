package com.villagecraft.entity.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.core.BlockPos;

public class VillagerGoalLocateEntity extends Goal {
	
	protected Villager villager;
	
	public VillagerGoalLocateEntity(Villager entity) { 
		super();
		villager = entity;
	}

	@Override
	public boolean canUse() {
		return false;
	}
	
	protected BlockPos getVillagerBlockPos() { 
		if (this.villager != null) {
			return new BlockPos(this.villager.getX(), this.villager.getY(), this.villager.getZ());
		}
		return null;
	}
}