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
 * Profession class for Farmer villagers.
 * Farmers sell crops, seeds, and food items.
 */
public class FarmerProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.WHEAT);

    public static String getName() { return "farmer"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the farmer trades");
        if (event.getType() == ModVillagerProfessions.FARMER.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Wheat and seeds
            trades.get(1).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.WHEAT, 20),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.WHEAT_SEEDS, 12),
                    16, 1, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.BEETROOT, 15),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Vegetables
            trades.get(2).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.CARROT, 22),
                    new ItemStack(Items.EMERALD, 1),
                    16, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.POTATO, 22),
                    new ItemStack(Items.EMERALD, 1),
                    16, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.CARROT, 6),
                    12, 2, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Fruits and berries
            trades.get(3).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.APPLE, 8),
                    new ItemStack(Items.EMERALD, 1),
                    8, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.SWEET_BERRIES, 10),
                    new ItemStack(Items.EMERALD, 1),
                    12, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.PUMPKIN, 1),
                    6, 5, 0.05f
                )
            ));

            // Tier 4 (Expert) - Processed goods
            trades.get(4).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.MELON, 4),
                    new ItemStack(Items.EMERALD, 1),
                    8, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.CAKE, 1),
                    4, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.GOLDEN_CARROT, 2),
                    4, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Rare crops
            trades.get(5).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.GLISTERING_MELON_SLICE, 3),
                    4, 20, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.GOLDEN_APPLE, 1),
                    2, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                    1, 100, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Farmer trades registered with 5 tiers");
        }
    }
}