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
 * Profession class for Trader villagers.
 *
 * The Trader unlocks the Auction House block and provides better trade prices
 * than the Merchant. They are the second tier in the economic progression.
 *
 * Economic Progression:
 * - Merchant (Level 3) + Manager Notebook → Manager
 * - Manager (Level 3) + Suppliers Manual → Outpost Liaison
 *
 * The Trader sells profession tokens and advanced economic goods.
 * Higher tier traders offer better deals and rarer items.
 */
public class TraderProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.EMERALD);

    public static String getName() { return "trader"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the trader trades");
        if (event.getType() == ModVillagerProfessions.TRADER.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades =
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic economic goods
            // Raw resources and common items for early village needs
            trades.get(1).addAll(Arrays.asList(
                // Sell wheat (village food supply)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.WHEAT, 8),
                    16, 2, 0.05f
                ),
                // Buy logs (village building materials)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.OAK_LOG, 16),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                ),
                // Sell bread (prepared food)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.BREAD, 4),
                    8, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Refined goods
            // Better prices than Merchant, processed materials
            trades.get(2).addAll(Arrays.asList(
                // Buy stone (building material)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STONE, 32),
                    new ItemStack(Items.EMERALD, 2),
                    10, 5, 0.05f
                ),
                // Sell iron ingots (critical resource)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.IRON_INGOT, 2),
                    6, 5, 0.05f
                ),
                // Buy coal (fuel)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COAL, 16),
                    new ItemStack(Items.EMERALD, 2),
                    8, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Advanced goods
            // Tools, books, and specialty items
            trades.get(3).addAll(Arrays.asList(
                // Sell name tags (useful for organization)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.NAME_TAG, 1),
                    4, 10, 0.05f
                ),
                // Sell bookshelves (enchantment support)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.BOOKSHELF, 1),
                    4, 10, 0.05f
                ),
                // Buy gold ingots (premium material)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.GOLD_INGOT, 4),
                    new ItemStack(Items.EMERALD, 4),
                    4, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - High-value items
            // Rare materials and powerful tools
            trades.get(4).addAll(Arrays.asList(
                // Sell diamonds
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    new ItemStack(Items.DIAMOND, 1),
                    2, 15, 0.05f
                ),
                // Sell anvil (tool repair)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 18),
                    new ItemStack(Items.ANVIL, 1),
                    2, 15, 0.05f
                ),
                // Buy emeralds (currency exchange)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND, 1),
                    new ItemStack(Items.EMERALD, 8),
                    4, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Exceptional trades
            // Rare and powerful items, auction house access
            trades.get(5).addAll(Arrays.asList(
                // Sell enchanted golden apples
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 48),
                    new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                    1, 30, 0.05f
                ),
                // Sell netherite scraps
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    new ItemStack(Items.NETHERITE_SCRAP, 1),
                    1, 30, 0.05f
                ),
                // Sell dragon breath (rare brewing ingredient)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 36),
                    new ItemStack(Items.DRAGON_BREATH, 1),
                    2, 30, 0.05f
                ),
                // Sell enchanted books (knowledge)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.ENCHANTED_BOOK, 1),
                    2, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Trader trades registered with 5 tiers");
        }
    }
}
