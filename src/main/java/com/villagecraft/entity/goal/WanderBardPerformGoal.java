package com.villagecraft.entity.goal;

import java.util.List;

import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class WanderBardPerformGoal extends VillagerGoalBase {

	protected VillagerEntity entity;
	protected boolean playedSong = false;
	
	public WanderBardPerformGoal(VillagerEntity entity) {
		super(entity);
		
	}
	
	@Override
	public boolean shouldExecute() { 
		if (!(villager.getVillagerData().getProfession() == ModVillagerProfessions.BARD.get())) { 
			return false;
		}
		
		List<VillagerEntity> list = this.villager.world.getEntitiesWithinAABB(VillagerEntity.class, this.villager.getBoundingBox().grow((double)10.0D));
		if (!list.isEmpty()) {
		  for(VillagerEntity entity : list) {
	          if (!entity.isInvisible()) {
	              this.entity = entity;
	              return true;
	            }
	       }
	    }
		return false;
	}
	
	@Override
	public void resetTask()
	{
		super.resetTask();
		playedSong = false;
	}

	@Override
	public void tick()
	{
		if (!(playedSong)) {
			playedSong = true;
			ResourceLocation location = new ResourceLocation("vcm", "flute_short_1");
			SoundEvent event = new SoundEvent(location);
			villager.playSound(event, 100, 1);
		}
	}
	
	
}
