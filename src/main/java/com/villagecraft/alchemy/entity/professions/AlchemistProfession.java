package com.villagecraft.alchemy.entity.professions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.villagecraft.VillageCraft;
import com.villagecraft.entity.goal.HealGolemGoal;
import com.villagecraft.entity.professions.VillagerCraftBaseProfession;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModFoods;
import com.villagecraft.init.ModItems;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.util.RandomTradeBuilder;
import com.villagecraft.util.TradeTypes;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;

public class AlchemistProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(ModItems.BEER_BUCKET.get());
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of(Blocks.BIRCH_LOG);
	public VillagerProfession PROFESSION = ModVillagerProfessions.ALCHEMIST.get();
	
	/**
	 * {@inheritDoc}
	 */
	public AlchemistProfession(String nameIn, PointOfInterestType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
			super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public static void RegisterVillagerTrades(VillagerTradesEvent event) { 
		if (event.getType() == ModVillagerProfessions.ALCHEMIST.get()) {
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 16), new ItemStack(ModItems.BEER_BUCKET.get()), 8, 10, 0F));
	        RandomTradeBuilder.forEachLevel((level, tradeBuild) -> event.getTrades().get(level.intValue()).add(tradeBuild.build()));	
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public static void RegisterVillagerGoals(EntityJoinWorldEvent event) { 
		Entity entity = event.getEntity();
		
		
	}

}
