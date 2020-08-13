package com.villagecraft.util;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VillageCenterBlockScanner extends BlockScanner {
	
	protected int maxRadius = 100;
	
	public VillageCenterBlockScanner(BlockPos center, World world) { 
		super(ModBlocks.BLOCK_VILLAGE_CENTER.get(), 150, center, world);
	}

	public VillageCenterBlockScanner(Block scanBlock, int scansPerTick, BlockPos center, World world) {
		super(scanBlock, scansPerTick, center, world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlockPos testBlock(World world, BlockPos bp) {
		// TODO Auto-generated method stub
		BlockState state = world.getBlockState(bp);
		
		if (state.getBlock().toString() == this.scanBlock.toString()) { 
			return bp;
		}
		return null;
	}

	@Override
	protected void scanNearby(BlockPos bp) {
		for (BlockPos scanPos : BlockPos.getAllInBoxMutable(bp.getX() - 7, bp.getY() + 2, bp.getZ() - 7, bp.getX() + 7, bp.getY() + 2, bp.getZ() + 7))
		{
			scanBlock(scanPos);
		}		
	}
	

}
