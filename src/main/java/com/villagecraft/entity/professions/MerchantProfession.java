package com.villagecraft.entity.professions;

import java.util.Map;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

// TODO: Reimplement MerchantProfession for 1.20.2
public class MerchantProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(Items.EMERALD);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	// TODO: MERCHANT profession not registered yet
	// public VillagerProfession PROFESSION = ModVillagerProfessions.MERCHANT.get();
	
	// TODO: Reimplement constructor
	public MerchantProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		// super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
	}
	
	// TODO: Reimplement trade registration
	public static void RegisterVillagerTrades(VillagerTradesEvent event) {
		// Trades disabled - needs rewrite for 1.20.2
	}
	
}