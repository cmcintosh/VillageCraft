package com.villagecraft.entity;

import com.villagecraft.util.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BardVillagerRenderer extends MobRenderer<BardVillager, BardVillagerModel> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/bard.png");
	
	public BardVillagerRenderer(EntityRendererManager renderManagerIn, BardVillagerModel entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
	}

	public BardVillagerRenderer(EntityRendererManager manager) {
		// TODO Auto-generated constructor stub
		super(manager, new BardVillagerModel(1f), 1f);
	}

	@Override
	public ResourceLocation getEntityTexture(BardVillager entity) {
		return TEXTURE;
	}

}
