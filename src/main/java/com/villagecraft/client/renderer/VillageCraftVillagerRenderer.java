package com.villagecraft.client.renderer;

import com.villagecraft.entity.VillageCraftVillager;
import com.villagecraft.util.Reference;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;
import org.jetbrains.annotations.NotNull;

/**
 * Renderer for the VillageCraftVillager entity.
 * Extends VillagerRenderer to reuse vanilla villager rendering logic.
 */
public class VillageCraftVillagerRenderer extends VillagerRenderer {

	// Default texture location for VillageCraft villagers
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/villager/villagecraft_villager.png");

	public VillageCraftVillagerRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	@NotNull
	public ResourceLocation getTextureLocation(Villager entity) {
		// Return custom texture or parent's texture based on implementation
		// For now, using the default villager texture logic from parent
		return super.getTextureLocation(entity);
	}
}
