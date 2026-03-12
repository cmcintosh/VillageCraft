package com.villagecraft.item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.world.item.Item;

public class ItemHeart extends Item {
	
	public static Properties properties = new Properties().stacksTo(64);
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	
	public ItemHeart(Properties properties) {
		super(properties);
	}
}