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

public class LandlordProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(
			Items.OAK_DOOR, Items.WHITE_BED, Items.CHEST);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public static final SoundEvent SOUND = SoundEvents.VILLAGER_WORK_LIBRARIAN;
	
	public LandlordProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
	}
	
	public static void registerTrades(VillagerTradesEvent event) { 
		if (ModVillagerProfessions.LANDLORD.get() == event.getType()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 2), 
					new ItemStack(Items.OAK_DOOR, 3), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3), 
					new ItemStack(Items.WHITE_BED, 1), 
					12, 2, 0.05f));
			
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.CHEST, 2), 
					16, 5, 0.05f));
			
			trades.get(2).add((entity, random) -> new MerchantOffer(
				new ItemStack(Items.EMERALD, 2), 
					new ItemStack(Items.BARREL, 1), 
					8, 5, 0.05f));
			
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 5), 
					new ItemStack(Items.FURNACE, 1), 
					6, 10, 0.05f));
			
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 4), 
					new ItemStack(Items.SMOKER, 1), 
					6, 10, 0.05f));
			
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 8), 
					new ItemStack(Items.BLAST_FURNACE, 1), 
					3, 15, 0.05f));
			
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 6), 
					new ItemStack(Items.COMPOSTER, 1), 
					4, 15, 0.05f));
			
			trades.get(5).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 10), 
					new ItemStack(Items.JUKEBOX, 1), 
					2, 30, 0.05f));
		}
	}
	
	public static void registerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager villager) {
			if (villager.getVillagerData().getProfession() == ModVillagerProfessions.LANDLORD.get()) {
				// TODO: Landlord goals
			}
		}
	}
}