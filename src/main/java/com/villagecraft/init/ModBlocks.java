package com.villagecraft.init;

import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.villagecraft.VillageCraft;
import com.villagecraft.block.BlockAideStation;
import com.villagecraft.block.BlockAlchemistTable;
import com.villagecraft.block.BlockAltar;
import com.villagecraft.block.BlockAuctionHouse;
import com.villagecraft.block.BlockBar;
import com.villagecraft.block.BlockBardStand;
import com.villagecraft.block.BlockBeeKeepersHive;
import com.villagecraft.block.BlockBrawlerEquipment;
import com.villagecraft.block.BlockBrickOven;
import com.villagecraft.block.BlockBuildersChest;
import com.villagecraft.block.BlockButchersBlock;
import com.villagecraft.block.BlockCaptainsArmorBlock;
import com.villagecraft.block.BlockCaravanStop;
import com.villagecraft.block.BlockChair;
import com.villagecraft.block.BlockCoffin;
import com.villagecraft.block.BlockDeliveryStation;
import com.villagecraft.block.BlockDemolitionStation;
import com.villagecraft.block.BlockDraftingTable;
import com.villagecraft.block.BlockDrums;
import com.villagecraft.block.BlockElementalAltar;
import com.villagecraft.block.BlockEmbassy;
import com.villagecraft.block.BlockEnchantedStone;
import com.villagecraft.block.BlockForestShrine;
import com.villagecraft.block.BlockGreatwoodSappling;
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
import com.villagecraft.util.Reference;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.OakTree;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Holds a list of all our {@link Block}s.
 * Suppliers that create Blocks are added to the DeferredRegister.
 * The DeferredRegister is then added to our mod event bus in our constructor.
 * When the Block Registry Event is fired by Forge and it is time for the mod to
 * register its Blocks, our Blocks are created and registered by the DeferredRegister.
 * The Block Registry Event will always be called before the Item registry is filled.
 * Note: This supports registry overrides.
 *
 * @author cmcintosh
 */
public final class ModBlocks {

	
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);
	
	/**
	 * Furniture / Decorative blocks
	 */
	public static final RegistryObject<BlockChair> BLOCK_CHAIR = (RegistryObject<BlockChair>) BLOCKS.register("chair", () -> new BlockChair(BlockChair.properties));
	
	/**
	 * Profession Blocks
	 */
	
    // @ALCHEMIST
	public static final RegistryObject<BlockAlchemistTable> BLOCK_ALCHEMIST_TABLE = (RegistryObject<BlockAlchemistTable>) BLOCKS.register("alchemist_table", () -> new BlockAlchemistTable(BlockAlchemistTable.properties));
	
    // @ARCHITECT
	public static final RegistryObject<BlockDraftingTable> DRAFTING_TABLE = (RegistryObject<BlockDraftingTable>) BLOCKS.register("drafting_table", () -> new BlockDraftingTable(BlockDraftingTable.properties));
	
    // @BARD
	public static final RegistryObject<BlockBardStand> BARD_STAND = (RegistryObject<BlockBardStand>) BLOCKS.register("bard_stand", () -> new BlockBardStand(BlockBardStand.properties));
	
 	// @BARKEEPER
	public static final RegistryObject<BlockBar> BAR = (RegistryObject<BlockBar>) BLOCKS.register("bar", () -> new BlockBar(BlockBar.properties));

 	// @BASSIST
	public static final RegistryObject<BlockGuitarStand> GUITAR_STAND = (RegistryObject<BlockGuitarStand>) BLOCKS.register("guitar_stand", () -> new BlockGuitarStand(BlockGuitarStand.properties));

	// @BEEKEEPER
	// uses vanilla beehive
	
    // @BLACKSMITH
	// uses vanilla anvil
	
    // @BRAWLER
	public static final RegistryObject<BlockBrawlerEquipment> BRAWLER_BOX = (RegistryObject<BlockBrawlerEquipment>) BLOCKS.register("brawler_box", () -> new BlockBrawlerEquipment(BlockBrawlerEquipment.properties));

	// @BREWMASTER
	public static final RegistryObject<BlockKeg> BLOCK_KEG = (RegistryObject<BlockKeg>) BLOCKS.register("keg", () -> new BlockKeg(BlockKeg.properties));
	
	// @BUILDER
	public static final RegistryObject<BlockBuildersChest> BUILDERS_CHEST = (RegistryObject<BlockBuildersChest>) BLOCKS.register("builders_chest", () -> new BlockBuildersChest(BlockBuildersChest.properties));
	
	// @BUTCHER
	public static final RegistryObject<BlockButchersBlock> BUTCHERS_BLOCK = (RegistryObject<BlockButchersBlock>) BLOCKS.register("butchers_block", () -> new BlockButchersBlock(BlockButchersBlock.properties));
  	
  	// @CAPTAIN
	public static final RegistryObject<BlockCaptainsArmorBlock> CAPTAINS_ARMORSTAND_BLOCK = (RegistryObject<BlockCaptainsArmorBlock>) BLOCKS.register("captains_armorstand_block", () -> new BlockCaptainsArmorBlock(BlockCaptainsArmorBlock.properties));
	
  	// @CARAVANEER
	public static final RegistryObject<BlockCaravanStop> CARAVAN_STOP = (RegistryObject<BlockCaravanStop>) BLOCKS.register("caravan_stop", () -> new BlockCaravanStop(BlockCaravanStop.properties));
	
	// @CARPENTAR
	public static final RegistryObject<BlockSawHorse> SAWHORSE = (RegistryObject<BlockSawHorse>) BLOCKS.register("sawhorse", () -> new BlockSawHorse(BlockSawHorse.properties));
	
	// @CLERIC
	public static final RegistryObject<BlockAltar> ALTAR = (RegistryObject<BlockAltar>) BLOCKS.register("altar", () -> new BlockAltar(BlockAltar.properties));
	
	// @COOK
	public static final RegistryObject<BlockBrickOven> BRICK_OVEN = (RegistryObject<BlockBrickOven>) BLOCKS.register("brick_oven", () -> new BlockBrickOven(BlockBrickOven.properties));
	
	// @DIPLOMAT
	public static final RegistryObject<BlockEmbassy> EMBASSY = (RegistryObject<BlockEmbassy>) BLOCKS.register("embassy", () -> new BlockEmbassy(BlockEmbassy.properties));
	
	// @DRUID
	public static final RegistryObject<BlockForestShrine> FOREST_SHRINE = (RegistryObject<BlockForestShrine>) BLOCKS.register("forest_shrine", () -> new BlockForestShrine(BlockForestShrine.properties));

	// @DRUMMER
	public static final RegistryObject<BlockDrums> DRUMS = (RegistryObject<BlockDrums>) BLOCKS.register("drums", () -> new BlockDrums(BlockDrums.properties));	
	
	// @ENCHANTER
	public static final RegistryObject<BlockEnchantedStone> ENCHANTED_STONE = (RegistryObject<BlockEnchantedStone>) BLOCKS.register("enchanted_stone", () -> new BlockEnchantedStone(BlockEnchantedStone.properties));
	
	// @ENGINEER
	public static final RegistryObject<BlockRailMachine> RAIL_MACHINE = (RegistryObject<BlockRailMachine>) BLOCKS.register("rail_machine", () -> new BlockRailMachine(BlockRailMachine.properties));
	
	// @FOOTMAN
	public static final RegistryObject<BlockWeaponStand> WEAPON_STAND = (RegistryObject<BlockWeaponStand>) BLOCKS.register("weapon_stand", () -> new BlockWeaponStand(BlockWeaponStand.properties));
	
	// @GEOMANCER
	public static final RegistryObject<BlockStoneAltar> STONE_ALTAR = (RegistryObject<BlockStoneAltar>) BLOCKS.register("stone_altar", () -> new BlockStoneAltar(BlockStoneAltar.properties));
	
	// @GUILDMASTER
	public static final RegistryObject<BlockGuildTaskBoard> GUILD_TASKBOARD = (RegistryObject<BlockGuildTaskBoard>) BLOCKS.register("guild_task_board", () -> new BlockGuildTaskBoard(BlockGuildTaskBoard.properties));
	
	// @HERBALIST
	public static final RegistryObject<BlockHerbalistPestle> HERBALIST_PESTLE = (RegistryObject<BlockHerbalistPestle>) BLOCKS.register("herbalist_pestle", () -> new BlockHerbalistPestle(BlockHerbalistPestle.properties));
	
	// @HUNTER
	public static final RegistryObject<BlockTamingPin> TAMING_PEN = (RegistryObject<BlockTamingPin>) BLOCKS.register("taming_pen", () -> new BlockTamingPin(BlockTamingPin.properties));
	
	// @INNKEEPER
	public static final RegistryObject<BlockInn> INN = (RegistryObject<BlockInn>) BLOCKS.register("inn", () -> new BlockInn(BlockInn.properties));
	
	// @KNIGHT
	public static final RegistryObject<BlockKnightStand> KNIGHT_STAND = (RegistryObject<BlockKnightStand>) BLOCKS.register("knight_stand", () -> new BlockKnightStand(BlockKnightStand.properties));
	
	// @LANDLORD
	public static final RegistryObject<BlockTitleOffice> TITLE_OFFICE = (RegistryObject<BlockTitleOffice>) BLOCKS.register("title_office", () -> new BlockTitleOffice(BlockTitleOffice.properties));
	
	// @LEATHERWORKER
	public static final RegistryObject<BlockTannery> TANNERY = (RegistryObject<BlockTannery>) BLOCKS.register("tannery", () -> new BlockTannery(BlockTannery.properties));
	
	// @LUMBERJACK
	public static final RegistryObject<BlockSawMill> SAW_MILL = (RegistryObject<BlockSawMill>) BLOCKS.register("saw_mill", () -> new BlockSawMill(BlockSawMill.properties));
	
	// @MAGE
	public static final RegistryObject<BlockElementalAltar> ELEMENTAL_ALTAR = (RegistryObject<BlockElementalAltar>) BLOCKS.register("elemental_altar", () -> new BlockElementalAltar(BlockElementalAltar.properties));
	
	// @MANAGER
	public static final RegistryObject<BlockVillageManager> VILLAGE_MANAGER = (RegistryObject<BlockVillageManager>) BLOCKS.register("village_manager", () -> new BlockVillageManager(BlockVillageManager.properties));
	
	// @MARKSMAN
	public static final RegistryObject<BlockTargetBlock> TARGET_BLOCK = (RegistryObject<BlockTargetBlock>) BLOCKS.register("target_block", () -> new BlockTargetBlock(BlockTargetBlock.properties));
	
	// @MAYOR
	public static final RegistryObject<BlockTownHall> TOWN_HALL = (RegistryObject<BlockTownHall>) BLOCKS.register("town_hall", () -> new BlockTownHall(BlockTownHall.properties));
	
	// @MERCHANT
	public static final RegistryObject<BlockAuctionHouse> AUCTION_HOUSE = (RegistryObject<BlockAuctionHouse>) BLOCKS.register("auction_house", () -> new BlockAuctionHouse(BlockAuctionHouse.properties));
		
	// @MINER
	public static final RegistryObject<BlockOreBox> ORE_BOX = (RegistryObject<BlockOreBox>) BLOCKS.register("ore_box", () -> new BlockOreBox(BlockOreBox.properties));
	
	// @NURSE
	public static final RegistryObject<BlockAideStation> AID_STATION = (RegistryObject<BlockAideStation>) BLOCKS.register("aid_station", () -> new BlockAideStation(BlockAideStation.properties));
	
	// @OUTPOST_LIASON
	public static final RegistryObject<BlockSupplyOffice> SUPPLY_OFFICE = (RegistryObject<BlockSupplyOffice>) BLOCKS.register("supply_office", () -> new BlockSupplyOffice(BlockSupplyOffice.properties));
	
	// @POTTER
	public static final RegistryObject<BlockPotterWheel> POTTERS_WHEEL = (RegistryObject<BlockPotterWheel>) BLOCKS.register("potter_wheel", () -> new BlockPotterWheel(BlockPotterWheel.properties));
	
	// @PYROTECHNIC
	public static final RegistryObject<BlockPyrotechnicTable> PYROTECHNIC_TABLE = (RegistryObject<BlockPyrotechnicTable>) BLOCKS.register("pyrotechnic_worktable", () -> new BlockPyrotechnicTable(BlockPyrotechnicTable.properties));
	
	// @RANGER
	public static final RegistryObject<BlockRangerCraftingTable> RANGER_TABLE = (RegistryObject<BlockRangerCraftingTable>) BLOCKS.register("ranger_table", () -> new BlockRangerCraftingTable(BlockRangerCraftingTable.properties));
	
	// @REDSTONE_ENGINEER
	public static final RegistryObject<BlockRedstoneWorkstation> REDSTONE_TABLE = (RegistryObject<BlockRedstoneWorkstation>) BLOCKS.register("redstone_workstation", () -> new BlockRedstoneWorkstation(BlockRedstoneWorkstation.properties));
	
	// @ROGUE
	public static final RegistryObject<BlockPoisonStation> POISON_STATION = (RegistryObject<BlockPoisonStation>) BLOCKS.register("poison_station", () -> new BlockPoisonStation(BlockPoisonStation.properties));
	
	// @SAPPER
	public static final RegistryObject<BlockDemolitionStation> DEMOLITION_STATION = (RegistryObject<BlockDemolitionStation>) BLOCKS.register("demolition_station", () -> new BlockDemolitionStation(BlockDemolitionStation.properties));
	
	// @SINGER
	public static final RegistryObject<BlockMicrophoneStand> MICROPHONE_STAND = (RegistryObject<BlockMicrophoneStand>) BLOCKS.register("microphone_stand", () -> new BlockMicrophoneStand(BlockMicrophoneStand.properties));
		
	// @TEACHER
	public static final RegistryObject<BlockLectureStand> LECTURE_STAND = (RegistryObject<BlockLectureStand>) BLOCKS.register("lecture_stand", () -> new BlockLectureStand(BlockLectureStand.properties));
	
	// @TRADER
	public static final RegistryObject<BlockMerchantStall> MERCHANT_STALL = (RegistryObject<BlockMerchantStall>) BLOCKS.register("merchant_stall", () -> new BlockMerchantStall(BlockMerchantStall.properties));
	
	// @TRADESMAN
	public static final RegistryObject<TradesmanHelmet> TRADESMAN_HELMET = (RegistryObject<TradesmanHelmet>) BLOCKS.register("tradesman_helmet", () -> new TradesmanHelmet(TradesmanHelmet.properties));	
    
    // @TRAPPER
	public static final RegistryObject<BlockTrapStation> TRAPPER_STATION = (RegistryObject<BlockTrapStation>) BLOCKS.register("trapper_station", () -> new BlockTrapStation(BlockTrapStation.properties));
    
    // @UNDERTAKER
	public static final RegistryObject<BlockCoffin> COFFIN = (RegistryObject<BlockCoffin>) BLOCKS.register("coffin", () -> new BlockCoffin(BlockCoffin.properties));
    
    // @WARLOCK
	public static final RegistryObject<BlockWarpedAltar> WARPED_ALTAR = (RegistryObject<BlockWarpedAltar>) BLOCKS.register("warped_altar", () -> new BlockWarpedAltar(BlockWarpedAltar.properties));
    
    // @WEAVER
	public static final RegistryObject<BlockWeavingStation> WEAVING_STATION = (RegistryObject<BlockWeavingStation>) BLOCKS.register("weaving_station", () -> new BlockWeavingStation(BlockWeavingStation.properties));
    
    // @WORKER
	public static final RegistryObject<BlockDeliveryStation> DELIVERY_STATION = (RegistryObject<BlockDeliveryStation>) BLOCKS.register("delivery_station", () -> new BlockDeliveryStation(BlockDeliveryStation.properties));
	// @TODO uses vanilla chest for now.
	

	public static final RegistryObject<BlockVillageCenter> BLOCK_VILLAGE_CENTER = (RegistryObject<BlockVillageCenter>) BLOCKS.register("village_center", () -> new BlockVillageCenter(BlockVillageCenter.properties));
	
	
	// Utility function
	public static final Set<BlockState> getAllStates(Block block) { 
		ImmutableList states = block.getStateContainer().getValidStates();
		
		return ImmutableSet.copyOf(states);
	}
	
}
