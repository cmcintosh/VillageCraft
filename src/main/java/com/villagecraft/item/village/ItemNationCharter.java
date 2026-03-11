package com.villagecraft.item.village;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.client.util.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemGroup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.NonNullSupplier;


public class ItemNationCharter extends Item {
	
	public static Properties properties = new Properties().group(ItemGroup.MISC).maxStackSize(1);
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	
	public ItemNationCharter(Properties properties) {
		super(properties);
	}
	
	
	
}