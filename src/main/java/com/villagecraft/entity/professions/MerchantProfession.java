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
 * Profession class for Merchant villagers.
 *
 * The Merchant is the entry-level economic profession, providing basic
 * resource trades for early village income. They sell raw materials and
 * buy common items, but at less favorable prices than the Trader.
 *
 * Economic Progression:
 * - Merchant (Level 3) + Manager Notebook → Trader (via promotion)
 * - Trader (Level 3) + Diamond Block → Manager (via promotion)
 * - Manager → later progression chain
 *
 * The Merchant primarily sells basic resources needed for village construction
 * and maintenance. They are the starting point for the economic tree.
 */
public class MerchantProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.EMERALD);

    public static String getName() { return "merchant"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the merchant trades");
        if (event.getType() == ModVillagerProfessions.MERCHANT.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades =
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Very basic goods, higher prices
            // The merchant gives worse deals - that's why you level them up
            trades.get(1).addAll(Arrays.asList(
                // Sell saplings (village expansion)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.OAK_SAPLING, 4),
                    12, 2, 0.05f
                ),
                // Buy seeds (valuable to merchant)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.WHEAT_SEEDS, 24),
                    new ItemStack(Items.EMERALD, 1),
                    10, 2, 0.05f
                ),
                // Sell sticks (basic resource)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.STICK, 16),
                    8, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Better materials
            // Still basic, but useful for village building
            trades.get(2).addAll(Arrays.asList(
                // Sell sand (building material)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.SAND, 16),
                    10, 5, 0.05f
                ),
                // Buy leather (from village hunters)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.LEATHER, 8),
                    new ItemStack(Items.EMERALD, 2),
                    6, 5, 0.05f
                ),
                // Sell arrows (defense/hunting)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.ARROW, 8),
                    8, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Essential village needs
            // Torches, basic tools, and lanterns
            trades.get(3).addAll(Arrays.asList(
                // Sell torches (lighting)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.TORCH, 32),
                    12, 10, 0.05f
                ),
                // Buy wool (from village shepherds)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.WHITE_WOOL, 16),
                    new ItemStack(Items.EMERALD, 2),
                    8, 10, 0.05f
                ),
                // Sell lanterns (decorative lighting)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.LANTERN, 4),
                    6, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Better materials
            // Glass, iron bars, building blocks
            trades.get(4).addAll(Arrays.asList(
                // Sell glass (windows)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.GLASS, 8),
                    8, 15, 0.05f
                ),
                // Buy clay (from village gatherers)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.CLAY_BALL, 24),
                    new ItemStack(Items.EMERALD, 2),
                    6, 15, 0.05f
                ),
                // Sell iron bars (decoration/fortification)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.IRON_BARS, 16),
                    4, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Best merchant items
            // Still not as good as Trader, but reasonable
            trades.get(5).addAll(Arrays.asList(
                // Sell redstone (automation)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.REDSTONE, 16),
                    6, 30, 0.05f
                ),
                // Buy copper (new resource)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COPPER_INGOT, 8),
                    new ItemStack(Items.EMERALD, 2),
                    8, 30, 0.05f
                ),
                // Sell minecarts (transportation)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.MINECART, 1),
                    2, 30, 0.05f
                ),
                // Sell rail (village transport)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.RAIL, 32),
                    3, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Merchant trades registered with 5 tiers");
        }
    }
}
