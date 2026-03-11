package com.villagecraft.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class BaseVillageCraftScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
	
	protected T container;
	protected Inventory playerInventory;
	
	// Backgrounds
	protected static ResourceLocation TEXTURE = new ResourceLocation("vcm:textures/gui/container/village_info.png");
	protected static final ResourceLocation TEXTURE_BACKDROP = new ResourceLocation("vcm:textures/gui/container/inventory_backdrop.png");
	
	// Initial Positions
	private float mousePosx;
    private float mousePosY;
    
	protected int StartX = 20;
	protected int StartY = 40;
	
	// Padding for gui
	protected int PaddingX= 20;
	protected int PaddingY = 20;

	
	public BaseVillageCraftScreen(T screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
		this.container = screenContainer;
		this.playerInventory = inv;
	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
		this.renderBackground(matrixStack);
		this.mousePosx = x;
        this.mousePosY = y;
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, TEXTURE_BACKDROP);
        this.blit(matrixStack, k, l, 0, 0, this.imageWidth, this.imageHeight);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(matrixStack, k, l, 0, 0, this.imageWidth, this.imageHeight);
	}
	
}
