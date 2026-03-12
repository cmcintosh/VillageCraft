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
import java.util.Arrays;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

/**
 * Profession class for Worker villagers.
 *
 * Workers are the base labor force of the village. They perform essential
 * tasks like harvesting, hauling, and supply order fulfillment. Unlike
 * specialized professions, Workers can perform ANY basic task.
 *
 * How to Create:
 * Give an unemployed villager a crafted tool (e.g., Hammer, Wooden Pickaxe).
 *
 * Worker Abilities:
 * - Harvest resources (wood, stone, crops)
 * - Haul items between storage locations
 * - Fill supply orders for other professions
 * - Flee enemies and retreat to Town Hall during alerts
 *
 * Important:
 * Keep at least 2 villagers as Workers. If promoted to certain jobs,
 * villagers may lose the ability to gather or haul. Workers are essential
 * for basic village operations.
 *
 * Promotion Path:
 * Workers can be promoted to specialized professions using profession tokens:
 * - Worker + Miner Token → Miner
 * - Worker + Builder Token → Builder
 * - Worker + Farmer Token → Farmer
 * - etc.
 *
 * Workers are NOT part of the Leadership or Economic chains.
 * They are a separate, standalone profession.
 */
public class WorkerProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.WOODEN_PICKAXE);

    public static String getName() { return "worker"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the worker trades");
        if (event.getType() == ModVillagerProfessions.WORKER.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades =
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic resource trades
            // Workers gather raw materials
            trades.get(1).addAll(Arrays.asList(
                // Buy oak logs (gathered)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.OAK_LOG, 16),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
                ),
                // Buy cobblestone (mined)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COBBLESTONE, 32),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
                ),
                // Buy wheat (farmed)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.WHEAT, 20),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Processed materials
            trades.get(2).addAll(Arrays.asList(
                // Sell planks (processed)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.OAK_PLANKS, 12),
                    12, 5, 0.05f
                ),
                // Buy stone (smelted)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STONE, 24),
                    new ItemStack(Items.EMERALD, 1),
                    10, 5, 0.05f
                ),
                // Buy coal (fuel gathering)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COAL, 16),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Better resources
            trades.get(3).addAll(Arrays.asList(
                // Buy iron ore (mining)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.IRON_ORE, 8),
                    new ItemStack(Items.EMERALD, 2),
                    6, 10, 0.05f
                ),
                // Sell tools (crafted)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.IRON_SHOVEL, 1),
                    4, 10, 0.05f
                ),
                // Buy carrots (farming)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.CARROT, 22),
                    new ItemStack(Items.EMERALD, 1),
                    10, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Advanced resources
            trades.get(4).addAll(Arrays.asList(
                // Buy gold ore (rare mining)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.GOLD_ORE, 4),
                    new ItemStack(Items.EMERALD, 3),
                    4, 15, 0.05f
                ),
                // Sell iron ingots (smelted)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.IRON_INGOT, 2),
                    6, 15, 0.05f
                ),
                // Buy potatoes (farming)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.POTATO, 26),
                    new ItemStack(Items.EMERALD, 1),
                    10, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Master worker goods
            trades.get(5).addAll(Arrays.asList(
                // Sell diamonds (rare find)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.DIAMOND, 1),
                    2, 30, 0.05f
                ),
                // Sell iron tools (crafted)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.IRON_AXE, 1),
                    3, 30, 0.05f
                ),
                // Buy emeralds (rare finds)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.EMERALD_BLOCK, 1),
                    1, 30, 0.05f
                ),
                // Sell experience (teaching)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.EXPERIENCE_BOTTLE, 1),
                    3, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Worker trades registered with 5 tiers");
        }
    }
}
