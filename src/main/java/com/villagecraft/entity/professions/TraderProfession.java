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
 * Profession class for Trader villagers.
 * Traders buy and sell a variety of goods from different regions.
 */
public class TraderProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.EMERALD);

    public static String getName() { return "trader"; }

    public static List<VillagerTrades.ItemListing> getTrades() {
        return new ArrayList<>();
    }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the trader trades");
        if (event.getType() == ModVillagerProfessions.TRADER.get()) {
            HashMap<Integer, ArrayList<VillagerTrades.ItemListing>> trades = 
                (HashMap<Integer, ArrayList<VillagerTrades.ItemListing>>) event.getTrades();

            // Initialize trade tiers
            if (trades.isEmpty()) {
                for (int i = 1; i <= 5; i++) {
                    trades.put(i, new ArrayList<VillagerTrades.ItemListing>());
                }
            }

            // Tier 1 (Novice) - Basic trading
            trades.get(1).addAll(Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 64),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.SAND, 32),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.STRING, 8),
                    8, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Building materials
            trades.get(2).addAll(Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.GRAVEL, 32),
                    new ItemStack(Items.EMERALD, 2),
                    12, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.FLINT, 24),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.LEATHER, 6),
                    6, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Crafting goods
            trades.get(3).addAll(Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.FEATHER, 32),
                    new ItemStack(Items.EMERALD, 1),
                    12, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.BOOK, 3),
                    6, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.COMPASS, 1),
                    3, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Valuable goods
            trades.get(4).addAll(Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.CLOCK, 1),
                    2, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.SADDLE, 1),
                    2, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.NAME_TAG, 2),
                    2, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Rare items
            trades.get(5).addAll(Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.ENDER_CHEST, 1),
                    1, 30, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.DIAMOND_HORSE_ARMOR, 1),
                    1, 30, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 30),
                    new ItemStack(Items.TOTEM_OF_UNDYING, 1),
                    1, 100, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Trader trades registered with 5 tiers");
        }
    }
}
