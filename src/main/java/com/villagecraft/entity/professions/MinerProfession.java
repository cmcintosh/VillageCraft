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
 * Profession class for Miner villagers.
 * Miners sell ores, minerals, and mining supplies.
 */
public class MinerProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.IRON_PICKAXE);

    public static String getName() { return "miner"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the miner trades");
        if (event.getType() == ModVillagerProfessions.MINER.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic minerals
            trades.get(1).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.IRON_ORE, 8),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COAL, 16),
                    new ItemStack(Items.EMERALD, 1),
                    16, 1, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.TORCH, 12),
                    12, 1, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Common ores
            trades.get(2).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COPPER_ORE, 12),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.LAPIS_LAZULI, 16),
                    new ItemStack(Items.EMERALD, 1),
                    8, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.IRON_PICKAXE, 1),
                    4, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Valuable ores
            trades.get(3).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.REDSTONE, 32),
                    new ItemStack(Items.EMERALD, 1),
                    12, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.GOLD_ORE, 6),
                    new ItemStack(Items.EMERALD, 1),
                    8, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.BUCKET, 1),
                    4, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Rare finds
            trades.get(4).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND_ORE, 2),
                    new ItemStack(Items.EMERALD, 6),
                    4, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.DIAMOND_PICKAXE, 1),
                    2, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.WATER_BUCKET, 1),
                    4, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Legendary mining goods
            trades.get(5).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.NETHERITE_SCRAP, 1),
                    2, 30, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.NETHERITE_PICKAXE, 1),
                    1, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.TNT, 4),
                    4, 20, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Miner trades registered with 5 tiers");
        }
    }
}
