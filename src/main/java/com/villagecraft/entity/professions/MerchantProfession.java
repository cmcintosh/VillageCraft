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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MerchantOffer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.event.entity.EntityJoinWorldEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import net.minecraft.world.item.Items;

public class MerchantProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(net.minecraft.item.Items.EMERALD);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public VillagerProfession PROFESSION = ModVillagerProfessions.MERCHANT.get();
	
	/**
	 * {@inheritDoc}
	 */
	public MerchantProfession(String nameIn, PoiType pointOfInterestIn, 
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
