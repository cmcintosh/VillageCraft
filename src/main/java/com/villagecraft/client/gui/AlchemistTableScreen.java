package com.villagecraft.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.villagecraft.container.AlchemistTableContainer;
import com.villagecraft.container.VillageCenterContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.Button.IPressable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;


public class AlchemistTableScreen extends ContainerScreen<AlchemistTableContainer>{
	
	protected AlchemistTableContainer container;
	protected PlayerInventory playerInventory;
	public int screenMode = 1; // 1, 2, 3
	
	// Backgrounds 
	protected static final ResourceLocation TEXTURE_CRAFTING = new ResourceLocation("vcm:textures/gui/container/alchemist_table_crafting.png");
	protected static final ResourceLocation TEXTURE_TASKS = new ResourceLocation("vcm:textures/gui/container/alchemist_table_tasks.png");
	protected static final ResourceLocation TEXTURE_JOB_BOARD = new ResourceLocation("vcm:textures/gui/container/alchemist_table_job_board.png");
	
	// Buttons
	protected static final ResourceLocation CRAFT_BN_ACTIVE = new ResourceLocation("vcm:textures/gui/button/alchemist_craft_btn_active.png");
	protected static final ResourceLocation CRAFT_BN_INACTIVE = new ResourceLocation("vcm:textures/gui/button/alchemist_craft_btn.png");
	
	protected static final ResourceLocation TASK_BN_ACTIVE = new ResourceLocation("vcm:textures/gui/button/alchemist_task_btn_active.png");
	protected static final ResourceLocation TASK_BN_INACTIVE = new ResourceLocation("vcm:textures/gui/button/alchemist_task_btn.png");
	
	protected static final ResourceLocation QUEST_BN_ACTIVE = new ResourceLocation("vcm:textures/gui/button/alchemist_quest_btn_active.png");
	protected static final ResourceLocation QUEST_BN_INACTIVE = new ResourceLocation("vcm:textures/gui/button/alchemist_quest_btn.png");
	
	// Initial Positions
	private float mousePosx;
    private float mousePosY;
    
	protected int StartX = 20;
	protected int StartY = 40;
	
	// Padding for gui
	protected int PaddingX= 20;
	protected int PaddingY = 20;
	
	// First tab button 125, 3	
	TexturableButton craftButton;
	TexturableButton taskButton;
	TexturableButton questButton;
	

	public AlchemistTableScreen(AlchemistTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.container = screenContainer;
		this.playerInventory = inv;
		
		
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		// TODO Auto-generated method stub
		this.renderBackground(matrixStack);
		
		
		this.mousePosx = x;
        this.mousePosY = y;
        
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        switch(this.screenMode) {
        	case 1:
        		this.getMinecraft().getTextureManager().bindTexture(this.TEXTURE_CRAFTING);
        	break;
        	case 2:
        		this.getMinecraft().getTextureManager().bindTexture(this.TEXTURE_TASKS);
        	break;
        	case 3:
        		this.getMinecraft().getTextureManager().bindTexture(this.TEXTURE_JOB_BOARD);
        	break;
        }
        this.blit(matrixStack, k, l, 0, 0, this.xSize, this.ySize);
        
	}
	
	@Override
	public void init() { 
		super.init();
	}
}
