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
import com.villagecraft.entity.goal.VillagerGoalDeliverToStorage;
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
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.Registries;

public class WorkerProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.copyOf(ForgeRegistries.ITEMS);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.copyOf(ForgeRegistries.BLOCKS);
	public VillagerProfession PROFESSION = ModVillagerProfessions.WORKER.get();
	
	/**
	 * {@inheritDoc}
	 */
	public WorkerProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
			super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
			
	}
	
	/**
	 * {@inheritDoc}
	 */
	public static void RegisterVillagerTrades(VillagerTradesEvent event) { 
		// RandomTradeBuilder.forEachLevel((level, tradeBuild) -> event.getTrades().get(level.intValue()).add(tradeBuild.build()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public static void RegisterVillagerGoals(EntityJoinLevelEvent event) { 
		VillagerEntity entity = (VillagerEntity)event.getEntity();
	}

}
