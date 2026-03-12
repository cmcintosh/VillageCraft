package com.villagecraft.entity.goal;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.animal.IronGolem;

public class WanderBardPerformGoal extends VillagerGoalBase {

	protected Villager entity;
	protected boolean playedSong = false;
	
	public WanderBardPerformGoal(Villager entity) {
		super(entity);
		this.entity = entity;
	}
	
	@Override
	public boolean canUse() { 
		// TODO: Reimplement BARD profession check
		// TODO: Reimplement nearby villager detection
		return false;
	}
	
	@Override
	public void stop()
	{
		super.stop();
		playedSong = false;
	}

	@Override
	public void tick()
	{
		// TODO: Reimplement sound playing for 1.20.2
		// - ResourceLocation creation changed
		// - playSound API may have changed
	}
	
	
}