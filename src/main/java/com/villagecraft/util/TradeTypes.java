package com.villagecraft.util;

import java.lang.reflect.Constructor;

import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.Item;

public class TradeTypes {
	private static Constructor<?> ctr1;
	  
	private static Constructor<?> ctr2;
	  
	public static ItemListing EmeraldForItemsTrade(Item item, int count, int maxUses, int xpValue) {
	    ItemListing ret = null;
	    try {
	      ret = (ItemListing)ctr1.newInstance(new Object[] { item, Integer.valueOf(count), Integer.valueOf(maxUses), Integer.valueOf(xpValue) });
	    } catch (Exception exception) {}
	    return ret;
	  }
	  
   public static ItemListing ItemsForEmeraldsTrade(Item item, int cost, int count, int xpValue) {
     ItemListing ret = null;
     try {
       ret = (ItemListing)ctr2.newInstance(new Object[] { item, Integer.valueOf(cost), Integer.valueOf(count), Integer.valueOf(xpValue) });
     } catch (Exception exception) {}
     return ret;
   }
}
