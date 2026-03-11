package com.villagecraft.gui;

import java.util.ArrayList;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.villagecraft.VillageCraft;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMessage;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screen.EditWorldScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Container;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


// AbstractContainerScreen<VillageCenterContainer>

@OnlyIn(Dist.CLIENT)
public class VillageCenterScreen extends AbstractContainerMenuScreen<VillageCenterContainer> {
	
	protected VillageCenterContainer container;
	protected Inventory playerInventory;
	
	// Backgrounds
	protected static final ResourceLocation TEXTURE = new ResourceLocation("vcm:textures/gui/container/village_info.png");
	protected static final ResourceLocation TEXTURE_BACKDROP = new ResourceLocation("vcm:textures/gui/container/inventory_backdrop.png");
	
	// Initial Positions
	private float mousePosx;
    private float mousePosY;
    
	protected int StartX = 20;
	protected int StartY = 40;
	
	// Padding for gui
	protected int PaddingX= 20;
	protected int PaddingY = 20;
	
	/**
	 * Labels for the UI
	 */
	protected TranslatableComponent nationLabel = Component.translatable("village_info.nation");
	protected TranslatableComponent villageNameLabel = Component.translatable("village_info.village_name");
	protected TranslatableComponent villagePopulationBeds = Component.translatable("village_info.population_beds");
	protected TranslatableComponent villageLevelValue = Component.translatable("village_info.level_value");
	protected EditBox villageNameTxtField;
	
	protected Button btnUpdate;
	protected Button btnPrevNation;
	protected Button btnNextNation;
	
	public VillageCenterScreen(VillageCenterContainer screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, Component.translatable("Village Info"));
		this.container = screenContainer;
		this.playerInventory = inv;	
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		
		int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
		this.villageNameTxtField = new EditBox(this.font, 8, 19, 101, 20, Component.translatable("village_info.village_name")); 
		
		this.blit(matrixStack, k, l, 0, 0, this.xSize, this.ySize);
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


/**
 * Examples:
 * protected TranslatableComponent nationLabel = Component.translatable("village_info.nation");
 * this.villageNameTxtField = new EditBox(this.font, StartX + 100, StartY + 60, 200, 20, Component.translatable("village_info.village_name")); 
 * btnUpdate = new Button(StartX + 100, StartY + 60, 200, 20, Component.translatable("village_info.update"), new EventVillageNameUpdate(this.container) ); 
 * this.addButton(btnUpdate); 
 */

