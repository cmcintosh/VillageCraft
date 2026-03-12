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
 * Profession class for Landlord villagers.
 * Landlords sell housing-related items.
 */
public class LandlordProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.OAK_DOOR);

    public static String getName() { return "landlord"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the landlord trades");
        if (event.getType() == ModVillagerProfessions.LANDLORD.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic housing
            trades.get(1).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.OAK_DOOR, 2),
                    8, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.WHITE_BED, 1),
                    6, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STONE_BRICKS, 32),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Comforts
            trades.get(2).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.FURNACE, 1),
                    4, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.CHEST, 4),
                    6, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.CRAFTING_TABLE, 1),
                    4, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Security
            trades.get(3).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.IRON_DOOR, 2),
                    4, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.IRON_BARS, 8),
                    4, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.OBSERVER, 2),
                    4, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Decoration
            trades.get(4).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.BOOKSHELF, 2),
                    4, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.ANVIL, 1),
                    2, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.ENCHANTING_TABLE, 1),
                    1, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Luxury housing
            trades.get(5).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.ENDER_CHEST, 1),
                    1, 30, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(Items.BEACON, 1),
                    2, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.SHULKER_BOX, 1),
                    2, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Landlord trades registered with 5 tiers");
        }
    }

    @SubscribeEvent
    public static void registerGoals(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
        // Landlord goals handled by VillagerCraftVillager
    }
}