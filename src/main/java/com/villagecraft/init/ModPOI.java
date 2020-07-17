package com.villagecraft.init;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ModPOI {

	public static final PointOfInterestType BARD = create("bard", ModBlocks.BLOCK_CHAIR.get(), 1, 1);
	
	private static PointOfInterestType create(String key, Block block, int arg2, int arg3) {
	    PointOfInterestType ret = null;
	    try {
	      Constructor<PointOfInterestType> ctr = PointOfInterestType.class.getDeclaredConstructor(new Class[] { String.class, Set.class, int.class, int.class });
	      ctr.setAccessible(true);
	      ret = ctr.newInstance(new Object[] { key, ImmutableSet.copyOf((Collection)block.getStateContainer().getValidStates()), Integer.valueOf(arg2), Integer.valueOf(arg3) });

	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    ret.getAllStates(block);
	    return ret;
	  }
	  
	  public static void register(IForgeRegistry<PointOfInterestType> registry) {
	    registry.register(BARD);
	    addStates(BARD);
	  }
	  
	  public static void addStates(PointOfInterestType type) {
	    try {
	      Method method = PointOfInterestType.class.getDeclaredMethod("func_221052_a", new Class[] { PointOfInterestType.class });
	      method.setAccessible(true);
	      method.invoke((Object)null, new Object[] { type });
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	  }
	  
}
