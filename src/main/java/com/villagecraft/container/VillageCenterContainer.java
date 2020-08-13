package com.villagecraft.container;

import com.villagecraft.tile.TileEntityVillageCenter;
import com.villagecraft.util.Reference;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class VillageCenterContainer extends Container {

	private static ContainerType<?> containerType;

	public VillageCenterContainer(int id, PlayerEntity player, TileEntityVillageCenter villageCenter) {
		super(containerType, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}

	
}
