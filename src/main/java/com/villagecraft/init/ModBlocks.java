package com.villagecraft.init;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.villagecraft.block.BlockChair;
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
	
	// Adds the Chair block
	public static final RegistryObject<BlockChair> BLOCK_CHAIR = (RegistryObject<BlockChair>) BLOCKS.register("chair", () -> new BlockChair(BlockChair.properties)); 
	
	
	// Utility function
	public static final Set<BlockState> getAllStates(Block block) { 
		return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
	}
}
