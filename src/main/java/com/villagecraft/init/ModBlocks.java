package com.villagecraft.init;

import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.villagecraft.VillageCraft;
import com.villagecraft.block.BlockAuctionHouse;
import com.villagecraft.block.BlockBar;
import com.villagecraft.block.BlockBardStand;
import com.villagecraft.block.BlockBeeKeepersHive;
import com.villagecraft.block.BlockBrawlerEquipment;
import com.villagecraft.block.BlockCaravanStop;
import com.villagecraft.block.BlockChair;

import com.villagecraft.block.BlockDraftingTable;
import com.villagecraft.block.BlockDrums;
import com.villagecraft.block.BlockEmbassy;
import com.villagecraft.block.BlockGuitarStand;
import com.villagecraft.block.BlockInn;
import com.villagecraft.block.BlockMicrophoneStand;
import com.villagecraft.block.BlockOreBox;
import com.villagecraft.block.BlockPyrotechnicTable;
import com.villagecraft.block.BlockSupplyOffice;
import com.villagecraft.block.BlockTownHall;
import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.block.BlockVillageManager;
import com.villagecraft.block.TradesmanHelmet;
import com.villagecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
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
	
	public static final RegistryObject<BlockVillageCenter> BLOCK_VILLAGE_CENTER = (RegistryObject<BlockVillageCenter>) BLOCKS.register("village_center", () -> new BlockVillageCenter(BlockVillageCenter.properties));
	
	public static final RegistryObject<BlockChair> BLOCK_CHAIR = (RegistryObject<BlockChair>) BLOCKS.register("chair", () -> new BlockChair(BlockChair.properties));
	public static final RegistryObject<BlockBardStand> BARD_STAND = (RegistryObject<BlockBardStand>) BLOCKS.register("bard_stand", () -> new BlockBardStand(BlockBardStand.properties));
	public static final RegistryObject<BlockOreBox> ORE_BOX = (RegistryObject<BlockOreBox>) BLOCKS.register("ore_box", () -> new BlockOreBox(BlockOreBox.properties));
	public static final RegistryObject<BlockDraftingTable> DRAFTING_TABLE = (RegistryObject<BlockDraftingTable>) BLOCKS.register("drafting_table", () -> new BlockDraftingTable(BlockDraftingTable.properties));
	public static final RegistryObject<TradesmanHelmet> TRADESMAN_HELMET = (RegistryObject<TradesmanHelmet>) BLOCKS.register("tradesman_helmet", () -> new TradesmanHelmet(TradesmanHelmet.properties));
	public static final RegistryObject<BlockBrawlerEquipment> BRAWLER_BOX = (RegistryObject<BlockBrawlerEquipment>) BLOCKS.register("brawler_box", () -> new BlockBrawlerEquipment(BlockBrawlerEquipment.properties));
	public static final RegistryObject<BlockBar> BAR = (RegistryObject<BlockBar>) BLOCKS.register("bar", () -> new BlockBar(BlockBar.properties));
	public static final RegistryObject<BlockDrums> DRUMS = (RegistryObject<BlockDrums>) BLOCKS.register("drums", () -> new BlockDrums(BlockDrums.properties));
	public static final RegistryObject<BlockGuitarStand> GUITAR_STAND = (RegistryObject<BlockGuitarStand>) BLOCKS.register("guitar_stand", () -> new BlockGuitarStand(BlockGuitarStand.properties));
	public static final RegistryObject<BlockMicrophoneStand> MICROPHONE_STAND = (RegistryObject<BlockMicrophoneStand>) BLOCKS.register("microphone_stand", () -> new BlockMicrophoneStand(BlockMicrophoneStand.properties));
	public static final RegistryObject<BlockAuctionHouse> AUCTION_HOUSE = (RegistryObject<BlockAuctionHouse>) BLOCKS.register("auction_house", () -> new BlockAuctionHouse(BlockAuctionHouse.properties));
	public static final RegistryObject<BlockPyrotechnicTable> PYROTECHNIC_TABLE = (RegistryObject<BlockPyrotechnicTable>) BLOCKS.register("pyrotechnic_worktable", () -> new BlockPyrotechnicTable(BlockPyrotechnicTable.properties));
	public static final RegistryObject<BlockCaravanStop> CARAVAN_STOP = (RegistryObject<BlockCaravanStop>) BLOCKS.register("caravaneer", () -> new BlockCaravanStop(BlockCaravanStop.properties));
	public static final RegistryObject<BlockEmbassy> EMBASSY = (RegistryObject<BlockEmbassy>) BLOCKS.register("embassy", () -> new BlockEmbassy(BlockEmbassy.properties));
	public static final RegistryObject<BlockVillageManager> VILLAGE_MANAGER = (RegistryObject<BlockVillageManager>) BLOCKS.register("village_manager", () -> new BlockVillageManager(BlockVillageManager.properties));
	public static final RegistryObject<BlockSupplyOffice> SUPPLY_OFFICE = (RegistryObject<BlockSupplyOffice>) BLOCKS.register("supply_office", () -> new BlockSupplyOffice(BlockSupplyOffice.properties));
	public static final RegistryObject<BlockTownHall> TOWN_HALL = (RegistryObject<BlockTownHall>) BLOCKS.register("town_hall", () -> new BlockTownHall(BlockTownHall.properties));
	public static final RegistryObject<BlockInn> INN = (RegistryObject<BlockInn>) BLOCKS.register("inn", () -> new BlockInn(BlockInn.properties));
	
	// caravaneer
	// Utility function
	public static final Set<BlockState> getAllStates(Block block) { 
		ImmutableList states = block.getStateContainer().getValidStates();
		return ImmutableSet.copyOf(states);
	}
}
