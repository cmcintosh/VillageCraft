package com.villagecraft.gui;

import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.util.Reference;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VillageCenterScreen extends AbstractContainerScreen<VillageCenterContainer> {
	
	public VillageCenterScreen(VillageCenterContainer container, Inventory inv, Component title) {
		super(container, inv, Component.translatable("Village Info"));
	}
	
	@Override
	protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
		// TODO: Reimplement rendering for 1.20.2
		// New GuiGraphics API replaces PoseStack
	}
}