package com.villagecraft.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;

import java.util.Queue;
import java.util.Comparator;

public abstract class BlockScanner {

	private Random rng = new Random();
	protected final Block scanBlock;
	private long tickCount = 0L;
	private long releaseTick = 0L;
	private int scansPerTick;
	private List<BlockPos> recentBlocks = new LinkedList<>();
	private Map<BlockPos, Long> claimedBlocks = new HashMap<>();
	protected Queue<BlockPos> scannedBlocks;
	protected BlockPos center;
	protected int maxRadius = 100;
	
	protected World world;
	
	public BlockScanner(Block scanBlock, int scansPerTick, BlockPos center, World world) {
		
		this.scanBlock = scanBlock;
		this.scansPerTick = scansPerTick;
		this.scannedBlocks = new PriorityQueue<>(50, Comparator.comparingInt(
				a -> (int) a.compareTo( (Vector3i) this.center)
		));
		this.world = world;
	}
	
	public Block getScanBlock() { 
		return this.scanBlock;
	}
	
	public void update() { 
		if (this.center != null) {
			this.tickCount++;
			if (!this.recentBlocks.isEmpty()) {
				BlockPos recent = this.recentBlocks.remove(0);
				scanNearby(recent);
			}
			for (int i = 0; i < this.scansPerTick; i++) { 
				scanRandomBlock(this.rng.nextFloat());
			}
			if (this.releaseTick-- < 0L) { 
				this.releaseTick = 100L;
				releaseClaimedBlocks();
			}
		}
	}
	
	public boolean hasBlocks() {
		return !this.scannedBlocks.isEmpty();
	}
	
	public int getBlockCount() {
		return this.scannedBlocks.size();
	}
	
	public BlockPos requestBlock() {
		while (!this.scannedBlocks.isEmpty()) {
			BlockPos bp = this.scannedBlocks.poll();
			World world = Minecraft.getInstance().world;
			if (world.getBlockState(this.center).equals(world.getBlockState(bp))) {
				this.claimedBlocks.put(bp, Long.valueOf(this.tickCount));
				return bp;
			}
		}
		return null;
	}
	
	public void releaseClaim(BlockPos bp) {
		this.claimedBlocks.remove(bp);
	}
	
	protected void scanRandomBlock(float mod) {
		// For now we are going to hard code this value to 100.
		int radius = Math.max((int)(maxRadius * mod), 20);
		int vertOffset = (int)(20.0F * mod) + 5;
		int X = center.getX() + radius - this.rng.nextInt(radius * 2);
		int Y = center.getY() + radius - this.rng.nextInt(radius * 2) ;
		int Z = this.center.getZ() + radius - this.rng.nextInt(radius * 2);
		scanBlock(new BlockPos(X, Y, Z));
	}
	
	protected boolean isInRadius(BlockPos pos) { 
		int maxX =   (int)(maxRadius + center.getX());
		int minX = - (int)(maxRadius - center.getX());
		int maxY =   (int)(maxRadius + center.getY());
		int minY = - (int)(maxRadius - center.getY());
		int maxZ =   (int)(maxRadius + center.getZ());
		int minZ = - (int)(maxRadius - center.getZ());
		
		return ( (pos.getX() > minX || pos.getX() < maxX)  && (pos.getY() > minY || pos.getY() < maxY)  && (pos.getZ() > minZ || pos.getZ() < maxZ) );
	}
	
	protected void scanBlock(BlockPos pos) { 
		if (this.isInRadius(pos)) {
			BlockPos targetPos = testBlock(this.world, pos);
			if (targetPos != null && !this.scannedBlocks.contains(targetPos)) {
				if (!this.claimedBlocks.containsKey(targetPos)) {
					this.scannedBlocks.add(targetPos);
					this.recentBlocks.add(targetPos);
				} 
			}
		} 
	}
	
	protected void releaseClaimedBlocks() {
		Iterator<Map.Entry<BlockPos, Long>> itr = this.claimedBlocks.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<BlockPos, Long> entry = itr.next();
			long timeClaimed = this.tickCount - ((Long)entry.getValue()).longValue();
			if (timeClaimed > 2400L) {
				itr.remove(); continue;
			} 
			if (timeClaimed > 600L);
		} 
	}
	

	public BlockPos getCenter() { 
		return this.center;
	}
	
	public void updateCenter(BlockPos bp) { 
		this.center = bp;
	}
	
	
	public abstract BlockPos testBlock(World paramWorld, BlockPos paramBlockPos);
	
	protected abstract void scanNearby(BlockPos paramBlockPos);
}
