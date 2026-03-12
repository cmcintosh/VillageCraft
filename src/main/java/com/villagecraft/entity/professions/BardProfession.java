package com.villagecraft.entity.professions;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.ArrayList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

/**
 * Profession class for Bard villagers.
 * Bards provide entertainment and sell musical items.
 */
public class BardProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.NOTE_BLOCK);

    public static String getName() { return "bard"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the bard trades");
        if (event.getType() == ModVillagerProfessions.BARD.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic instruments
            trades.get(1).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.NOTE_BLOCK, 2),
                    16, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.STICK, 8),
                    12, 1, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STRING, 12),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Musical materials
            trades.get(2).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.JUKEBOX, 1),
                    4, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.MUSIC_DISC_13, 1),
                    4, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.MUSIC_DISC_CAT, 1),
                    4, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Rare music
            trades.get(3).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.MUSIC_DISC_BLOCKS, 1),
                    2, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.MUSIC_DISC_CHIRP, 1),
                    2, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.MUSIC_DISC_FAR, 1),
                    2, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Enchanted instruments
            trades.get(4).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.MUSIC_DISC_MALL, 1),
                    2, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    new ItemStack(Items.MUSIC_DISC_MELLOHI, 1),
                    2, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.MUSIC_DISC_STAL, 1),
                    2, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Legendary music
            trades.get(5).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.MUSIC_DISC_STRAD, 1),
                    2, 30, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.MUSIC_DISC_WARD, 1),
                    2, 30, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 25),
                    new ItemStack(Items.MUSIC_DISC_11, 1),
                    1, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 30),
                    new ItemStack(Items.MUSIC_DISC_WAIT, 1),
                    1, 100, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Bard trades registered with 5 tiers");
        }
    }
}
