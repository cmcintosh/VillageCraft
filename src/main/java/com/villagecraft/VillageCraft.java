package com.villagecraft;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.block.BlockChair;
import com.villagecraft.config.ModConfig;
import com.villagecraft.entity.BardVillager;
import com.villagecraft.entity.BardVillagerRenderer;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModFoods;
import com.villagecraft.init.ModItems;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.item.ItemNationCharter;
import com.villagecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(Reference.MODID)
public class VillageCraft {

	public static final String MODID = Reference.MODID;
	public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
	
	public static VillageCraft instance;
	
	
	public VillageCraft() { 
		LOGGER.debug("VillageCraft, building villages since 1902.");
		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		// Registering mod blocks for VillageCraft
		ModBlocks.BLOCKS.register(modEventBus);
		
		// Registering mod items for VillageCraft
		ModItems.ITEMS.register(modEventBus);
		
		// Registering the mod foods for VillageCraft
		ModFoods.ITEMS.register(modEventBus);
		
		// Registering the mod villager professions
		ModVillagerProfessions.POINTS_OF_INTEREST.register(modEventBus);
		ModVillagerProfessions.PROFESSIONS.register(modEventBus);
		
		modEventBus.addListener(ModVillagerProfessions::trades);
		
	}
	
}
