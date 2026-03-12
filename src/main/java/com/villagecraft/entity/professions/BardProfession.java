package com.villagecraft.entity.professions;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.entity.npc.VillagerTrades.EmeraldForItems;
import net.minecraft.world.entity.npc.VillagerTrades.DyedArmorForEmeralds;
import net.minecraft.world.entity.npc.VillagerTrades.EnchantedItemForEmeralds;
import net.minecraft.world.entity.npc.VillagerTrades.ItemsAndEmeraldsToItems;
import net.minecraft.world.entity.npc.VillagerTrades.ItemsForEmeralds;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Profession class for Bard villagers.
 * Bards provide entertainment and sell musical items.
 */
public class BardProfession {

	public static final ItemStack PROFESSION_ICON = new ItemStack(Items.NOTE_BLOCK);

	public static String getName() { return "bard"; }

	public static List<ItemListing> getTrades() {
		// Bard-specific trades - musical instruments, entertainment items
		// NeoForge 1.20.2: Trade patterns updated

		return new ArrayList<>(Arrays.asList(
			new EmeraldForItems(Items.NOTE_BLOCK, 5, 2, 12, 2),                // Buy 5 note blocks for 2 emeralds
			new EmeraldForItems(Items.JUKEBOX, 1, 4, 8, 5),                   // Buy 1 jukebox for 4 emeralds
			new ItemsForEmeralds(Items.MUSIC_DISC_13, 3, 1, 4, 10),           // Sell Music Disc for 3 emeralds
			new ItemsForEmeralds(Items.MUSIC_DISC_CAT, 3, 1, 4, 10),          // Sell Music Disc for 3 emeralds
			new ItemsForEmeralds(Items.MUSIC_DISC_BLOCKS, 4, 1, 3, 15),       // Sell Music Disc for 4 emeralds
			new ItemsForEmeralds(Items.NAME_TAG, 1, 3, 6, 5),                 // Sell name tags
			new ItemsForEmeralds(Items.ENDER_PEARL, 3, 2, 4, 5)               // Sell ender pearls (magical bard)
		));
	}

	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		VillageCraft.LOGGER.debug("Registering the bard trades");
		if (event.getType() == ModVillagerProfessions.BARD.get()) {
			HashMap<Integer, ArrayList<ItemListing>> trades = (HashMap<Integer, ArrayList<ItemListing>>) event.getTrades();

			// NeoForge 1.20.2: Initialize trade list if needed
			if (trades.isEmpty()) {
				for (int i = 1; i <= 5; i++) {
					trades.put(i, new ArrayList<ItemListing>());
				}
			}

			// Tier 1 (Novice) - Basic musical items
			trades.get(1).addAll(Arrays.asList(
				(event, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 2),
					new ItemStack(Items.NOTE_BLOCK, 4),
					12, 2, 0.05f
				),
				(event, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1),
					new ItemStack(Items.FEATHER, 8),
					16, 1, 0.05f
				)
			));

			// Tier 2 (Apprentice) - Entertainment items
			trades.get(2).addAll(Arrays.asList(
				(event, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 5),
					new ItemStack(Items.JUKEBOX, 1),
					8, 5, 0.05f
				),
				(event, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3),
					new ItemStack(Items.NAME_TAG, 1),
					6, 5, 0.05f
				)
			));

			// Tier 3 (Journeyman) - Music discs
			final Random random = new Random();
			// Random music disc for tier 3
			trades.get(3).add((e, r) -> {
				int discChoice = r.nextInt(5);
				ItemStack disc;
				if (discChoice == 0) disc = new ItemStack(Items.MUSIC_DISC_13);
				else if (discChoice == 1) disc = new ItemStack(Items.MUSIC_DISC_CAT);
				else if (discChoice == 2) disc = new ItemStack(Items.MUSIC_DISC_BLOCKS);
				else if (discChoice == 3) disc = new ItemStack(Items.MUSIC_DISC_CHIRP);
				else disc = new ItemStack(Items.MUSIC_DISC_FAR);
				return new MerchantOffer(
					new ItemStack(Items.EMERALD, 3),
					disc.copy(),
					4, 15, 0.05f
				);
			});

			// Tier 4 (Expert) - Magical items for entertainment
			trades.get(4).addAll(Arrays.asList(
				(event, random2) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 8),
					new ItemStack(Items.ENDER_PEARL, 2),
					4, 5, 0.05f
				),
				(event, random2) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 6),
					new ItemStack(Items.GLOWSTONE_DUST, 12),
					12, 8, 0.05f
				)
			));

			// Tier 5 (Master) - Premium entertainment
			trades.get(5).add((e, r) -> {
				// Random music disc from pool
				int discChoice = r.nextInt(6);
				ItemStack masterDisc;
				if (discChoice == 0) masterDisc = new ItemStack(Items.MUSIC_DISC_MELLOHI);
				else if (discChoice == 1) masterDisc = new ItemStack(Items.MUSIC_DISC_STAL);
				else if (discChoice == 2) masterDisc = new ItemStack(Items.MUSIC_DISC_STRAD);
				else if (discChoice == 3) masterDisc = new ItemStack(Items.MUSIC_DISC_WARD);
				else if (discChoice == 4) masterDisc = new ItemStack(Items.MUSIC_DISC_WAIT);
				else masterDisc = new ItemStack(Items.MUSIC_DISC_PIGSTEP);
				return new MerchantOffer(
					new ItemStack(Items.EMERALD, 10),
					masterDisc,
					2, 30, 0.05f
				);
			});

			VillageCraft.LOGGER.debug("Bard trades registered with 5 tiers");
		}
	}
}
