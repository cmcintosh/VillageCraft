package com.villagecraft.tile;

import java.util.ArrayList;

import com.villagecraft.data.VillageCraftNation;
import com.villagecraft.data.VillageCraftVillage;
import com.villagecraft.data.VillageDataHelper;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerHelper;

public abstract class TileBasicVillageBlock extends BlockEntity {
	
	protected int size;
	public NonNullList<ItemStack> content;
	protected VillageDataHelper dataHelper;
	protected String dataType = "GenericVillageBlock";

	public TileBasicVillageBlock(BlockEntityType<?> tileEntityTypeIn, int size) {
		super(tileEntityTypeIn, BlockPos.ZERO, null);
		this.size = size;
		dataHelper = VillageDataHelper.nextDataEntity(dataType);
		content = NonNullList.withSize(size, ItemStack.EMPTY);
		
	}
	
	@Override
	public CompoundTag saveWithoutMetadata(CompoundTag compound) {
		this.dataHelper.write(compound);
		ContainerHelper.saveAllItems(compound, content);
		return super.saveWithoutMetadata(compound);
	}
	
	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.dataHelper.read(nbt);
		ContainerHelper.loadAllItems(nbt, content);
	}

	public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
		return null;
	}

	public void clearContent() {
		content.clear();
	}

	public int getContainerSize() {
		return size;
	}

	public boolean isEmpty() {
		return false;
	}

	public ItemStack getItem(int index) {
		if (index > size - 1)
			return ItemStack.EMPTY;
		return content.get(index);
	}

	public ItemStack removeItem(int index, int count) {
		if (index > size - 1)
			return ItemStack.EMPTY;
		ItemStack stack = content.get(index);
		if (count >= stack.getCount())
			return removeItemNoUpdate(index);
		else {
			stack.shrink(count);
			return new ItemStack(stack.getItem(), count);
		}
	}

	public ItemStack removeItemNoUpdate(int index) {
		if (index > size - 1)
			return ItemStack.EMPTY;
		ItemStack stack = content.get(index).copy();
		content.set(index, ItemStack.EMPTY);
		return stack;
	}

	public void setItem(int index, ItemStack stack) {
		if (index > size - 1)
			return;
		content.set(index, stack);
	}

	public boolean stillValid(Player player) {
		Level level = this.getLevel();
		if (level == null) return false;
		if (level.getBlockEntity(this.getBlockPos()) != this) {
			return false;
		} else {
			return !(player.distanceToSqr((double) this.getBlockPos().getX() + 0.5D, (double) this.getBlockPos().getY() + 0.5D,
					(double) this.getBlockPos().getZ() + 0.5D) > 64.0D);
		}
	}

	public Component getDisplayName() {
		return null;
	}

	public void tick() {
		
	}

}
