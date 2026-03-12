package com.villagecraft.init;

import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.villagecraft.VillageCraft;
import com.villagecraft.block.BlockAlchemistTable;
import com.villagecraft.block.BlockAuctionHouse;
import com.villagecraft.block.BlockBar;
import com.villagecraft.block.BlockBardStand;
import com.villagecraft.block.BlockBeeKeepersHive;
import com.villagecraft.block.BlockBrawlerEquipment;
import com.villagecraft.block.BlockBuildersChest;
import com.villagecraft.block.BlockCaravanStop;
import com.villagecraft.block.BlockChair;

import com.villagecraft.block.BlockDraftingTable;
import com.villagecraft.block.BlockDrums;
import com.villagecraft.block.BlockEmbassy;
import com.villagecraft.block.BlockGuitarStand;
import com.villagecraft.block.BlockInn;
import com.villagecraft.block.BlockMicrophoneStand;
import com.villagecraft.block.BlockOreBox;
import com.villagecraft.block.BlockPotterWheel;
import com.villagecraft.block.BlockPyrotechnicTable;
import com.villagecraft.block.BlockSupplyOffice;
import com.villagecraft.block.BlockTitleOffice;
import com.villagecraft.block.BlockTownHall;
import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.block.BlockVillageManager;
import com.villagecraft.block.TradesmanHelmet;
import com.villagecraft.util.Reference;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
// Material removed - use BlockBehaviour
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

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

	
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(net.minecraft.core.registries.Registries.BLOCK, Reference.MODID);
	
	public static final DeferredHolder<Block, BlockAlchemistTable> BLOCK_ALCHEMIST_TABLE =  BLOCKS.register("alchemist_table", () -> new BlockAlchemistTable(BlockAlchemistTable.properties));
	public static final DeferredHolder<Block, BlockDraftingTable> DRAFTING_TABLE =  BLOCKS.register("drafting_table", () -> new BlockDraftingTable(BlockDraftingTable.properties));
	
	
	public static final DeferredHolder<Block, BlockChair> BLOCK_CHAIR =  BLOCKS.register("chair", () -> new BlockChair(BlockChair.properties));
	public static final DeferredHolder<Block, BlockBardStand> BARD_STAND =  BLOCKS.register("bard_stand", () -> new BlockBardStand(BlockBardStand.properties));
	public static final DeferredHolder<Block, BlockOreBox> ORE_BOX =  BLOCKS.register("ore_box", () -> new BlockOreBox(BlockOreBox.properties));
	
	public static final DeferredHolder<Block, TradesmanHelmet> TRADESMAN_HELMET =  BLOCKS.register("tradesman_helmet", () -> new TradesmanHelmet(TradesmanHelmet.properties));
	public static final DeferredHolder<Block, BlockBrawlerEquipment> BRAWLER_BOX =  BLOCKS.register("brawler_box", () -> new BlockBrawlerEquipment(BlockBrawlerEquipment.properties));
	public static final DeferredHolder<Block, BlockBar> BAR =  BLOCKS.register("bar", () -> new BlockBar(BlockBar.properties));
	public static final DeferredHolder<Block, BlockDrums> DRUMS =  BLOCKS.register("drums", () -> new BlockDrums(BlockDrums.properties));
	public static final DeferredHolder<Block, BlockGuitarStand> GUITAR_STAND =  BLOCKS.register("guitar_stand", () -> new BlockGuitarStand(BlockGuitarStand.properties));
	public static final DeferredHolder<Block, BlockMicrophoneStand> MICROPHONE_STAND =  BLOCKS.register("microphone_stand", () -> new BlockMicrophoneStand(BlockMicrophoneStand.properties));
	public static final DeferredHolder<Block, BlockAuctionHouse> AUCTION_HOUSE =  BLOCKS.register("auction_house", () -> new BlockAuctionHouse(BlockAuctionHouse.properties));
	public static final DeferredHolder<Block, BlockPyrotechnicTable> PYROTECHNIC_TABLE =  BLOCKS.register("pyrotechnic_worktable", () -> new BlockPyrotechnicTable(BlockPyrotechnicTable.properties));
	public static final DeferredHolder<Block, BlockCaravanStop> CARAVAN_STOP =  BLOCKS.register("caravaneer", () -> new BlockCaravanStop(BlockCaravanStop.properties));
	public static final DeferredHolder<Block, BlockEmbassy> EMBASSY =  BLOCKS.register("embassy", () -> new BlockEmbassy(BlockEmbassy.properties));
	public static final DeferredHolder<Block, BlockVillageManager> VILLAGE_MANAGER =  BLOCKS.register("village_manager", () -> new BlockVillageManager(BlockVillageManager.properties));
	public static final DeferredHolder<Block, BlockSupplyOffice> SUPPLY_OFFICE =  BLOCKS.register("supply_office", () -> new BlockSupplyOffice(BlockSupplyOffice.properties));
	public static final DeferredHolder<Block, BlockTownHall> TOWN_HALL =  BLOCKS.register("town_hall", () -> new BlockTownHall(BlockTownHall.properties));
	public static final DeferredHolder<Block, BlockInn> INN =  BLOCKS.register("inn", () -> new BlockInn(BlockInn.properties));
	public static final DeferredHolder<Block, BlockTitleOffice> TITLE_OFFICE =  BLOCKS.register("title_office", () -> new BlockTitleOffice(BlockTitleOffice.properties));
	public static final DeferredHolder<Block, BlockPotterWheel> POTTERS_WHEEL =  BLOCKS.register("potters_wheel", () -> new BlockPotterWheel(BlockPotterWheel.properties));
	public static final DeferredHolder<Block, BlockBeeKeepersHive> BEE_KEEPERS_HIVE = BLOCKS.register("bee_keepers_hive", () -> new BlockBeeKeepersHive(BlockBeeKeepersHive.properties));

	public static final DeferredHolder<Block, BlockBuildersChest> BUILDERS_CHEST =  BLOCKS.register("builders_chest", () -> new BlockBuildersChest(BlockBuildersChest.properties));
	public static final DeferredHolder<Block, BlockVillageCenter> BLOCK_VILLAGE_CENTER =  BLOCKS.register("village_center", () -> new BlockVillageCenter(BlockVillageCenter.properties));
	
	// caravaneer
	// Utility function
	public static final Set<BlockState> getAllStates(Block block) { 
		ImmutableList states = block.getStateDefinition().getPossibleStates();
		return ImmutableSet.copyOf(states);
	}
}
