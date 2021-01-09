package com.villagecraft.item.tool;

import com.villagecraft.init.ModItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class ItemWandCap extends Item {
	
	public static Properties properties = new Properties().group(ModItems.MAGIC_ITEMS).maxStackSize(64);
	protected String type;
	
	public ItemWandCap(Properties properties2, String type) {
		// TODO Auto-generated constructor stub
		super(properties2);
		this.type = type;
	}

}
