package com.villagecraft.init;

import com.villagecraft.VillageCraft;
import com.villagecraft.entity.professions.ArchitectProfession;
import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.entity.professions.MerchantProfession;
import com.villagecraft.entity.professions.MinerProfession;
import com.villagecraft.entity.professions.TradesmanProfession;
import com.villagecraft.entity.professions.WorkerProfession;
import com.villagecraft.util.ProfessionUtils;
import com.villagecraft.util.RandomTradeBuilder;
import com.villagecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;

import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import net.minecraft.item.Items;


public class ModVillagerProfessions {

	public static final DeferredRegister<PointOfInterestType> POINTS_OF_INTEREST = DeferredRegister.create(ForgeRegistries.POI_TYPES, Reference.MODID);
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Reference.MODID);
	
	
	/**
	 * Points of interests
	 */
	public static final RegistryObject<PointOfInterestType> BARD_CHAIR = POINTS_OF_INTEREST.register("bard", () -> { 	
		PointOfInterestType bardPlace = new PointOfInterestType("bard_stand", ModVillagerProfessions.getAllStates(ModBlocks.BARD_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(bardPlace);
	});
	
	public static final RegistryObject<PointOfInterestType> ORE_BOX = POINTS_OF_INTEREST.register("ore_box", () -> {
		PointOfInterestType minerWorkplace = new PointOfInterestType("ore_box", ModVillagerProfessions.getAllStates(ModBlocks.ORE_BOX.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(minerWorkplace);
	});
	
	public static final RegistryObject<PointOfInterestType> DRAFTING_TABLE = POINTS_OF_INTEREST.register("drafting_table", () -> {
		PointOfInterestType draftingTable = new PointOfInterestType("drafting_table", ModVillagerProfessions.getAllStates(ModBlocks.DRAFTING_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(draftingTable);
	});
	
	public static final RegistryObject<PointOfInterestType> TRADESMAN_HELMET = POINTS_OF_INTEREST.register("tradesman_helmet", () -> {
		PointOfInterestType tradesman_helmet = new PointOfInterestType("tradesman_helmet", ModVillagerProfessions.getAllStates(ModBlocks.TRADESMAN_HELMET.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(tradesman_helmet);
	});
	
	public static final RegistryObject<PointOfInterestType> CRAFTING_TABLE = POINTS_OF_INTEREST.register("village_crafting_table", () -> {
		PointOfInterestType village_crafting_table = new PointOfInterestType("village_crafting_table", ModVillagerProfessions.getAllStates(net.minecraft.block.Blocks.CRAFTING_TABLE), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_crafting_table);
	});
	
	public static final RegistryObject<PointOfInterestType> CHEST = POINTS_OF_INTEREST.register("village_chest", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("village_chest", ModVillagerProfessions.getAllStates(net.minecraft.block.Blocks.CHEST), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	/**
	 * Goal Related Points of Interest
	 * @param block
	 * @return
	 */
	public static final RegistryObject<PointOfInterestType> VILLAGE_CENTER = POINTS_OF_INTEREST.register("village_center", () ->{
		PointOfInterestType tradesman_helmet = new PointOfInterestType("village_center", ModVillagerProfessions.getAllStates(ModBlocks.BLOCK_VILLAGE_CENTER.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(tradesman_helmet);
	});
	
	// Get All Block States
	public static Set<BlockState> getAllStates(Block block) {
	     return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
	}
	
	private static Method blockStatesInjector;
    
    public static PointOfInterestType fixPOITypeBlockStates(PointOfInterestType poiType) {
    	try {
	    	Method func_221052_a = ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_221052_a", PointOfInterestType.class);
	        func_221052_a.invoke(null, poiType);
    	} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return poiType;
    }
	
	/**
	 * Villager Professions
	 */
    // @TRADESMAN
    public static final RegistryObject<VillagerProfession> TRADESMAN = PROFESSIONS.register("tradesman", () -> { 
    	return TradesmanProfession.villagerProfession("tradesman", TRADESMAN_HELMET.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")) );
    });
    
    // @WORKER
    public static final RegistryObject<VillagerProfession> WORKER = PROFESSIONS.register("worker", () -> {
    	return WorkerProfession.villagerProfession("worker", CHEST.get(), WorkerProfession.PROFESSION_ITEM, WorkerProfession.PROFESSION_BLOCK, new SoundEvent(new ResourceLocation("vcm", "villager_socialize")) );	
    });
    
    // @MERCHANT
    public static final RegistryObject<VillagerProfession> MERCHANT = PROFESSIONS.register("merchant", () -> {
    	return MerchantProfession.villagerProfession("merchant", CRAFTING_TABLE.get(), MerchantProfession.PROFESSION_ITEM, MerchantProfession.PROFESSION_BLOCK, new SoundEvent(new ResourceLocation("vcm", "villager_socialize")) );	
    });
    
    // @ARCHITECT
    public static final RegistryObject<VillagerProfession> ARCHITECT = PROFESSIONS.register("architect", () -> { 
    	return ArchitectProfession.villagerProfession("architect", DRAFTING_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")) );
    });
    
	// @BARD
	public static final RegistryObject<VillagerProfession> BARD = PROFESSIONS.register("bard", () -> {
		return BardProfession.villagerProfession("bard", BARD_CHAIR.get(), ImmutableSet.of(), ImmutableSet.of(ModBlocks.BARD_STAND.get()), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @MINER
	public static final RegistryObject<VillagerProfession> MINER = PROFESSIONS.register("miner", () -> {
		return MinerProfession.villagerProfession("miner", ORE_BOX.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
}


















