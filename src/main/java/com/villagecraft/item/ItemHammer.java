package com.villagecraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class ItemHammer extends SwordItem {
	
	public static Item.Properties properties = new Item.Properties().stacksTo(1);
	public static float attackSpeed = 0.3f;
	public static int damage = 10;
	
	
	public ItemHammer(Tier tier, int attackDamageIn, float attackSpeedIn, Item.Properties p_i48460_4_) {
		super(tier, attackDamageIn, attackSpeedIn, p_i48460_4_);
	}

}