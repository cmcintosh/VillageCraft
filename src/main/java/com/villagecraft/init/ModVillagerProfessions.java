package com.villagecraft.init;

import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.entity.trade.RandomTradeBuilder;
import com.villagecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;

import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;

import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;


import com.google.common.collect.ImmutableSet;
import net.minecraft.item.Items;

/****
 * 
 * @author chris
 * Adding the following custom professions into minecraft for the mod:
 * 
 * Architect
 * Bard
 * Chef
 * Druid
 * Enchanter
 * Miner
 * Lumberjack
 * Carpenter
 * Teacher
 * Tradesman
 * Vendor
 * Engineer
 * Geomancer
 * Weaver
 * Guildmaster
 * Sapper
 * Mayor
 * Outpost Manager
 * Diplomat
 * Alchemist
 * Hunter
 * Rogue
 * Warlock
 * Mage
 * Undertaker
 * Potter
 * Nurse
 * Ranger
 * Herbalist
 * Caravan Master
 * Trapper
 * Guard
 * Knight
 * Footman
 * Archer
 * Brawler
 * Captain of the Guard
 * 
 */


public class ModVillagerProfessions {

	public static final DeferredRegister<PointOfInterestType> POINTS_OF_INTEREST = DeferredRegister.create(ForgeRegistries.POI_TYPES, Reference.MODID);
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Reference.MODID);
	
	/**
	 * Points of interests
	 */
	public static final RegistryObject<PointOfInterestType> BARD_CHAIR = POINTS_OF_INTEREST.register("chair", () -> ModVillagerProfessions.getPointOfInterestType("bard", ModBlocks.getAllStates(ModBlocks.BLOCK_CHAIR.get()), 1, 1) );
	
	/**
	 * Villager Professions
	 */
	public static final RegistryObject<VillagerProfession> BARD = PROFESSIONS.register("bard", () -> BardProfession.villagerProfession("bard", BARD_CHAIR.get(), ImmutableSet.of(), ImmutableSet.of(ModBlocks.BLOCK_CHAIR.get()), null));
	
	
	/**
	 * Villager Trades
	 */
	public static final void trades(VillagerTradesEvent event) { 
		
		if(event.getType() == ModVillagerProfessions.BARD.get())
        {
			event.getTrades().get(1).add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 16), new ItemStack(ModItems.VILLAGECRAFT_CHAIR.get()), 8, 10, 0F));
            RandomTradeBuilder.forEachLevel((level, tradeBuild) -> event.getTrades().get(level.intValue()).add(tradeBuild.build()));
        }
	}
	
	
	/**
	 * Helper to get points of interest.
	 */
    public static final PointOfInterestType getPointOfInterestType(String p1, Set<BlockState> p2, int p3, int p4) { 
	   try
       {
           //          Constructor<PointOfInterestType> c = (Constructor<PointOfInterestType>)PointOfInterestType.class.getDeclaredConstructors()[1];
           Constructor<PointOfInterestType> c = PointOfInterestType.class.getDeclaredConstructor(String.class, Set.class, Integer.TYPE, Integer.TYPE);
           c.setAccessible(true);
           return c.newInstance(p1, p2, p3, p4);
       }
       catch (NoSuchMethodException e)
       {
           e.printStackTrace();
       }
       catch (SecurityException e)
       {
           e.printStackTrace();
       }
       catch (InstantiationException e)
       {
           e.printStackTrace();
       }
       catch (IllegalAccessException e)
       {
           e.printStackTrace();
       }
       catch (IllegalArgumentException e)
       {
           e.printStackTrace();
       }
       catch (InvocationTargetException e)
       {
           e.printStackTrace();
       }
       
       return null;
   }
    
   
}


















