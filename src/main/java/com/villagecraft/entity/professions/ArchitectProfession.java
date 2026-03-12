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
 * Architect Profession - sells building materials and plans.
 */
public class ArchitectProfession {

	public static final ItemStack PROFESSION_ICON = new ItemStack(Items.BRICKS);

	public static String getName() { return "architect"; }

	public static List<VillagerTrades.ItemListing> getTrades() {
		return new ArrayList<>();
	}

	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		VillageCraft.LOGGER.debug("Registering the architect trades");
		if (event.getType() == ModVillagerProfessions.ARCHITECT.get()) {
			HashMap<Integer, ArrayList<VillagerTrades.ItemListing>> trades = 
				(HashMap<Integer, ArrayList<VillagerTrades.ItemListing>>) event.getTrades();

			if (trades.isEmpty()) {
				for (int i = 1; i <= 5; i++) trades.put(i, new ArrayList<>());
			}

			// Tier 1: Basic building blocks
			trades.get(1).addAll(Arrays.asList(
				(e,r) -> new MerchantOffer(new ItemStack(Items.BRICKS, 16), new ItemStack(Items.EMERALD, 1), 16, 2, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.LADDER, 8), 12, 1, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.PAINTING, 2), 8, 2, 0.05f)
			));

			// Tier 2: Decorative blocks
			trades.get(2).addAll(Arrays.asList(
				(e,r) -> new MerchantOffer(new ItemStack(Items.STONE_BRICKS, 32), new ItemStack(Items.EMERALD, 1), 16, 5, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.GLASS, 16), new ItemStack(Items.EMERALD, 1), 12, 5, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(Items.ITEM_FRAME, 4), 8, 5, 0.05f)
			));

			// Tier 3: Glass and lighting
			trades.get(3).addAll(Arrays.asList(
				(e,r) -> new MerchantOffer(new ItemStack(Items.GLASS_PANE, 32), new ItemStack(Items.EMERALD, 1), 12, 10, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.GLOWSTONE, 4), 8, 10, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4), new ItemStack(Items.SEA_LANTERN, 2), 4, 15, 0.05f)
			));

			// Tier 4: Prismarine and terracotta
			trades.get(4).addAll(Arrays.asList(
				(e,r) -> new MerchantOffer(new ItemStack(Items.PRISMARINE, 32), new ItemStack(Items.EMERALD, 3), 8, 15, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.TERRACOTTA, 16), new ItemStack(Items.EMERALD, 1), 12, 10, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.END_ROD, 4), 6, 15, 0.05f)
			));

			// Tier 5: Luxury materials
			trades.get(5).addAll(Arrays.asList(
				(e,r) -> new MerchantOffer(new ItemStack(Items.DARK_PRISMARINE, 16), new ItemStack(Items.EMERALD, 4), 4, 30, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.BEACON, 1), 2, 100, 0.05f),
				(e,r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 8), new ItemStack(Items.SHULKER_SHELL, 1), 4, 30, 0.05f)
			));

			VillageCraft.LOGGER.debug("Architect trades registered with 5 tiers");
		}
	}
}
