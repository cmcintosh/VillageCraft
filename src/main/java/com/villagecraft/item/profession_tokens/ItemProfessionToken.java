package com.villagecraft.item.profession_tokens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.world.item.Item;

public class ItemProfessionToken extends Item {
	public static Item.Properties properties = new Item.Properties().stacksTo(64);
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	private final String professionName;
	
	public ItemProfessionToken(Item.Properties properties, String professionName) {
		super(properties);
		this.professionName = professionName;
	}
	
	public String getProfessionName() {
		return professionName;
	}
}