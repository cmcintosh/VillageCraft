package com.villagecraft.item.village;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import net.minecraft.network.chat.Component;

public class ItemNationCharter extends Item {
	
	public static Properties properties = new Properties().stacksTo(1);
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	
	public ItemNationCharter(Properties properties) {
		super(properties);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
	}

}