package com.villagecraft.professions.alchemy.api;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class AlchemyApiHelper {

	public boolean areItemsEqual(ItemStack s1, ItemStack s2) {
		return (s1.getItem() == s2.getItem());
	}
	
	public static void markRunicDirty(Entity entity) {
		AlchemyApi.internalMethods.markRunicDirty(entity);
	}
	
	public static boolean containsMatch(ArrayList<ItemStack> base, ItemStack input) {
		if (base.size() < 1) {
			return false;
		}
		for (ItemStack i : base) {
			if (i.getItem() == input.getItem()) {
				return true;
			}
		}
		return false;
	}
}
