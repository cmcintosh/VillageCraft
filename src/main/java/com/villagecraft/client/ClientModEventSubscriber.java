package com.villagecraft.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

/**
 * Subscribe to events from the MOD EventBus that should be handled on the PHYSICAL CLIENT side in this class
 *
 * @author Cadiboo
 */
@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	
	/**
	 * We need to register our renderers on the client because rendering code does not exist on the server
	 * and trying to use it on a dedicated server will crash the game.
	 * <p>
	 * This method will be called by Forge when it is time for the mod to do its client-side setup
	 * This method will always be called after the Registry events.
	 * This means that all Blocks, Items, BlockEntityTypes, etc. will all have been registered already
	 */
	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {

		// Register BlockEntity Renderers
//	event.enqueueWork(() -> {
//		// Renderer registration using EntityRenderersEvent.RegisterRenderers
	//	});
		LOGGER.debug("Registered BlockEntity Renderers");

		// Register Entity Renderers
		LOGGER.debug("Registered Entity Renderers");

		// Register ContainerType Screens
		// Use EntityRenderersEvent.RegisterRenderers or RegisterEvent for these in 1.20.2
		LOGGER.debug("Registered ContainerType Screens");

	}
}