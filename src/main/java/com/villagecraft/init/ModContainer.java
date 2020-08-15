package com.villagecraft.init;

import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.util.Reference;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainer {
	
	public static final DeferredRegister CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MODID);
	
	public static final RegistryObject<ContainerType<VillageCenterContainer>> VILLAGE_CENTER_CONTAINER = CONTAINER_TYPE.register("village_center", ()->{
		return new ContainerType(VillageCenterContainer::new);
	});
	
	 public static ContainerType register(ContainerType type, String name) {
        type.setRegistryName(name);
        return type;
    }
}
