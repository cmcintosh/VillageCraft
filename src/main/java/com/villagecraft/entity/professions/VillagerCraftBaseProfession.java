package com.villagecraft.entity.professions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.VillageCraft;
import com.villagecraft.entity.goal.VillagerGoalBase;
import com.villagecraft.init.ModFoods;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.util.TradeTypes;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.registries.ForgeRegistries;


public class VillagerCraftBaseProfession  extends VillagerProfession {
	
	public VillagerProfession PROFESSION;
	public PointOfInterestType PONT_OF_INTEREST;
	
	/**
	 * Constructor for the profession
	 * @param nameIn
	 * @param pointOfInterestIn
	 * @param specificItemsIn
	 * @param relatedWorldBlocksIn
	 * @param soundIn
	 */
	public VillagerCraftBaseProfession(String nameIn, PointOfInterestType pointOfInterestIn,
			ImmutableSet<Item> specificItemsIn, ImmutableSet<Block> relatedWorldBlocksIn, SoundEvent soundIn) {
		super(nameIn, pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
		PONT_OF_INTEREST = pointOfInterestIn;
		VillageCraft.LOGGER.debug("Initialized Villager Profession for " + this.toString() );		
	}
	
	/**
	 * Register Trades for the profession
	 * @return Init2ObjectArrayMap
	 */
	public static Int2ObjectArrayMap getTrades() {
		VillageCraft.LOGGER.debug("Creating trades");
		Int2ObjectArrayMap int2ObjectArrayMap = new Int2ObjectArrayMap();
		VillagerTrades.ITrade[] value = { TradeTypes.EmeraldForItemsTrade(Items.EMERALD, 4, 8, 2), TradeTypes.ItemsForEmeraldsTrade(ModFoods.BEER.get(), 1, 1, 2) };
		int2ObjectArrayMap.put(1, value);
		return int2ObjectArrayMap;
	}
	
	/**
	 * Used to register the profession.
	 * @param String - profession name
	 * @param PointOfInterestType - Point of interest for the profession
	 * @param Item - Items for the profession
	 * @param Block - Blocks for the profession.
	 * @param SoundEvent - Sound event for the profession
	 * @return
	 */
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

	
	/**
	 * Register trades for custom professions.
	 */
	public static void RegisterVillagerTrades(VillagerTradesEvent event) { }
	
	
	
	
	/**
	 * Execute the goal for this Profession 
	 * @param villager
	 */
	public boolean shouldExecuteGoal(VillagerEntity villager) { 
		return false;
	}
	
	/**
	 * Should continue executing the goal
	 * @return
	 */
	public boolean shouldContinueExecutingGoal(VillagerEntity villager) { 
		return false;
	}
	
	/**
	 * Start executing the goal
	 * @return
	 */
	public void startExecutingGoal(VillagerEntity villager) { }

	/**
	 * execute a tick for goals.
	 * @param villager
	 */
	public void tickGoal(VillagerEntity villager) { }
}
