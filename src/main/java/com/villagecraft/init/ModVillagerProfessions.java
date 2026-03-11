package com.villagecraft.init;

import com.villagecraft.VillageCraft;
import com.villagecraft.entity.professions.AlchemistProfession;
import com.villagecraft.entity.professions.ArchitectProfession;
import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.entity.professions.BarkeeperProfession;
import com.villagecraft.entity.professions.BassistProfession;
import com.villagecraft.entity.professions.BeekeeperProfession;
import com.villagecraft.entity.professions.BrawlerProfession;
import com.villagecraft.entity.professions.CaravaneerProfession;
import com.villagecraft.entity.professions.DiplomatProfession;
import com.villagecraft.entity.professions.DrummerProfession;
import com.villagecraft.entity.professions.InnkeeperProfession;
import com.villagecraft.entity.professions.LandlordProfession;
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

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MerchantOffer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.item.Items;


public class ModVillagerProfessions {

	public static final DeferredRegister<PoiType> POINTS_OF_INTEREST = DeferredRegister.create(BuiltInRegistries.POI_TYPE, Reference.MODID);
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, Reference.MODID);
	
	
	/**
	 * Points of interests
	 */
	public static final DeferredHolder<PoiType, PoiType> ALCHEMIST_TABLE = POINTS_OF_INTEREST.register("alchemist", () -> { 	
		PoiType alchemist_table = new PoiType("alchemist_table", ModVillagerProfessions.getAllStates(ModBlocks.BLOCK_ALCHEMIST_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(alchemist_table);
	});
	
	public static final DeferredHolder<PoiType, PoiType> BARD_CHAIR = POINTS_OF_INTEREST.register("bard", () -> { 	
		PoiType bardPlace = new PoiType("bard_stand", ModVillagerProfessions.getAllStates(ModBlocks.BARD_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(bardPlace);
	});
	
	public static final DeferredHolder<PoiType, PoiType> ORE_BOX = POINTS_OF_INTEREST.register("ore_box", () -> {
		PoiType minerWorkplace = new PoiType("ore_box", ModVillagerProfessions.getAllStates(ModBlocks.ORE_BOX.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(minerWorkplace);
	});
	
	public static final DeferredHolder<PoiType, PoiType> DRAFTING_TABLE = POINTS_OF_INTEREST.register("drafting_table", () -> {
		PoiType draftingTable = new PoiType("drafting_table", ModVillagerProfessions.getAllStates(ModBlocks.DRAFTING_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(draftingTable);
	});
	
	public static final DeferredHolder<PoiType, PoiType> TRADESMAN_HELMET = POINTS_OF_INTEREST.register("tradesman_helmet", () -> {
		PoiType tradesman_helmet = new PoiType("tradesman_helmet", ModVillagerProfessions.getAllStates(ModBlocks.TRADESMAN_HELMET.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(tradesman_helmet);
	});
	
	public static final DeferredHolder<PoiType, PoiType> CRAFTING_TABLE = POINTS_OF_INTEREST.register("village_crafting_table", () -> {
		PoiType village_crafting_table = new PoiType("village_crafting_table", ModVillagerProfessions.getAllStates(net.minecraft.block.Blocks.CRAFTING_TABLE), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_crafting_table);
	});
	
	public static final DeferredHolder<PoiType, PoiType> CHEST = POINTS_OF_INTEREST.register("village_chest", () -> {
		PoiType village_chest = new PoiType("village_chest", ModVillagerProfessions.getAllStates(net.minecraft.block.Blocks.CHEST), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final DeferredHolder<PoiType, PoiType> BRAWLER_BOX = POINTS_OF_INTEREST.register("brawler_box", () -> {
		PoiType village_chest = new PoiType("brawler_box", ModVillagerProfessions.getAllStates(ModBlocks.BRAWLER_BOX.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final DeferredHolder<PoiType, PoiType> BAR = POINTS_OF_INTEREST.register("bar", () -> {
		PoiType village_chest = new PoiType("bar", ModVillagerProfessions.getAllStates(ModBlocks.BAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final DeferredHolder<PoiType, PoiType> DRUMS = POINTS_OF_INTEREST.register("drums", () -> {
		PoiType village_chest = new PoiType("drums", ModVillagerProfessions.getAllStates(ModBlocks.DRUMS.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final DeferredHolder<PoiType, PoiType> MICROPHONE_STAND = POINTS_OF_INTEREST.register("microphone_stand", () -> {
		PoiType village_chest = new PoiType("microphone_stand", ModVillagerProfessions.getAllStates(ModBlocks.MICROPHONE_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final DeferredHolder<PoiType, PoiType> GUITAR_STAND = POINTS_OF_INTEREST.register("guitar_stand", () -> {
		PoiType village_chest = new PoiType("guitar_stand", ModVillagerProfessions.getAllStates(ModBlocks.GUITAR_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	public static final DeferredHolder<PoiType, PoiType> AUCTION_HOUSE = POINTS_OF_INTEREST.register("auction_house", () -> {
		PoiType auction_house = new PoiType("auction_house", ModVillagerProfessions.getAllStates(ModBlocks.AUCTION_HOUSE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(auction_house);
	});
	
	public static final DeferredHolder<PoiType, PoiType> PYROTECHNIC_TABLE = POINTS_OF_INTEREST.register("pyrotechnic_worktable", () -> {
		PoiType pyrotechnic_worktable = new PoiType("pyrotechnic_worktable", ModVillagerProfessions.getAllStates(ModBlocks.PYROTECHNIC_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(pyrotechnic_worktable);
	});
	
	public static final DeferredHolder<PoiType, PoiType> CARAVAN_STOP = POINTS_OF_INTEREST.register("caravan_stop", () -> {
		PoiType caravan_stop = new PoiType("caravan_stop", ModVillagerProfessions.getAllStates(ModBlocks.CARAVAN_STOP.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(caravan_stop);
	});
	
	public static final DeferredHolder<PoiType, PoiType> EMBASSY = POINTS_OF_INTEREST.register("embassy", () -> {
		PoiType embassy = new PoiType("embassy", ModVillagerProfessions.getAllStates(ModBlocks.EMBASSY.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(embassy);
	});
	
	public static final DeferredHolder<PoiType, PoiType> VILLAGE_MANAGEMENT = POINTS_OF_INTEREST.register("village_management", () -> {
		PoiType village_management = new PoiType("village_management", ModVillagerProfessions.getAllStates(ModBlocks.VILLAGE_MANAGER.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_management);
	});
	
	public static final DeferredHolder<PoiType, PoiType> SUPPLY_OFFICE = POINTS_OF_INTEREST.register("supply_office", () -> {
		PoiType supply_office = new PoiType("supply_office", ModVillagerProfessions.getAllStates(ModBlocks.SUPPLY_OFFICE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(supply_office);
	});
	
	public static final DeferredHolder<PoiType, PoiType> TOWN_HALL = POINTS_OF_INTEREST.register("town_hall", () -> {
		PoiType town_hall = new PoiType("town_hall", ModVillagerProfessions.getAllStates(ModBlocks.TOWN_HALL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(town_hall);
	});
	
	public static final DeferredHolder<PoiType, PoiType> INN = POINTS_OF_INTEREST.register("inn", () -> {
		PoiType inn = new PoiType("inn", ModVillagerProfessions.getAllStates(ModBlocks.INN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(inn);
	});
	
	public static final DeferredHolder<PoiType, PoiType> TITLE_OFFICE = POINTS_OF_INTEREST.register("title_office", () -> {
		PoiType title_office = new PoiType("title_office", ModVillagerProfessions.getAllStates(ModBlocks.TITLE_OFFICE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(title_office);
	});
	
	public static final DeferredHolder<PoiType, PoiType> POTTERS_WHEEL = POINTS_OF_INTEREST.register("potter_wheel", () -> {
		PoiType potter_wheel = new PoiType("potter_wheel", ModVillagerProfessions.getAllStates(ModBlocks.POTTERS_WHEEL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(potter_wheel);
	});
	
	public static final DeferredHolder<PoiType, PoiType> BUILDERS_CHEST = POINTS_OF_INTEREST.register("builders_chest", () -> {
		PoiType builders_chest = new PoiType("builders_chest", ModVillagerProfessions.getAllStates(ModBlocks.BUILDERS_CHEST.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(builders_chest);
	});
	
	public static final DeferredHolder<PoiType, PoiType> BEEHIVE = POINTS_OF_INTEREST.register("beehive", () -> {
		PoiType beehive = new PoiType("beehive", ModVillagerProfessions.getAllStates(net.minecraft.block.Blocks.BEEHIVE), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(beehive);
	});
	
	/**
	 * Goal Related Points of Interest
	 * @param block
	 * @return
	 */
	public static final DeferredHolder<PoiType, PoiType> VILLAGE_CENTER = POINTS_OF_INTEREST.register("village_center", () ->{
		PoiType tradesman_helmet = new PoiType("village_center", ModVillagerProfessions.getAllStates(ModBlocks.BLOCK_VILLAGE_CENTER.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(tradesman_helmet);
	});
	
	// Get All Block States
	public static Set<BlockState> getAllStates(Block block) {
	     return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
	}
	
	private static Method blockStatesInjector;
    
    public static PoiType fixPOITypeBlockStates(PoiType poiType) {
    	try {
	    	Method func_221052_a = ObfuscationReflectionHelper.findMethod(PoiType.class, "func_221052_a", PoiType.class);
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
    // @ALCHEMIST
    public static final DeferredHolder<VillagerProfession, VillagerProfession> ALCHEMIST = PROFESSIONS.register("alchemist", () -> { 
    	return AlchemistProfession.villagerProfession("alchemist", ALCHEMIST_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @ARCHITECT
    public static final DeferredHolder<VillagerProfession, VillagerProfession> ARCHITECT = PROFESSIONS.register("architect", () -> { 
    	return ArchitectProfession.villagerProfession("architect", DRAFTING_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @BEE KEEPER
    public static final DeferredHolder<VillagerProfession, VillagerProfession> BEEKEEPER = PROFESSIONS.register("beekeeper", () -> { 
    	return BeekeeperProfession.villagerProfession("beekeeper", BEEHIVE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @TRADESMAN
    public static final DeferredHolder<VillagerProfession, VillagerProfession> TRADESMAN = PROFESSIONS.register("tradesman", () -> { 
    	return TradesmanProfession.villagerProfession("tradesman", TRADESMAN_HELMET.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @WORKER
    public static final DeferredHolder<VillagerProfession, VillagerProfession> WORKER = PROFESSIONS.register("worker", () -> {
    	return WorkerProfession.villagerProfession("worker", CHEST.get(), WorkerProfession.PROFESSION_ITEM, WorkerProfession.PROFESSION_BLOCK, new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );	
    });
    
    // @MERCHANT
    public static final DeferredHolder<VillagerProfession, VillagerProfession> MERCHANT = PROFESSIONS.register("merchant", () -> {
    	return MerchantProfession.villagerProfession("merchant", CRAFTING_TABLE.get(), MerchantProfession.PROFESSION_ITEM, MerchantProfession.PROFESSION_BLOCK, new SoundEvent(new ResourceLocation("vcm", "villager_socialize")) );	
    });
    
 // @TRADESMAN
    public static final DeferredHolder<VillagerProfession, VillagerProfession> TRADER = PROFESSIONS.register("trader", () -> { 
    	return TradesmanProfession.villagerProfession("trader", AUCTION_HOUSE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    
    
	// @BARD
	public static final DeferredHolder<VillagerProfession, VillagerProfession> BARD = PROFESSIONS.register("bard", () -> {
		return BardProfession.villagerProfession("bard", BARD_CHAIR.get(), ImmutableSet.of(), ImmutableSet.of(ModBlocks.BARD_STAND.get()), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @MINER
	public static final DeferredHolder<VillagerProfession, VillagerProfession> MINER = PROFESSIONS.register("miner", () -> {
		return MinerProfession.villagerProfession("miner", ORE_BOX.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")));
	});
	
	// @Brawler
	public static final DeferredHolder<VillagerProfession, VillagerProfession> BRAWLER = PROFESSIONS.register("brawler", () -> {
		return BrawlerProfession.villagerProfession("brawler", BRAWLER_BOX.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @Barkeeper
	public static final DeferredHolder<VillagerProfession, VillagerProfession> BAR_KEEPER = PROFESSIONS.register("barkeeper", () -> {
		return BarkeeperProfession.villagerProfession("barkeeper", BAR.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")));
	});
	
	// @Drummer
	public static final DeferredHolder<VillagerProfession, VillagerProfession> DRUMMER = PROFESSIONS.register("drummer", () -> {
		return DrummerProfession.villagerProfession("drummer", DRUMS.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @Singer
	public static final DeferredHolder<VillagerProfession, VillagerProfession> SINGER = PROFESSIONS.register("singer", () -> {
		return SingerProfession.villagerProfession("singer", MICROPHONE_STAND.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> BASSIST = PROFESSIONS.register("bassist", () -> {
		return BassistProfession.villagerProfession("bassist", GUITAR_STAND.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> PYROTECHNIC = PROFESSIONS.register("pyrotechnic", () -> {
		return PyrotechnicProfession.villagerProfession("pyrotechnic", PYROTECHNIC_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> CARAVANEER = PROFESSIONS.register("caravaneer", () -> {
		return CaravaneerProfession.villagerProfession("caravaneer", CARAVAN_STOP.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> DIPLOMAT = PROFESSIONS.register("diplomat", () -> {
		return DiplomatProfession.villagerProfession("diplomat", EMBASSY.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> MANAGER = PROFESSIONS.register("manager", () -> {
		return ManagerProfession.villagerProfession("manager", VILLAGE_MANAGEMENT.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> OUTPOST_LIASON = PROFESSIONS.register("outpost_liason", () -> {
		return OutpostLiasonProfession.villagerProfession("outpost_liason", SUPPLY_OFFICE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> MAYOR = PROFESSIONS.register("mayor", () -> {
		return MayorProfession.villagerProfession("mayor", TOWN_HALL.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> INNKEEPER = PROFESSIONS.register("innkeeper", () -> {
		return InnkeeperProfession.villagerProfession("innkeeper", INN.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> LANDLORD = PROFESSIONS.register("landlord", () -> {
		return LandlordProfession.villagerProfession("landlord", TITLE_OFFICE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> POTTER = PROFESSIONS.register("potter", () -> {
		return LandlordProfession.villagerProfession("potter", POTTERS_WHEEL.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	public static final DeferredHolder<VillagerProfession, VillagerProfession> BUILDER = PROFESSIONS.register("builder", () -> {
		return LandlordProfession.villagerProfession("builder", BUILDERS_CHEST.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
}

//caravaneer
















