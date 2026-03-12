package com.villagecraft.entity.professions;

import java.util.List;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.init.ModVillagerProfessions;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

/**
 * Mayor Profession - Leadership and village management.
 * Handles high-value trades for village governance.
 */
public class MayorProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(
			Items.PAPER, Items.BOOK, Items.EMERALD);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public static final SoundEvent SOUND = SoundEvents.VILLAGER_WORK_CARTOGRAPHER;
	
	public MayorProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
	}
	
	public static void registerTrades(VillagerTradesEvent event) { 
		if (ModVillagerProfessions.MAYOR.get() == event.getType()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			
			// Level 1 - Basic documentation
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.PAPER, 24), 
					new ItemStack(Items.EMERALD, 1), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.PAPER, 12), 
					16, 2, 0.05f));
			
			// Level 2 - Village management items
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 8), 
					new ItemStack(Items.NAME_TAG, 2), 
					8, 5, 0.05f));
			
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.BOOK, 12), 
					new ItemStack(Items.EMERALD, 2), 
					8, 5, 0.05f));
			
			// Level 3 - Village expansion resources
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD_BLOCK, 2), 
					new ItemStack(Items.IRON_BLOCK, 1), 
					4, 10, 0.05f));
			
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.GOLD_INGOT, 16), 
					new ItemStack(Items.EMERALD, 2), 
					6, 10, 0.05f));
			
			// Level 4 - High-value leadership items
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 12), 
					new ItemStack(Items.ANVIL, 1), 
					3, 15, 0.05f));
			
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 16), 
					new ItemStack(Items.RESPAWN_ANCHOR, 1), 
					2, 15, 0.05f));
			
			// Level 5 - Ultimate village perks
			trades.get(5).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD_BLOCK, 16), 
					new ItemStack(Items.BEACON, 1), 
					2, 30, 0.05f));
			
			trades.get(5).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.NETHER_STAR, 1), 
					new ItemStack(Items.EMERALD_BLOCK, 8), 
					1, 30, 0.05f));
		}
	}
	
	public static void registerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager villager) {
			if (villager.getVillagerData().getProfession() == ModVillagerProfessions.MAYOR.get()) {
				// TODO: Mayor goals
			}
		}
	}
}