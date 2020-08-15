package com.villagecraft.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.villagecraft.container.VillageCenterContainer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VillageCenterGUI extends ContainerScreen<VillageCenterContainer> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("vcm:textures/gui/village_center.png");
	
	public VillageCenterGUI(VillageCenterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		// TODO Auto-generated constructor stub
	}

	public void func_230430_a_(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		this.func_230446_a_(p_230430_1_);
        super.func_230430_a_(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
        this.func_230459_a_(p_230430_1_, p_230430_2_, p_230430_3_);
	}
	
	public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, LivingEntity p_228187_5_) {
        float f = (float)Math.atan((double)(mouseX / 40.0F));
        float f1 = (float)Math.atan((double)(mouseY / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)posX, (float)posY, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.translate(0.0D, 0.0D, 1000.0D);
        matrixstack.scale((float)scale, (float)scale, (float)scale);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.multiply(quaternion1);
        matrixstack.rotate(quaternion);
        float f2 = p_228187_5_.renderYawOffset;
        float f3 = p_228187_5_.rotationYaw;
        float f4 = p_228187_5_.rotationPitch;
        float f5 = p_228187_5_.prevRotationYawHead;
        float f6 = p_228187_5_.rotationYawHead;
        p_228187_5_.renderYawOffset = 180.0F + f * 20.0F;
        p_228187_5_.rotationYaw = 180.0F + f * 40.0F;
        p_228187_5_.rotationPitch = -f1 * 20.0F;
        p_228187_5_.rotationYawHead = p_228187_5_.rotationYaw;
        p_228187_5_.prevRotationYawHead = p_228187_5_.rotationYaw;
        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getRenderManager();
        quaternion1.conjugate();
        entityrenderermanager.setCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.renderEntityStatic(p_228187_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, irendertypebuffer$impl, 15728880);
        });
        irendertypebuffer$impl.finish();
        entityrenderermanager.setRenderShadow(true);
        p_228187_5_.renderYawOffset = f2;
        p_228187_5_.rotationYaw = f3;
        p_228187_5_.rotationPitch = f4;
        p_228187_5_.prevRotationYawHead = f5;
        p_228187_5_.rotationYawHead = f6;
        RenderSystem.popMatrix();
    }

	@Override
	protected void func_230450_a_(MatrixStack p_230430_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		// TODO Auto-generated method stub
		 this.func_230446_a_(p_230430_1_);
	        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.field_230706_i_.getTextureManager().bindTexture(TEXTURE);
	        int i = (this.field_230708_k_ - this.xSize) / 2;
	        int j = (this.field_230709_l_ - this.ySize) / 2;

	        this.func_238474_b_(p_230430_1_, i, j, 0, 0, this.xSize, this.ySize);
	        
	        this.func_238474_b_(p_230430_1_, i + 29, j + 15, 0, 166, 24, 58);
	        drawGuiContainerForegroundLayer(p_230430_1_, p_230450_3_, p_230450_4_);
	}
	
	 protected void drawGuiContainerForegroundLayer(MatrixStack stackIn, int mouseX, int mouseY) {
	        String s = this.func_231171_q_().getString();
	        Minecraft.getInstance().fontRenderer.func_238405_a_(stackIn, s, this.xSize / 2 - Minecraft.getInstance().fontRenderer.getStringWidth(s) / 2, 5, 4210752);
	        Minecraft.getInstance().fontRenderer.func_238405_a_(stackIn, this.func_231171_q_().getString(), 8, this.ySize - 94 + 2, 4210752);
	        int screenW = (this.field_230708_k_ - this.xSize) / 2;
	        int screenH = (this.field_230709_l_ - this.ySize) / 2;
	        
	    }


}
