package com.villagecraft.init;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.util.Reference;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagerProfessions {

	public static final DeferredRegister<PoiType> POINTS_OF_INTEREST = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Reference.MODID);
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, Reference.MODID);
	
	// Points of interest - workstation bindings for profession job sites
	// Each POI links a profession to its workstation block
	public static final DeferredHolder<PoiType, PoiType> ALCHEMIST_POI = POINTS_OF_INTEREST.register("alchemist_workstation",
			() -> new PoiType(getAllStates(ModBlocks.BLOCK_ALCHEMIST_TABLE.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> ARCHITECT_POI = POINTS_OF_INTEREST.register("architect_workstation",
			() -> new PoiType(getAllStates(ModBlocks.DRAFTING_TABLE.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> BARD_POI = POINTS_OF_INTEREST.register("bard_workstation",
			() -> new PoiType(getAllStates(ModBlocks.BARD_STAND.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> BEEKEEPER_POI = POINTS_OF_INTEREST.register("beekeeper_workstation",
			() -> new PoiType(getAllStates(ModBlocks.BEE_KEEPERS_HIVE.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> BRAWLER_POI = POINTS_OF_INTEREST.register("brawler_workstation",
			() -> new PoiType(getAllStates(ModBlocks.BRAWLER_BOX.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> BUILDER_POI = POINTS_OF_INTEREST.register("builder_workstation",
			() -> new PoiType(getAllStates(ModBlocks.BUILDERS_CHEST.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> CARAVANEER_POI = POINTS_OF_INTEREST.register("caravaneer_workstation",
			() -> new PoiType(getAllStates(ModBlocks.CARAVAN_STOP.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> INNKEEPER_POI = POINTS_OF_INTEREST.register("innkeeper_workstation",
			() -> new PoiType(getAllStates(ModBlocks.INN.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> LANDLORD_POI = POINTS_OF_INTEREST.register("landlord_workstation",
			() -> new PoiType(getAllStates(ModBlocks.TITLE_OFFICE.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> MAYOR_POI = POINTS_OF_INTEREST.register("mayor_workstation",
			() -> new PoiType(getAllStates(ModBlocks.TOWN_HALL.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> MINER_POI = POINTS_OF_INTEREST.register("miner_workstation",
			() -> new PoiType(getAllStates(ModBlocks.ORE_BOX.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> POTTER_POI = POINTS_OF_INTEREST.register("potter_workstation",
			() -> new PoiType(getAllStates(ModBlocks.POTTERS_WHEEL.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> PYROTECHNIC_POI = POINTS_OF_INTEREST.register("pyrotechnic_workstation",
			() -> new PoiType(getAllStates(ModBlocks.PYROTECHNIC_TABLE.get()), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> TRADER_POI = POINTS_OF_INTEREST.register("trader_workstation",
			() -> new PoiType(ImmutableSet.of(), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> WORKER_POI = POINTS_OF_INTEREST.register("worker_workstation",
			() -> new PoiType(ImmutableSet.of(), 1, 1));

	public static final DeferredHolder<PoiType, PoiType> VILLAGE_CENTER = POINTS_OF_INTEREST.register("village_center",
			() -> new PoiType(getAllStates(ModBlocks.BLOCK_VILLAGE_CENTER.get()), 1, 1));
	
	// Professions - VillagerProfession is a record in 1.20.2
	// Constructor: VillagerProfession(String name, Predicate<Holder<PoiType>> heldJobSite, Predicate<Holder<PoiType>> acquirableJobSite, ImmutableSet<Item> requestedItems, ImmutableSet<Block> secondaryPois, SoundEvent workSound)
	// NeoForge 1.20.2: Each profession now has its own workstation POI, enabling job site acquisition

	public static final DeferredHolder<VillagerProfession, VillagerProfession> ALCHEMIST = PROFESSIONS.register("alchemist",
			() -> new VillagerProfession(
					"alchemist",
					(holder) -> holder.value() == ALCHEMIST_POI.get(),
					(holder) -> holder.value() == ALCHEMIST_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> ARCHITECT = PROFESSIONS.register("architect",
			() -> new VillagerProfession(
					"architect",
					(holder) -> holder.value() == ARCHITECT_POI.get(),
					(holder) -> holder.value() == ARCHITECT_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> BARD = PROFESSIONS.register("bard",
			() -> new VillagerProfession(
					"bard",
					(holder) -> holder.value() == BARD_POI.get(),
					(holder) -> holder.value() == BARD_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> BEEKEEPER = PROFESSIONS.register("beekeeper",
			() -> new VillagerProfession(
					"beekeeper",
					(holder) -> holder.value() == BEEKEEPER_POI.get(),
					(holder) -> holder.value() == BEEKEEPER_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> BRAWLER = PROFESSIONS.register("brawler",
			() -> new VillagerProfession(
					"brawler",
					(holder) -> holder.value() == BRAWLER_POI.get(),
					(holder) -> holder.value() == BRAWLER_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> BUILDER = PROFESSIONS.register("builder",
			() -> new VillagerProfession(
					"builder",
					(holder) -> holder.value() == BUILDER_POI.get(),
					(holder) -> holder.value() == BUILDER_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> CARAVANEER = PROFESSIONS.register("caravaneer",
			() -> new VillagerProfession(
					"caravaneer",
					(holder) -> holder.value() == CARAVANEER_POI.get(),
					(holder) -> holder.value() == CARAVANEER_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> INNKEEPER = PROFESSIONS.register("innkeeper",
			() -> new VillagerProfession(
					"innkeeper",
					(holder) -> holder.value() == INNKEEPER_POI.get(),
					(holder) -> holder.value() == INNKEEPER_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> LANDLORD = PROFESSIONS.register("landlord",
			() -> new VillagerProfession(
					"landlord",
					(holder) -> holder.value() == LANDLORD_POI.get(),
					(holder) -> holder.value() == LANDLORD_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> MAYOR = PROFESSIONS.register("mayor",
			() -> new VillagerProfession(
					"mayor",
					(holder) -> holder.value() == MAYOR_POI.get(),
					(holder) -> holder.value() == MAYOR_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> MINER = PROFESSIONS.register("miner",
			() -> new VillagerProfession(
					"miner",
					(holder) -> holder.value() == MINER_POI.get(),
					(holder) -> holder.value() == MINER_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> POTTER = PROFESSIONS.register("potter",
			() -> new VillagerProfession(
					"potter",
					(holder) -> holder.value() == POTTER_POI.get(),
					(holder) -> holder.value() == POTTER_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> PYROTECHNIC = PROFESSIONS.register("pyrotechnic",
			() -> new VillagerProfession(
					"pyrotechnic",
					(holder) -> holder.value() == PYROTECHNIC_POI.get(),
					(holder) -> holder.value() == PYROTECHNIC_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> TRADER = PROFESSIONS.register("trader",
			() -> new VillagerProfession(
					"trader",
					(holder) -> holder.value() == TRADER_POI.get(),
					(holder) -> holder.value() == TRADER_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));

	public static final DeferredHolder<VillagerProfession, VillagerProfession> WORKER = PROFESSIONS.register("worker",
			() -> new VillagerProfession(
					"worker",
					(holder) -> holder.value() == WORKER_POI.get(),
					(holder) -> holder.value() == WORKER_POI.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));
	
	// Helper method
	public static Set<BlockState> getAllStates(Block block) {
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}
}