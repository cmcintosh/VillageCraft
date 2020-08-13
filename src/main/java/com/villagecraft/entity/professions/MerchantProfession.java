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

import net.minecraft.item.Items;

public class MerchantProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(net.minecraft.item.Items.EMERALD);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public VillagerProfession PROFESSION = ModVillagerProfessions.MERCHANT.get();
	
	/**
	 * {@inheritDoc}
	 */
	public MerchantProfession(String nameIn, PointOfInterestType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
			super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public static void RegisterVillagerTrades(VillagerTradesEvent event) {
		if (ModVillagerProfessions.MERCHANT.get() == event.getType()) {
			RandomTradeBuilder.forEachLevel((level, tradeBuild) -> event.getTrades().get(level.intValue()).add(tradeBuild.build()));
		
			// wood logs
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.ACACIA_LOG, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.BIRCH_LOG, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.DARK_OAK_LOG, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.OAK_LOG, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.JUNGLE_LOG, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.SPRUCE_LOG, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.ACACIA_LOG, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.BIRCH_LOG, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.DARK_OAK_LOG, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.OAK_LOG, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.JUNGLE_LOG, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.SPRUCE_LOG, 64), 80, 1, 0F));
			
			// Food
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.WHEAT, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.MELON, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.PUMPKIN, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.CARROT, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.POTATO, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.BEETROOT, 64), new ItemStack(Items.EMERALD, 1), 80, 1, 0F));
			
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.WHEAT, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.MELON, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.PUMPKIN, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.CARROT, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.POTATO, 64), 80, 1, 0F));
			event.getTrades().get(2).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.BEETROOT, 64), 80, 1, 0F));
			
			// Unlock Upgrade token
			
		}
		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public static void RegisterVillagerGoals(EntityJoinWorldEvent event) { 
		Entity entity = event.getEntity();
			
	}

}
