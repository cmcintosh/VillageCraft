package com.villagecraft.init;

import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.util.Reference;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

/**
 * Container registration for VillageCraft.
 * Handles MenuType registration for GUIs.
 * Updated for NeoForge 1.20.2
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainer {
	
	public static int VILLAGE_CENTER_GUI_ID = 0;
	
	public static final DeferredRegister<MenuType<?>> CONTAINER_TYPE = 
		DeferredRegister.create(Registries.MENU, Reference.MODID);
	
	/**
	 * VillageCenter Container registration.
	 * NeoForge 1.20.2: Use IMenuTypeExtension.create() with factory
	 */
	public static final DeferredHolder<MenuType<?>, MenuType<VillageCenterContainer>> VILLAGE_CENTER_CONTAINER = 
		CONTAINER_TYPE.register(
			"village_center",
			() -> IMenuTypeExtension.create(
				(net.neoforged.neoforge.network.IContainerFactory<VillageCenterContainer>) (windowId, inv, data) -> 
					new VillageCenterContainer(windowId, inv, data)
			)
		);
	
}
