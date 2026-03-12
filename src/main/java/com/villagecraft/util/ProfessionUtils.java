package com.villagecraft.util;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;

/**
 * Utility class for villager profession operations.
 * Updated for NeoForge 1.20.2
 * 
 * Provides helper methods for profession queries and validation.
 */
public class ProfessionUtils {
	
	/**
	 * Check if a profession has a work station (POI)
	 * In NeoForge 1.20.2, professions may or may not have associated POIs
	 */
	public static boolean hasWorkStation(VillagerProfession profession) {
		// All professions except NONE and NITWIT have work stations
		return profession != VillagerProfession.NONE && profession != VillagerProfession.NITWIT;
	}
	
	/**
	 * Check if a villager is unemployed
	 */
	public static boolean isUnemployed(VillagerProfession profession) {
		return profession == VillagerProfession.NONE;
	}
	
	/**
	 * Check if a villager is a nitwit
	 */
	public static boolean isNitwit(VillagerProfession profession) {
		return profession == VillagerProfession.NITWIT;
	}
	
	/**
	 * Check if a profession has trades
	 */
	public static boolean hasTrades(VillagerProfession profession) {
		return profession != VillagerProfession.NONE && profession != VillagerProfession.NITWIT;
	}
	
	/**
	 * Get profession display name
	 */
	public static String getProfessionName(VillagerProfession profession) {
		return profession.toString();
	}
	
	/**
	 * Check if a profession is a worker (has valuable trades)
	 */
	public static boolean isWorker(VillagerProfession profession) {
		return profession == VillagerProfession.LIBRARIAN ||
		       profession == VillagerProfession.CLERIC ||
		       profession == VillagerProfession.TOOLSMITH ||
		       profession == VillagerProfession.WEAPONSMITH ||
		       profession == VillagerProfession.ARMORER ||
		       profession == VillagerProfession.BUTCHER ||
		       profession == VillagerProfession.FARMER ||
		       profession == VillagerProfession.FISHERMAN ||
		       profession == VillagerProfession.FLETCHER ||
		       profession == VillagerProfession.LEATHERWORKER ||
		       profession == VillagerProfession.MASON ||
		       profession == VillagerProfession.SHEPHERD;
	}
	
	/**
	 * Check if a profession generates resources
	 */
	public static boolean isResourceGatherer(VillagerProfession profession) {
		return profession == VillagerProfession.FARMER ||
		       profession == VillagerProfession.FISHERMAN ||
		       profession == VillagerProfession.BUTCHER ||
		       profession == VillagerProfession.SHEPHERD;
	}
	
	/**
	 * Check if a profession is a crafter
	 */
	public static boolean isCrafter(VillagerProfession profession) {
		return profession == VillagerProfession.TOOLSMITH ||
		       profession == VillagerProfession.WEAPONSMITH ||
		       profession == VillagerProfession.ARMORER ||
		       profession == VillagerProfession.FLETCHER ||
		       profession == VillagerProfession.LEATHERWORKER;
	}
	
}
