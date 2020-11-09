package com.villagecraft.client.gui;

import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
	 * This means that all Blocks, Items, TileEntityTypes, etc. will all have been registered already
	 */
	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {

		// Register TileEntity Renderers
//		ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.MINI_MODEL.get(), MiniModelTileEntityRenderer::new);
//		ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.ELECTRIC_FURNACE.get(), ElectricFurnaceTileEntityRenderer::new);
		LOGGER.debug("Registered TileEntity Renderers");

		// Register Entity Renderers
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WILD_BOAR.get(), WildBoarRenderer::new);
		LOGGER.debug("Registered Entity Renderers");

		// Register ContainerType Screens
		// ScreenManager.registerFactory is not safe to call during parallel mod loading so we queue it to run later
//		DeferredWorkQueue.runLater(() -> {
//			ScreenManager.registerFactory(ModContainerTypes.HEAT_COLLECTOR.get(), HeatCollectorScreen::new);
//			ScreenManager.registerFactory(ModContainerTypes.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
//			ScreenManager.registerFactory(ModContainerTypes.MOD_FURNACE.get(), ModFurnaceScreen::new);
//			LOGGER.debug("Registered ContainerType Screens");
//		});

	}
}
