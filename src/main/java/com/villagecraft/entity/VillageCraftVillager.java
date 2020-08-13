package com.villagecraft.entity;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.villagecraft.data.VillageCraftVillagerData;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.schedule.Schedule;
import net.minecraft.entity.ai.brain.task.VillagerTasks;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class VillageCraftVillager extends VillagerEntity {
	
	protected VillageCraftVillagerData villageData;

	public VillageCraftVillager(EntityType<? extends VillagerEntity> type, World worldIn, IVillagerType villagerType) {
		super(type, worldIn, villagerType);
	}
	
	public VillageCraftVillager(EntityType<? extends VillagerEntity> type, World worldIn, IVillagerType villagerType, VillageCraftVillagerData data) {
		super(type, worldIn, villagerType);
		this.villageData = data;
	}
	
	private void initBrain(Brain<VillagerEntity> villagerBrain) {
      VillagerProfession villagerprofession = this.getVillagerData().getProfession();
      if (this.isChild()) {
         villagerBrain.setSchedule(Schedule.VILLAGER_BABY);
         villagerBrain.registerActivity(Activity.PLAY, VillagerTasks.play(0.5F));
      } else {
         villagerBrain.setSchedule(Schedule.VILLAGER_DEFAULT);
         villagerBrain.func_233700_a_(Activity.WORK, VillagerTasks.work(villagerprofession, 0.5F), ImmutableSet.of(Pair.of(MemoryModuleType.JOB_SITE, MemoryModuleStatus.VALUE_PRESENT)));
      }

      villagerBrain.registerActivity(Activity.CORE, VillagerTasks.core(villagerprofession, 0.5F));
      villagerBrain.func_233700_a_(Activity.MEET, VillagerTasks.meet(villagerprofession, 0.5F), ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryModuleStatus.VALUE_PRESENT)));
      villagerBrain.registerActivity(Activity.REST, VillagerTasks.rest(villagerprofession, 0.5F));
      villagerBrain.registerActivity(Activity.IDLE, VillagerTasks.idle(villagerprofession, 0.5F));
      villagerBrain.registerActivity(Activity.PANIC, VillagerTasks.panic(villagerprofession, 0.5F));
      villagerBrain.registerActivity(Activity.PRE_RAID, VillagerTasks.preRaid(villagerprofession, 0.5F));
      villagerBrain.registerActivity(Activity.RAID, VillagerTasks.raid(villagerprofession, 0.5F));
      villagerBrain.registerActivity(Activity.HIDE, VillagerTasks.hide(villagerprofession, 0.5F));
      villagerBrain.setDefaultActivities(ImmutableSet.of(Activity.CORE));
      villagerBrain.setFallbackActivity(Activity.IDLE);
      villagerBrain.switchTo(Activity.IDLE);
      villagerBrain.updateActivity(this.world.getDayTime(), this.world.getGameTime());
   }

}
