package com.villagecraft.entity.professions;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.VillageCraft;
import com.villagecraft.entity.goal.VillagerGoalBase;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import javax.annotation.Nullable;

/**
 * Base class for VillagerCraft professions.
 * In 1.20.2, VillagerProfession is a record registered via DeferredRegister.
 * This class provides utility methods and event handlers.
 */
public class VillagerCraftBaseProfession {
	
	public VillagerProfession profession;
	public PoiType pointOfInterest;
	
	public String name;
	public ImmutableSet<Item> specificItems;
	public ImmutableSet<Block> relatedWorldBlocks;
	public SoundEvent sound;
	
	/**
	 * Constructor stores profession data
	 */
	public VillagerCraftBaseProfession(String nameIn, PoiType pointOfInterestIn,
			ImmutableSet<Item> specificItemsIn, ImmutableSet<Block> relatedWorldBlocksIn, SoundEvent soundIn) {
		this.name = nameIn;
		this.pointOfInterest = pointOfInterestIn;
		this.specificItems = specificItemsIn;
		this.relatedWorldBlocks = relatedWorldBlocksIn;
		this.sound = soundIn;
		VillageCraft.LOGGER.debug("Initialized Villager Profession for " + this.name);
	}
	
	/**
	 * Register trades for custom professions.
	 */
	public static void RegisterVillagerTrades(VillagerTradesEvent event) { }
	
	/**
	 * Register Goals for a profession.
	 */
	public static void RegisterVillagerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager) {
			Villager entity = (Villager) event.getEntity();
			VillagerGoalBase goal = new VillagerGoalBase(entity);
			entity.goalSelector.addGoal(1, goal);
		}
	}
}