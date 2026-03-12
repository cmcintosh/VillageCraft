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
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

public class AlchemistProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(
			Items.GLASS_BOTTLE, Items.GUNPOWDER, Items.BLAZE_POWDER);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public static final SoundEvent SOUND = SoundEvents.BREWING_STAND_BREW;
	
	public AlchemistProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
	}
	
	public static void registerTrades(VillagerTradesEvent event) { 
		if (ModVillagerProfessions.ALCHEMIST.get() == event.getType()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			
			// Level 1 - Bottles and basics
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3), 
					new ItemStack(Items.GLASS_BOTTLE, 5), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3), 
					new ItemStack(Items.NETHER_WART, 5), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3), 
					new ItemStack(Items.REDSTONE, 10), 
					8, 2, 0.05f));
			
			// Level 2 - Brewing ingredients
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 4), 
					new ItemStack(Items.GLOWSTONE_DUST, 5), 
					12, 5, 0.05f));
			
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 4), 
					new ItemStack(Items.FERMENTED_SPIDER_EYE, 3), 
					8, 5, 0.05f));
			
			// Level 3 - Basic potions
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 6),
					PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HEALING),
					4, 10, 0.05f));
			
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 6),
					PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS),
					4, 10, 0.05f));
			
			// Level 4 - Intermediate potions
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 8),
					PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_SWIFTNESS),
					3, 15, 0.05f));
			
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 10),
					PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HEALING),
					3, 15, 0.05f));
			
			// Level 5 - High-tier ingredients
			trades.get(5).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 12), 
					new ItemStack(Items.GHAST_TEAR, 1), 
					4, 30, 0.05f));
			
			trades.get(5).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 15), 
					new ItemStack(Items.BLAZE_ROD, 2), 
					4, 30, 0.05f));
		}
	}
	
	public static void registerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager villager) {
			if (villager.getVillagerData().getProfession() == ModVillagerProfessions.ALCHEMIST.get()) {
				// TODO: Alchemist goals
			}
		}
	}
}