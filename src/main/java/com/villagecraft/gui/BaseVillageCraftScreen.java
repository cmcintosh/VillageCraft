package com.villagecraft.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;

// TODO: Reimplement base screen for 1.20.2
public class BaseVillageCraftScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
	
	public BaseVillageCraftScreen(T container, Inventory inv, Component title) {
		super(container, inv, title);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
		// TODO: Reimplement for 1.20.2
	}
}