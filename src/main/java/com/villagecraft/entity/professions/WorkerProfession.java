package com.villagecraft.entity.professions;

import java.util.List;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.init.ModVillagerProfessions;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

/**
 * Worker Profession - Basic villager profession for production.
 * Gathers resources, delivers to storage.
 */
public class WorkerProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(Items.WOODEN_PICKAXE, Items.WOODEN_AXE, Items.WOODEN_SHOVEL);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public static final SoundEvent SOUND = SoundEvents.VILLAGER_WORK_TOOLSMITH;
	
	public WorkerProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
	}
	
	/**
	 * Register Worker trades - basic resource trades
	 */
	public static void registerTrades(VillagerTradesEvent event) { 
		if (ModVillagerProfessions.WORKER.get() == event.getType()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			
			// Level 1 trades - raw materials for emeralds
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.OAK_LOG, 16), 
					new ItemStack(Items.EMERALD, 1), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.COBBLESTONE, 32), 
					new ItemStack(Items.EMERALD, 1), 
					16, 2, 0.05f));
			
			// Level 2 trades - better prices
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.OAK_PLANKS, 8), 
					12, 5, 0.05f));
		}
	}
	
	/**
	 * Register Worker goals - go to work site, gather resources
	 * 
	 * Note: Core goals are registered in VillageCraftVillager.registerGoals()
	 * including HealGolemGoal and VillagerGoalGotoVillageCenter.
	 * Worker-specific resource gathering and delivery goals can be
	 * added here if needed for advanced behavior.
	 */
	public static void registerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager villager) {
			// Check if this villager is a worker
			if (villager.getVillagerData().getProfession() == ModVillagerProfessions.WORKER.get()) {
				// Worker goals are handled by VillageCraftVillager.registerGoals()
				// Additional worker-specific behaviors (resource gathering, tool use)
				// can be registered here if needed
			}
		}
	}
}