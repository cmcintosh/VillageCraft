package com.villagecraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.capabilities.CapabilityVillagerAttribute;
import com.villagecraft.capabilities.HonorProvider;
import com.villagecraft.capabilities.HungerProvider;
import com.villagecraft.data.VillageCraftData;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModContainer;
import com.villagecraft.init.ModEntity;
import com.villagecraft.init.ModFoods;
import com.villagecraft.init.ModItems;
import com.villagecraft.init.ModTiles;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.util.Reference;
import com.villagecraft.entity.professions.WorkerProfession;
import com.villagecraft.entity.professions.TraderProfession;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AttachCapabilitiesEvent;
// TODO: Import when AI goals are fixed
// import com.villagecraft.entity.goal.VillagerGoalBase;
// import com.villagecraft.entity.goal.VillagerHungerGoal;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = Reference.MODID)
@Mod(Reference.MODID)
public class VillageCraft {

	public static final String MODID = Reference.MODID;
	public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
	public static VillageCraftData data = new VillageCraftData();

	public static VillageCraft instance;

	public VillageCraft() {
		LOGGER.debug("VillageCraft, building villages since 1902.");
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		// Registering mod blocks for VillageCraft
		ModBlocks.BLOCKS.register(modEventBus);
		ModContainer.CONTAINER_TYPE.register(modEventBus);
		ModTiles.TILES.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);
		ModFoods.ITEMS.register(modEventBus);
		ModVillagerProfessions.POINTS_OF_INTEREST.register(modEventBus);
		ModVillagerProfessions.PROFESSIONS.register(modEventBus);
		ModEntity.ENTITY_TYPES.register(modEventBus);

		// Registering the villager trades
		NeoForge.EVENT_BUS.addListener(WorkerProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(WorkerProfession::registerGoals);
		NeoForge.EVENT_BUS.addListener(TraderProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(TraderProfession::registerGoals);
		NeoForge.EVENT_BUS.addListener(this::entityJoinWorldEvent);
		NeoForge.EVENT_BUS.addListener(this::onAttachCapabilitiesEvent);

		this.LOGGER.debug(this.data.getName() + " Is created");
	}

	/**
	 * Register Capabilities hook.
	 * TODO: Reimplement for NeoForge 1.20.2 - Capability API changed
	 */
	public void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> e) {
		// Commented - Capability API changed in 1.20.2
		// if (e.getObject() instanceof Villager) {
		// 	HungerProvider hProvider = new HungerProvider();
		// 	e.addCapability(new ResourceLocation(Reference.MODID, "hunger"), hProvider);
		// 	e.addListener(hProvider::invalidate);
		//
		// 	HonorProvider provider = new HonorProvider();
		// 	e.addCapability(new ResourceLocation(Reference.MODID, "honor"), provider);
		// 	e.addListener(provider::invalidate);
		// }
	}

	@SubscribeEvent
	public void entityJoinWorldEvent(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof IronGolem) {
			// Iron golem spawn logic - TODO
		}

		if (entity instanceof Villager) {
			Villager villager = (Villager) event.getEntity();

			if (!villager.level().isClientSide()) {
				// TODO: Reimplement data storage initialization for 1.20.2
				// SavedData.computeIfAbsent() signature changed
				/*
				if (this.data.initialized == false) {
					ServerLevel world = (ServerLevel) villager.level();
					world.getDataStorage().computeIfAbsent(() -> {
						VillageCraft.data.setWorld(world);
						return VillageCraft.data;
					}, "VillageCraftData");
					VillageCraft.data.initialize();
				}
				*/
				VillageCraft.data.initialize();

				// TODO: Reimplement AI goals for 1.20.2
				// villager.goalSelector.addGoal(1, new VillagerGoalBase(villager));
				// villager.goalSelector.addGoal(1, new VillagerHungerGoal(villager));
			}
		}
	}
}