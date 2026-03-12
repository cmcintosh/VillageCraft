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
 * Profession class for Caravaneer villagers.
 *
 * The Caravaneer unlocks the Caravan Station block, enabling trade routes
 * between villages. They specialize in long-distance trade goods and
 * maintain llamas for caravan transport.
 *
 * Role:
 * - Enables inter-village trade routes via Caravan Station
 * - Tames and maintains llamas for transport
 * - Deals in regional specialties and exotic goods
 * - Specializes in bulk goods transport
 *
 * This profession allows villages to specialize and trade with each other
 * rather than requiring every village to have every profession.
 */
public class CaravaneerProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.LEAD);

    public static String getName() { return "caravaneer"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the caravaneer trades");
        if (event.getType() == ModVillagerProfessions.CARAVANEER.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades =
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic caravan supplies
            // Items needed to start trading
            trades.get(1).addAll(Arrays.asList(
                // Sell leads (for llamas)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.LEAD, 2),
                    8, 2, 0.05f
                ),
                // Buy leather (pack material)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.LEATHER, 8),
                    new ItemStack(Items.EMERALD, 3),
                    10, 2, 0.05f
                ),
                // Sell chests (for storage)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Items.CHEST, 2),
                    6, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Llamas and transport
            // The caravaneer's specialty - llamas
            trades.get(2).addAll(Arrays.asList(
                // Sell carpets (decoration)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.WHITE_CARPET, 8),
                    8, 5, 0.05f
                ),
                // Buy hay bales (llama food)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.HAY_BLOCK, 2),
                    new ItemStack(Items.EMERALD, 4),
                    6, 5, 0.05f
                ),
                // Sell saddles (transport)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.SADDLE, 1),
                    4, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Regional specialties
            // Goods from different biomes/regions
            trades.get(3).addAll(Arrays.asList(
                // Sell cooked meat (travel food)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(Items.COOKED_BEEF, 8),
                    10, 10, 0.05f
                ),
                // Sell paper (trade documentation)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.PAPER, 12),
                    8, 10, 0.05f
                ),
                // Buy string (rope material)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.STRING, 16),
                    new ItemStack(Items.EMERALD, 2),
                    8, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Valuable transport goods
            // Better items for established caravans
            trades.get(4).addAll(Arrays.asList(
                // Sell bundles (inventory management)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.BUNDLE, 1),
                    4, 15, 0.05f
                ),
                // Sell horse armor (protection)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.IRON_HORSE_ARMOR, 1),
                    2, 15, 0.05f
                ),
                // Buy rabbit hide (premium leather)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.RABBIT_HIDE, 8),
                    new ItemStack(Items.EMERALD, 3),
                    6, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Master trader goods
            // Best items for caravan masters
            trades.get(5).addAll(Arrays.asList(
                // Sell diamond horse armor
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    new ItemStack(Items.DIAMOND_HORSE_ARMOR, 1),
                    1, 30, 0.05f
                ),
                // Sell name tags (for organizing animals)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.NAME_TAG, 2),
                    4, 30, 0.05f
                ),
                // Sell spyglass (navigation)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.SPYGLASS, 1),
                    2, 30, 0.05f
                ),
                // Transport-oriented enchanted books would go here
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    new ItemStack(Items.ENCHANTED_BOOK, 1),
                    2, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Caravaneer trades registered with 5 tiers");
        }
    }
}
