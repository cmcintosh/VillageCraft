package com.villagecraft.init;

import com.villagecraft.VillageCraft;
import com.villagecraft.alchemy.entity.professions.AlchemistProfession;
import com.villagecraft.entity.professions.ArchitectProfession;
import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.entity.professions.BarkeeperProfession;
import com.villagecraft.entity.professions.BassistProfession;
import com.villagecraft.entity.professions.BeekeeperProfession;
import com.villagecraft.entity.professions.BrawlerProfession;
import com.villagecraft.entity.professions.BrewmasterProfession;
import com.villagecraft.entity.professions.BuilderProfession;
import com.villagecraft.entity.professions.CaravaneerProfession;
import com.villagecraft.entity.professions.CarpenterProfession;
import com.villagecraft.entity.professions.ClericProfession;
import com.villagecraft.entity.professions.CookProfession;
import com.villagecraft.entity.professions.DiplomatProfession;
import com.villagecraft.entity.professions.DruidProfession;
import com.villagecraft.entity.professions.DrummerProfession;
import com.villagecraft.entity.professions.EnchanterProfession;
import com.villagecraft.entity.professions.EngineerProfession;
import com.villagecraft.entity.professions.FootmanProfession;
import com.villagecraft.entity.professions.GeomancerProfession;
import com.villagecraft.entity.professions.GuildmasterProfession;
import com.villagecraft.entity.professions.HerbalistProfession;
import com.villagecraft.entity.professions.InnkeeperProfession;
import com.villagecraft.entity.professions.KnightProfession;
import com.villagecraft.entity.professions.LandlordProfession;
import com.villagecraft.entity.professions.LumberjackProfession;
import com.villagecraft.entity.professions.MageProfession;
import com.villagecraft.entity.professions.ManagerProfession;
import com.villagecraft.entity.professions.MarksmanProfession;
import com.villagecraft.entity.professions.MayorProfession;
import com.villagecraft.entity.professions.MerchantProfession;
import com.villagecraft.entity.professions.MinerProfession;
import com.villagecraft.entity.professions.NurseProfession;
import com.villagecraft.entity.professions.OutpostLiasonProfession;
import com.villagecraft.entity.professions.PyrotechnicProfession;
import com.villagecraft.entity.professions.RangerProfession;
import com.villagecraft.entity.professions.RedstoneEngineerProfession;
import com.villagecraft.entity.professions.RogueProfession;
import com.villagecraft.entity.professions.SapperProfession;
import com.villagecraft.entity.professions.SingerProfession;
import com.villagecraft.entity.professions.TannerProfession;
import com.villagecraft.entity.professions.TeacherProfession;
import com.villagecraft.entity.professions.TradesmanProfession;
import com.villagecraft.entity.professions.TrapperProfession;
import com.villagecraft.entity.professions.UndertakerProfession;
import com.villagecraft.entity.professions.WarlockProfession;
import com.villagecraft.entity.professions.WeaverProfession;
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
	
	// @ALCHEMIST
	public static final RegistryObject<PointOfInterestType> ALCHEMIST_TABLE = POINTS_OF_INTEREST.register("alchemist", () -> { 	
		PointOfInterestType alchemist_table = new PointOfInterestType("alchemist_table", ModVillagerProfessions.getAllStates(ModBlocks.BLOCK_ALCHEMIST_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(alchemist_table);
	});
	
	// @ARCHITECT
	public static final RegistryObject<PointOfInterestType> DRAFTING_TABLE = POINTS_OF_INTEREST.register("drafting_table", () -> {
		PointOfInterestType draftingTable = new PointOfInterestType("drafting_table", ModVillagerProfessions.getAllStates(ModBlocks.DRAFTING_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(draftingTable);
	});
	
	// @BARD
	public static final RegistryObject<PointOfInterestType> BARD_CHAIR = POINTS_OF_INTEREST.register("bard", () -> { 	
		PointOfInterestType bardPlace = new PointOfInterestType("bard_stand", ModVillagerProfessions.getAllStates(ModBlocks.BARD_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(bardPlace);
	});
	
	// @BARKEEPER
	public static final RegistryObject<PointOfInterestType> BAR = POINTS_OF_INTEREST.register("bar", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("bar", ModVillagerProfessions.getAllStates(ModBlocks.BAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	// @BASSIST
	public static final RegistryObject<PointOfInterestType> GUITAR_STAND = POINTS_OF_INTEREST.register("guitar_stand", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("guitar_stand", ModVillagerProfessions.getAllStates(ModBlocks.GUITAR_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	// @BEEKEEPER
	public static final RegistryObject<PointOfInterestType> BEEHIVE = POINTS_OF_INTEREST.register("beehive", () -> {
		PointOfInterestType beehive = new PointOfInterestType("beehive", ModVillagerProfessions.getAllStates(net.minecraft.block.Blocks.BEEHIVE), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(beehive);
	});
	
	// @BLACKSMITH
	public static final RegistryObject<PointOfInterestType> ANVIL = POINTS_OF_INTEREST.register("anvil", () -> {
		PointOfInterestType anvil = new PointOfInterestType("anvil", ModVillagerProfessions.getAllStates(net.minecraft.block.Blocks.ANVIL), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(anvil);
	});
	
	// @BRAWLER
	public static final RegistryObject<PointOfInterestType> BRAWLER_BOX = POINTS_OF_INTEREST.register("brawler_box", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("brawler_box", ModVillagerProfessions.getAllStates(ModBlocks.BRAWLER_BOX.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	// @BREWMASTER
	public static final RegistryObject<PointOfInterestType> KEG = POINTS_OF_INTEREST.register("keg", () -> {
		PointOfInterestType keg = new PointOfInterestType("keg", ModVillagerProfessions.getAllStates(ModBlocks.BLOCK_KEG.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(keg);
	});
	
	// @BUILDER
	public static final RegistryObject<PointOfInterestType> BUILDERS_CHEST = POINTS_OF_INTEREST.register("builders_chest", () -> {
		PointOfInterestType builders_chest = new PointOfInterestType("builders_chest", ModVillagerProfessions.getAllStates(ModBlocks.BUILDERS_CHEST.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(builders_chest);
	});
	
	// @BUTCHER
	public static final RegistryObject<PointOfInterestType> BUTCHERS_BLOCK = POINTS_OF_INTEREST.register("butchers_block", () -> {
		PointOfInterestType butchers_block = new PointOfInterestType("butchers_block", ModVillagerProfessions.getAllStates(ModBlocks.BUTCHERS_BLOCK.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(butchers_block);
	});
	
	// @CAPTAIN
	public static final RegistryObject<PointOfInterestType> CAPTAINS_ARMORSTAND_BLOCK = POINTS_OF_INTEREST.register("captains_armorstand_block", () -> {
		PointOfInterestType captains_armorstand_block = new PointOfInterestType("captains_armorstand_block", ModVillagerProfessions.getAllStates(ModBlocks.CAPTAINS_ARMORSTAND_BLOCK.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(captains_armorstand_block);
	});
	
	// @CARAVANEER
	public static final RegistryObject<PointOfInterestType> CARAVAN_STOP = POINTS_OF_INTEREST.register("caravan_stop", () -> {
		PointOfInterestType caravan_stop = new PointOfInterestType("caravan_stop", ModVillagerProfessions.getAllStates(ModBlocks.CARAVAN_STOP.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(caravan_stop);
	});
	
	// @CARPENTER
	public static final RegistryObject<PointOfInterestType> SAWHORSE = POINTS_OF_INTEREST.register("sawhorse", () -> {
		PointOfInterestType sawhorse = new PointOfInterestType("sawhorse", ModVillagerProfessions.getAllStates(ModBlocks.SAWHORSE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(sawhorse);
	});
	
	// @CLERIC
	public static final RegistryObject<PointOfInterestType> ALTAR = POINTS_OF_INTEREST.register("altar", () -> {
		PointOfInterestType altar = new PointOfInterestType("altar", ModVillagerProfessions.getAllStates(ModBlocks.ALTAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(altar);
	});
	
	// @COOK
	public static final RegistryObject<PointOfInterestType> BRICK_OVEN = POINTS_OF_INTEREST.register("brick_oven", () -> {
		PointOfInterestType brick_oven = new PointOfInterestType("brick_oven", ModVillagerProfessions.getAllStates(ModBlocks.BRICK_OVEN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(brick_oven);
	});
	
	// @DIPLOMAT
	public static final RegistryObject<PointOfInterestType> EMBASSY = POINTS_OF_INTEREST.register("embassy", () -> {
		PointOfInterestType embassy = new PointOfInterestType("embassy", ModVillagerProfessions.getAllStates(ModBlocks.EMBASSY.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(embassy);
	});
	
	// @DRUID
	public static final RegistryObject<PointOfInterestType> FOREST_SHRINE = POINTS_OF_INTEREST.register("forest_shrine", () -> {
		PointOfInterestType forest_shrine = new PointOfInterestType("forest_shrine", ModVillagerProfessions.getAllStates(ModBlocks.FOREST_SHRINE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(forest_shrine);
	});
	
	// @DRUMMER
	public static final RegistryObject<PointOfInterestType> DRUMS = POINTS_OF_INTEREST.register("drums", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("drums", ModVillagerProfessions.getAllStates(ModBlocks.DRUMS.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	// @ENCHANTER
	public static final RegistryObject<PointOfInterestType> ENCHANTED_STONE = POINTS_OF_INTEREST.register("enchanted_stone", () -> {
		PointOfInterestType enchanted_stone = new PointOfInterestType("enchanted_stone", ModVillagerProfessions.getAllStates(ModBlocks.ENCHANTED_STONE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(enchanted_stone);
	});
	
	// @ENGINEER
	public static final RegistryObject<PointOfInterestType> RAIL_MACHINE = POINTS_OF_INTEREST.register("rail_machine", () -> {
		PointOfInterestType rail_machine = new PointOfInterestType("rail_machine", ModVillagerProfessions.getAllStates(ModBlocks.RAIL_MACHINE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(rail_machine);
	});
	
	// @FOOTMAN
	public static final RegistryObject<PointOfInterestType> WEAPON_STAND = POINTS_OF_INTEREST.register("weapon_stand", () -> {
		PointOfInterestType poi = new PointOfInterestType("weapon_stand", ModVillagerProfessions.getAllStates(ModBlocks.WEAPON_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @GEOMANCER
	public static final RegistryObject<PointOfInterestType> STONE_ALTAR = POINTS_OF_INTEREST.register("stone_altar", () -> {
		PointOfInterestType poi = new PointOfInterestType("stone_altar", ModVillagerProfessions.getAllStates(ModBlocks.STONE_ALTAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @GUILDMASTER
	public static final RegistryObject<PointOfInterestType> GUILDMASTER_TASK = POINTS_OF_INTEREST.register("guildmaster_task_board", () -> {
		PointOfInterestType poi = new PointOfInterestType("guildmaster_task_board", ModVillagerProfessions.getAllStates(ModBlocks.GUILD_TASKBOARD.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @HERBALIST
	public static final RegistryObject<PointOfInterestType> HERBALIST_PESTLE = POINTS_OF_INTEREST.register("herbalist_pestle", () -> {
		PointOfInterestType poi = new PointOfInterestType("herbalist_pestle", ModVillagerProfessions.getAllStates(ModBlocks.HERBALIST_PESTLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @HUNTER
	public static final RegistryObject<PointOfInterestType> TAMING_PIN = POINTS_OF_INTEREST.register("taming_pin", () -> {
		PointOfInterestType poi = new PointOfInterestType("taming_pin", ModVillagerProfessions.getAllStates(ModBlocks.TAMING_PIN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @INNKEEPER
	public static final RegistryObject<PointOfInterestType> INN = POINTS_OF_INTEREST.register("inn", () -> {
		PointOfInterestType inn = new PointOfInterestType("inn", ModVillagerProfessions.getAllStates(ModBlocks.INN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(inn);
	});
	
	// @KNIGHT
	public static final RegistryObject<PointOfInterestType> KNIGHT_STAND = POINTS_OF_INTEREST.register("knight_stand", () -> {
		PointOfInterestType poi = new PointOfInterestType("knight_stand", ModVillagerProfessions.getAllStates(ModBlocks.KNIGHT_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @LANDLORD
	public static final RegistryObject<PointOfInterestType> TITLE_OFFICE = POINTS_OF_INTEREST.register("title_office", () -> {
		PointOfInterestType title_office = new PointOfInterestType("title_office", ModVillagerProfessions.getAllStates(ModBlocks.TITLE_OFFICE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(title_office);
	});
	
	// @LEATHERWORKER
	public static final RegistryObject<PointOfInterestType> TANNERY = POINTS_OF_INTEREST.register("tannery", () -> {
		PointOfInterestType poi = new PointOfInterestType("tannery", ModVillagerProfessions.getAllStates(ModBlocks.TANNERY.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @LUMBERJACK
	public static final RegistryObject<PointOfInterestType> SAW_MILL = POINTS_OF_INTEREST.register("saw_mill", () -> {
		PointOfInterestType poi = new PointOfInterestType("saw_mill", ModVillagerProfessions.getAllStates(ModBlocks.SAW_MILL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @MAGE
	public static final RegistryObject<PointOfInterestType> ELEMENTAL_ALTAR = POINTS_OF_INTEREST.register("elemental_altar", () -> {
		PointOfInterestType poi = new PointOfInterestType("elemental_altar", ModVillagerProfessions.getAllStates(ModBlocks.ELEMENTAL_ALTAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @MANAGER
	public static final RegistryObject<PointOfInterestType> VILLAGE_MANAGEMENT = POINTS_OF_INTEREST.register("village_management", () -> {
		PointOfInterestType village_management = new PointOfInterestType("village_management", ModVillagerProfessions.getAllStates(ModBlocks.VILLAGE_MANAGER.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_management);
	});
	
	// @MARKSMAN
	public static final RegistryObject<PointOfInterestType> TARGET_BLOCK = POINTS_OF_INTEREST.register("target_block", () -> {
		PointOfInterestType poi = new PointOfInterestType("target_block", ModVillagerProfessions.getAllStates(ModBlocks.TARGET_BLOCK.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});

	
	// @MAYOR
	public static final RegistryObject<PointOfInterestType> TOWN_HALL = POINTS_OF_INTEREST.register("town_hall", () -> {
		PointOfInterestType town_hall = new PointOfInterestType("town_hall", ModVillagerProfessions.getAllStates(ModBlocks.TOWN_HALL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(town_hall);
	});
	
	// @MERCHANT
	public static final RegistryObject<PointOfInterestType> AUCTION_HOUSE = POINTS_OF_INTEREST.register("auction_house", () -> {
		PointOfInterestType auction_house = new PointOfInterestType("auction_house", ModVillagerProfessions.getAllStates(ModBlocks.AUCTION_HOUSE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(auction_house);
	});
	
	// @MINER
	public static final RegistryObject<PointOfInterestType> ORE_BOX = POINTS_OF_INTEREST.register("ore_box", () -> {
		PointOfInterestType minerWorkplace = new PointOfInterestType("ore_box", ModVillagerProfessions.getAllStates(ModBlocks.ORE_BOX.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(minerWorkplace);
	});
	
	// @NURSE
	public static final RegistryObject<PointOfInterestType> AID_STATION = POINTS_OF_INTEREST.register("aid_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("aid_station", ModVillagerProfessions.getAllStates(ModBlocks.AID_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @OUTPOST_LIASON
	public static final RegistryObject<PointOfInterestType> SUPPLY_OFFICE = POINTS_OF_INTEREST.register("supply_office", () -> {
		PointOfInterestType supply_office = new PointOfInterestType("supply_office", ModVillagerProfessions.getAllStates(ModBlocks.SUPPLY_OFFICE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(supply_office);
	});
	
	// @POTTER
	public static final RegistryObject<PointOfInterestType> POTTERS_WHEEL = POINTS_OF_INTEREST.register("potter_wheel", () -> {
		PointOfInterestType potter_wheel = new PointOfInterestType("potter_wheel", ModVillagerProfessions.getAllStates(ModBlocks.POTTERS_WHEEL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(potter_wheel);
	});
	
	// @PYROTECHNIC
	public static final RegistryObject<PointOfInterestType> PYROTECHNIC_TABLE = POINTS_OF_INTEREST.register("pyrotechnic_worktable", () -> {
		PointOfInterestType pyrotechnic_worktable = new PointOfInterestType("pyrotechnic_worktable", ModVillagerProfessions.getAllStates(ModBlocks.PYROTECHNIC_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(pyrotechnic_worktable);
	});
	
	// @RANGER
	public static final RegistryObject<PointOfInterestType> RANGER_TABLE = POINTS_OF_INTEREST.register("ranger_table", () -> {
		PointOfInterestType poi = new PointOfInterestType("ranger_table", ModVillagerProfessions.getAllStates(ModBlocks.RANGER_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});

	
	// @REDSTONE_ENGINEER
	public static final RegistryObject<PointOfInterestType> REDSTONE_TABLE = POINTS_OF_INTEREST.register("redstone_table", () -> {
		PointOfInterestType poi = new PointOfInterestType("redstone_table", ModVillagerProfessions.getAllStates(ModBlocks.REDSTONE_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @ROGUE
	public static final RegistryObject<PointOfInterestType> POISON_STATION = POINTS_OF_INTEREST.register("poison_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("poison_station", ModVillagerProfessions.getAllStates(ModBlocks.POISON_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @SAPPER
	public static final RegistryObject<PointOfInterestType> DEMOLITION_STATION = POINTS_OF_INTEREST.register("demolition_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("demolition_station", ModVillagerProfessions.getAllStates(ModBlocks.DEMOLITION_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @SINGER
	public static final RegistryObject<PointOfInterestType> MICROPHONE_STAND = POINTS_OF_INTEREST.register("microphone_stand", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("microphone_stand", ModVillagerProfessions.getAllStates(ModBlocks.MICROPHONE_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	// @TEACHER
	public static final RegistryObject<PointOfInterestType> LECTURE_STAND = POINTS_OF_INTEREST.register("lecture_stand", () -> {
		PointOfInterestType poi = new PointOfInterestType("lecture_stand", ModVillagerProfessions.getAllStates(ModBlocks.LECTURE_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @TRADER
	// @TODO currently using vanilla crafting table...
	public static final RegistryObject<PointOfInterestType> CRAFTING_TABLE = POINTS_OF_INTEREST.register("village_crafting_table", () -> {
		PointOfInterestType village_crafting_table = new PointOfInterestType("village_crafting_table", ModVillagerProfessions.getAllStates(net.minecraft.block.Blocks.CRAFTING_TABLE), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_crafting_table);
	});
	
	// @TRADESMAN
	public static final RegistryObject<PointOfInterestType> TRADESMAN_HELMET = POINTS_OF_INTEREST.register("tradesman_helmet", () -> {
		PointOfInterestType tradesman_helmet = new PointOfInterestType("tradesman_helmet", ModVillagerProfessions.getAllStates(ModBlocks.TRADESMAN_HELMET.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(tradesman_helmet);
	});
	
	// @TRAPPER
	public static final RegistryObject<PointOfInterestType> TRAPPER_STATION = POINTS_OF_INTEREST.register("trapper_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("trapper_station", ModVillagerProfessions.getAllStates(ModBlocks.TRAPPER_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @UNDERTAKER
	public static final RegistryObject<PointOfInterestType> COFFIN = POINTS_OF_INTEREST.register("coffin", () -> {
		PointOfInterestType poi = new PointOfInterestType("coffin", ModVillagerProfessions.getAllStates(ModBlocks.COFFIN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @WARLOCK
	public static final RegistryObject<PointOfInterestType> WARPED_ALTAR = POINTS_OF_INTEREST.register("warped_altar", () -> {
		PointOfInterestType poi = new PointOfInterestType("warped_altar", ModVillagerProfessions.getAllStates(ModBlocks.WARPED_ALTAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});

	
	// @WEAVER
	public static final RegistryObject<PointOfInterestType> WEAVING_STATION = POINTS_OF_INTEREST.register("weaving_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("weaving_station", ModVillagerProfessions.getAllStates(ModBlocks.WEAVING_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @WORKER
	// @TODO currently using vanilla chest...
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
    // @ALCHEMIST
    public static final RegistryObject<VillagerProfession> ALCHEMIST = PROFESSIONS.register("alchemist", () -> { 
    	return AlchemistProfession.villagerProfession("alchemist", ALCHEMIST_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @ARCHITECT
    public static final RegistryObject<VillagerProfession> ARCHITECT = PROFESSIONS.register("architect", () -> { 
    	return ArchitectProfession.villagerProfession("architect", DRAFTING_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @BARD
 	public static final RegistryObject<VillagerProfession> BARD = PROFESSIONS.register("bard", () -> {
 		return BardProfession.villagerProfession("bard", BARD_CHAIR.get(), ImmutableSet.of(), ImmutableSet.of(ModBlocks.BARD_STAND.get()), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
 	});

 	// @BARKEEPER
 	public static final RegistryObject<VillagerProfession> BAR_KEEPER = PROFESSIONS.register("barkeeper", () -> {
 		return BarkeeperProfession.villagerProfession("barkeeper", BAR.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")));
 	});
    
 	// @BASSIST
 	public static final RegistryObject<VillagerProfession> BASSIST = PROFESSIONS.register("bassist", () -> {
		return BassistProfession.villagerProfession("bassist", GUITAR_STAND.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
 	
    // @BEEKEEPER
    public static final RegistryObject<VillagerProfession> BEEKEEPER = PROFESSIONS.register("beekeeper", () -> { 
    	return BeekeeperProfession.villagerProfession("beekeeper", BEEHIVE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @BLACKSMITH
    public static final RegistryObject<VillagerProfession> BLACKSMITH = PROFESSIONS.register("blacksmith", () -> { 
    	return BeekeeperProfession.villagerProfession("blacksmith", ANVIL.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @BRAWLER
 	public static final RegistryObject<VillagerProfession> BRAWLER = PROFESSIONS.register("brawler", () -> {
 		return BrawlerProfession.villagerProfession("brawler", BRAWLER_BOX.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
 	});
 	
 	// @BREWMASTER
  	public static final RegistryObject<VillagerProfession> BREWMASTER = PROFESSIONS.register("brewmaster", () -> {
  		return BrewmasterProfession.villagerProfession("brewmaster", KEG.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
  	});
    
	// @BUILDER
  	public static final RegistryObject<VillagerProfession> BUILDER = PROFESSIONS.register("builder", () -> {
		return BuilderProfession.villagerProfession("builder", BUILDERS_CHEST.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
  	
  	// @BUTCHER
  	public static final RegistryObject<VillagerProfession> BUTCHER = PROFESSIONS.register("butcher", () -> {
		return BuilderProfession.villagerProfession("butcher", BUTCHERS_BLOCK.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
  	
  	// @CAPTAIN
  	public static final RegistryObject<VillagerProfession> CAPTAIN = PROFESSIONS.register("captain", () -> {
		return BuilderProfession.villagerProfession("captain", CAPTAINS_ARMORSTAND_BLOCK.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
  	
  	// @CARAVANEER
	public static final RegistryObject<VillagerProfession> CARAVANEER = PROFESSIONS.register("caravaneer", () -> {
		return CaravaneerProfession.villagerProfession("caravaneer", CARAVAN_STOP.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @CARPENTER
	public static final RegistryObject<VillagerProfession> CARPENTER = PROFESSIONS.register("carpenter", () -> {
		return CarpenterProfession.villagerProfession("carpenter", SAWHORSE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @CLERIC
	public static final RegistryObject<VillagerProfession> CLERIC = PROFESSIONS.register("cleric", () -> {
		return ClericProfession.villagerProfession("cleric", ALTAR.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @COOK
	public static final RegistryObject<VillagerProfession> COOK = PROFESSIONS.register("cook", () -> {
		return CookProfession.villagerProfession("cook", BRICK_OVEN.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @DIPLOMAT
	public static final RegistryObject<VillagerProfession> DIPLOMAT = PROFESSIONS.register("diplomat", () -> {
		return DiplomatProfession.villagerProfession("diplomat", EMBASSY.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @DRUID
	public static final RegistryObject<VillagerProfession> DRUID = PROFESSIONS.register("druid", () -> {
		return DruidProfession.villagerProfession("druid", FOREST_SHRINE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @DRUMMER
	public static final RegistryObject<VillagerProfession> DRUMMER = PROFESSIONS.register("drummer", () -> {
		return DrummerProfession.villagerProfession("drummer", DRUMS.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @ENCHANTER
	public static final RegistryObject<VillagerProfession> ENCHANTER = PROFESSIONS.register("enchanter", () -> {
		return EnchanterProfession.villagerProfession("enchanter", ENCHANTED_STONE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @ENGINEER
	public static final RegistryObject<VillagerProfession> ENGINEER = PROFESSIONS.register("engineer", () -> {
		return EngineerProfession.villagerProfession("engineer", RAIL_MACHINE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @FOOTMAN
	public static final RegistryObject<VillagerProfession> FOOTMAN = PROFESSIONS.register("footman", () -> {
		return FootmanProfession.villagerProfession("footman", WEAPON_STAND.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @GEOMANCER
	public static final RegistryObject<VillagerProfession> GEOMANCER = PROFESSIONS.register("geomancer", () -> {
		return GeomancerProfession.villagerProfession("geomancer", STONE_ALTAR.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @GUILDMASTER
	public static final RegistryObject<VillagerProfession> GUILDMASTER = PROFESSIONS.register("guildmaster", () -> {
		return GuildmasterProfession.villagerProfession("guildmaster", GUILDMASTER_TASK.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @HERBALIST
	public static final RegistryObject<VillagerProfession> HERBALIST = PROFESSIONS.register("herbalist", () -> {
		return HerbalistProfession.villagerProfession("herbalist", HERBALIST_PESTLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @HERBALIST
		public static final RegistryObject<VillagerProfession> HUNTER = PROFESSIONS.register("hunter", () -> {
			return HerbalistProfession.villagerProfession("hunter", TAMING_PIN.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
		});
	
	// @INNKEEPER
	public static final RegistryObject<VillagerProfession> INNKEEPER = PROFESSIONS.register("innkeeper", () -> {
		return InnkeeperProfession.villagerProfession("innkeeper", INN.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @KNIGHT
	public static final RegistryObject<VillagerProfession> KNIGHT = PROFESSIONS.register("knight", () -> {
		return KnightProfession.villagerProfession("knight", KNIGHT_STAND.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @LANDLORD
	public static final RegistryObject<VillagerProfession> LANDLORD = PROFESSIONS.register("landlord", () -> {
		return LandlordProfession.villagerProfession("landlord", TITLE_OFFICE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @LEATHERWORKER
	public static final RegistryObject<VillagerProfession> LEATHERWORKER = PROFESSIONS.register("leatherworker", () -> {
		return TannerProfession.villagerProfession("leatherworker", TANNERY.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @LUMBERJACK
	public static final RegistryObject<VillagerProfession> LUMBERJACK = PROFESSIONS.register("lumberjack", () -> {
		return LumberjackProfession.villagerProfession("lumberjack", SAW_MILL.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @MAGE
	public static final RegistryObject<VillagerProfession> MAGE = PROFESSIONS.register("mage", () -> {
		return MageProfession.villagerProfession("mage", ELEMENTAL_ALTAR.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @MANAGER
	public static final RegistryObject<VillagerProfession> MANAGER = PROFESSIONS.register("manager", () -> {
		return ManagerProfession.villagerProfession("manager", VILLAGE_MANAGEMENT.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @MARKSMAN
	public static final RegistryObject<VillagerProfession> MARKSMAN = PROFESSIONS.register("marksman", () -> {
		return MarksmanProfession.villagerProfession("marksman", TARGET_BLOCK.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @MAYOR
	public static final RegistryObject<VillagerProfession> MAYOR = PROFESSIONS.register("mayor", () -> {
		return MayorProfession.villagerProfession("mayor", TOWN_HALL.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @MERCHANT
    public static final RegistryObject<VillagerProfession> MERCHANT = PROFESSIONS.register("merchant", () -> {
    	return MerchantProfession.villagerProfession("merchant", CRAFTING_TABLE.get(), MerchantProfession.PROFESSION_ITEM, MerchantProfession.PROFESSION_BLOCK, new SoundEvent(new ResourceLocation("vcm", "villager_socialize")) );	
    });	
    
	// @MINER
	public static final RegistryObject<VillagerProfession> MINER = PROFESSIONS.register("miner", () -> {
		return MinerProfession.villagerProfession("miner", ORE_BOX.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")));
	});
	
	// @NURSE
	public static final RegistryObject<VillagerProfession> NURSE = PROFESSIONS.register("nurse", () -> {
		return NurseProfession.villagerProfession("nurse", AID_STATION.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @OUTPOST_LIASON
	public static final RegistryObject<VillagerProfession> OUTPOST_LIASON = PROFESSIONS.register("outpost_liason", () -> {
		return OutpostLiasonProfession.villagerProfession("outpost_liason", SUPPLY_OFFICE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @POTTER
	public static final RegistryObject<VillagerProfession> POTTER = PROFESSIONS.register("potter", () -> {
		return LandlordProfession.villagerProfession("potter", POTTERS_WHEEL.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @PYROTECHNIC
	public static final RegistryObject<VillagerProfession> PYROTECHNIC = PROFESSIONS.register("pyrotechnic", () -> {
		return PyrotechnicProfession.villagerProfession("pyrotechnic", PYROTECHNIC_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @RANGER
	public static final RegistryObject<VillagerProfession> RANGER = PROFESSIONS.register("ranger", () -> {
		return RangerProfession.villagerProfession("ranger", RANGER_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @REDSTONE_ENGINEER
	public static final RegistryObject<VillagerProfession> REDSTONE_ENGINEER = PROFESSIONS.register("redstone_engineer", () -> {
		return RedstoneEngineerProfession.villagerProfession("redstone_engineer", REDSTONE_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @ROGUE
	public static final RegistryObject<VillagerProfession> ROGUE = PROFESSIONS.register("rogue", () -> {
		return RogueProfession.villagerProfession("rogue", POISON_STATION.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @SAPPER
	public static final RegistryObject<VillagerProfession> SAPPER = PROFESSIONS.register("sapper", () -> {
		return SapperProfession.villagerProfession("sapper", DEMOLITION_STATION.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @SINGER
	public static final RegistryObject<VillagerProfession> SINGER = PROFESSIONS.register("singer", () -> {
		return SingerProfession.villagerProfession("singer", MICROPHONE_STAND.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @TEACHER
	public static final RegistryObject<VillagerProfession> TEACHER = PROFESSIONS.register("teacher", () -> {
		return TeacherProfession.villagerProfession("teacher", LECTURE_STAND.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
	});
	
	// @TRADER
	public static final RegistryObject<VillagerProfession> TRADER = PROFESSIONS.register("trader", () -> { 
    	return TradesmanProfession.villagerProfession("trader", AUCTION_HOUSE.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
	
	// @TRADESMAN
    public static final RegistryObject<VillagerProfession> TRADESMAN = PROFESSIONS.register("tradesman", () -> { 
    	return TradesmanProfession.villagerProfession("tradesman", TRADESMAN_HELMET.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @TRAPPER
    public static final RegistryObject<VillagerProfession> TRAPPER = PROFESSIONS.register("trapper", () -> { 
    	return TrapperProfession.villagerProfession("trapper", TRAPPER_STATION.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @UNDERTAKER
    public static final RegistryObject<VillagerProfession> UNDERTAKER = PROFESSIONS.register("undertaker", () -> { 
    	return UndertakerProfession.villagerProfession("undertaker", COFFIN.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @WARLOCK
    public static final RegistryObject<VillagerProfession> WARLOCK = PROFESSIONS.register("warlock", () -> { 
    	return WarlockProfession.villagerProfession("warlock", WARPED_ALTAR.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @WEAVER
    public static final RegistryObject<VillagerProfession> WEAVER = PROFESSIONS.register("weaver", () -> { 
    	return WeaverProfession.villagerProfession("weaver", WEAVING_STATION.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
    });
    
    // @WORKER
    public static final RegistryObject<VillagerProfession> WORKER = PROFESSIONS.register("worker", () -> {
    	return WorkerProfession.villagerProfession("worker", CHEST.get(), WorkerProfession.PROFESSION_ITEM, WorkerProfession.PROFESSION_BLOCK, new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );	
    });
    

}

