package com.villagecraft.entity.professions;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

// TODO: Reimplement InnkeeperProfession for 1.20.2
public class InnkeeperProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of();
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	// TODO: Profession not registered
	// public VillagerProfession PROFESSION;
	
	public InnkeeperProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		// super(); - VillagerCraftBaseProfession not available
	}
	
	public static void RegisterVillagerTrades(VillagerTradesEvent event) {
		// TODO: Trades disabled - profession not registered
	}
	
	public static void RegisterVillagerGoals(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
		// TODO: Goals not implemented
	}
}
