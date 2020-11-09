package com.villagecraft.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class TexturableButton extends Button {

	protected ResourceLocation texture;
	
	public TexturableButton(int x, int y, int width, int height, ITextComponent title, IPressable pressedAction) {
		super(x, y, width, height, title, pressedAction);
		this.texture = this.WIDGETS_LOCATION;
	}
	
	public void setTexture(ResourceLocation texture) { }
	
	@Override
	public void renderButton(MatrixStack p_230431_1_, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
	      Minecraft minecraft = Minecraft.getInstance();
	      FontRenderer fontrenderer = minecraft.fontRenderer;
	      minecraft.getTextureManager().bindTexture(this.texture);
	      RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
	      int i = this.getYImage(this.isHovered());
	      RenderSystem.enableBlend();
	      RenderSystem.defaultBlendFunc();
	      RenderSystem.enableDepthTest();
	      this.blit(p_230431_1_, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
	      this.blit(p_230431_1_, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
	      this.renderBg(p_230431_1_, minecraft, p_230431_2_, p_230431_3_);
	      int j = getFGColor();
	      this.drawCenteredString(p_230431_1_, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
	   }
}
