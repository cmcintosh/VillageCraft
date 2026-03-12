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
 * Miner Profession - Resource gathering from underground.
 * Buys ores/stone, sells processed materials and tools.
 */
public class MinerProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(
			Items.IRON_PICKAXE, Items.STONE_PICKAXE, Items.TORCH);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public static final SoundEvent SOUND = SoundEvents.VILLAGER_WORK_TOOLSMITH;
	
	public MinerProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
	}
	
	public static void registerTrades(VillagerTradesEvent event) { 
		if (ModVillagerProfessions.MINER.get() == event.getType()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			
			// Level 1 - Buy raw stone
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.COBBLESTONE, 32), 
					new ItemStack(Items.EMERALD, 1), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.COAL, 16), 
					new ItemStack(Items.EMERALD, 1), 
					16, 2, 0.05f));
			
			// Level 2 - Buy ores
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.IRON_ORE, 12), 
					new ItemStack(Items.EMERALD, 1), 
					12, 5, 0.05f));
			
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.GOLD_ORE, 8), 
					new ItemStack(Items.EMERALD, 2), 
					8, 5, 0.05f));
			
			// Level 3 - Sell processed materials
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 2), 
					new ItemStack(Items.IRON_INGOT, 4), 
					12, 10, 0.05f));
			
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 4), 
					new ItemStack(Items.GOLD_INGOT, 2), 
					8, 10, 0.05f));
			
			// Level 4 - Rare ores
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.REDSTONE, 32), 
					new ItemStack(Items.EMERALD, 1), 
					12, 15, 0.05f));
			
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.LAPIS_LAZULI, 24), 
					new ItemStack(Items.EMERALD, 1), 
					12, 15, 0.05f));
			
			// Level 5 - Diamond access
			trades.get(5).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 8), 
					new ItemStack(Items.DIAMOND, 1), 
					4, 30, 0.05f));
		}
	}
	
	public static void registerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager villager) {
			if (villager.getVillagerData().getProfession() == ModVillagerProfessions.MINER.get()) {
				// TODO: Miner goals
			}
		}
	}
}