package com.villagecraft.professions.alchemy.api.crafting;

import com.villagecraft.professions.alchemy.api.aspects.AspectList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public interface IArcaneRecipe extends IRecipe {

	boolean matches(IInventory inventory, World world, PlayerEntity player);
	
	AspectList getAspects();
	
	AspectList getAspects(IInventory inventory);
	
	String[] getResearch();
	
}
