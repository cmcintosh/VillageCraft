package com.villagecraft.entity.goal;

import com.villagecraft.VillageCraft;
import com.villagecraft.capabilities.CapabilityVillagerAttribute;
import com.villagecraft.capabilities.IVillagerHunger;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class VillagerHungerGoal extends VillagerGoalBase {
	
	protected CompoundTag extraVillagerData;
	protected int lastHungerTick = 0;
	protected int maxHungerTicks = 24000 / 10; 
	protected int hungerLevel = 4;
	protected int theftHungerLevel = 2;
	protected int starvationLevel = 1;
	protected int maxHonorTheft = -4;
	
	public VillagerHungerGoal(Villager entity) { 
		super(entity);
		extraVillagerData = new CompoundTag();
	}

	@Override
	public boolean canUse() {
		lastHungerTick++;
		if (lastHungerTicks == maxHungerTicks) {
			lastHungerTick = 0;
			return true;
		}
		return false;
	}
	
	@Override
	public void tick() { 
		// TODO: Reimplement with new API
		VillageCraft.LOGGER.debug("VillagerHungerGoal.tick() - needs 1.20.2 API update");
	}
}