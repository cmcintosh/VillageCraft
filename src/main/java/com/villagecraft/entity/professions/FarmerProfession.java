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
 * Farmer Profession - Agricultural villager for food production.
 * Buys crops, sells food and seeds.
 */
public class FarmerProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(
			Items.WHEAT, Items.CARROT, Items.POTATO, Items.BEETROOT);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public static final SoundEvent SOUND = SoundEvents.VILLAGER_WORK_FARMER;
	
	public FarmerProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
	}
	
	/**
	 * Register Farmer trades
	 */
	public static void registerTrades(VillagerTradesEvent event) { 
		if (ModVillagerProfessions.FARMER.get() == event.getType()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			
			// Level 1 - Buy crops
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.WHEAT, 20), 
					new ItemStack(Items.EMERALD, 1), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.POTATO, 26), 
					new ItemStack(Items.EMERALD, 1), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.CARROT, 22), 
					new ItemStack(Items.EMERALD, 1), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.BEETROOT, 15), 
					new ItemStack(Items.EMERALD, 1), 
					16, 2, 0.05f));
			
			// Level 2 - Sell seeds
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.WHEAT_SEEDS, 8), 
					16, 5, 0.05f));
			
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.CARROT, 4), 
					8, 5, 0.05f));
			
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.POTATO, 4), 
					8, 5, 0.05f));
			
			// Level 3 - Sell bread and food
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.BREAD, 6), 
					12, 10, 0.05f));
			
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3), 
					new ItemStack(Items.PUMPKIN_PIE, 4), 
					8, 10, 0.05f));
			
			// Level 4 - Golden crops
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3), 
					new ItemStack(Items.GOLDEN_CARROT, 3), 
					8, 15, 0.05f));
			
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 4), 
					new ItemStack(Items.GLISTERING_MELON_SLICE, 3), 
					8, 15, 0.05f));
			
			// Level 5 - Cake
			trades.get(5).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 10), 
					new ItemStack(Items.CAKE, 1), 
					4, 30, 0.05f));
		}
	}
	
	public static void registerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager villager) {
			if (villager.getVillagerData().getProfession() == ModVillagerProfessions.FARMER.get()) {
				// TODO: Farmer-specific goals
				// - Till farmland
				// - Plant seeds
				// - Harvest crops
				// - Deliver to storage
			}
		}
	}
}