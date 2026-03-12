package com.villagecraft.gui;

import com.villagecraft.VillageCraft;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.util.Reference;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * Village Center Screen - GUI for managing villages.
 * Shows village info, storage, and villager management.
 */
@OnlyIn(Dist.CLIENT)
public class VillageCenterScreen extends AbstractContainerScreen<VillageCenterContainer> {
	
	// GUI Texture - using generic container texture for now
	// TODO: Create custom texture at assets/villagecraft/textures/gui/village_center.png
	private static final ResourceLocation TEXTURE = 
			new ResourceLocation("minecraft", "textures/gui/container/generic_54.png");
	
	// Texture dimensions (standard 9x6 inventory texture is 176x222)
	private static final int TEXTURE_WIDTH = 176;
	private static final int TEXTURE_HEIGHT = 222;
	
	public VillageCenterScreen(VillageCenterContainer container, Inventory inv, Component title) {
		super(container, inv, title);
		this.titleLabelY = 6; // Title position
		this.inventoryLabelY = 74; // "Inventory" label position
	}
	
	@Override
	protected void init() {
		super.init();
		this.imageWidth = TEXTURE_WIDTH;
		this.imageHeight = TEXTURE_HEIGHT;
		this.leftPos = (this.width - this.imageWidth) / 2;
		this.topPos = (this.height - this.imageHeight) / 2;
		
		VillageCraft.LOGGER.debug("VillageCenterScreen initialized");
	}
	
	/**
	 * Render the GUI background
	 */
	@Override
	protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
		// Draw background texture
		graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		
		// Render village info panel (placeholder)
		// TODO: Draw custom elements here
	}
	
	/**
	 * Render foreground elements (tooltips, etc.)
	 */
	@Override
	protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
		super.renderLabels(graphics, mouseX, mouseY);
		
		// Draw village info
		String villageName = this.menu.getVillageName();
		graphics.drawString(this.font, villageName, 
				this.titleLabelX, this.titleLabelY, 4210752, false);
		
		// Draw villager count
		int villagerCount = this.menu.getVillagerCount();
		String villagerText = "Villagers: " + villagerCount;
		graphics.drawString(this.font, villagerText,
				this.titleLabelX, this.titleLabelY + 15, 4210752, false);
	}
	
	/**
	 * Render the entire screen
	 */
	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
		super.render(graphics, mouseX, mouseY, partialTick);
		this.renderTooltip(graphics, mouseX, mouseY);
	}
	
	/**
	 * Render tooltips when hovering over slots or special areas
	 */
	@Override
	protected void renderTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
		super.renderTooltip(graphics, mouseX, mouseY);
		
		// TODO: Add custom tooltips for village info areas
	}
}