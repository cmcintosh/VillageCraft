package com.villagecraft.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class ItemHammer extends SwordItem {
	
	public static Properties properties = new Properties().group(ItemGroup.TOOLS).maxStackSize(1);
	public static float attackSpeed = 0.3f;
	public static int damage = 10;
	
	
	public ItemHammer(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties p_i48460_4_) {
		super(tier, attackDamageIn, attackSpeedIn, p_i48460_4_);
	}

}
