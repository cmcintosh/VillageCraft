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
import net.minecraft.block.OreBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.OakTree;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
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
	
	
    
	// Utility function
	public static final Set<BlockState> getAllStates(Block block) { 
		ImmutableList states = block.getStateContainer().getValidStates();
		return ImmutableSet.copyOf(states);
	}
	
}
