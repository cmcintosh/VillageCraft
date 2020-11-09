package com.villagecraft.init;

import com.villagecraft.alchemy.container.AlchemistTableContainer;
import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.util.Reference;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainer {
	
	public static int VILLAGE_CENTER_GUI_ID = 0;
	
	public static final DeferredRegister CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MODID);
	
	public static RegistryObject<ContainerType<VillageCenterContainer>> VILLAGE_CENTER_CONTAINER = CONTAINER_TYPE.register("village_center", () -> IForgeContainerType.create(VillageCenterContainer::new));
	public static RegistryObject<ContainerType<AlchemistTableContainer>> ALCHEMIST_TABLE_CONTAINER = CONTAINER_TYPE.register("alchemist_table", () -> IForgeContainerType.create(AlchemistTableContainer::new));
	
	 public static ContainerType register(ContainerType type, String name) {
        type.setRegistryName(name);
        return type;
    }
}
