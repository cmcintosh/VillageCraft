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

public class BuilderProfession extends VillagerCraftBaseProfession {
	
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(
			Items.BRICKS, Items.STONE_BRICKS, Items.OAK_PLANKS);
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of();
	public static final SoundEvent SOUND = SoundEvents.VILLAGER_WORK_MASON;
	
	public BuilderProfession(String nameIn, PoiType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
	}
	
	public static void registerTrades(VillagerTradesEvent event) { 
		if (ModVillagerProfessions.BUILDER.get() == event.getType()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.BRICKS, 10), 
					16, 2, 0.05f));
			
			trades.get(1).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.STONE_BRICKS, 10), 
					16, 2, 0.05f));
			
			trades.get(2).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 1), 
					new ItemStack(Items.OAK_PLANKS, 16), 
					16, 5, 0.05f));
			
			trades.get(3).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 2), 
					new ItemStack(Items.GLASS, 12), 
					12, 10, 0.05f));
			
			trades.get(4).add((entity, random) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 3), 
					new ItemStack(Items.LANTERN, 4), 
					8, 15, 0.05f));
		}
	}
	
	public static void registerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager villager) {
			if (villager.getVillagerData().getProfession() == ModVillagerProfessions.BUILDER.get()) {
				// TODO: Builder goals
			}
		}
	}
}
