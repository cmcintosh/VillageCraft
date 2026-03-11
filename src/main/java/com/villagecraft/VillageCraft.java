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
import com.villagecraft.entity.vanilla.Golem;
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
import net.minecraft.world.entity.npc.VillagerTrades.ITrade;
import net.minecraft.world.entity.animal.Golem;
import net.minecraft.world.inventory.Container;
// TODO: MenuType import;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemModelsProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.MinecraftForge;
import net.neoforged.neoforge.common.VillagerTradingManager;
import net.neoforged.neoforge.event.AttachCapabilitiesEvent;
// Removed - use DeferredRegister;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.EntityJoinWorldEvent;
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
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.Registries;
import net.neoforged.neoforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MODID)
@Mod(Reference.MODID)
public class VillageCraft {

	public static final String MODID = Reference.MODID;
	public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
	public static VillageCraftData data = new VillageCraftData();


	public static VillageCraft instance;

	public static final CreativeModeTab VILLAGE_CRAFT = new CreativeModeTab(CreativeModeTab.Row.BOTTOM, 0)
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.TOWN_HALL.get());
        }
    };


	public VillageCraft() {
		LOGGER.debug("VillageCraft, building villages since 1902.");
		final ModLoadingContext modLoadingContext = ModLoadingContext.get();

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


		// Registering mod blocks for VillageCraft
		ModBlocks.BLOCKS.register(modEventBus);

		ModContainer.CONTAINER_TYPE.register(modEventBus);

		// Registering mod tile entity
		ModTiles.TILES.register(modEventBus);

		// Registering mod items for VillageCraft
		ModItems.ITEMS.register(modEventBus);

		// Registering the mod foods for VillageCraft
		ModFoods.ITEMS.register(modEventBus);

		// Registering the mod villager Points of interest
		ModVillagerProfessions.POINTS_OF_INTEREST.register(modEventBus);

		// Registering the villager professions
		ModVillagerProfessions.PROFESSIONS.register(modEventBus);

		ModEntity.ENTITY_TYPES.register(modEventBus);

		// Registering the villager trades
		MinecraftForge.EVENT_BUS.addListener(this::villagerTrades);
		MinecraftForge.EVENT_BUS.addListener(this::wandererTrades);
		MinecraftForge.EVENT_BUS.addListener(this::entityJoinWorldEvent);
		MinecraftForge.EVENT_BUS.addListener(this::onAttachCapabilitiesEvent);



		// Register GUI handlers
		MinecraftForge.EVENT_BUS.register(this);

		// EntityJoinWorldEvent
		this.LOGGER.debug(this.data.getName() + " Is created");
	}

	/**
	 * Register Capabilities hook.
	 */
	public void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> e) {
		if (e.getObject() instanceof VillagerEntity) {
			HungerProvider hProvider = new HungerProvider();
			e.addCapability(new ResourceLocation(Reference.MODID, "hunger"), hProvider);
			e.addListener(hProvider::invalidate);

			HonorProvider provider = new HonorProvider();
			e.addCapability(new ResourceLocation(Reference.MODID, "honor"), provider);
			e.addListener(provider::invalidate);


		}
	}

	/**
	 * Register all trades for villagers.
	 * @param event
	 */
    public void villagerTrades(VillagerTradesEvent event)
    {
    	BardProfession.RegisterVillagerTrades(event);
        WorkerProfession.RegisterVillagerTrades(event);
        MerchantProfession.RegisterVillagerTrades(event);
        TradesmanProfession.RegisterVillagerTrades(event);
    }


	/**
	 * Register all trades for wanderer.
	 * @param event
	 */
    public void wandererTrades(WandererTradesEvent event)
    {
        List<ITrade> genericList = event.getGenericTrades();
        RandomTradeBuilder.forEachWanderer((tradeBuild) -> genericList.add(tradeBuild.build()));

        List<ITrade> rareList = event.getRareTrades();
        RandomTradeBuilder.forEachWandererRare((tradeBuild) -> rareList.add(tradeBuild.build()));
    }


    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
  	  Entity entity = event.getEntity();
  	  	if (entity instanceof GolemEntity && !(entity instanceof Golem) ) {

  	  	}

        if (entity instanceof VillagerEntity) {
          VillagerEntity villager = (VillagerEntity)event.getEntity();

      	  if (!villager.level().isClientSide()) {
      		if (this.data.initialized == false) {
      			ServerLevel world = (ServerLevel) villager.level();
          		world.getDataStorage().computeIfAbsent(() -> { VillageCraft.data.setWorld(world); return VillageCraft.data; }, "VillageCraftData");
          		VillageCraft.data.initialize();
      		}

      		// all villagers need the base goal
      		villager.goalSelector.addGoal(1, new VillagerGoalBase(villager));
      		villager.goalSelector.addGoal(1, new VillagerHungerGoal(villager));

        	// Register goals for each villager type
        	TradesmanProfession.RegisterVillagerGoals(event);
        	WorkerProfession.RegisterVillagerGoals(event);
        	BardProfession.RegisterVillagerGoals(event);

      	  }
        }
    }

    protected BlockEntity refTE;
    public BlockEntity getRefrencedTE() {
        return refTE;
    }

    public void setRefrencedTE(BlockEntity te) {
    	this.refTE = te;
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    @OnlyIn(Dist.CLIENT)
    public static class ClientRegistryEvents {
    	@SuppressWarnings("deprecation")
		@SubscribeEvent
        public static void onClientSetupEvent(FMLClientSetupEvent event) {

            MenuScreens.register(
            		ModContainer.VILLAGE_CENTER_CONTAINER.get(), 
            		VillageCenterScreen::new
            );
            
            event.enqueueWork(() -> {
                ItemModelsProperties.func_239418_a_( 
                		ModItems.VILLAGE_CENTER.get(), 
                		new ResourceLocation(Reference.MODID, "location"), 
                		new ItemVillageCenter.LocationProperty()
                );
            });
            CapabilityVillagerAttribute.register();
        }
    }

}
