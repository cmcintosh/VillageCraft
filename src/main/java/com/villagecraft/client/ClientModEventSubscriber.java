package com.villagecraft.client;

import com.villagecraft.client.renderer.VillageCraftVillagerRenderer;
import com.villagecraft.init.ModEntity;
import com.villagecraft.util.Reference;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Subscribe to events from the MOD EventBus that should be handled on the PHYSICAL CLIENT side in this class
 *
 * @author Cadiboo
 */
@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");

	/**
	 * Register entity renderers when the RegisterRenderers event is fired.
	 * This is the NeoForge 1.20.2 way to register entity renderers.
	 */
	@SubscribeEvent
	public static void onRegisterRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		// Register VillageCraftVillager renderer
		event.registerEntityRenderer(ModEntity.VILLAGECRAFT_VILLAGER.get(), VillageCraftVillagerRenderer::new);
		LOGGER.debug("Registered VillageCraftVillager Renderer");
	}

	/**
	 * Register layer definitions if needed for custom model layers
	 */
	@SubscribeEvent
	public static void onRegisterLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
		// Layer definitions go here if custom models are needed
		LOGGER.debug("Registered Layer Definitions");
	}

	/**
	 * FML Client Setup - called after registry events
	 */
	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
		LOGGER.debug("Client setup complete");
	}
}