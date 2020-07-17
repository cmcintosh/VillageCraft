package com.villagecraft.util;

import java.lang.reflect.Constructor;

import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;

public class TradeTypes {
	private static Constructor<?> ctr1;
	  
	private static Constructor<?> ctr2;
	  
	public static VillagerTrades.ITrade EmeraldForItemsTrade(Item item, int count, int maxUses, int xpValue) {
	    VillagerTrades.ITrade ret = null;
	    try {
	      ret = (VillagerTrades.ITrade)ctr1.newInstance(new Object[] { item, Integer.valueOf(count), Integer.valueOf(maxUses), Integer.valueOf(xpValue) });
	    } catch (Exception exception) {}
	    return ret;
	  }
	  
   public static VillagerTrades.ITrade ItemsForEmeraldsTrade(Item item, int cost, int count, int xpValue) {
     VillagerTrades.ITrade ret = null;
     try {
       ret = (VillagerTrades.ITrade)ctr2.newInstance(new Object[] { item, Integer.valueOf(cost), Integer.valueOf(count), Integer.valueOf(xpValue) });
     } catch (Exception exception) {}
     return ret;
   }
}
