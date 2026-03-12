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
	
	// Points of interest - using minimal POI registration for 1.20.2
	public static final DeferredHolder<PoiType, PoiType> ALCHEMIST_TABLE = POINTS_OF_INTEREST.register("alchemist", 
			() -> new PoiType(getAllStates(ModBlocks.BLOCK_ALCHEMIST_TABLE.get()), 1, 1));
	
	public static final DeferredHolder<PoiType, PoiType> VILLAGE_CENTER = POINTS_OF_INTEREST.register("village_center", 
			() -> new PoiType(getAllStates(ModBlocks.BLOCK_VILLAGE_CENTER.get()), 1, 1));
	
	// Professions - VillagerProfession is a record in 1.20.2
	// Constructor: VillagerProfession(String name, Predicate<Holder<PoiType>> heldJobSite, Predicate<Holder<PoiType>> acquirableJobSite, ImmutableSet<Item> requestedItems, ImmutableSet<Block> secondaryPois, SoundEvent workSound)
	public static final DeferredHolder<VillagerProfession, VillagerProfession> WORKER = PROFESSIONS.register("worker",
			() -> new VillagerProfession(
					"worker",
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));
	
	// TRADER profession - referenced by TraderProfession.java
	public static final DeferredHolder<VillagerProfession, VillagerProfession> TRADER = PROFESSIONS.register("trader",
			() -> new VillagerProfession(
					"trader",
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));
	
	// ARCHITECT profession - referenced by TradesmanProfession.java
	public static final DeferredHolder<VillagerProfession, VillagerProfession> ARCHITECT = PROFESSIONS.register("architect",
			() -> new VillagerProfession(
					"architect",
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));
	
	// FARMER profession - referenced by FarmerProfession.java
	public static final DeferredHolder<VillagerProfession, VillagerProfession> FARMER = PROFESSIONS.register("farmer",
			() -> new VillagerProfession(
					"farmer",
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));
	
	// BUILDER profession - referenced by BuilderProfession.java
	public static final DeferredHolder<VillagerProfession, VillagerProfession> BUILDER = PROFESSIONS.register("builder",
			() -> new VillagerProfession(
					"builder",
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));
	
	// ALCHEMIST profession - referenced by AlchemistProfession.java
	public static final DeferredHolder<VillagerProfession, VillagerProfession> ALCHEMIST = PROFESSIONS.register("alchemist",
			() -> new VillagerProfession(
					"alchemist",
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					(holder) -> holder.value() == VILLAGE_CENTER.get(),
					ImmutableSet.of(),
					ImmutableSet.of(),
					null
			));
	
	// Helper method
	public static Set<BlockState> getAllStates(Block block) {
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}
}