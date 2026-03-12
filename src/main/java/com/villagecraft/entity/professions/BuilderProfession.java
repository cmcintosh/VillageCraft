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
 * Profession class for Builder villagers.
 * Builders buy building materials and sell constructed items.
 */
public class BuilderProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.BRICK);

    public static String getName() { return "builder"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the builder trades");
        if (event.getType() == ModVillagerProfessions.BUILDER.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades = 
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic materials
            trades.get(1).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.COBBLESTONE, 32),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.OAK_LOG, 16),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.OAK_PLANKS, 16),
                    16, 1, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Processed materials
            trades.get(2).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STONE, 32),
                    new ItemStack(Items.EMERALD, 1),
                    16, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.CLAY_BALL, 32),
                    new ItemStack(Items.EMERALD, 1),
                    12, 5, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.SCAFFOLDING, 8),
                    8, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Constructed items
            trades.get(3).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.BRICKS, 16),
                    new ItemStack(Items.EMERALD, 1),
                    12, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.LADDER, 16),
                    8, 10, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.OAK_DOOR, 2),
                    8, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Quality builds
            trades.get(4).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STONE_BRICKS, 32),
                    new ItemStack(Items.EMERALD, 1),
                    12, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.BOOKSHELF, 2),
                    4, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.GLOWSTONE, 4),
                    6, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Blueprints and premium builds
            trades.get(5).addAll(java.util.Arrays.asList(
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.BEACON, 1),
                    2, 100, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.NETHER_BRICKS, 32),
                    new ItemStack(Items.EMERALD, 3),
                    8, 15, 0.05f
                ),
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.SHULKER_BOX, 1),
                    2, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Builder trades registered with 5 tiers");
        }
    }
}
