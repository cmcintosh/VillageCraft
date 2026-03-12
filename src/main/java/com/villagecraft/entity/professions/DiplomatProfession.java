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
 * Profession class for Diplomat villagers.
 *
 * The Diplomat unlocks the Embassy block, enabling inter-village relations
 * and diplomacy mechanics. They are the highest tier of the political tree.
 *
 * Role:
 * - Unlocks Embassy block for diplomatic functions
 * - Define relations with other villages and players
 * - Declare wars to attempt takeover of other villages
 * - Manages gifts and tribute between nations
 * - Promoted from Level 3 Caravaneer with Diamond Block
 *
 * Political Progression Chain:
 * - Start: Basic village professions
 * - Level 3 Caravaneer + Diamond Block → Diplomat
 * - Diplomat manages inter-village relations
 *
 * The Diplomat deals in rare, high-value items that demonstrate
 * wealth and status appropriate for diplomatic functions.
 */
public class DiplomatProfession {

    public static final ItemStack PROFESSION_ICON = new ItemStack(Items.WRITTEN_BOOK);

    public static String getName() { return "diplomat"; }

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillageCraft.LOGGER.debug("Registering the diplomat trades");
        if (event.getType() == ModVillagerProfessions.DIPLOMAT.get()) {
            @SuppressWarnings("unchecked")
            Int2ObjectMap<java.util.List<VillagerTrades.ItemListing>> trades =
                event.getTrades();

            // Ensure all tier lists exist
            for (int i = 1; i <= 5; i++) {
                if (!trades.containsKey(i)) {
                    trades.put(i, new ArrayList<>());
                }
            }

            // Tier 1 (Novice) - Basic diplomatic goods
            // Stationery and basic gifts
            trades.get(1).addAll(Arrays.asList(
                // Sell paper (diplomatic correspondence)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.PAPER, 24),
                    12, 2, 0.05f
                ),
                // Sell books (records/treaties)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(Items.BOOK, 4),
                    8, 2, 0.05f
                ),
                // Buy flowers (diplomatic gifts)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.POPPY, 12),
                    new ItemStack(Items.EMERALD, 2),
                    10, 2, 0.05f
                )
            ));

            // Tier 2 (Apprentice) - Formal gifts
            // Items suitable for diplomatic exchange
            trades.get(2).addAll(Arrays.asList(
                // Sell banners (heraldry)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(Items.WHITE_BANNER, 2),
                    6, 5, 0.05f
                ),
                // Sell paintings (cultural exchange)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.PAINTING, 2),
                    4, 5, 0.05f
                ),
                // Buy glowstone dust (decoration)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.GLOWSTONE_DUST, 16),
                    new ItemStack(Items.EMERALD, 3),
                    8, 5, 0.05f
                )
            ));

            // Tier 3 (Journeyman) - Rare gifts
            // Prestige items for important negotiations
            trades.get(3).addAll(Arrays.asList(
                // Sell clocks (valuable gifts)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.CLOCK, 1),
                    4, 10, 0.05f
                ),
                // Sell music discs (cultural items)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(Items.MUSIC_DISC_13, 1),
                    3, 10, 0.05f
                ),
                // Buy amethyst (valuable crystal)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.AMETHYST_SHARD, 8),
                    new ItemStack(Items.EMERALD, 4),
                    6, 10, 0.05f
                )
            ));

            // Tier 4 (Expert) - Prestige items
            // Gifts fit for royalty and major treaties
            trades.get(4).addAll(Arrays.asList(
                // Sell enchanted golden apples (ultimate gift)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 64),
                    new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                    2, 15, 0.05f
                ),
                // Sell totems (rare protective items)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 48),
                    new ItemStack(Items.TOTEM_OF_UNDYING, 1),
                    1, 15, 0.05f
                ),
                // Buy echo shards (ancient items)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.ECHO_SHARD, 2),
                    new ItemStack(Items.EMERALD, 8),
                    4, 15, 0.05f
                )
            ));

            // Tier 5 (Master) - Master diplomat items
            // The finest goods for managing kingdoms
            trades.get(5).addAll(Arrays.asList(
                // Sell dragon heads (trophy)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 48),
                    new ItemStack(Items.DRAGON_HEAD, 1),
                    1, 30, 0.05f
                ),
                // Sell shulker shells (exclusive storage)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    new ItemStack(Items.SHULKER_SHELL, 1),
                    2, 30, 0.05f
                ),
                // Sell ancient debris scraps (rare)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 40),
                    new ItemStack(Items.NETHERITE_SCRAP, 1),
                    2, 30, 0.05f
                ),
                // Sell elytra (exceptional gift)
                (e, r) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 64),
                    new ItemStack(Items.ELYTRA, 1),
                    1, 100, 0.05f
                )
            ));

            VillageCraft.LOGGER.debug("Diplomat trades registered with 5 tiers");
        }
    }
}
