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
 * Profession class for Alchemist villagers.
 * Alchemists buy potion ingredients and sell potions.
 */
public class AlchemistProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.BREWING_STAND);

    public static String getName() { return "alchemist"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the alchemist trades");
        if (event.getType() == ModVillagerProfessions.ALCHEMIST.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic ingredients
            trades.get(1).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.NETHER_WART, 16),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.GLASS_BOTTLE, 8),
                    12, 1, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.REDSTONE, 20),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Common potions
            trades.get(2).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.POTION, 1), // Water bottle base
                    8, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.GLOWSTONE_DUST, 12),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.FERMENTED_SPIDER_EYE, 4),
                    6, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Rare ingredients
            trades.get(3).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.BLAZE_ROD, 8),
                    new ItemStack(Items.EMERALD, 2),
                    8, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.GHAST_TEAR, 2),
                    new ItemStack(Items.EMERALD, 4),
                    4, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.PHANTOM_MEMBRANE, 2),
                    4, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Magical items
            trades.get(4).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.DRAGON_BREATH, 1),
                    2, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.NETHER_WART_BLOCK, 2),
                    4, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.EXPERIENCE_BOTTLE, 4),
                    6, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Legendary alchemy
            trades.get(5).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                    1, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.TOTEM_OF_UNDYING, 1),
                    1, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.SPLASH_POTION, 4),
                    4, 20, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Alchemist trades registered with 5 tiers");
        }
    }

    @SubscribeEvent
    public static void registerGoals(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
        // Alchemist goals handled by VillagerCraftVillager
    }
}