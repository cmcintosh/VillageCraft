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
 * Profession class for Mayor villagers.
 * Mayors sell leadership items and village management goods.
 */
public class MayorProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.GOLD_INGOT);

    public static String getName() { return "mayor"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the mayor trades");
        if (event.getType() == ModVillagerProfessions.MAYOR.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Administrative supplies
            trades.get(1).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.BOOK, 4),
                    8, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.INK_SAC, 8),
                    6, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.FEATHER, 16),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Leadership tools
            trades.get(2).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.CLOCK, 1),
                    4, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.GOLD_INGOT, 4),
                    new ItemStack(Items.EMERALD, 2),
                    8, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.COMPASS, 1),
                    4, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Village infrastructure
            trades.get(3).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.BELL, 1),
                    2, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.LODESTONE, 1),
                    2, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.NAME_TAG, 4),
                    4, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Governance
            trades.get(4).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.NETHER_STAR, 1),
                    1, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.BEACON, 1),
                    2, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND, 4),
                    new ItemStack(Items.EMERALD, 8),
                    6, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Legendary leadership
            trades.get(5).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 25),
                    new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                    1, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 30),
                    new ItemStack(Items.TOTEM_OF_UNDYING, 1),
                    1, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD_BLOCK, 8),
                    new ItemStack(Items.DRAGON_EGG, 1),
                    1, 100, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Mayor trades registered with 5 tiers");
        }
    }

    @SubscribeEvent
    public static void registerGoals(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
        // Mayor goals handled by VillagerCraftVillager
    }
}