package com.villagecraft.entity.professions;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModItems;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.ArrayList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

/**
 * Profession class for Tradesman villagers.
 * 
 * The Tradesman is the gateway to village economic activity.
 * They sell Tier 1 Profession Tokens which enable villagers to select
 * professions from available workstations. Without a token, villagers
 * will not select a profession.
 * 
 * Tier System:
 * - Tier 1 (Novice): Basic profession tokens (Worker, Trader, Farmer)
 * - Tier 2 (Apprentice): Common profession tokens (Builder, Miner, Alchemist)
 * - Tier 3 (Journeyman): Specialized tokens (Architect, Bard, Innkeeper)
 * - Tier 4 (Expert): Advanced tokens (Mayor, Landlord, Beekeeper)
 * - Tier 5 (Master): Rare profession tokens (All remaining professions)
 * 
 * Future: Village Manager will auto-purchase and assign tokens to villagers
 */
public class TradesmanProfession {

    public static String getName() { return "tradesman"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the tradesman trades");
        if (event.getType() == ModVillagerProfessions.TRADESMAN.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic profession tokens
            // Foundation professions for any village
            trades.get(1).addAll(java.util.Arrays.asList(
                // Worker Token - Most basic profession
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for worker token
                    12, 2, 0.05f
                ),
                // Trader Token - Economic profession
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for trader token
                    10, 2, 0.05f
                ),
                // Farmer Token - Food production
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for farmer token
                    14, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Common profession tokens
            // Professions for resource gathering and building
            trades.get(2).addAll(java.util.Arrays.asList(
                // Builder Token - Construction profession
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for builder token
                    8, 5, 0.05f
                ),
                // Miner Token - Resource gathering
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 14),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for miner token
                    6, 5, 0.05f
                ),
                // Alchemist Token - Brewing profession
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for alchemist token
                    4, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Specialized tokens
            // Cultural and entertainment professions
            trades.get(3).addAll(java.util.Arrays.asList(
                // Architect Token - Design and decoration
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for architect token
                    4, 10, 0.05f
                ),
                // Bard Token - Entertainment profession
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 18),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for bard token
                    4, 10, 0.05f
                ),
                // Innkeeper Token - Hospitality
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for innkeeper token
                    6, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Advanced profession tokens
            // Leadership and specialized gathering
            trades.get(4).addAll(java.util.Arrays.asList(
                // Mayor Token - Village leadership
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for mayor token
                    2, 15, 0.05f
                ),
                // Landlord Token - Housing management
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for landlord token
                    3, 15, 0.05f
                ),
                // Beekeeper Token - Animal husbandry
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 18),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for beekeeper token
                    4, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Rare profession tokens
            // Elite and specialized professions
            trades.get(5).addAll(java.util.Arrays.asList(
                // Singer Token - Musical performance
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for singer token
                    2, 30, 0.05f
                ),
                // Drummer Token - Rhythmic performance
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for drummer token
                    2, 30, 0.05f
                ),
                // Bassist Token - Bass performance
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for bassist token
                    2, 30, 0.05f
                ),
                // Caravaneer Token - Trade caravans
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 28),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for caravaneer token
                    2, 30, 0.05f
                ),
                // Brawler Token - Combat profession
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 30),
                    new ItemStack(net.minecraft.world.item.Items.PAPER, 1), // Placeholder for brawler token
                    2, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Tradesman trades registered with 5 tiers of profession tokens");
        }
    }
}
