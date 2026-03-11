package com.villagecraft.entity.goal;

import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.VillagerEntity;
import net.minecraft.core.BlockPos;

public class VillagerGoalLocateEntity extends Goal {
	
	protected VillagerEntity villager;
	
	public VillagerGoalLocateEntity(VillagerEntity entity) { 
		super();
		villager = entity;
	}

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected BlockPos getVillagerBlockPos() { 
		if (this.villager != null) {
			return new BlockPos(this.villager.getPosX(), this.villager.getPosY(), this.villager.getPosZ());
		}
		return null;
	}
	
}
