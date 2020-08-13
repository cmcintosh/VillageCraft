package com.villagecraft.util;

import java.util.HashMap;
import java.util.Map;

import com.villagecraft.VillageCraft;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFinder {

	private int debugTick = 100;
	private Map<Block, BlockScanner> scanners = new HashMap<>();
	
	public void registerBlockScanner(BlockScanner blockScanner) {
		this.scanners.put(blockScanner.getScanBlock(), blockScanner);
	}
	
	public boolean hasBlock(Block block) {
		BlockScanner scanner = this.scanners.get(block);
		return (scanner != null && scanner.hasBlocks());
	}
	
	public BlockPos requestBlock(Block block) { 
		BlockScanner scanner = this.scanners.get(block);
		if (scanner != null && scanner.hasBlocks()) {
			BlockPos bp = scanner.requestBlock();
			return bp;
		}
		return null;
	}
	
	public void releaseClaim(World world, Block block, BlockPos bp) { 
		BlockScanner scanner = this.scanners.get(block);
		if (scanner != null) {
			scanner.releaseClaim(bp);
		}
	}
	
	public int getBlockCount(Block b) {
		BlockScanner scanner = this.scanners.get(b);
		if (scanner != null) {
			return scanner.getBlockCount();
		}
		return 0;
	}
	
	public void update() {
		this.scanners.forEach((k, v) -> v.update());
	}
	
	private void debugOut() {
		this.scanners.forEach((k, v) -> VillageCraft.LOGGER.debug("    Block Finder: [" + k.toString() + "]  " + v.getBlockCount()));
	}
}
