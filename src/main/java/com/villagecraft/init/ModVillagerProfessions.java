package com.villagecraft.init;

import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.util.Reference;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import com.google.common.collect.ImmutableSet;

/****
 * 
 * @author chris
 * Adding the following custom professions into minecraft for the mod:
 * 
 * Architect
 * Bard
 * Chef
 * Druid
 * Enchanter
 * Miner
 * Lumberjack
 * Carpenter
 * Teacher
 * Tradesman
 * Vendor
 * Engineer
 * Geomancer
 * Weaver
 * Guildmaster
 * Sapper
 * Mayor
 * Outpost Manager
 * Diplomat
 * Alchemist
 * Hunter
 * Rogue
 * Warlock
 * Mage
 * Undertaker
 * Potter
 * Nurse
 * Ranger
 * Herbalist
 * Caravan Master
 * Trapper
 * Guard
 * Knight
 * Footman
 * Archer
 * Brawler
 * Captain of the Guard
 * 
 */


public class ModVillagerProfessions {

	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Reference.MODID);
	
	/**
	 * Villager Professions
	 */
	public static final RegistryObject<VillagerProfession> BARD = PROFESSIONS.register("bard", () -> new BardProfession("bard", BardProfession.POINT_OF_INTEREST, BardProfession.PROFESSION_ITEM, BardProfession.PROFESSION_BLOCK, (SoundEvent) null));
	
}






















