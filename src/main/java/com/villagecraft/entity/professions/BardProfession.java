package com.villagecraft.entity.professions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModFoods;
import com.villagecraft.init.ModItems;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.util.TradeTypes;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;

public class BardProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(ModItems.BEER_BUCKET.get());
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of(ModBlocks.BLOCK_CHAIR.get());
	public VillagerProfession PROFESSION = ModVillagerProfessions.BARD.get();
	
	public BardProfession(String nameIn, PointOfInterestType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
			super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
			
	}
	
	@Override
	public void setupTrades() { 
		Int2ObjectArrayMap int2ObjectArrayMap = new Int2ObjectArrayMap();
		    VillagerTrades.ITrade[] value = { TradeTypes.EmeraldForItemsTrade(Items.EMERALD, 4, 8, 2), TradeTypes.ItemsForEmeraldsTrade(ModFoods.BEER.get(), 1, 1, 2) };
		    int2ObjectArrayMap.put(1, value);
		    VillagerTrades.VILLAGER_DEFAULT_TRADES.put(this.PROFESSION, int2ObjectArrayMap);
	}

}
