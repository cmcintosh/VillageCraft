package com.villagecraft.init;

import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.util.Reference;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainer {
	
	public static int VILLAGE_CENTER_GUI_ID = 0;
	
	public static final DeferredRegister CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MODID);
	
	
	public static final RegistryObject<ContainerType<VillageCenterContainer>> VILLAGE_CENTER_CONTAINER = CONTAINER_TYPE.register("village_center", ()->{
		return new ContainerType(VillageCenterContainer::new);
	});
	
	public static VillageCenterContainer createContainerClientSide(int windowID, PlayerInventory playerInventory, net.minecraft.network.PacketBuffer extraData){
        return new VillageCenterContainer(windowID, playerInventory, extraData);
    }
	
	
	 public static ContainerType register(ContainerType type, String name) {
        type.setRegistryName(name);
        return type;
    }
}
