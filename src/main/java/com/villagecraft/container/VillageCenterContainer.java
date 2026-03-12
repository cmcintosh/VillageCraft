package com.villagecraft.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

// TODO: Reimplement container for 1.20.2
public class VillageCenterContainer extends AbstractContainerMenu {
	
	public VillageCenterContainer(int id, Inventory inv, final BlockEntity tile) {
		super(null, id); // MenuType is null - needs registration
		// TODO: Reimplement container slots
	}
}