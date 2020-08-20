package com.villagecraft.init;

import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.villagecraft.VillageCraft;
import com.villagecraft.block.BlockBardStand;
import com.villagecraft.block.BlockBeeKeepersHive;
import com.villagecraft.block.BlockBrawlerEquipment;
import com.villagecraft.block.BlockChair;

import com.villagecraft.block.BlockDraftingTable;
import com.villagecraft.block.BlockOreBox;
import com.villagecraft.block.BlockVillageCenter;
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
	
	// Utility function
	public static final Set<BlockState> getAllStates(Block block) { 
		ImmutableList states = block.getStateContainer().getValidStates();
		return ImmutableSet.copyOf(states);
	}
}
