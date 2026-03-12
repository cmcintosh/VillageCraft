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
 * How to Create:
 * Promote a Level 3 Merchant by giving them a Gold Ingot.
 *
 * Economic Progression Chain:
 * - Merchant (Level 3) + Gold Ingot → Trader
 *
 * The Trader sells valuable goods and materials not available from
 * the basic Merchant. They also enable access to the Auction House.
 *
 * Note: Traders do NOT sell profession tokens. Those are crafted
 * by players using recipes and given directly to unemployed villagers.
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

            // Tier 1 (Novice) - Better than Merchant
            // Valued resources and processed goods
            trades.get(1).addAll(Arrays.asList(
                // Buy spruce logs (better wood)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.SPRUCE_LOG, 14),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
                ),
                // Sell wheat (bulk food)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.WHEAT, 16),
                    12, 2, 0.05f
                ),
                // Sell string (crafting)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.STRING, 12),
                    10, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Processed materials
            trades.get(2).addAll(Arrays.asList(
                // Buy iron nuggets (ore processing)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.IRON_NUGGET, 36),
                    new ItemStack(Items.EMERALD, 2),
                    8, 5, 0.05f
                ),
                // Sell iron ingots (smelted)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.IRON_INGOT, 2),
                    6, 5, 0.05f
                ),
                // Sell wool (colored)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.WHITE_WOOL, 8),
                    8, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Tools and books
            trades.get(3).addAll(Arrays.asList(
                // Sell books (knowledge)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.BOOK, 3),
                    6, 10, 0.05f
                ),
                // Sell compass (navigation)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.COMPASS, 1),
                    3, 10, 0.05f
                ),
                // Buy amethyst (crystal)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.AMETHYST_SHARD, 8),
                    new ItemStack(Items.EMERALD, 4),
                    6, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Valuable goods
            trades.get(4).addAll(Arrays.asList(
                // Sell gold ingots
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.GOLD_INGOT, 2),
                    4, 15, 0.05f
                ),
                // Sell name tags (organization)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.NAME_TAG, 1),
                    2, 15, 0.05f
                ),
                // Sell clock (valuable)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.CLOCK, 1),
                    2, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Rare items
            trades.get(5).addAll(Arrays.asList(
                // Sell diamond (rare)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.DIAMOND, 1),
                    2, 30, 0.05f
                ),
                // Sell horse armor
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.IRON_HORSE_ARMOR, 1),
                    1, 30, 0.05f
                ),
                // Sell saddle (transport)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.SADDLE, 1),
                    2, 30, 0.05f
                ),
                // Sell enchanted book
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 18),
                    new ItemStack(Items.ENCHANTED_BOOK, 1),
                    2, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Trader trades registered with 5 tiers");
        }
    }
}
