package com.villagecraft.gui;

import java.util.ArrayList;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.villagecraft.VillageCraft;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.EditWorldScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.NBTTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


// ContainerScreen<VillageCenterContainer>

@OnlyIn(Dist.CLIENT)
public class VillageCenterScreen extends ContainerScreen<VillageCenterContainer> {
	
	protected VillageCenterContainer container;
	protected PlayerInventory playerInventory;
	
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
	protected TranslationTextComponent nationLabel = new TranslationTextComponent("village_info.nation");
	protected TranslationTextComponent villageNameLabel = new TranslationTextComponent("village_info.village_name");
	protected TranslationTextComponent villagePopulationBeds = new TranslationTextComponent("village_info.population_beds");
	protected TranslationTextComponent villageLevelValue = new TranslationTextComponent("village_info.level_value");
	protected TextFieldWidget villageNameTxtField;
	
	protected Button btnUpdate;
	protected Button btnPrevNation;
	protected Button btnNextNation;
	
	public VillageCenterScreen(VillageCenterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, new TranslationTextComponent("Village Info"));
		this.container = screenContainer;
		this.playerInventory = inv;	
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		
		int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
		this.villageNameTxtField = new TextFieldWidget(this.font, 8, 19, 101, 20, new TranslationTextComponent("village_info.village_name")); 
		
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
 * protected TranslationTextComponent nationLabel = new TranslationTextComponent("village_info.nation");
 * this.villageNameTxtField = new TextFieldWidget(this.font, StartX + 100, StartY + 60, 200, 20, new TranslationTextComponent("village_info.village_name")); 
 * btnUpdate = new Button(StartX + 100, StartY + 60, 200, 20, new TranslationTextComponent("village_info.update"), new EventVillageNameUpdate(this.container) ); 
 * this.addButton(btnUpdate); 
 */

