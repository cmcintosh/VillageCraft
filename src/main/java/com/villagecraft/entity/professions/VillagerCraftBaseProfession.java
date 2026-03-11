package com.villagecraft.entity.professions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.VillageCraft;
import com.villagecraft.entity.goal.VillagerGoalBase;
import com.villagecraft.entity.goal.VillagerGoalDeliverToStorage;
import com.villagecraft.init.ModFoods;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.util.TradeTypes;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;


/**
 * Base class for VillagerCraft professions.
 * Note: VillagerProfession is final in 1.20.1, so we use a factory pattern instead of extending.
 */
public class VillagerCraftBaseProfession {
	
	public VillagerProfession PROFESSION;
	public PoiType PONT_OF_INTEREST;
	
	public String name;
	public PoiType pointOfInterest;
	public ImmutableSet<Item> specificItems;
	public ImmutableSet<Block> relatedWorldBlocks;
	public SoundEvent sound;
	
	/**
	 * Constructor for the profession
	 * @param nameIn
	 * @param pointOfInterestIn
	 * @param specificItemsIn
	 * @param relatedWorldBlocksIn
	 * @param soundIn
	 */
	public VillagerCraftBaseProfession(String nameIn, PoiType pointOfInterestIn,
			ImmutableSet<Item> specificItemsIn, ImmutableSet<Block> relatedWorldBlocksIn, SoundEvent soundIn) {
		this.name = nameIn;
		this.pointOfInterest = pointOfInterestIn;
		this.specificItems = specificItemsIn;
		this.relatedWorldBlocks = relatedWorldBlocksIn;
		this.sound = soundIn;
		this.PONT_OF_INTEREST = pointOfInterestIn;
		VillageCraft.LOGGER.debug("Initialized Villager Profession for " + this.toString() );		
	}
	
	/**
	 * Register Trades for the profession
	 * @return Init2ObjectArrayMap
	 */
	public static Int2ObjectArrayMap getTrades() {
		VillageCraft.LOGGER.debug("Creating trades");
		Int2ObjectArrayMap int2ObjectArrayMap = new Int2ObjectArrayMap();
		ItemListing[] value = { TradeTypes.EmeraldForItemsTrade(Items.EMERALD, 4, 8, 2), TradeTypes.ItemsForEmeraldsTrade(ModFoods.BEER.get(), 1, 1, 2) };
		int2ObjectArrayMap.put(1, value);
		return int2ObjectArrayMap;
	}
	
	/**
	 * Used to register the profession.
	 * @param String - profession name
	 * @param PoiType - Point of interest for the profession
	 * @param Item - Items for the profession
	 * @param Block - Blocks for the profession.
	 * @param SoundEvent - Sound event for the profession
	 * @return
	 */
	public static VillagerProfession villagerProfession(String p1, PoiType p2, ImmutableSet<Item> p3, ImmutableSet<Block> p4, @Nullable SoundEvent p5) {
	       try
	       {
	    	   // Create using reflection since constructor is protected
	    	   Constructor<VillagerProfession> c = VillagerProfession.class.getDeclaredConstructor(String.class, PoiType.class, ImmutableSet.class, ImmutableSet.class, SoundEvent.class);
	           c.setAccessible(true);
	           VillagerProfession profession = c.newInstance(p1, p2, p3, p4, p5);
	           
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
	 * Register Goals for a profession.
	 */
	public static void RegisterVillagerGoals(EntityJoinLevelEvent event) { 
		if (event.getEntity() instanceof Villager) {
			Villager entity = (Villager)event.getEntity();
			VillagerGoalBase goal = new VillagerGoalBase(entity);
			entity.goalSelector.addGoal(1, goal);
		}
	}
}
