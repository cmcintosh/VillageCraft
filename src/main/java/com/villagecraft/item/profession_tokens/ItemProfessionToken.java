package com.villagecraft.item.profession_tokens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.world.item.Item;

public class ItemProfessionToken extends Item {
	public static Item.Properties properties = new Item.Properties().stacksTo(64);
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	
	public ItemProfessionToken(Item.Properties properties) {
		super(properties);
	}
}