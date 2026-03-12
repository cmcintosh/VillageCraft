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
 * Profession class for Outpost Liaison villagers.
 *
 * The Outpost Liaison unlocks the Village Job Board block, enabling
 * job order creation and automated task assignment to villagers.
 *
 * Role:
 * - Unlocks Village Job Board block for creating job orders
 * - Processes orders placed by players before villager assignment
 * - Assigns tasks to villagers with appropriate skills
 * - Manages outpost connections and resource requisitions
 * - Promoted from Level 3 Manager with Suppliers Manual
 *
 * Economic Progression Chain:
 * - Merchant (Level 3) + Manager Notebook → Manager
 * - Manager (Level 3) + Suppliers Manual → Outpost Liaison
 *
 * The Outpost Liaison deals in logistical supplies and task management items.
 * Their trades focus on goods needed for coordination and task assignment.
 */
public class OutpostLiaisonProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.OAK_SIGN);

    public static String getName() { return "outpost_liaison"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the outpost liaison trades");
        if (event.getType() == ModVillagerProfessions.OUTPOST_LIASON.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades =
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic logistics
            // Signs, tools for marking tasks
            trades.get(1).addAll(Arrays.asList(
                // Sell oak signs (job posting)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.OAK_SIGN, 6),
                    12, 2, 0.05f
                ),
                // Sell paper (job orders)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.PAPER, 16),
                    10, 2, 0.05f
                ),
                // Buy feathers (for writing)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.FEATHER, 16),
                    new ItemStack(Items.EMERALD, 2),
                    8, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Task tools
            // Tools for organizing work
            trades.get(2).addAll(Arrays.asList(
                // Sell hanging signs (advanced posting)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.OAK_HANGING_SIGN, 4),
                    8, 5, 0.05f
                ),
                // Sell item frames (task boards)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.ITEM_FRAME, 4),
                    8, 5, 0.05f
                ),
                // Buy sticks (basic tools)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STICK, 32),
                    new ItemStack(Items.EMERALD, 2),
                    10, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Coordination tools
            // Maps, compasses for logistics
            trades.get(3).addAll(Arrays.asList(
                // Sell compasses (navigation)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.COMPASS, 1),
                    4, 10, 0.05f
                ),
                // Sell empty maps (planning)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.MAP, 2),
                    6, 10, 0.05f
                ),
                // Buy redstone (coordination circuits)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.REDSTONE, 16),
                    new ItemStack(Items.EMERALD, 3),
                    8, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Advanced coordination
            // Better tools for complex logistics
            trades.get(4).addAll(Arrays.asList(
                // Sell recovery compasses (advanced navigation)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.RECOVERY_COMPASS, 1),
                    2, 15, 0.05f
                ),
                // Sell writable books (task manuals)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.WRITABLE_BOOK, 2),
                    4, 15, 0.05f
                ),
                // Buy copper (communication)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COPPER_INGOT, 12),
                    new ItemStack(Items.EMERALD, 4),
                    6, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Master logistics
            // Best coordination tools
            trades.get(5).addAll(Arrays.asList(
                // Sell name tags (task assignment)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.NAME_TAG, 2),
                    4, 30, 0.05f
                ),
                // Sell ender chests (secure coordination)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.ENDER_CHEST, 1),
                    2, 30, 0.05f
                ),
                // Sell bundles (logistics)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.BUNDLE, 1),
                    3, 30, 0.05f
                ),
                // Sell spyglass (reconnaissance)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.SPYGLASS, 1),
                    2, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Outpost Liaison trades registered with 5 tiers");
        }
    }
}
