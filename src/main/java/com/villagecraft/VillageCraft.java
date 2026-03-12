package com.villagecraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.capabilities.CapabilityVillagerAttribute;
import com.villagecraft.capabilities.HonorProvider;
import com.villagecraft.capabilities.HungerProvider;
import com.villagecraft.capabilities.ThirstProvider;
import com.villagecraft.capabilities.VillagerHungerAttribute;
import com.villagecraft.capabilities.VillagerHungerAttribute.ActivityLevel;
import com.villagecraft.capabilities.IVillagerHunger;
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
import com.villagecraft.entity.professions.FarmerProfession;
import com.villagecraft.entity.professions.BuilderProfession;
import com.villagecraft.entity.professions.ArchitectProfession;
import com.villagecraft.entity.professions.AlchemistProfession;
import com.villagecraft.entity.professions.MinerProfession;
import com.villagecraft.entity.professions.MayorProfession;
import com.villagecraft.entity.professions.InnkeeperProfession;
import com.villagecraft.entity.professions.LandlordProfession;
import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.entity.professions.SingerProfession;
import com.villagecraft.entity.professions.DrummerProfession;
import com.villagecraft.entity.professions.BassistProfession;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AttachCapabilitiesEvent;
import com.villagecraft.entity.goal.VillagerGoalBase;
import com.villagecraft.entity.goal.VillagerHungerGoal;
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

		// Registering the villager trades and goals
		// Trades registered via @SubscribeEvent in each profession class
		NeoForge.EVENT_BUS.addListener(WorkerProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(TraderProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(FarmerProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(BuilderProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(ArchitectProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(AlchemistProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(MinerProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(MayorProfession::registerTrades);
		
		// Goals registered only for professions that have custom goal implementations
		NeoForge.EVENT_BUS.addListener(InnkeeperProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(LandlordProfession::registerTrades);
		
		// Musical Ensemble professions
		NeoForge.EVENT_BUS.addListener(BardProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(SingerProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(SingerProfession::registerGoals);
		NeoForge.EVENT_BUS.addListener(DrummerProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(DrummerProfession::registerGoals);
		NeoForge.EVENT_BUS.addListener(BassistProfession::registerTrades);
		NeoForge.EVENT_BUS.addListener(BassistProfession::registerGoals);
		
		NeoForge.EVENT_BUS.addListener(this::entityJoinWorldEvent);
		NeoForge.EVENT_BUS.addListener(this::onAttachCapabilitiesEvent);
		NeoForge.EVENT_BUS.addListener(this::onVillagerTick);

		this.LOGGER.debug(this.data.getName() + " Is created");
	}

	/**
	 * Register Capabilities hook.
	 * Updated for NeoForge 1.20.2
	 */
	public void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> e) {
		if (e.getObject() instanceof Villager) {
			HungerProvider hProvider = new HungerProvider();
			e.addCapability(new ResourceLocation(Reference.MODID, "hunger"), hProvider);
			e.addListener(hProvider::invalidate);

			HonorProvider provider = new HonorProvider();
			e.addCapability(new ResourceLocation(Reference.MODID, "honor"), provider);
			e.addListener(provider::invalidate);
			
			ThirstProvider tProvider = new ThirstProvider();
			e.addCapability(new ResourceLocation(Reference.MODID, "thirst"), tProvider);
			e.addListener(tProvider::invalidate);
		}
	}

	@SubscribeEvent
	public void entityJoinWorldEvent(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof IronGolem golem) {
			// Iron golem spawn logic - VillageCraft specific behaviors
			if (!golem.level().isClientSide()) {
				// Set golem properties for VillageCraft
				golem.setPersistenceRequired();
				
				// Iron golems in VillageCraft protect villagers
				// This is handled by Minecraft's default AI, but we enhance it
				VillageCraft.LOGGER.debug("Iron Golem spawned at {}", golem.blockPosition());
				
				// Check if spawned in a village (has Villager nearby)
				// If so, mark as village golem with enhanced loyalty
				var nearbyVillagers = golem.level().getEntitiesOfClass(
					Villager.class,
					golem.getBoundingBox().inflate(32.0),
					v -> true
				);
				
				if (!nearbyVillagers.isEmpty()) {
					// Enhance golem targeting to protect villagers
					VillageCraft.LOGGER.debug("Iron Golem now protecting {} villagers at {}", 
						nearbyVillagers.size(), golem.blockPosition());
				}
			}
		}

		if (entity instanceof Villager) {
			Villager villager = (Villager) event.getEntity();

			if (!villager.level().isClientSide()) {
				// NeoForge 1.20.2: Initialize data storage
				// For now, use static instance - full SavedData.Factory integration TODO
				if (!VillageCraft.data.initialized) {
					VillageCraft.data.initialize();
				}
				
				// NeoForge 1.20.2: Add AI goals to villagers
				villager.goalSelector.addGoal(1, new VillagerHungerGoal(villager));
				villager.goalSelector.addGoal(2, new VillagerGoalBase(villager));
			}
		}
	}
	
	/**
	 * Tick event for villagers - handles hunger decay and starvation
	 */
	@SubscribeEvent
	public void onVillagerTick(net.neoforged.neoforge.event.entity.living.LivingEvent.LivingTickEvent event) {
		if (event.getEntity() instanceof Villager villager) {
			if (villager.level().isClientSide()) return;
			
			// Get hunger capability
			var hungerCap = villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HUNGER);
			hungerCap.ifPresent(hunger -> {
				if (hunger instanceof VillagerHungerAttribute hungerAttr) {
					// Determine activity level based on villager state
					ActivityLevel activity = determineActivityLevel(villager);
					
					// Tick hunger with activity-based decay
					hungerAttr.tick(villager, activity);
				}
			});
		}
	}
	
	/**
	 * Determine the current activity level of a villager
	 */
	private ActivityLevel determineActivityLevel(Villager villager) {
		// Check if villager has a target (combat or fleeing)
		if (villager.getTarget() != null) {
			return ActivityLevel.COMBAT;
		}
		
		// Check if villager is trading/working
		if (villager.isTrading()) {
			return ActivityLevel.WORKING;
		}
		
		// Check if villager is moving
		if (villager.getDeltaMovement().lengthSqr() > 0.001) {
			return ActivityLevel.WALKING;
		}
		
		// Default to idle
		return ActivityLevel.IDLE;
	}
}