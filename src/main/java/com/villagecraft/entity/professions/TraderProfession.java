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
 * Trader Profession - Economic villager for buying/selling goods.
 * Sells building materials, tools, and buys raw resources.
 */
public class TraderProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(Items.EMERALD, Items.GOLD_INGOT);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public static final SoundEvent SOUND = SoundEvents.VILLAGER_WORK_CARTOGRAPHER;
	
	public TraderProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
	}
	
	/**
	 * Register Trader trades - economic trades for building
	 */
	public static void registerTrades(VillagerTradesEvent event) { 
		if (ModVillagerProfessions.TRADER.get() == event.getType()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			
			// Level 1 - Basic building supplies
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.OAK_PLANKS, 16), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.COBBLESTONE, 16), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1),
					new ItemStack(Items.GLASS, 8),
					8, 2, 0.05f));
			
			// Level 2 - Better materials
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 2), 
					new ItemStack(Items.BRICKS, 16), 
					8, 5, 0.05f));
			
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3), 
					new ItemStack(Items.OAK_LOG, 8), 
					8, 5, 0.05f));
			
			// Level 3 - Tools and valuable resources
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 5), 
					new ItemStack(Items.IRON_PICKAXE, 1), 
					3, 10, 0.05f));
			
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 4),
					new ItemStack(Items.LANTERN, 8),
					8, 10, 0.05f));
			
			// Level 4 - Specialized items
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 8), 
					new ItemStack(Items.CLOCK, 1), 
					2, 15, 0.05f));
			
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 6),
					new ItemStack(Items.COMPASS, 1),
					2, 15, 0.05f));
			
			// Level 5 - High-tier building
			trades.get(5).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 16), 
					new ItemStack(Items.BEACON, 1), 
					1, 30, 0.05f));
		}
	}
	
	/**
	 * Register Trader goals
	 */
	public static void registerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager villager) {
			if (villager.getVillagerData().getProfession() == ModVillagerProfessions.TRADER.get()) {
				// TODO: Add trader-specific goals
				// - Go to trading post
				// - Display trade goods
			}
		}
	}
}