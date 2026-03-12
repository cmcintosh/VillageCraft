package com.villagecraft.item;

import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class ItemLute extends SwordItem {
	public static Properties properties = new Properties().stacksTo(1);
	public static float attackSpeed = 0.3f;
	public static int damage = 10;
	
	
	public ItemLute(Tier tier, int attackDamageIn, float attackSpeedIn, Properties prop) {
		super(tier, attackDamageIn, attackSpeedIn, prop);
	}
}
