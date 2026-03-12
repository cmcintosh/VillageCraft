package com.villagecraft.entity.professions;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.init.ModItems;

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
 * Profession class for Trader (Tradesman) villagers.
 *
 * The Trader is the gateway to village economic activity.
 * They sell Profession Tokens/Spawn Eggs which enable villagers to select
 * professions from available workstations. Without a token, villagers
 * remain unemployed and will not select a profession.
 *
 * Token System:
 * - Villagers spawn as "Unemployed" (NONE profession)
 * - They will not select a profession until given a token
 * - Once given a token/spawn egg, they can claim matching workstations
 *
 * Tier System:
 * - Tier 1 (Novice): Basic worker tokens (Worker, Trader, Farmer)
 * - Tier 2 (Apprentice): Gathering professions (Miner, Alchemist, Beekeeper)
 * - Tier 3 (Journeyman): Service professions (Innkeeper, Caravaneer, Fisherman)
 * - Tier 4 (Expert): Leadership roles (Mayor, Landlord, Manager)
 * - Tier 5 (Master): Rare specialists (Brawler, Potter, etc.)
 *
 * Future: Village Manager will auto-purchase and assign tokens
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

            // Tier 1 (Novice) - Basic worker tokens
            trades.get(1).addAll(Arrays.asList(
                // Worker Token - Basic labor
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.WORKER_SPAWNER.get(), 1),
                    12, 2, 0.05f
                ),
                // Trader Token - Economic role
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ModItems.TRADER_TOKEN.get(), 1),
                    10, 2, 0.05f
                ),
                // Beekeeper Token - Animal husbandry
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(ModItems.BEEKEEPER_SPAWN_EGG.get(), 1),
                    14, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Gathering professions
            trades.get(2).addAll(Arrays.asList(
                // Miner Token - Resource gathering
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(ModItems.PROSPECTOR_PICKAXE.get(), 1),
                    8, 5, 0.05f
                ),
                // Caravaneer Token - Trade expeditions
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 14),
                    new ItemStack(ModItems.CARAVANEER_COMPASS.get(), 1),
                    6, 5, 0.05f
                ),
                // Fisherman Token - Food gathering
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ModItems.FISHERMAN_NET.get(), 1),
                    8, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Service professions
            trades.get(3).addAll(Arrays.asList(
                // Innkeeper Token - Hospitality
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 14),
                    new ItemStack(ModItems.INNKEEPER_SPAWN_EGG.get(), 1),
                    8, 10, 0.05f
                ),
                // Mayor Token - Village leadership
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(ModItems.MAYOR_BOOK.get(), 1),
                    4, 10, 0.05f
                ),
                // Landlord Token - Housing management
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.LANDLORD_SPAWN_EGG.get(), 1),
                    6, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Management roles
            trades.get(4).addAll(Arrays.asList(
                // Manager Token - Village administration
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    new ItemStack(ModItems.MANAGER_EGG.get(), 1),
                    3, 15, 0.05f
                ),
                // Pyrotechnic Token - Rare specialist
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 18),
                    new ItemStack(ModItems.PYROTECHNIC_CHARGE.get(), 1),
                    4, 15, 0.05f
                ),
                // Alchemist Token - Brewing profession
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.ALCHEMIST_PHIAL.get(), 1),
                    6, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Rare specialists using Paper as placeholders
            // These professions don't have custom items yet
            trades.get(5).addAll(Arrays.asList(
                // Builder Token - Construction (placeholder)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.PAPER, 1),
                    6, 30, 0.05f
                ),
                // Architect Token - Design (placeholder)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.PAPER, 1),
                    4, 30, 0.05f
                ),
                // Bard Token - Entertainment (placeholder)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 14),
                    new ItemStack(Items.PAPER, 1),
                    4, 30, 0.05f
                ),
                // Farmer Token - Agriculture (placeholder)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.PAPER, 1),
                    8, 30, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Trader trades registered with 5 tiers of profession tokens");
        }
    }
}
