package com.villagecraft;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.block.BlockChair;
import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.capabilities.CapabilityVillagerAttribute;
import com.villagecraft.capabilities.HonorProvider;
import com.villagecraft.capabilities.HungerProvider;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.data.VillageCraftData;
import com.villagecraft.entity.goal.HealGolemGoal;
import com.villagecraft.entity.goal.VillagerGoalBase;
import com.villagecraft.entity.goal.VillagerGoalGotoVillageCenter;
import com.villagecraft.entity.goal.VillagerHungerGoal;
import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.entity.professions.MerchantProfession;
import com.villagecraft.entity.professions.TradesmanProfession;
import com.villagecraft.entity.professions.WorkerProfession;
import com.villagecraft.gui.RenderVillageCenter;
import com.villagecraft.gui.VillageCenterScreen;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModContainer;
import com.villagecraft.init.ModEntity;
import com.villagecraft.init.ModFoods;
import com.villagecraft.init.ModItems;
import com.villagecraft.init.ModTiles;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.item.blockitems.ItemVillageCenter;
import com.villagecraft.item.village.ItemNationCharter;
import com.villagecraft.util.RandomTradeBuilder;
import com.villagecraft.util.Reference;


import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.VillagerTradingManager;
import net.neoforged.neoforge.event.AttachCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.DeferredWorkQueue;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLPaths;

@Mod.EventBusSubscriber(modid = Reference.MODID)
@Mod(Reference.MODID)
public class VillageCraft {

	public static final String MODID = Reference.MODID;
	public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
	public static VillageCraftData data = new VillageCraftData();

	public VillageCraft() {
		// Register the setup method for modloading
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);
		
		// Register ourselves for server and other game events we are interested in
		NeoForge.EVENT_BUS.register(this);
	
		ModItems.ITEMS.register(modEventBus);
		ModBlocks.BLOCKS.register(modEventBus);
		ModTiles.TILES.register(modEventBus);
		ModVillagerProfessions.PROFESSIONS.register(modEventBus);
		ModVillagerProfessions.POINTS_OF_INTEREST.register(modEventBus);
		ModContainer.CONTAINER_TYPE.register(modEventBus);
		ModEntity.ENTITIES.register(modEventBus);
		ModFoods.ITEMS.register(modEventBus);
	}
	
	private void setup(final ServerStartingEvent event) {
		// some preinit code
		// LOGGER.info("HELLO FROM PREINIT");
		// LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
		
		
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		// do something that can only be done on the client
		// LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
		MenuScreens.register(ModContainer.VILLAGE_CENTER_CONTAINER.get(), VillageCenterScreen::new);
	}


	// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		// Registration is now done via DeferredRegisters
	}

	
}
