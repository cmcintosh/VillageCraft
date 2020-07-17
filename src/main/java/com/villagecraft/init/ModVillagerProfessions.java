package com.villagecraft.init;

import com.villagecraft.VillageCraft;
import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.util.RandomTradeBuilder;
import com.villagecraft.util.Reference;

import net.minecraft.entity.merchant.villager.VillagerProfession;

import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;

import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.common.collect.ImmutableSet;
import net.minecraft.item.Items;


public class ModVillagerProfessions {

	public static final DeferredRegister<PointOfInterestType> POINTS_OF_INTEREST = DeferredRegister.create(ForgeRegistries.POI_TYPES, Reference.MODID);
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Reference.MODID);
	
	
	/**
	 * Points of interests
	 */
	public static final RegistryObject<PointOfInterestType> BARD_CHAIR = POINTS_OF_INTEREST.register("bard", () -> ModPOI.BARD );
	
	/**
	 * Villager Professions
	 */
	public static final RegistryObject<VillagerProfession> BARD = PROFESSIONS.register("bard", () -> BardProfession.villagerProfession("bard", BARD_CHAIR.get(), ImmutableSet.of(), ImmutableSet.of(ModBlocks.BLOCK_CHAIR.get()), null));
	
	
	
	
}


















