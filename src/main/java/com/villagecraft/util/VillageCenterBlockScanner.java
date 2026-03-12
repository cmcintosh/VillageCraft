package com.villagecraft.util;

import com.villagecraft.init.ModBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

// TODO: Reimplement for 1.20.2
public class VillageCenterBlockScanner extends BlockScanner {
	
	public VillageCenterBlockScanner(BlockPos center, Level world) { 
		super(ModBlocks.BLOCK_VILLAGE_CENTER.get(), 150, center, world);
	}

	public VillageCenterBlockScanner(Block scanBlock, int scansPerTick, BlockPos center, Level world) {
		super(scanBlock, scansPerTick, center, world);
	}

	@Override
	public BlockPos testBlock(Level world, BlockPos bp) {
		BlockState state = world.getBlockState(bp);
		if (state.getBlock().toString().equals(this.scanBlock.toString())) { 
			return bp;
		}
		return null;
	}

	@Override
	protected void scanNearby(BlockPos bp) {
		// TODO: BlockPos.getAllInBoxMutable API changed
	}
}
