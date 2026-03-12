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
 * Profession class for Manager villagers.
 *
 * The Manager unlocks the Village Manager block, enabling automatic profession
 * assignment with emeralds and village oversight features.
 *
 * Role:
 * - Unlocks Village Manager block for automatic profession token purchase
 * - View individual villager happiness levels
 * - Set working schedules for villagers
 * - Manage village-wide resource allocation
 * - Promoted from Level 3 Merchant with Manager Notebook
 *
 * Economic Progression Chain:
 * - Merchant (Level 3) + Manager Notebook → Manager
 * - Manager (Level 3) + Suppliers Manual → Outpost Liaison
 *
 * The Manager deals in organizational goods and administrative supplies.
 * Their trades focus on items useful for village management and automation.
 */
public class ManagerProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.BOOK);

    public static String getName() { return "manager"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the manager trades");
        if (event.getType() == ModVillagerProfessions.MANAGER.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades =
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic management supplies
            // Items for organization and record keeping
            trades.get(1).addAll(Arrays.asList(
                // Sell books (documentation)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.BOOK, 3),
                    12, 2, 0.05f
                ),
                // Sell paper (records)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.PAPER, 16),
                    10, 2, 0.05f
                ),
                // Buy ink sacs (record keeping supplies)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.INK_SAC, 8),
                    new ItemStack(Items.EMERALD, 2),
                    8, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Administrative tools
            // Better organization tools
            trades.get(2).addAll(Arrays.asList(
                // Sell bookshelves (for village library)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.BOOKSHELF, 2),
                    6, 5, 0.05f
                ),
                // Sell item frames (organization)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.ITEM_FRAME, 4),
                    8, 5, 0.05f
                ),
                // Buy leather (book binding)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.LEATHER, 6),
                    new ItemStack(Items.EMERALD, 2),
                    10, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Management tools
            // Tools for overseeing village operations
            trades.get(3).addAll(Arrays.asList(
                // Sell writable books (logbooks)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.WRITABLE_BOOK, 1),
                    6, 10, 0.05f
                ),
                // Sell clocks (time management)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.CLOCK, 1),
                    4, 10, 0.05f
                ),
                // Buy paper (continued documentation needs)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.PAPER, 32),
                    new ItemStack(Items.EMERALD, 3),
                    8, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Advanced management
            // Tools for complex village operations
            trades.get(4).addAll(Arrays.asList(
                // Sell named books (specialized manuals)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.WRITABLE_BOOK, 2),
                    4, 15, 0.05f
                ),
                // Sell ender chests (secure storage)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.ENDER_CHEST, 1),
                    2, 15, 0.05f
                ),
                // Sell maps (village planning)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.MAP, 2),
                    4, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Master management tools
            // Best tools for village administration
            trades.get(5).addAll(Arrays.asList(
                // Sell enchanted books (management mastery)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 18),
                    new ItemStack(Items.ENCHANTED_BOOK, 1),
                    3, 30, 0.05f
                ),
                // Sell beacons (village center piece)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 48),
                    new ItemStack(Items.BEACON, 1),
                    1, 30, 0.05f
                ),
                // Sell conduits (planning tool)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    new ItemStack(Items.CONDUIT, 1),
                    1, 30, 0.05f
                ),
                // Sell lodestone (waypoint management)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    new ItemStack(Items.LODESTONE, 1),
                    2, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Manager trades registered with 5 tiers");
        }
    }
}
