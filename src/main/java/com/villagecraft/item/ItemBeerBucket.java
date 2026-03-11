package com.villagecraft.item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.world.item.ItemGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

public class ItemBeerBucket extends Item {
	public static Properties properties = new Properties().group(ItemGroup.BREWING).maxStackSize(64);
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	
	public ItemBeerBucket(Properties properties) {
		super(properties);
	}

}
