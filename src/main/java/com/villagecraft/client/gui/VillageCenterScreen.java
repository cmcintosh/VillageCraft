package com.villagecraft.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.villagecraft.VillageCraft;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.tile.TileEntityVillageCenter;
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
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.NBTTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.entity.merchant.villager.VillagerEntity;

// ContainerScreen<VillageCenterContainer>

@OnlyIn(Dist.CLIENT)
public class VillageCenterScreen extends ContainerScreen<VillageCenterContainer> {
	
	protected VillageCenterContainer container;
	protected PlayerInventory playerInventory;
	protected Button upgradeVillage;
	
	// Backgrounds
	protected static final ResourceLocation TEXTURE = new ResourceLocation("vcm:textures/gui/container/village_center_ui.png");
	
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
	
	public VillageCenterScreen(VillageCenterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.container = screenContainer;
		
		this.playerInventory = inv;	
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
		super.drawGuiContainerForegroundLayer(matrixStack, x, y);
		UpgradeVillageAction action = new UpgradeVillageAction(this.container);
		upgradeVillage = new Button(this.getGuiLeft() + 44, this.getGuiTop() + 55, 58, 18, new StringTextComponent("Upgrade"), action);
		if (this.nextLevelRequirementsMet()) {
			this.addButton(upgradeVillage);	
		}
		
		// Village Level
		this.font.func_243248_b(matrixStack, new StringTextComponent("Level: " + this.getVillageLevel()), (float)this.titleX + 5, (float)this.titleY + 14, 4210752);
		this.font.func_243248_b(matrixStack, new StringTextComponent("Villagers: " + this.getVillagers()), (float)this.titleX + 5, (float)this.titleY + 36, 4210752);
	}
	
	/**
	 * Returns the text label for the village.
	 * @return
	 */
	protected String getVillageLevel() {
		int level = this.container.tile.getTileData().getInt(TileEntityVillageCenter.VILLAGE_LEVEL);
		switch (level) {
			default:
			case 0:
				return "Outpost";
			case 1:
				return "Settlement";
			case 2:
				return "Village";
			case 3:
				return "Town";
			case 4:
				return "County";
			case 5:
				return "City";
			case 6:
				return "Large City";
			case 7:
				return "Metropolis";
		}
	}
	
	/**
	 * Returns the village radius
	 * @return
	 */
	protected int getVillageRadius() {
		int level = this.container.tile.getTileData().getInt(TileEntityVillageCenter.VILLAGE_LEVEL);
		switch (level) {
			default:
			case 0:
				return 50;
			case 1:
				return 75;
			case 2:
				return 125;
			case 3:
				return 175;
			case 4:
				return 225;
			case 5:
				return 275;
			case 6:
				return 325;
			case 7:
				return 400;
		}
	}
	
	/**
	 * Check if the village meets the requirements to upgrade.
	 * @return
	 */
	protected boolean nextLevelRequirementsMet() {
		int level = this.container.tile.getTileData().getInt(TileEntityVillageCenter.VILLAGE_LEVEL);
		switch (level) {
			case 0:
				if ( this.getVillagers() > 15 && this.getEmeralds() > 50) {
					return true;
				}
				break;
			case 1:
				if ( this.getVillagers() > 25 && this.getEmeralds() > 125) {
					return true;
				}
				break;
			case 2:
				
				break;
			case 3:
				
				break;
		}
		
		return false;
	}
	
	protected int emeraldsForLevel() {
		int level = this.container.tile.getTileData().getInt(TileEntityVillageCenter.VILLAGE_LEVEL);
		switch(level) {
			default:
			case 0:
				return 50;
			case 1:
				return 125;
			case 2:
				return 250;
			case 3:
				return 400;
			case 4:
				return 500;
			case 5:
				return 750;
			case 6:
				return 1000;
			case 7:
				return 1500;
		}
	}
	
	/**
	 * Returns the number of emeralds in the village center inventory.
	 * @return
	 */
	protected int emeraldCount = 0;
	protected int getEmeralds() {
		emeraldCount = 0;
		this.container.getInventory().forEach(i -> {
			if (i.getItem() == net.minecraft.item.Items.EMERALD) { 
				emeraldCount += i.getCount();
			} else if (i.getItem() == net.minecraft.item.Items.EMERALD_BLOCK) {
				emeraldCount += (i.getCount() * 9);
			}
		});;
		return emeraldCount;
	}
	
	public int remainingEmeralds = 0;
	
	public void removeEmeraldsForUpgrade() {
		this.remainingEmeralds = this.getEmeralds();
		
		this.container.getInventory().forEach(i -> {
			if (i.getItem() == net.minecraft.item.Items.EMERALD) { 
				remainingEmeralds -= i.getCount();
			} else if (i.getItem() == net.minecraft.item.Items.EMERALD_BLOCK) {
				remainingEmeralds -= (i.getCount() * 9);
			}
			if (remainingEmeralds < 1) {
				return;
			}
		});
	}
	
	/**
	 * Returns the number of villagers within a radius
	 * @return
	 */
	protected int getVillagers() { 
		return this.findLivingEntitiesWithinAABB(this.container.tile.getPos()).size();
	}
	
	/**
	 * Locate a entity of type
	 */
	protected List findLivingEntitiesWithinAABB(BlockPos pos) { 
		World world = this.container.tile.getWorld();
		AxisAlignedBB boundingBox = new AxisAlignedBB(pos).grow(this.getVillageRadius(), 50, this.getVillageRadius());
		List<VillagerEntity> list = world.getEntitiesWithinAABB(VillagerEntity.class, boundingBox);
		return list;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		this.renderBackground(matrixStack);
		this.mousePosx = x;
        this.mousePosY = y;
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        
        
        this.blit(matrixStack, k, l, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	public void init() { 
		super.init();
	}
	
	class UpgradeVillageAction implements Button.IPressable {
		
		protected VillageCenterContainer container;
		
		public UpgradeVillageAction(VillageCenterContainer container2) {
			this.container = container;
		}
		
		@Override
		public void onPress(Button b) {
			// TODO Auto-generated method stub
			
		} 
	}
}


/**
 * Examples:
 * protected TranslationTextComponent nationLabel = new TranslationTextComponent("village_info.nation");
 * this.villageNameTxtField = new TextFieldWidget(this.font, StartX + 100, StartY + 60, 200, 20, new TranslationTextComponent("village_info.village_name")); 
 * btnUpdate = new Button(StartX + 100, StartY + 60, 200, 20, new TranslationTextComponent("village_info.update"), new EventVillageNameUpdate(this.container) ); 
 * this.addButton(btnUpdate); 
 */

