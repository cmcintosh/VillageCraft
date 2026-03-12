package com.villagecraft.init;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.util.Reference;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
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
	
	// Professions - simplified for now
	public static final DeferredHolder<VillagerProfession, VillagerProfession> WORKER = PROFESSIONS.register("worker", 
			() -> new VillagerProfession("worker", VILLAGE_CENTER.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvent.createVariableRangeEvent(new ResourceLocation("entity.villager.ambient"))));
	
	// Helper method
	public static Set<BlockState> getAllStates(Block block) {
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}
}