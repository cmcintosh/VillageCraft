package com.villagecraft.item;

import net.minecraft.world.item.IItemTier;
import net.minecraft.world.item.ItemGroup;
import net.minecraft.world.item.SwordItem;

public class ItemHammer extends SwordItem {
	
	public static Properties properties = new Properties().group(ItemGroup.TOOLS).maxStackSize(1);
	public static float attackSpeed = 0.3f;
	public static int damage = 10;
	
	
	public ItemHammer(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties p_i48460_4_) {
		super(tier, attackDamageIn, attackSpeedIn, p_i48460_4_);
	}

}
