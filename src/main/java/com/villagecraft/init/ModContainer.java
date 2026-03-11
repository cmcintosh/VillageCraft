package com.villagecraft.init;

import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.util.Reference;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IForgeMenuType;
import net.neoforged.neoforge.eventbus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.RegistryObject;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainer {
	
	public static int VILLAGE_CENTER_GUI_ID = 0;
	
	public static final DeferredRegister<MenuType<?>> CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Reference.MODID);
	
	public static final RegistryObject<MenuType<VillageCenterContainer>> VILLAGE_CENTER_CONTAINER = CONTAINER_TYPE.register("village_center", 
			() -> IForgeMenuType.create(VillageCenterContainer::new));
}
