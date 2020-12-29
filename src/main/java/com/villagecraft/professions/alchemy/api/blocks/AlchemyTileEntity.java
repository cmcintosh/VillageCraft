package com.villagecraft.professions.alchemy.api.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class AlchemyTileEntity extends TileEntity {

	public AlchemyTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		// TODO Auto-generated constructor stub
		
	}
	
	public void deserializeNBT(CompoundNBT nbt) {
		super.deserializeNBT(nbt);
		readCustomNBT(nbt);
	}
	
	public CompoundNBT serializeNBT() {
		CompoundNBT nbt = super.serializeNBT();
		writeCustomNBT(nbt);
		return nbt;
	}
	
	public void readCustomNBT(CompoundNBT nbt) { }
	
	public void writeCustomNBT(CompoundNBT nbt) { }
	
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		super.onDataPacket(net, pkt);
		readCustomNBT(pkt.getNbtCompound());
	}
	
	public boolean shouldRefresh(World world, BlockPos pos, BlockState oldState, BlockState newState) {
		return (oldState.getProperties() == newState.getProperties());
	}
	
	public Direction getDirection() {
		try {
			return Direction.NORTH;
		} catch (Exception e) {
			return Direction.UP;
		}
	}
	
	public boolean gettingPower() {
		return false;
	}

}
