package com.villagecraft.tile;

import java.util.ArrayList;

import com.villagecraft.data.VillageCraftNation;
import com.villagecraft.data.VillageCraftVillage;
import com.villagecraft.data.VillageDataHelper;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.server.ServerWorld;

public abstract class TileBasicVillageBlock extends TileEntity implements ITickableTileEntity, INamedContainerProvider, IInventory {
	
	protected int size;
	public NonNullList<ItemStack> content;
	protected VillageDataHelper dataHelper;
	protected String dataType = "GenericVillageBlock";

	public TileBasicVillageBlock(TileEntityType<?> tileEntityTypeIn, int size) {
		super(tileEntityTypeIn);
		this.size = size;
		dataHelper = VillageDataHelper.nextDataEntity(dataType);
		content = NonNullList.withSize(size, ItemStack.EMPTY);
		
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		this.dataHelper.write(compound);
		ItemStackHelper.saveAllItems(compound, content);
		return super.write(compound);
	}
	
	@Override
	public void read(BlockState blockState, CompoundNBT nbt) {
		super.read(blockState, nbt);
		this.dataHelper.read(nbt);
		ItemStackHelper.loadAllItems(nbt, content);
	}


	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		content.clear();
		
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.content.isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index > size - 1)
			return ItemStack.EMPTY;
		return content.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (index > size - 1)
			return ItemStack.EMPTY;
		ItemStack stack = content.get(index);
		if (count >= stack.getCount())
			return removeStackFromSlot(index);
		else {
			stack.shrink(count);
			return new ItemStack(stack.getItem(), count);
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (index > size - 1)
			return ItemStack.EMPTY;
		ItemStack stack = content.get(index).copy();
		content.set(index, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index > size - 1)
			return;
		content.set(index, stack);
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return !(player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) > 64.0D);
		}
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
