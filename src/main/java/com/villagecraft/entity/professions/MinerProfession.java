package com.villagecraft.entity.professions;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Profession class for Miner villagers.
 * Miners sell ores, minerals, and mining supplies.
 */
public class MinerProfession {

	public static final ItemStack PROFESSION_ICON = new ItemStack(Items.IRON_PICKAXE);

	public static String getName() { return "miner"; }

	public static List<VillagerTrades.ItemListing> getTrades() {
		return new ArrayList<>(Arrays.asList(
			// NeoForge 1.20.2 Basic trades
			(event, random) -> new MerchantOffer(
				new ItemStack(Items.IRON_ORE, 8),
				new ItemStack(Items.EMERALD, 1),
				12, 2, 0.05f
			),
			(event, random) -> new MerchantOffer(
				new ItemStack(Items.COAL, 16),
				new ItemStack(Items.EMERALD, 1),
				16, 1, 0.05f
			)
		));
	}

	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		VillageCraft.LOGGER.debug("Registering the miner trades");
		if (event.getType() == ModVillagerProfessions.MINER.get()) {
			HashMap<Integer, ArrayList<VillagerTrades.ItemListing>> trades = 
				(HashMap<Integer, ArrayList<VillagerTrades.ItemListing>>) event.getTrades();

			// Initialize trade tiers
			if (trades.isEmpty()) {
				for (int i = 1; i <= 5; i++) {
					trades.put(i, new ArrayList<VillagerTrades.ItemListing>());
				}
			}

			// Tier 1 (Novice) - Basic minerals
			trades.get(1).addAll(Arrays.asList(
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.COAL, 16),
					new ItemStack(Items.EMERALD, 1),
					16, 2, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.IRON_ORE, 8),
					new ItemStack(Items.EMERALD, 1),
					12, 2, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1),
					new ItemStack(Items.TORCH, 16),
					16, 1, 0.05f
				)
			));

			// Tier 2 (Apprentice) - Better ores and tools
			trades.get(2).addAll(Arrays.asList(
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.COPPER_ORE, 12),
					new ItemStack(Items.EMERALD, 1),
					12, 5, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 2),
					new ItemStack(Items.LAPIS_LAZULI, 8),
					8, 5, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1),
					new ItemStack(Items.STONE_PICKAXE, 1),
					8, 2, 0.05f
				)
			));

			// Tier 3 (Journeyman) - Valuable minerals
			trades.get(3).addAll(Arrays.asList(
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.GOLD_ORE, 6),
					new ItemStack(Items.EMERALD, 2),
					8, 10, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3),
					new ItemStack(Items.LAVA_BUCKET, 1),
					4, 10, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 4),
					new ItemStack(Items.IRON_PICKAXE, 1),
					4, 10, 0.05f
				)
			));

			// Tier 4 (Expert) - Rare mining finds
			trades.get(4).addAll(Arrays.asList(
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.DIAMOND, 1),
					new ItemStack(Items.EMERALD, 4),
					4, 15, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.REDSTONE, 32),
					new ItemStack(Items.EMERALD, 2),
					8, 10, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.GLOWSTONE, 8),
					new ItemStack(Items.EMERALD, 3),
					8, 10, 0.05f
				)
			));

			// Tier 5 (Master) - Premium mining goods
			trades.get(5).addAll(Arrays.asList(
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 12),
					new ItemStack(Items.DIAMOND_PICKAXE, 1),
					2, 30, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.NETHERITE_SCRAP, 1),
					new ItemStack(Items.EMERALD, 8),
					2, 20, 0.05f
				),
				(event2, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 16),
					new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
					1, 100, 0.05f
				)
			));

			VillageCraft.LOGGER.debug("Miner trades registered with 5 tiers");
		}
	}
}
