package com.villagecraft.util;

import com.villagecraft.init.ModBlocks;
import com.villagecraft.VillageCraft;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * Scanner for VillageCenter blocks in a village.
 * Updated for NeoForge 1.20.2
 */
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
		// 1.20.2: Use equals() comparison for block matching
		if (state.is(this.scanBlock)) { 
			return bp;
		}
		return null;
	}

	@Override
	protected void scanNearby(BlockPos bp) {
		// 1.20.2: BlockPos.getAllInBoxMutable changed to BlockPos.betweenClosed()
		// This scans a 3x3x3 area around the found block
		int radius = 1;
		for (BlockPos pos : BlockPos.betweenClosed(
			bp.getX() - radius, bp.getY() - radius, bp.getZ() - radius,
			bp.getX() + radius, bp.getY() + radius, bp.getZ() + radius
		)) {
			// Convert to immutable and check if we can find valid positions
			BlockPos immutable = pos.immutable();
			// scanBlock() method handles adding to scannedBlocks
			scanBlock(immutable);
		}
		
		VillageCraft.LOGGER.debug("Scanned nearby blocks around VillageCenter at {}, found {} blocks", 
			bp, this.scannedBlocks.size());
	}
	
	/**
	 * Check if a position is within a village center
	 * Used to validate village center block positions
	 */
	public boolean isValidVillageCenter(Level level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		return state.is(this.scanBlock) || state.is(ModBlocks.BLOCK_VILLAGE_CENTER.get());
	}
	
	/**
	 * Get the nearest village center to a position
	 */
	public BlockPos getNearestVillageCenter(BlockPos from) {
		if (this.scannedBlocks.isEmpty()) {
			return null;
		}
		
		BlockPos nearest = null;
		double nearestDist = Double.MAX_VALUE;
		
		for (BlockPos pos : this.scannedBlocks) {
			double dist = pos.distSqr(from);
			if (dist < nearestDist) {
				nearestDist = dist;
				nearest = pos;
			}
		}
		
		return nearest;
	}
}