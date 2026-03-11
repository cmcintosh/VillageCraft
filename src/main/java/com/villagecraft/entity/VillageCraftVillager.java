package com.villagecraft.entity;

import java.util.Optional;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.villagecraft.data.VillageCraftVillagerData;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.brain.Brain;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class VillageCraftVillager extends Villager {
	
	protected VillageCraftVillagerData villageData;

	public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn) {
		super(type, worldIn);
	}
	
	public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, VillagerType villagerType) {
		super(type, worldIn, villagerType);
	}
	
	public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, VillagerType villagerType, VillageCraftVillagerData data) {
		super(type, worldIn, villagerType);
		this.villageData = data;
	}
	
	@Override
	protected void initBrain(Brain<Villager> brain) {
		VillagerProfession villagerprofession = this.getVillagerData().getProfession();
		if (this.isBaby()) {
			brain.setSchedule(Schedule.VILLAGER_BABY);
			brain.addActivity(Activity.PLAY, Villager.createPlayPackage(0.5F));
		} else {
			brain.setSchedule(Schedule.VILLAGER_DEFAULT);
			brain.addActivity(Activity.WORK, Villager.createWorkPackage(villagerprofession, 0.5F), 
				ImmutableSet.of(Pair.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_PRESENT)));
		}

		brain.addActivity(Activity.CORE, Villager.createCorePackage(villagerprofession, 0.5F));
		brain.addActivityAndRemoveMemoriesWhenStopped(Activity.MEET, Villager.createMeetPackage(villagerprofession, 0.5F), 
			ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryStatus.VALUE_PRESENT)));
		brain.addActivity(Activity.REST, Villager.createRestPackage(villagerprofession, 0.5F));
		brain.addActivity(Activity.IDLE, Villager.createIdlePackage(villagerprofession, 0.5F));
		brain.addActivity(Activity.PANIC, Villager.createPanicPackage(villagerprofession, 0.5F));
		brain.addActivity(Activity.PRE_RAID, Villager.createPreRaidPackage(villagerprofession, 0.5F));
		brain.addActivity(Activity.RAID, Villager.createRaidPackage(villagerprofession, 0.5F));
		brain.addActivity(Activity.HIDE, Villager.createHidePackage(villagerprofession, 0.5F));
		brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
		brain.setDefaultActivity(Activity.IDLE);
		brain.setActiveActivityIfPossible(Activity.IDLE);
		brain.updateActivityFromSchedule(this.level().getDayTime(), this.level().getGameTime());
	}

	public VillageCraftVillagerData getVillageData() {
		return villageData;
	}
	
	public void setVillageData(VillageCraftVillagerData data) {
		this.villageData = data;
	}
}
