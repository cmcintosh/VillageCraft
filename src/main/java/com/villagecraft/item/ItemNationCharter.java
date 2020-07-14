package com.villagecraft.item;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.util.NonNullSupplier;


public class ItemNationCharter extends Item {
	
	public static Properties properties = new Properties().group(ItemGroup.MISC).maxStackSize(1);
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	
	public ItemNationCharter(Properties properties) {
		super(properties);
	}
	
	
	
}