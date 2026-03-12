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
 * Profession class for Bassist villagers.
 * Bassists provide bass-tone buffs with defense and resistance focus.
 * Part of the Musical Ensemble system.
 */
public class BassistProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.JUKEBOX);

    public static String getName() { return "bassist"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the bassist trades");
        if (event.getType() == ModVillagerProfessions.BASSIST.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic strings
            trades.get(1).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STRING, 16),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.STICK, 8),
                    16, 1, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.OAK_PLANKS, 16),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Better materials
            trades.get(2).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.SPIDER_EYE, 8),
                    new ItemStack(Items.EMERALD, 2),
                    6, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.LEATHER, 6),
                    6, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.IRON_INGOT, 4),
                    new ItemStack(Items.EMERALD, 2),
                    8, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Quality strings
            trades.get(3).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.GOLD_INGOT, 4),
                    new ItemStack(Items.EMERALD, 3),
                    6, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.GLOWSTONE_DUST, 8),
                    6, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.REDSTONE_BLOCK, 2),
                    new ItemStack(Items.EMERALD, 4),
                    4, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Deep bass materials
            trades.get(4).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.LAPIS_BLOCK, 2),
                    new ItemStack(Items.EMERALD, 6),
                    4, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.DIAMOND, 1),
                    2, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.IRON_BLOCK, 1),
                    4, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Legendary resonance
            trades.get(5).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND_BLOCK, 1),
                    new ItemStack(Items.EMERALD, 12),
                    2, 30, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.AMETHYST_BLOCK, 4),
                    4, 30, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.NOTE_BLOCK, 16),
                    8, 100, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Bassist trades registered with 5 tiers");
        }
    }

    @SubscribeEvent
    public static void registerGoals(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
        // Bassist goals handled by BassistPerformGoal in entity.goal package
    }
}
