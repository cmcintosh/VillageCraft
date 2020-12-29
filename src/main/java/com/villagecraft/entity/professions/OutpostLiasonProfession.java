package com.villagecraft.entity.professions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.villagecraft.VillageCraft;
import com.villagecraft.entity.goal.HealGolemGoal;
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

public class OutpostLiasonProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(ModItems.BEER_BUCKET.get());
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of(Blocks.BIRCH_LOG);
	public VillagerProfession PROFESSION = ModVillagerProfessions.OUTPOST_LIASON.get();
	
	/**
	 * {@inheritDoc}
	 */
	public OutpostLiasonProfession(String nameIn, PointOfInterestType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
			super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public static void RegisterVillagerTrades(VillagerTradesEvent event) { 
		if (event.getType() == ModVillagerProfessions.OUTPOST_LIASON.get()) {
			// Level 1
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.CHEST, 32), new ItemStack(ModItems.DELIVERY_STATION.get()), 8, 10, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.IRON_HELMET, 1), new ItemStack(Items.LANTERN, 1), new ItemStack(ModItems.TRADESMAN_HELMET.get()), 1, 30, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 16), new ItemStack(ModItems.AUCTION_HOUSE.get()), 100, 30, 0F));
			event.getTrades().get(3).add((entity, random) -> new MerchantOffer(new ItemStack(Items.WOODEN_SWORD, 1), new ItemStack(Items.CHEST, 1), new ItemStack(ModItems.BRALWER_BOX.get()), 100, 10, 0F));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public static void RegisterVillagerGoals(EntityJoinWorldEvent event) { 
		Entity entity = event.getEntity();
		
		
	}

}
