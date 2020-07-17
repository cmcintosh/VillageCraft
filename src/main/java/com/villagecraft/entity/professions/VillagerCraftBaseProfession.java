package com.villagecraft.entity.professions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModFoods;
import com.villagecraft.util.TradeTypes;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.block.Block;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;


public class VillagerCraftBaseProfession  extends VillagerProfession {
	
	public VillagerProfession PROFESSION;
	
	public VillagerCraftBaseProfession(String nameIn, PointOfInterestType pointOfInterestIn,
			ImmutableSet<Item> specificItemsIn, ImmutableSet<Block> relatedWorldBlocksIn, SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
		
		
		VillageCraft.LOGGER.debug("Initialized Villager Profession for CustomProfession");
		
	}
	
	// Setup trades for our professions
	public void setupTrades() {
		VillageCraft.LOGGER.debug("Setting up Villager Profession trades for " + this.getRegistryName().toString());
		Int2ObjectArrayMap int2ObjectArrayMap = new Int2ObjectArrayMap();
		VillagerTrades.ITrade[] value = { TradeTypes.EmeraldForItemsTrade(Items.EMERALD, 4, 8, 2), TradeTypes.ItemsForEmeraldsTrade(ModFoods.BEER.get(), 1, 1, 2) };
		int2ObjectArrayMap.put(1, value);
		VillagerTrades.VILLAGER_DEFAULT_TRADES.put(this.PROFESSION, int2ObjectArrayMap);
	}
	
	// Helper function to register professions
	   public static VillagerProfession villagerProfession(String p1, PointOfInterestType p2, ImmutableSet<Item> p3, ImmutableSet<Block> p4, @Nullable SoundEvent p5) {
	       try
	       {
	    	   Constructor<VillagerCraftBaseProfession> c = VillagerCraftBaseProfession.class.getDeclaredConstructor(String.class, PointOfInterestType.class, ImmutableSet.class, ImmutableSet.class, SoundEvent.class);
	           c.setAccessible(true);
	           VillagerCraftBaseProfession profession = c.newInstance(p1, p2, p3, p4, p5);
	           return profession;
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
