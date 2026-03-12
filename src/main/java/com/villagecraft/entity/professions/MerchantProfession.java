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
 * resource trades for early village income. They buy raw materials from
 * players and sell basic building supplies.
 *
 * How to Create:
 * Give an unemployed villager a crafted Merchant Ledger.
 *
 * Economic Progression Chain:
 * - Merchant (Level 3) + Emerald Block → Trader
 *
 * The Merchant gives fair prices for basic resources. Level them up
 * quickly to access better trades as a Trader.
 *
 * Note: Merchants do NOT sell profession tokens. Those are crafted
 * by players and given directly to unemployed villagers.
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

            // Tier 1 (Novice) - Basic economic goods
            // Buy from players, sell basic supplies
            trades.get(1).addAll(Arrays.asList(
                // Buy oak logs (player gathering)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.OAK_LOG, 16),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
                ),
                // Buy wheat (player farming)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.WHEAT, 20),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                ),
                // Sell oak saplings (village expansion)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.OAK_SAPLING, 4),
                    12, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Building materials
            trades.get(2).addAll(Arrays.asList(
                // Buy cobblestone (player mining)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COBBLESTONE, 32),
                    new ItemStack(Items.EMERALD, 1),
                    16, 5, 0.05f
                ),
                // Buy leather (player hunting)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.LEATHER, 8),
                    new ItemStack(Items.EMERALD, 2),
                    6, 5, 0.05f
                ),
                // Sell arrows (defense)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.ARROW, 8),
                    8, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Village needs
            trades.get(3).addAll(Arrays.asList(
                // Buy string (spider drops)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STRING, 16),
                    new ItemStack(Items.EMERALD, 2),
                    8, 10, 0.05f
                ),
                // Sell torches (lighting)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.TORCH, 32),
                    12, 10, 0.05f
                ),
                // Sell bread (food)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.BREAD, 4),
                    8, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Better materials
            trades.get(4).addAll(Arrays.asList(
                // Buy wool (player shearing)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.WHITE_WOOL, 16),
                    new ItemStack(Items.EMERALD, 2),
                    8, 15, 0.05f
                ),
                // Sell glass (windows)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.GLASS, 8),
                    8, 15, 0.05f
                ),
                // Sell lanterns (decorative)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.LANTERN, 4),
                    6, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Advanced goods
            trades.get(5).addAll(Arrays.asList(
                // Buy copper (new resource)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COPPER_INGOT, 8),
                    new ItemStack(Items.EMERALD, 2),
                    8, 30, 0.05f
                ),
                // Sell redstone
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.REDSTONE, 16),
                    6, 30, 0.05f
                ),
                // Sell iron bars (decoration)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.IRON_BARS, 16),
                    4, 30, 0.05f
                ),
                // Sell minecarts (transport)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.MINECART, 1),
                    2, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Merchant trades registered with 5 tiers");
        }
    }
}
