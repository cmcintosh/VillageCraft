package com.villagecraft.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.villagecraft.container.VillageCenterContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BaseVillageCraftScreen<T> extends ContainerScreen {
	
	protected Container container;
	protected PlayerInventory playerInventory;
	
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

	
	public BaseVillageCraftScreen(Container screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.container = screenContainer;
		this.playerInventory = inv;
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		this.renderBackground(matrixStack);
		this.mousePosx = x;
        this.mousePosY = y;
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.getMinecraft().getTextureManager().bindTexture(TEXTURE_BACKDROP);
        this.blit(matrixStack, k, l, 0, 0, this.xSize, this.ySize);
        this.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        this.blit(matrixStack, k, l, 0, 0, this.xSize, this.ySize);
	}
	
}
