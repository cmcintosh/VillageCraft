package com.villagecraft.init;

import com.villagecraft.VillageCraft;
import com.villagecraft.block.BlockAideStation;
import com.villagecraft.block.BlockAlchemistTable;
import com.villagecraft.block.BlockAltar;
import com.villagecraft.block.BlockAuctionHouse;
import com.villagecraft.block.BlockBar;
import com.villagecraft.block.BlockBardStand;
import com.villagecraft.block.BlockBrawlerEquipment;
import com.villagecraft.block.BlockBrickOven;
import com.villagecraft.block.BlockBuildersChest;
import com.villagecraft.block.BlockButchersBlock;
import com.villagecraft.block.BlockCaptainsArmorBlock;
import com.villagecraft.block.BlockCaravanStop;
import com.villagecraft.block.BlockCoffin;
import com.villagecraft.block.BlockDeliveryStation;
import com.villagecraft.block.BlockDemolitionStation;
import com.villagecraft.block.BlockDraftingTable;
import com.villagecraft.block.BlockDrums;
import com.villagecraft.block.BlockElementalAltar;
import com.villagecraft.block.BlockEmbassy;
import com.villagecraft.block.BlockEnchantedStone;
import com.villagecraft.block.BlockForestShrine;
import com.villagecraft.block.BlockGuildTaskBoard;
import com.villagecraft.block.BlockGuitarStand;
import com.villagecraft.block.BlockHerbalistPestle;
import com.villagecraft.block.BlockInn;
import com.villagecraft.block.BlockKeg;
import com.villagecraft.block.BlockKnightStand;
import com.villagecraft.block.BlockLectureStand;
import com.villagecraft.block.BlockMerchantStall;
import com.villagecraft.block.BlockMicrophoneStand;
import com.villagecraft.block.BlockOreBox;
import com.villagecraft.block.BlockPoisonStation;
import com.villagecraft.block.BlockPotterWheel;
import com.villagecraft.block.BlockPyrotechnicTable;
import com.villagecraft.block.BlockRailMachine;
import com.villagecraft.block.BlockRangerCraftingTable;
import com.villagecraft.block.BlockRedstoneWorkstation;
import com.villagecraft.block.BlockSawHorse;
import com.villagecraft.block.BlockSawMill;
import com.villagecraft.block.BlockStoneAltar;
import com.villagecraft.block.BlockSupplyOffice;
import com.villagecraft.block.BlockTamingPin;
import com.villagecraft.block.BlockTannery;
import com.villagecraft.block.BlockTargetBlock;
import com.villagecraft.block.BlockTitleOffice;
import com.villagecraft.block.BlockTownHall;
import com.villagecraft.block.BlockTrapStation;
import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.block.BlockVillageManager;
import com.villagecraft.block.BlockWarpedAltar;
import com.villagecraft.block.BlockWeaponStand;
import com.villagecraft.block.BlockWeavingStation;
import com.villagecraft.block.TradesmanHelmet;
import com.villagecraft.entity.professions.AlchemistProfession;
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
import com.villagecraft.item.blockitems.ItemVillageCenter;
import com.villagecraft.util.ProfessionUtils;
import com.villagecraft.util.RandomTradeBuilder;
import com.villagecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);
	public static final ItemGroup PROFESSION_BLOCKS = new ItemGroup("professions") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.NATION_CHARTER.get());
		}
	};
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);
	public static final DeferredRegister<PointOfInterestType> POINTS_OF_INTEREST = DeferredRegister.create(ForgeRegistries.POI_TYPES, Reference.MODID);
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Reference.MODID);
	
	/**
	 * Blocks
	 */
	public static final RegistryObject<BlockAlchemistTable> BLOCK_ALCHEMIST_TABLE = (RegistryObject<BlockAlchemistTable>) BLOCKS.register("alchemist_table", () -> new BlockAlchemistTable(BlockAlchemistTable.properties));
	public static final RegistryObject<BlockDraftingTable> BLOCK_DRAFTING_TABLE = (RegistryObject<BlockDraftingTable>) BLOCKS.register("drafting_table", () -> new BlockDraftingTable(BlockDraftingTable.properties));
	public static final RegistryObject<BlockBardStand> BLOCK_BARD_STAND = (RegistryObject<BlockBardStand>) BLOCKS.register("bard_stand", () -> new BlockBardStand(BlockBardStand.properties));
	public static final RegistryObject<BlockBar> BLOCK_BAR = (RegistryObject<BlockBar>) BLOCKS.register("bar", () -> new BlockBar(BlockBar.properties));
	public static final RegistryObject<BlockGuitarStand> BLOCK_GUITAR_STAND = (RegistryObject<BlockGuitarStand>) BLOCKS.register("guitar_stand", () -> new BlockGuitarStand(BlockGuitarStand.properties));
	public static final RegistryObject<BlockBrawlerEquipment> BLOCK_BRAWLER_BOX = (RegistryObject<BlockBrawlerEquipment>) BLOCKS.register("brawler_box", () -> new BlockBrawlerEquipment(BlockBrawlerEquipment.properties));
	public static final RegistryObject<BlockKeg> BLOCK_KEG = (RegistryObject<BlockKeg>) BLOCKS.register("keg", () -> new BlockKeg(BlockKeg.properties));
	public static final RegistryObject<BlockBuildersChest> BLOCK_BUILDERS_CHEST = (RegistryObject<BlockBuildersChest>) BLOCKS.register("builders_chest", () -> new BlockBuildersChest(BlockBuildersChest.properties));
	public static final RegistryObject<BlockButchersBlock> BLOCK_BUTCHERS_BLOCK = (RegistryObject<BlockButchersBlock>) BLOCKS.register("butchers_block", () -> new BlockButchersBlock(BlockButchersBlock.properties));
  	public static final RegistryObject<BlockCaptainsArmorBlock> BLOCK_CAPTAINS_ARMORSTAND = (RegistryObject<BlockCaptainsArmorBlock>) BLOCKS.register("captains_armorstand_block", () -> new BlockCaptainsArmorBlock(BlockCaptainsArmorBlock.properties));
	public static final RegistryObject<BlockCaravanStop> BLOCK_CARAVAN_STOP = (RegistryObject<BlockCaravanStop>) BLOCKS.register("caravan_stop", () -> new BlockCaravanStop(BlockCaravanStop.properties));
	public static final RegistryObject<BlockSawHorse> BLOCK_SAWHORSE = (RegistryObject<BlockSawHorse>) BLOCKS.register("sawhorse", () -> new BlockSawHorse(BlockSawHorse.properties));
	public static final RegistryObject<BlockAltar> BLOCK_ALTAR = (RegistryObject<BlockAltar>) BLOCKS.register("altar", () -> new BlockAltar(BlockAltar.properties));
	public static final RegistryObject<BlockBrickOven> BLOCK_BRICK_OVEN = (RegistryObject<BlockBrickOven>) BLOCKS.register("brick_oven", () -> new BlockBrickOven(BlockBrickOven.properties));
	public static final RegistryObject<BlockEmbassy> BLOCK_EMBASSY = (RegistryObject<BlockEmbassy>) BLOCKS.register("embassy", () -> new BlockEmbassy(BlockEmbassy.properties));
	public static final RegistryObject<BlockForestShrine> BLOCK_FOREST_SHRINE = (RegistryObject<BlockForestShrine>) BLOCKS.register("forest_shrine", () -> new BlockForestShrine(BlockForestShrine.properties));
	public static final RegistryObject<BlockDrums> BLOCK_DRUMS = (RegistryObject<BlockDrums>) BLOCKS.register("drums", () -> new BlockDrums(BlockDrums.properties));	
	public static final RegistryObject<BlockEnchantedStone> BLOCK_ENCHANTED_STONE = (RegistryObject<BlockEnchantedStone>) BLOCKS.register("enchanted_stone", () -> new BlockEnchantedStone(BlockEnchantedStone.properties));
	public static final RegistryObject<BlockRailMachine> BLOCK_RAIL_MACHINE = (RegistryObject<BlockRailMachine>) BLOCKS.register("rail_machine", () -> new BlockRailMachine(BlockRailMachine.properties));
	public static final RegistryObject<BlockWeaponStand> BLOCK_WEAPON_STAND = (RegistryObject<BlockWeaponStand>) BLOCKS.register("weapon_stand", () -> new BlockWeaponStand(BlockWeaponStand.properties));
	public static final RegistryObject<BlockStoneAltar> BLOCK_STONE_ALTAR = (RegistryObject<BlockStoneAltar>) BLOCKS.register("stone_altar", () -> new BlockStoneAltar(BlockStoneAltar.properties));
	public static final RegistryObject<BlockGuildTaskBoard> BLOCK_GUILD_TASKBOARD = (RegistryObject<BlockGuildTaskBoard>) BLOCKS.register("guild_task_board", () -> new BlockGuildTaskBoard(BlockGuildTaskBoard.properties));
	public static final RegistryObject<BlockHerbalistPestle> BLOCK_HERBALIST_PESTLE = (RegistryObject<BlockHerbalistPestle>) BLOCKS.register("herbalist_pestle", () -> new BlockHerbalistPestle(BlockHerbalistPestle.properties));
	public static final RegistryObject<BlockTamingPin> BLOCK_TAMING_PEN = (RegistryObject<BlockTamingPin>) BLOCKS.register("taming_pen", () -> new BlockTamingPin(BlockTamingPin.properties));
	public static final RegistryObject<BlockInn> BLOCK_INN = (RegistryObject<BlockInn>) BLOCKS.register("inn", () -> new BlockInn(BlockInn.properties));
	public static final RegistryObject<BlockKnightStand> BLOCK_KNIGHT_STAND = (RegistryObject<BlockKnightStand>) BLOCKS.register("knight_stand", () -> new BlockKnightStand(BlockKnightStand.properties));
	public static final RegistryObject<BlockTitleOffice> BLOCK_TITLE_OFFICE = (RegistryObject<BlockTitleOffice>) BLOCKS.register("title_office", () -> new BlockTitleOffice(BlockTitleOffice.properties));
	public static final RegistryObject<BlockTannery> BLOCK_TANNERY = (RegistryObject<BlockTannery>) BLOCKS.register("tannery", () -> new BlockTannery(BlockTannery.properties));
	public static final RegistryObject<BlockSawMill> BLOCK_SAW_MILL = (RegistryObject<BlockSawMill>) BLOCKS.register("saw_mill", () -> new BlockSawMill(BlockSawMill.properties));
	public static final RegistryObject<BlockElementalAltar> BLOCK_ELEMENTAL_ALTAR = (RegistryObject<BlockElementalAltar>) BLOCKS.register("elemental_altar", () -> new BlockElementalAltar(BlockElementalAltar.properties));
	public static final RegistryObject<BlockVillageManager> BLOCK_VILLAGE_MANAGER = (RegistryObject<BlockVillageManager>) BLOCKS.register("village_manager", () -> new BlockVillageManager(BlockVillageManager.properties));
	public static final RegistryObject<BlockTargetBlock> BLOCK_TARGET_BLOCK = (RegistryObject<BlockTargetBlock>) BLOCKS.register("target_block", () -> new BlockTargetBlock(BlockTargetBlock.properties));
	public static final RegistryObject<BlockTownHall> BLOCK_TOWN_HALL = (RegistryObject<BlockTownHall>) BLOCKS.register("town_hall", () -> new BlockTownHall(BlockTownHall.properties));
	public static final RegistryObject<BlockAuctionHouse> BLOCK_AUCTION_HOUSE = (RegistryObject<BlockAuctionHouse>) BLOCKS.register("auction_house", () -> new BlockAuctionHouse(BlockAuctionHouse.properties));
	public static final RegistryObject<BlockOreBox> BLOCK_ORE_BOX = (RegistryObject<BlockOreBox>) BLOCKS.register("ore_box", () -> new BlockOreBox(BlockOreBox.properties));
	public static final RegistryObject<BlockAideStation> BLOCK_AID_STATION = (RegistryObject<BlockAideStation>) BLOCKS.register("aid_station", () -> new BlockAideStation(BlockAideStation.properties));
	public static final RegistryObject<BlockSupplyOffice> BLOCK_SUPPLY_OFFICE = (RegistryObject<BlockSupplyOffice>) BLOCKS.register("supply_office", () -> new BlockSupplyOffice(BlockSupplyOffice.properties));
	public static final RegistryObject<BlockPotterWheel> BLOCK_POTTERS_WHEEL = (RegistryObject<BlockPotterWheel>) BLOCKS.register("potter_wheel", () -> new BlockPotterWheel(BlockPotterWheel.properties));
	public static final RegistryObject<BlockPyrotechnicTable> BLOCK_PYROTECHNIC_TABLE = (RegistryObject<BlockPyrotechnicTable>) BLOCKS.register("pyrotechnic_worktable", () -> new BlockPyrotechnicTable(BlockPyrotechnicTable.properties));
	public static final RegistryObject<BlockRangerCraftingTable> BLOCK_RANGER_TABLE = (RegistryObject<BlockRangerCraftingTable>) BLOCKS.register("ranger_table", () -> new BlockRangerCraftingTable(BlockRangerCraftingTable.properties));
	public static final RegistryObject<BlockRedstoneWorkstation> BLOCK_REDSTONE_TABLE = (RegistryObject<BlockRedstoneWorkstation>) BLOCKS.register("redstone_workstation", () -> new BlockRedstoneWorkstation(BlockRedstoneWorkstation.properties));
	public static final RegistryObject<BlockPoisonStation> BLOCK_POISON_STATION = (RegistryObject<BlockPoisonStation>) BLOCKS.register("poison_station", () -> new BlockPoisonStation(BlockPoisonStation.properties));
	public static final RegistryObject<BlockDemolitionStation> BLOCK_DEMOLITION_STATION = (RegistryObject<BlockDemolitionStation>) BLOCKS.register("demolition_station", () -> new BlockDemolitionStation(BlockDemolitionStation.properties));
	public static final RegistryObject<BlockMicrophoneStand> BLOCK_MICROPHONE_STAND = (RegistryObject<BlockMicrophoneStand>) BLOCKS.register("microphone_stand", () -> new BlockMicrophoneStand(BlockMicrophoneStand.properties));
	public static final RegistryObject<BlockLectureStand> BLOCK_LECTURE_STAND = (RegistryObject<BlockLectureStand>) BLOCKS.register("lecture_stand", () -> new BlockLectureStand(BlockLectureStand.properties));
	public static final RegistryObject<BlockMerchantStall> BLOCK_MERCHANT_STALL = (RegistryObject<BlockMerchantStall>) BLOCKS.register("merchant_stall", () -> new BlockMerchantStall(BlockMerchantStall.properties));
	public static final RegistryObject<TradesmanHelmet> BLOCK_TRADESMAN_HELMET = (RegistryObject<TradesmanHelmet>) BLOCKS.register("tradesman_helmet", () -> new TradesmanHelmet(TradesmanHelmet.properties));	
    public static final RegistryObject<BlockTrapStation> BLOCK_TRAPPER_STATION = (RegistryObject<BlockTrapStation>) BLOCKS.register("trapper_station", () -> new BlockTrapStation(BlockTrapStation.properties));
    public static final RegistryObject<BlockCoffin> BLOCK_COFFIN = (RegistryObject<BlockCoffin>) BLOCKS.register("coffin", () -> new BlockCoffin(BlockCoffin.properties));
    public static final RegistryObject<BlockWarpedAltar> BLOCK_WARPED_ALTAR = (RegistryObject<BlockWarpedAltar>) BLOCKS.register("warped_altar", () -> new BlockWarpedAltar(BlockWarpedAltar.properties));
    public static final RegistryObject<BlockWeavingStation> BLOCK_WEAVING_STATION = (RegistryObject<BlockWeavingStation>) BLOCKS.register("weaving_station", () -> new BlockWeavingStation(BlockWeavingStation.properties));
    public static final RegistryObject<BlockDeliveryStation> BLOCK_DELIVERY_STATION = (RegistryObject<BlockDeliveryStation>) BLOCKS.register("delivery_station", () -> new BlockDeliveryStation(BlockDeliveryStation.properties));
	public static final RegistryObject<BlockVillageCenter> BLOCK_VILLAGE_CENTER = (RegistryObject<BlockVillageCenter>) BLOCKS.register("village_center", () -> new BlockVillageCenter(BlockVillageCenter.properties));
	
	
	
	/**
	 * Block Items
	 */
     public static final RegistryObject<Item> ITEM_ALCHEMIST_TABLE = ITEMS.register("alchemist_table", () -> ( (new BlockItem(  BLOCK_ALCHEMIST_TABLE.get(), BLOCK_ALCHEMIST_TABLE.get().item_properties))) );
    
    // @ARCHITECT
    public static final RegistryObject<Item> ITEM_DRAFTING_TABLE = ITEMS.register("drafting_table", () -> ((new BlockItem( BLOCK_DRAFTING_TABLE.get(), BLOCK_DRAFTING_TABLE.get().item_properties))));
    
    // @BARD
    public static final RegistryObject<Item> ITEM_BARD_STAND = ITEMS.register("bard_stand", () -> ( (new BlockItem(  BLOCK_BARD_STAND.get(), BLOCK_BARD_STAND.get().item_properties))) );
    
    // @BARKEEPER
    public static final RegistryObject<Item> ITEM_BAR = ITEMS.register("bar", () -> ( (new BlockItem(  BLOCK_BAR.get(), BLOCK_BAR.get().item_properties))) );
    
    // @BASSIST
    public static final RegistryObject<Item> ITEM_GUITAR_STAND = ITEMS.register("guitar_stand", () -> ( (new BlockItem(  BLOCK_GUITAR_STAND.get(), BLOCK_GUITAR_STAND.get().item_properties))) );
    
    // @BEEKEEPER
    public static final RegistryObject<Item> ITEM_BEEKEEPER_ITEM = ITEMS.register("beekeeper_station", () -> ( (new BlockItem(  net.minecraft.block.Blocks.BEEHIVE, BLOCK_GUITAR_STAND.get().item_properties))) );
    
    // @BLACKSMITH
    public static final RegistryObject<Item> ITEM_ANVIL_ITEM = ITEMS.register("blacksmith_station", () -> ( (new BlockItem(  net.minecraft.block.Blocks.ANVIL, BLOCK_GUITAR_STAND.get().item_properties))) );
    
    // @BRAWLER
    public static final RegistryObject<Item> ITEM_BRALWER_BOX = ITEMS.register("brawler_box", () -> ( (new BlockItem(  BLOCK_BRAWLER_BOX.get(), BLOCK_BRAWLER_BOX.get().item_properties))) );
    
    // @BREWMASTER
    public static final RegistryObject<Item> ITEM_KEG = ITEMS.register("keg", () -> ( (new BlockItem(  BLOCK_KEG.get(), BLOCK_KEG.get().item_properties))) );
    
    // @BUILDER
    public static final RegistryObject<Item> ITEM_BUILDERS_CHEST = ITEMS.register("builders_chest", () -> ( (new BlockItem(  BLOCK_BUILDERS_CHEST.get(), BLOCK_BUILDERS_CHEST.get().item_properties))) );
    
    // @BUTCHER
    public static final RegistryObject<Item> ITEM_BUTCHERS_BLOCK = ITEMS.register("butchers_block", () -> ( (new BlockItem(  BLOCK_BUTCHERS_BLOCK.get(), BLOCK_BUTCHERS_BLOCK.get().item_properties))) );
    
    // @CAPTAIN
    public static final RegistryObject<Item> ITEM_CAPTAINS_ARMORSTAND_BLOCK = ITEMS.register("captains_armorstand_block", () -> ( (new BlockItem(  BLOCK_CAPTAINS_ARMORSTAND.get(), BLOCK_CAPTAINS_ARMORSTAND.get().item_properties))) );
    
    // @CARAVANEER
    public static final RegistryObject<Item> ITEM_CARAVAN_STOP = ITEMS.register("caravan_stop", () -> ( (new BlockItem(  BLOCK_CARAVAN_STOP.get(), BLOCK_CARAVAN_STOP.get().item_properties))) );
    
    // @CARPENTER
    public static final RegistryObject<Item> ITEM_SAWHORSE = ITEMS.register("sawhorse", () -> ( (new BlockItem(  BLOCK_SAWHORSE.get(), BLOCK_SAWHORSE.get().item_properties))) );
    
    // @CLERIC
    public static final RegistryObject<Item> ITEM_ALTAR = ITEMS.register("altar", () -> ( (new BlockItem(  BLOCK_ALTAR.get(), BLOCK_ALTAR.get().item_properties))) );
    
    // @COOK
    public static final RegistryObject<Item> ITEM_BRICK_OVEN = ITEMS.register("brick_oven", () -> ( (new BlockItem(  BLOCK_BRICK_OVEN.get(), BLOCK_BRICK_OVEN.get().item_properties))) );
    
    // @DIPLOMAT
    public static final RegistryObject<Item> ITEM_EMBASSY = ITEMS.register("embassy", () -> ( (new BlockItem(  BLOCK_EMBASSY.get(), BLOCK_EMBASSY.get().item_properties))) );
    
    // @DRUID
    public static final RegistryObject<Item> ITEM_FOREST_SHRINE = ITEMS.register("forest_shrine", () -> ( (new BlockItem(  BLOCK_FOREST_SHRINE.get(), BLOCK_FOREST_SHRINE.get().item_properties))) );
    
    // @DRUMMER
    public static final RegistryObject<Item> ITEM_DRUMS = ITEMS.register("drums", () -> ( (new BlockItem(  BLOCK_DRUMS.get(), BLOCK_DRUMS.get().item_properties))) );
    
    // @ENCHANTER
    public static final RegistryObject<Item> ITEM_ENCHANTED_STONE = ITEMS.register("enchanted_stone", () -> ( (new BlockItem(  BLOCK_ENCHANTED_STONE.get(), BLOCK_ENCHANTED_STONE.get().item_properties))) );
    
    // @ENGINEER
    public static final RegistryObject<Item> ITEM_RAIL_MACHINE = ITEMS.register("rail_machine", () -> ( (new BlockItem(  BLOCK_RAIL_MACHINE.get(), BLOCK_RAIL_MACHINE.get().item_properties))) );
    
    // @FOOTMAN
    public static final RegistryObject<Item> ITEM_WEAPON_STAND = ITEMS.register("weapon_stand", () -> ( (new BlockItem(  BLOCK_WEAPON_STAND.get(), BLOCK_WEAPON_STAND.get().item_properties))) );
    
    // @GEOMANCER
    public static final RegistryObject<Item> ITEM_STONE_ALTAR = ITEMS.register("stone_altar", () -> ( (new BlockItem(  BLOCK_STONE_ALTAR.get(), BLOCK_STONE_ALTAR.get().item_properties))) );
    
    // @GUILDMASTER
    public static final RegistryObject<Item> ITEM_GUILD_TASKBOARD = ITEMS.register("guild_taskboard", () -> ( (new BlockItem(  BLOCK_GUILD_TASKBOARD.get(), BLOCK_GUILD_TASKBOARD.get().item_properties))) );
    
    // @HERBALIST
    public static final RegistryObject<Item> ITEM_HERBALIST_PESTLE = ITEMS.register("herbalist_pestle", () -> ( (new BlockItem(  BLOCK_HERBALIST_PESTLE.get(), BLOCK_HERBALIST_PESTLE.get().item_properties))) );
    
    // @HUNTER
    public static final RegistryObject<Item> ITEM_TAMING_PEN = ITEMS.register("taming_pen", () -> ( (new BlockItem(  BLOCK_TAMING_PEN.get(), BLOCK_TAMING_PEN.get().item_properties))) );
    
    // @INNKEEPER
    public static final RegistryObject<Item> ITEM_INN = ITEMS.register("inn", () -> ( (new BlockItem(  BLOCK_INN.get(), BLOCK_INN.get().item_properties))) );
    
    // @KNIGHT
    public static final RegistryObject<Item> ITEM_KNIGHT_STAND = ITEMS.register("knight_stand", () -> ( (new BlockItem(  BLOCK_KNIGHT_STAND.get(), BLOCK_KNIGHT_STAND.get().item_properties))) );
    
    // @LANDLORD
    public static final RegistryObject<Item> ITEM_TITLE_OFFICE = ITEMS.register("title_office", () -> ( (new BlockItem(  BLOCK_TITLE_OFFICE.get(), BLOCK_TITLE_OFFICE.get().item_properties))) );
    
    // @LEATHERWORKER
    public static final RegistryObject<Item> ITEM_TANNERY = ITEMS.register("tannery", () -> ( (new BlockItem(  BLOCK_TANNERY.get(), BLOCK_TANNERY.get().item_properties))) );
    
    // @LUMBERJACK
    public static final RegistryObject<Item> ITEM_SAW_MILL = ITEMS.register("sawmill", () -> ( (new BlockItem(  BLOCK_SAW_MILL.get(), BLOCK_SAW_MILL.get().item_properties))) );
    
    // @MAGE
    public static final RegistryObject<Item> ITEM_ELEMENTAL_ALTAR = ITEMS.register("elemental_altar", () -> ( (new BlockItem(  BLOCK_ELEMENTAL_ALTAR.get(), BLOCK_ELEMENTAL_ALTAR.get().item_properties))) );
    
    // @MANAGER
    public static final RegistryObject<Item> ITEM_VILLAGE_MANAGER = ITEMS.register("village_manager", () -> ( (new BlockItem(  BLOCK_VILLAGE_MANAGER.get(), BLOCK_VILLAGE_MANAGER.get().item_properties))) );
    
    // @MARKSMAN
    public static final RegistryObject<Item> ITEM_TARGET_BLOCK = ITEMS.register("target_block", () -> ( (new BlockItem(  BLOCK_TARGET_BLOCK.get(), BLOCK_TARGET_BLOCK.get().item_properties))) );
    
    // @MAYOR
    public static final RegistryObject<Item> ITEM_TOWN_HALL = ITEMS.register("town_hall", () -> ( (new BlockItem(  BLOCK_TOWN_HALL.get(), BLOCK_TOWN_HALL.get().item_properties))) );
    
    // @MERCHANT
    public static final RegistryObject<Item> ITEM_AUCTION_HOUSE = ITEMS.register("auction_house", () -> ( (new BlockItem(  BLOCK_AUCTION_HOUSE.get(), BLOCK_AUCTION_HOUSE.get().item_properties))) );
    
    // @MINER
    public static final RegistryObject<Item> ITEM_ORE_BOX = ITEMS.register("ore_box", () -> ( (new BlockItem(  BLOCK_ORE_BOX.get(), BLOCK_ORE_BOX.get().item_properties))) );
    
    // @NURSE
    public static final RegistryObject<Item> ITEM_AID_STATION = ITEMS.register("aid_station", () -> ( (new BlockItem(  BLOCK_AID_STATION.get(), BLOCK_AID_STATION.get().item_properties))) );
    
    // @OUTPOST_LIASON
    public static final RegistryObject<Item> ITEM_SUPPLY_OFFICE = ITEMS.register("supply_office", () -> ( (new BlockItem(  BLOCK_SUPPLY_OFFICE.get(), BLOCK_SUPPLY_OFFICE.get().item_properties))) );
    
    // @POTTER
    public static final RegistryObject<Item> ITEM_POTTERS_WHEEL = ITEMS.register("potters_wheel", () -> ( (new BlockItem(  BLOCK_POTTERS_WHEEL.get(), BLOCK_POTTERS_WHEEL.get().item_properties))) );
    
    // @PYROTECHNIC
    public static final RegistryObject<Item> ITEM_PYROTECHNIC = ITEMS.register("pyrotechnic_worktable", () -> ( (new BlockItem(  BLOCK_PYROTECHNIC_TABLE.get(), BLOCK_PYROTECHNIC_TABLE.get().item_properties))) );
    
    // @RANGER
    public static final RegistryObject<Item> ITEM_RANGER_TABLE = ITEMS.register("ranger_table", () -> ( (new BlockItem(  BLOCK_RANGER_TABLE.get(), BLOCK_RANGER_TABLE.get().item_properties))) );
    
    // @REDSTONE_ENGINEER
    public static final RegistryObject<Item> ITEM_REDSTONE_TABLE = ITEMS.register("redstone_table", () -> ( (new BlockItem(  BLOCK_REDSTONE_TABLE.get(), BLOCK_REDSTONE_TABLE.get().item_properties))) );
    
    // @ROGUE
    public static final RegistryObject<Item> ITEM_POISON_STATION = ITEMS.register("poison_station", () -> ( (new BlockItem(  BLOCK_POISON_STATION.get(), BLOCK_POISON_STATION.get().item_properties))) );
    
    // @SAPPER
    public static final RegistryObject<Item> ITEM_DEMOLITION_STATION = ITEMS.register("demolition_station", () -> ( (new BlockItem(  BLOCK_DEMOLITION_STATION.get(), BLOCK_DEMOLITION_STATION.get().item_properties))) );
    
    // @SINGER
    public static final RegistryObject<Item> ITEM_MICROPHONE_STAND = ITEMS.register("microphone_stand", () -> ( (new BlockItem(  BLOCK_MICROPHONE_STAND.get(), BLOCK_MICROPHONE_STAND.get().item_properties))) );
    
    // @TEACHER
    public static final RegistryObject<Item> ITEM_LECTURE_STAND = ITEMS.register("lecture_stand", () -> ( (new BlockItem(  BLOCK_LECTURE_STAND.get(), BLOCK_LECTURE_STAND.get().item_properties))) );
    
    // @TRADER
    public static final RegistryObject<Item> ITEM_MERCHANT_STALL = ITEMS.register("merchant_stall", () -> ( (new BlockItem(  BLOCK_MERCHANT_STALL.get(), BLOCK_MERCHANT_STALL.get().item_properties))) );
    
    // @TRADESMAN
    public static final RegistryObject<Item> ITEM_TRADESMAN_HELMET = ITEMS.register("tradesman_helmet", () -> ((new BlockItem( BLOCK_TRADESMAN_HELMET.get(), BLOCK_TRADESMAN_HELMET.get().item_properties))));
    
    // @TRAPPER
    public static final RegistryObject<Item> ITEM_TRAPPER_STATION = ITEMS.register("trapper_station", () -> ((new BlockItem( BLOCK_TRAPPER_STATION.get(), BLOCK_TRAPPER_STATION.get().item_properties))));
    
    // @UNDERAKER
    public static final RegistryObject<Item> ITEM_COFFIN = ITEMS.register("coffin", () -> ((new BlockItem( BLOCK_COFFIN.get(), BLOCK_COFFIN.get().item_properties))));
    
    // @WARLOCK
    public static final RegistryObject<Item> ITEM_WARPED_ALTAR = ITEMS.register("warped_altar", () -> ((new BlockItem( BLOCK_WARPED_ALTAR.get(), BLOCK_WARPED_ALTAR.get().item_properties))));
    
    // @WEAVER
    public static final RegistryObject<Item> ITEM_WEAVING_STATION = ITEMS.register("weaving_station", () -> ((new BlockItem( BLOCK_WEAVING_STATION.get(), BLOCK_WEAVING_STATION.get().item_properties))));
    
    // @WORKER
    public static final RegistryObject<Item> ITEM_DELIVERY_STATION = ITEMS.register("delivery_station", () -> ((new BlockItem( BLOCK_DELIVERY_STATION.get(), BLOCK_DELIVERY_STATION.get().item_properties))));
    
    public static final RegistryObject<Item> ITEM_VILLAGE_CENTER = ITEMS.register("village_center", () -> ( (new ItemVillageCenter(  BLOCK_VILLAGE_CENTER.get(), BLOCK_VILLAGE_CENTER.get().item_properties))) );
	
	
	
	/**
	 * Points of interests
	 */
	// @ALCHEMIST
	public static final RegistryObject<PointOfInterestType> ALCHEMIST_TABLE = POINTS_OF_INTEREST.register("alchemist", () -> { 	
		PointOfInterestType alchemist_table = new PointOfInterestType("alchemist_table", ModVillagerProfessions.getAllStates(BLOCK_ALCHEMIST_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(alchemist_table);
	});
	
	// @ARCHITECT
	public static final RegistryObject<PointOfInterestType> DRAFTING_TABLE = POINTS_OF_INTEREST.register("drafting_table", () -> {
		PointOfInterestType draftingTable = new PointOfInterestType("drafting_table", ModVillagerProfessions.getAllStates(BLOCK_DRAFTING_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(draftingTable);
	});
	
	// @BARD
	public static final RegistryObject<PointOfInterestType> BARD_CHAIR = POINTS_OF_INTEREST.register("bard", () -> { 	
		PointOfInterestType bardPlace = new PointOfInterestType("bard_stand", ModVillagerProfessions.getAllStates(BLOCK_BARD_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(bardPlace);
	});
	
	// @BARKEEPER
	public static final RegistryObject<PointOfInterestType> BAR = POINTS_OF_INTEREST.register("bar", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("bar", ModVillagerProfessions.getAllStates(BLOCK_BAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	// @BASSIST
	public static final RegistryObject<PointOfInterestType> GUITAR_STAND = POINTS_OF_INTEREST.register("guitar_stand", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("guitar_stand", ModVillagerProfessions.getAllStates(BLOCK_GUITAR_STAND.get()), 1, 1);
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
		PointOfInterestType village_chest = new PointOfInterestType("brawler_box", ModVillagerProfessions.getAllStates(BLOCK_BRAWLER_BOX.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	// @BREWMASTER
	public static final RegistryObject<PointOfInterestType> KEG = POINTS_OF_INTEREST.register("keg", () -> {
		PointOfInterestType keg = new PointOfInterestType("keg", ModVillagerProfessions.getAllStates(BLOCK_KEG.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(keg);
	});
	
	// @BUILDER
	public static final RegistryObject<PointOfInterestType> BUILDERS_CHEST = POINTS_OF_INTEREST.register("builders_chest", () -> {
		PointOfInterestType builders_chest = new PointOfInterestType("builders_chest", ModVillagerProfessions.getAllStates(BLOCK_BUILDERS_CHEST.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(builders_chest);
	});
	
	// @BUTCHER
	public static final RegistryObject<PointOfInterestType> BUTCHERS_BLOCK = POINTS_OF_INTEREST.register("butchers_block", () -> {
		PointOfInterestType butchers_block = new PointOfInterestType("butchers_block", ModVillagerProfessions.getAllStates(BLOCK_BUTCHERS_BLOCK.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(butchers_block);
	});
	
	// @CAPTAIN
	public static final RegistryObject<PointOfInterestType> CAPTAINS_ARMORSTAND_BLOCK = POINTS_OF_INTEREST.register("captains_armorstand_block", () -> {
		PointOfInterestType captains_armorstand_block = new PointOfInterestType("captains_armorstand_block", ModVillagerProfessions.getAllStates(BLOCK_CAPTAINS_ARMORSTAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(captains_armorstand_block);
	});
	
	// @CARAVANEER
	public static final RegistryObject<PointOfInterestType> CARAVAN_STOP = POINTS_OF_INTEREST.register("caravan_stop", () -> {
		PointOfInterestType caravan_stop = new PointOfInterestType("caravan_stop", ModVillagerProfessions.getAllStates(BLOCK_CARAVAN_STOP.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(caravan_stop);
	});
	
	// @CARPENTER
	public static final RegistryObject<PointOfInterestType> SAWHORSE = POINTS_OF_INTEREST.register("sawhorse", () -> {
		PointOfInterestType sawhorse = new PointOfInterestType("sawhorse", ModVillagerProfessions.getAllStates(BLOCK_SAWHORSE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(sawhorse);
	});
	
	// @CLERIC
	public static final RegistryObject<PointOfInterestType> ALTAR = POINTS_OF_INTEREST.register("altar", () -> {
		PointOfInterestType altar = new PointOfInterestType("altar", ModVillagerProfessions.getAllStates(BLOCK_ALTAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(altar);
	});
	
	// @COOK
	public static final RegistryObject<PointOfInterestType> BRICK_OVEN = POINTS_OF_INTEREST.register("brick_oven", () -> {
		PointOfInterestType brick_oven = new PointOfInterestType("brick_oven", ModVillagerProfessions.getAllStates(BLOCK_BRICK_OVEN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(brick_oven);
	});
	
	// @DIPLOMAT
	public static final RegistryObject<PointOfInterestType> EMBASSY = POINTS_OF_INTEREST.register("embassy", () -> {
		PointOfInterestType embassy = new PointOfInterestType("embassy", ModVillagerProfessions.getAllStates(BLOCK_EMBASSY.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(embassy);
	});
	
	// @DRUID
	public static final RegistryObject<PointOfInterestType> FOREST_SHRINE = POINTS_OF_INTEREST.register("forest_shrine", () -> {
		PointOfInterestType forest_shrine = new PointOfInterestType("forest_shrine", ModVillagerProfessions.getAllStates(BLOCK_FOREST_SHRINE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(forest_shrine);
	});
	
	// @DRUMMER
	public static final RegistryObject<PointOfInterestType> DRUMS = POINTS_OF_INTEREST.register("drums", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("drums", ModVillagerProfessions.getAllStates(BLOCK_DRUMS.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	// @ENCHANTER
	public static final RegistryObject<PointOfInterestType> ENCHANTED_STONE = POINTS_OF_INTEREST.register("enchanted_stone", () -> {
		PointOfInterestType enchanted_stone = new PointOfInterestType("enchanted_stone", ModVillagerProfessions.getAllStates(BLOCK_ENCHANTED_STONE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(enchanted_stone);
	});
	
	// @ENGINEER
	public static final RegistryObject<PointOfInterestType> RAIL_MACHINE = POINTS_OF_INTEREST.register("rail_machine", () -> {
		PointOfInterestType rail_machine = new PointOfInterestType("rail_machine", ModVillagerProfessions.getAllStates(BLOCK_RAIL_MACHINE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(rail_machine);
	});
	
	// @FOOTMAN
	public static final RegistryObject<PointOfInterestType> WEAPON_STAND = POINTS_OF_INTEREST.register("weapon_stand", () -> {
		PointOfInterestType poi = new PointOfInterestType("weapon_stand", ModVillagerProfessions.getAllStates(BLOCK_WEAPON_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @GEOMANCER
	public static final RegistryObject<PointOfInterestType> STONE_ALTAR = POINTS_OF_INTEREST.register("stone_altar", () -> {
		PointOfInterestType poi = new PointOfInterestType("stone_altar", ModVillagerProfessions.getAllStates(BLOCK_STONE_ALTAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @GUILDMASTER
	public static final RegistryObject<PointOfInterestType> GUILDMASTER_TASK = POINTS_OF_INTEREST.register("guildmaster_task_board", () -> {
		PointOfInterestType poi = new PointOfInterestType("guildmaster_task_board", ModVillagerProfessions.getAllStates(BLOCK_GUILD_TASKBOARD.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @HERBALIST
	public static final RegistryObject<PointOfInterestType> HERBALIST_PESTLE = POINTS_OF_INTEREST.register("herbalist_pestle", () -> {
		PointOfInterestType poi = new PointOfInterestType("herbalist_pestle", ModVillagerProfessions.getAllStates(BLOCK_HERBALIST_PESTLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @HUNTER
	public static final RegistryObject<PointOfInterestType> TAMING_PEN = POINTS_OF_INTEREST.register("taming_pen", () -> {
		PointOfInterestType poi = new PointOfInterestType("taming_pen", ModVillagerProfessions.getAllStates(BLOCK_TAMING_PEN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @INNKEEPER
	public static final RegistryObject<PointOfInterestType> INN = POINTS_OF_INTEREST.register("inn", () -> {
		PointOfInterestType inn = new PointOfInterestType("inn", ModVillagerProfessions.getAllStates(BLOCK_INN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(inn);
	});
	
	// @KNIGHT
	public static final RegistryObject<PointOfInterestType> KNIGHT_STAND = POINTS_OF_INTEREST.register("knight_stand", () -> {
		PointOfInterestType poi = new PointOfInterestType("knight_stand", ModVillagerProfessions.getAllStates(BLOCK_KNIGHT_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @LANDLORD
	public static final RegistryObject<PointOfInterestType> TITLE_OFFICE = POINTS_OF_INTEREST.register("title_office", () -> {
		PointOfInterestType title_office = new PointOfInterestType("title_office", ModVillagerProfessions.getAllStates(BLOCK_TITLE_OFFICE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(title_office);
	});
	
	// @LEATHERWORKER
	public static final RegistryObject<PointOfInterestType> TANNERY = POINTS_OF_INTEREST.register("tannery", () -> {
		PointOfInterestType poi = new PointOfInterestType("tannery", ModVillagerProfessions.getAllStates(BLOCK_TANNERY.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @LUMBERJACK
	public static final RegistryObject<PointOfInterestType> SAW_MILL = POINTS_OF_INTEREST.register("saw_mill", () -> {
		PointOfInterestType poi = new PointOfInterestType("saw_mill", ModVillagerProfessions.getAllStates(BLOCK_SAW_MILL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @MAGE
	public static final RegistryObject<PointOfInterestType> ELEMENTAL_ALTAR = POINTS_OF_INTEREST.register("elemental_altar", () -> {
		PointOfInterestType poi = new PointOfInterestType("elemental_altar", ModVillagerProfessions.getAllStates(BLOCK_ELEMENTAL_ALTAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @MANAGER
	public static final RegistryObject<PointOfInterestType> VILLAGE_MANAGEMENT = POINTS_OF_INTEREST.register("village_management", () -> {
		PointOfInterestType village_management = new PointOfInterestType("village_management", ModVillagerProfessions.getAllStates(BLOCK_VILLAGE_MANAGER.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_management);
	});
	
	// @MARKSMAN
	public static final RegistryObject<PointOfInterestType> TARGET_BLOCK = POINTS_OF_INTEREST.register("target_block", () -> {
		PointOfInterestType poi = new PointOfInterestType("target_block", ModVillagerProfessions.getAllStates(BLOCK_TARGET_BLOCK.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});

	
	// @MAYOR
	public static final RegistryObject<PointOfInterestType> TOWN_HALL = POINTS_OF_INTEREST.register("town_hall", () -> {
		PointOfInterestType town_hall = new PointOfInterestType("town_hall", ModVillagerProfessions.getAllStates(BLOCK_TOWN_HALL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(town_hall);
	});
	
	// @MERCHANT
	public static final RegistryObject<PointOfInterestType> AUCTION_HOUSE = POINTS_OF_INTEREST.register("auction_house", () -> {
		PointOfInterestType auction_house = new PointOfInterestType("auction_house", ModVillagerProfessions.getAllStates(BLOCK_AUCTION_HOUSE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(auction_house);
	});
	
	// @MINER
	public static final RegistryObject<PointOfInterestType> ORE_BOX = POINTS_OF_INTEREST.register("ore_box", () -> {
		PointOfInterestType minerWorkplace = new PointOfInterestType("ore_box", ModVillagerProfessions.getAllStates(BLOCK_ORE_BOX.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(minerWorkplace);
	});
	
	// @NURSE
	public static final RegistryObject<PointOfInterestType> AID_STATION = POINTS_OF_INTEREST.register("aid_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("aid_station", ModVillagerProfessions.getAllStates(BLOCK_AID_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @OUTPOST_LIASON
	public static final RegistryObject<PointOfInterestType> VILLAGE_CENTER = POINTS_OF_INTEREST.register("village_center", () ->{
		PointOfInterestType tradesman_helmet = new PointOfInterestType("village_center", ModVillagerProfessions.getAllStates(BLOCK_VILLAGE_CENTER.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(tradesman_helmet);
	});
	
	// @POTTER
	public static final RegistryObject<PointOfInterestType> POTTERS_WHEEL = POINTS_OF_INTEREST.register("potter_wheel", () -> {
		PointOfInterestType potter_wheel = new PointOfInterestType("potter_wheel", ModVillagerProfessions.getAllStates(BLOCK_POTTERS_WHEEL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(potter_wheel);
	});
	
	// @PYROTECHNIC
	public static final RegistryObject<PointOfInterestType> PYROTECHNIC_TABLE = POINTS_OF_INTEREST.register("pyrotechnic_worktable", () -> {
		PointOfInterestType pyrotechnic_worktable = new PointOfInterestType("pyrotechnic_worktable", ModVillagerProfessions.getAllStates(BLOCK_PYROTECHNIC_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(pyrotechnic_worktable);
	});
	
	// @RANGER
	public static final RegistryObject<PointOfInterestType> RANGER_TABLE = POINTS_OF_INTEREST.register("ranger_table", () -> {
		PointOfInterestType poi = new PointOfInterestType("ranger_table", ModVillagerProfessions.getAllStates(BLOCK_RANGER_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});

	
	// @REDSTONE_ENGINEER
	public static final RegistryObject<PointOfInterestType> REDSTONE_TABLE = POINTS_OF_INTEREST.register("redstone_table", () -> {
		PointOfInterestType poi = new PointOfInterestType("redstone_table", ModVillagerProfessions.getAllStates(BLOCK_REDSTONE_TABLE.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @ROGUE
	public static final RegistryObject<PointOfInterestType> POISON_STATION = POINTS_OF_INTEREST.register("poison_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("poison_station", ModVillagerProfessions.getAllStates(BLOCK_POISON_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @SAPPER
	public static final RegistryObject<PointOfInterestType> DEMOLITION_STATION = POINTS_OF_INTEREST.register("demolition_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("demolition_station", ModVillagerProfessions.getAllStates(BLOCK_DEMOLITION_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @SINGER
	public static final RegistryObject<PointOfInterestType> MICROPHONE_STAND = POINTS_OF_INTEREST.register("microphone_stand", () -> {
		PointOfInterestType village_chest = new PointOfInterestType("microphone_stand", ModVillagerProfessions.getAllStates(BLOCK_MICROPHONE_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_chest);
	});
	
	// @TEACHER
	public static final RegistryObject<PointOfInterestType> LECTURE_STAND = POINTS_OF_INTEREST.register("lecture_stand", () -> {
		PointOfInterestType poi = new PointOfInterestType("lecture_stand", ModVillagerProfessions.getAllStates(BLOCK_LECTURE_STAND.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @TRADER
	// @TODO currently using vanilla crafting table...
	public static final RegistryObject<PointOfInterestType> MERCHANT_STALL = POINTS_OF_INTEREST.register("merchant_stall", () -> {
		PointOfInterestType village_crafting_table = new PointOfInterestType("merchant_stall", ModVillagerProfessions.getAllStates(BLOCK_MERCHANT_STALL.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(village_crafting_table);
	});
	
	// @TRADESMAN
	public static final RegistryObject<PointOfInterestType> TRADESMAN_HELMET = POINTS_OF_INTEREST.register("tradesman_helmet", () -> {
		PointOfInterestType tradesman_helmet = new PointOfInterestType("tradesman_helmet", ModVillagerProfessions.getAllStates(BLOCK_TRADESMAN_HELMET.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(tradesman_helmet);
	});
	
	// @TRAPPER
	public static final RegistryObject<PointOfInterestType> TRAPPER_STATION = POINTS_OF_INTEREST.register("trapper_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("trapper_station", ModVillagerProfessions.getAllStates(BLOCK_TRAPPER_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @UNDERTAKER
	public static final RegistryObject<PointOfInterestType> COFFIN = POINTS_OF_INTEREST.register("coffin", () -> {
		PointOfInterestType poi = new PointOfInterestType("coffin", ModVillagerProfessions.getAllStates(BLOCK_COFFIN.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @WARLOCK
	public static final RegistryObject<PointOfInterestType> WARPED_ALTAR = POINTS_OF_INTEREST.register("warped_altar", () -> {
		PointOfInterestType poi = new PointOfInterestType("warped_altar", ModVillagerProfessions.getAllStates(BLOCK_WARPED_ALTAR.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});

	
	// @WEAVER
	public static final RegistryObject<PointOfInterestType> WEAVING_STATION = POINTS_OF_INTEREST.register("weaving_station", () -> {
		PointOfInterestType poi = new PointOfInterestType("weaving_station", ModVillagerProfessions.getAllStates(BLOCK_WEAVING_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(poi);
	});
	
	// @WORKER
	// @TODO currently using vanilla chest...
	public static final RegistryObject<PointOfInterestType> CHEST = POINTS_OF_INTEREST.register("delivery_station", () -> {
		PointOfInterestType delivery_station = new PointOfInterestType("delivery_station", ModVillagerProfessions.getAllStates(BLOCK_DELIVERY_STATION.get()), 1, 1);
		return ModVillagerProfessions.fixPOITypeBlockStates(delivery_station);
	});
	
	
	/**
	 * Goal Related Points of Interest
	 * @param block
	 * @return
	 */
	
	
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
 		return BardProfession.villagerProfession("bard", BARD_CHAIR.get(), ImmutableSet.of(), ImmutableSet.of(BLOCK_BARD_STAND.get()), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
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
			return HerbalistProfession.villagerProfession("hunter", TAMING_PEN.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
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
    	return MerchantProfession.villagerProfession("merchant", AUCTION_HOUSE.get(), MerchantProfession.PROFESSION_ITEM, MerchantProfession.PROFESSION_BLOCK, new SoundEvent(new ResourceLocation("vcm", "villager_socialize")) );	
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
		return OutpostLiasonProfession.villagerProfession("outpost_liason", VILLAGE_CENTER.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_socialize")));
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
    	return TradesmanProfession.villagerProfession("trader", MERCHANT_STALL.get(), ImmutableSet.of(), ImmutableSet.of(), new SoundEvent(new ResourceLocation("vcm", "villager_grunt")) );
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

