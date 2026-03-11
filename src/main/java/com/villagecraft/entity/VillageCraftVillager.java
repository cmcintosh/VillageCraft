package com.villagecraft.entity;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.villagecraft.data.VillageCraftVillagerData;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleStatus;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.behavior.Schedule;
import net.minecraft.world.entity.ai.behavior.Activity;
import net.minecraft.world.entity.ai.behavior.VillagerGoalPackages;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class VillageCraftVillager extends Villager {
	
	protected VillageCraftVillagerData villageData;

	public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, VillagerType villagerType) {
		super(type, worldIn, villagerType);
	}
	
	public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, VillagerType villagerType, VillageCraftVillagerData data) {
		super(type, worldIn, villagerType);
		this.villageData = data;
	}
	
	private void initBrain(Brain<VillagerEntity> villagerBrain) {
      VillagerProfession villagerprofession = this.getVillagerData().getProfession();
      if (this.isChild()) {
         villagerBrain.setSchedule(Schedule.VILLAGER_BABY);
         villagerBrain.registerActivity(Activity.PLAY, VillagerGoalPackages.getPlayPackage(0.5F));
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
