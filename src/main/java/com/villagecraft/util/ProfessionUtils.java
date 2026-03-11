package com.villagecraft.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.VillageCraft;
import com.villagecraft.entity.professions.VillagerCraftBaseProfession;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;

public class ProfessionUtils {
	
	// Helper function to register professions
	   public static VillagerProfession villagerProfession(String p1, PoiType p2, ImmutableSet<Item> p3, ImmutableSet<Block> p4, @Nullable SoundEvent p5) {
	       try
	       {
	    	   Constructor<VillagerCraftBaseProfession> c = VillagerCraftBaseProfession.class.getDeclaredConstructor(String.class, PoiType.class, ImmutableSet.class, ImmutableSet.class, SoundEvent.class);
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
