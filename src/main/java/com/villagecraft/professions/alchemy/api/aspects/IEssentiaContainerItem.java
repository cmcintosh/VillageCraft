package com.villagecraft.professions.alchemy.api.aspects;

import net.minecraft.item.ItemStack;

public interface IEssentiaContainerItem {

	AspectList getAspects(ItemStack itemStack);
	
	void setAspects(ItemStack itemStack, AspectList aspectList);
	
	boolean ignoreContainedAspects();
}
