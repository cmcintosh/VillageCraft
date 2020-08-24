package com.villagecraft.item.profession_tokens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Properties;

public class ItemProfessionToken extends Item {
	public static Properties properties = new Properties().group(ItemGroup.MISC).maxStackSize(64);
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	
	public ItemProfessionToken(Properties properties) {
		super(properties);
	}
}
