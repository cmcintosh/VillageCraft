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
 * Profession class for Innkeeper villagers.
 * Innkeepers sell food and hospitality items.
 */
public class InnkeeperProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.COOKED_BEEF);

    public static String getName() { return "innkeeper"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the innkeeper trades");
        if (event.getType() == ModVillagerProfessions.INNKEEPER.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic food
            trades.get(1).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.BREAD, 4),
                    12, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.POTATO, 6),
                    12, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.WHEAT, 18),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Cooked food
            trades.get(2).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.COOKED_BEEF, 3),
                    8, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.COOKED_CHICKEN, 4),
                    8, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.COOKED_PORKCHOP, 3),
                    6, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Meals
            trades.get(3).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.COOKED_RABBIT, 4),
                    6, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.COOKED_MUTTON, 4),
                    6, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.COOKED_COD, 6),
                    8, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Delicacies
            trades.get(4).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.COOKED_SALMON, 6),
                    6, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.PUMPKIN_PIE, 4),
                    4, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.CAKE, 1),
                    4, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Feast
            trades.get(5).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.GOLDEN_CARROT, 4),
                    4, 30, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                    1, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(Items.RABBIT_STEW, 1),
                    2, 20, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Innkeeper trades registered with 5 tiers");
        }
    }

    @SubscribeEvent
    public static void registerGoals(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
        // Innkeeper goals handled by VillagerCraftVillager
    }
}