package com.villagecraft.init;

import com.villagecraft.VillageCraft;
import com.villagecraft.entity.professions.ArchitectProfession;
import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.entity.professions.BarkeeperProfession;
import com.villagecraft.entity.professions.BassistProfession;
import com.villagecraft.entity.professions.BrawlerProfession;
import com.villagecraft.entity.professions.CaravaneerProfession;
import com.villagecraft.entity.professions.DiplomatProfession;
import com.villagecraft.entity.professions.DrummerProfession;
import com.villagecraft.entity.professions.InnkeeperProfession;
import com.villagecraft.entity.professions.ManagerProfession;
import com.villagecraft.entity.professions.MayorProfession;
import com.villagecraft.entity.professions.MerchantProfession;
import com.villagecraft.entity.professions.MinerProfession;
import com.villagecraft.entity.professions.OutpostLiasonProfession;
import com.villagecraft.entity.professions.PyrotechnicProfession;
import com.villagecraft.entity.professions.SingerProfession;
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
	
	public static final RegistryObject<PointOfInterestType> BRAWLER_BOX = POINTS_OF_INTEREST.register("brawler_box", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("brawler_box", ModVillagerProfessions.getAllStates(ModBlocks.BRAWLER_BOX.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final RegistryObject<PointOfInterestType> BAR = POINTS_OF_INTEREST.register("bar", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("bar", ModVillagerProfessions.getAllStates(ModBlocks.BAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final RegistryObject<PointOfInterestType> DRUMS = POINTS_OF_INTEREST.register("drums", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("drums", ModVillagerProfessions.getAllStates(ModBlocks.DRUMS.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final RegistryObject<PointOfInterestType> MICROPHONE_STAND = POINTS_OF_INTEREST.register("microphone_stand", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("microphone_stand", ModVillagerProfessions.getAllStates(ModBlocks.MICROPHONE_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final RegistryObject<PointOfInterestType> GUITAR_STAND = POINTS_OF_INTEREST.register("guitar_stand", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("guitar_stand", ModVillagerProfessions.getAllStates(ModBlocks.GUITAR_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final RegistryObject<PointOfInterestType> AUCTION_HOUSE = POINTS_OF_INTEREST.register("auction_house", () -> {
		PointOfInterestType auction_house = new PointOfInterestType("auction_house", ModVillagerProfessions.getAllStates(ModBlocks.AUCTION_HOUSE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(auction_house);
	});
	
	public static final RegistryObject<PointOfInterestType> PYROTECHNIC_TABLE = POINTS_OF_INTEREST.register("pyrotechnic_worktable", () -> {
		PointOfInterestType pyrotechnic_worktable = new PointOfInterestType("pyrotechnic_worktable", ModVillagerProfessions.getAllStates(ModBlocks.PYROTECHNIC_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(pyrotechnic_worktable);
	});
	
	public static final RegistryObject<PointOfInterestType> CARAVAN_STOP = POINTS_OF_INTEREST.register("caravan_stop", () -> {
		PointOfInterestType caravan_stop = new PointOfInterestType("caravan_stop", ModVillagerProfessions.getAllStates(ModBlocks.CARAVAN_STOP.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(caravan_stop);
	});
	
	public static final RegistryObject<PointOfInterestType> EMBASSY = POINTS_OF_INTEREST.register("embassy", () -> {
		PointOfInterestType embassy = new PointOfInterestType("embassy", ModVillagerProfessions.getAllStates(ModBlocks.EMBASSY.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(embassy);
	});
	
	public static final RegistryObject<PointOfInterestType> VILLAGE_MANAGEMENT = POINTS_OF_INTEREST.register("village_management", () -> {
		PointOfInterestType village_management = new PointOfInterestType("village_management", ModVillagerProfessions.getAllStates(ModBlocks.VILLAGE_MANAGER.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_management);
	});
	
	public static final RegistryObject<PointOfInterestType> SUPPLY_OFFICE = POINTS_OF_INTEREST.register("supply_office", () -> {
		PointOfInterestType supply_office = new PointOfInterestType("supply_office", ModVillagerProfessions.getAllStates(ModBlocks.SUPPLY_OFFICE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(supply_office);
	});
	
	public static final RegistryObject<PointOfInterestType> TOWN_HALL = POINTS_OF_INTEREST.register("town_hall", () -> {
		PointOfInterestType town_hall = new PointOfInterestType("town_hall", ModVillagerProfessions.getAllStates(ModBlocks.TOWN_HALL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(town_hall);
	});
	
	public static final RegistryObject<PointOfInterestType> INN = POINTS_OF_INTEREST.register("inn", () -> {
		PointOfInterestType inn = new PointOfInterestType("inn", ModVillagerProfessions.getAllStates(ModBlocks.INN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(inn);
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
    	return TradesmanProfession.villagerProfession("tradesman", TRADESMAN_HELMET.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @WORKER
    public static final RegistryObject<VillagerProfession> WORKER = PROFESSIONS.register("worker", () -> {
    	return WorkerProfession.villagerProfession("worker", CHEST.get(), WorkerProfession.PROFESSION_ITEM, WorkerProfession.PROFESSION_BLOCK, new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );	
    });
    
    // @MERCHANT
    public static final RegistryObject<VillagerProfession> MERCHANT = PROFESSIONS.register("merchant", () -> {
    	return MerchantProfession.villagerProfession("merchant", CRAFTING_TABLE.get(), MerchantProfession.PROFESSION_ITEM, MerchantProfession.PROFESSION_BLOCK, new SoundEvent(new ResourceLocation("vcm", "villager_socialize")) );	
    });
    
 // @TRADESMAN
    public static final RegistryObject<VillagerProfession> TRADER = PROFESSIONS.register("trader", () -> { 
    	return TradesmanProfession.villagerProfession("trader", AUCTION_HOUSE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @ARCHITECT
    public static final RegistryObject<VillagerProfession> ARCHITECT = PROFESSIONS.register("architect", () -> { 
    	return ArchitectProfession.villagerProfession("architect", DRAFTING_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
	// @BARD
	public static final RegistryObject<VillagerProfession> BARD = PROFESSIONS.register("bard", () -> {
		return BardProfession.villagerProfession("bard", BARD_CHAIR.get(), ImmutableSet.of(), ImmutableSet.of(ModBlocks.BARD_STAND.get()), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @MINER
	public static final RegistryObject<VillagerProfession> MINER = PROFESSIONS.register("miner", () -> {
		return MinerProfession.villagerProfession("miner", ORE_BOX.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")));
	});
	
	// @Brawler
	public static final RegistryObject<VillagerProfession> BRAWLER = PROFESSIONS.register("brawler", () -> {
		return BrawlerProfession.villagerProfession("brawler", BRAWLER_BOX.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @Barkeeper
	public static final RegistryObject<VillagerProfession> BAR_KEEPER = PROFESSIONS.register("barkeeper", () -> {
		return BarkeeperProfession.villagerProfession("barkeeper", BAR.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")));
	});
	
	// @Drummer
	public static final RegistryObject<VillagerProfession> DRUMMER = PROFESSIONS.register("drummer", () -> {
		return DrummerProfession.villagerProfession("drummer", DRUMS.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @Singer
	public static final RegistryObject<VillagerProfession> SINGER = PROFESSIONS.register("singer", () -> {
		return SingerProfession.villagerProfession("singer", MICROPHONE_STAND.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final RegistryObject<VillagerProfession> BASSIST = PROFESSIONS.register("bassist", () -> {
		return BassistProfession.villagerProfession("bassist", GUITAR_STAND.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final RegistryObject<VillagerProfession> PYROTECHNIC = PROFESSIONS.register("pyrotechnic", () -> {
		return PyrotechnicProfession.villagerProfession("pyrotechnic", PYROTECHNIC_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final RegistryObject<VillagerProfession> CARAVANEER = PROFESSIONS.register("caravaneer", () -> {
		return CaravaneerProfession.villagerProfession("caravaneer", CARAVAN_STOP.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final RegistryObject<VillagerProfession> DIPLOMAT = PROFESSIONS.register("diplomat", () -> {
		return DiplomatProfession.villagerProfession("diplomat", EMBASSY.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final RegistryObject<VillagerProfession> MANAGER = PROFESSIONS.register("manager", () -> {
		return ManagerProfession.villagerProfession("manager", VILLAGE_MANAGEMENT.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final RegistryObject<VillagerProfession> OUTPOST_LIASON = PROFESSIONS.register("outpost_liason", () -> {
		return OutpostLiasonProfession.villagerProfession("outpost_liason", SUPPLY_OFFICE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final RegistryObject<VillagerProfession> MAYOR = PROFESSIONS.register("mayor", () -> {
		return MayorProfession.villagerProfession("mayor", TOWN_HALL.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final RegistryObject<VillagerProfession> INNKEEPER = PROFESSIONS.register("innkeeper", () -> {
		return InnkeeperProfession.villagerProfession("innkeeper", INN.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
}

//caravaneer
















