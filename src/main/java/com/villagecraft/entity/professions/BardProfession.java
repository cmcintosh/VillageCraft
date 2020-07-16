package com.villagecraft.entity.professions;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;

public class BardProfession extends VillagerProfession {
	
	// @TODO create something specific to the bard here ...
	
	public static final PointOfInterestType POINT_OF_INTEREST = new PointOfInterestType("chair", getAllStates(ModBlocks.BLOCK_CHAIR.get()), 1, 1).setRegistryName("chair");       
	public static final ImmutableSet<Item> PROFESSION_ITEM = ImmutableSet.of(ModItems.BEER_BUCKET.get());
	public static final ImmutableSet<Block> PROFESSION_BLOCK = ImmutableSet.of(ModBlocks.BLOCK_CHAIR.get());
	
	public BardProfession(String nameIn, PointOfInterestType pointOfInterestIn, 
			ImmutableSet<Item> specificItemsIn,
			ImmutableSet<Block> relatedWorldBlocksIn, 
			SoundEvent soundIn) {
			super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
			
	}
	
	// Get all states for a provided block.
	public static Set<BlockState> getAllStates(Block block) { 
		return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
	}
	

}
