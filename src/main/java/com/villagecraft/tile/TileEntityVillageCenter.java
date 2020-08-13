package com.villagecraft.tile;

import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.init.ModTiles;
import com.villagecraft.util.Reference;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TileEntityVillageCenter extends TileEntity implements IClearable, INamedContainerProvider {

	public TileEntityVillageCenter() {
		this(ModTiles.TILE_VILLAGE_CENTER.get());
		
	}

	public TileEntityVillageCenter(final TileEntityType<?> type) {
		super(type);
	}

	@Override
	public Container createMenu(int id, PlayerInventory p_createMenu_2_, PlayerEntity player) {
		// TODO Auto-generated method stub
		return new VillageCenterContainer(id, player, this);
	}

	/*
	 * This method gets the name of this tile that will be used in several places
	 * such as our screen class. We return a translation text component so that the
	 * name of this tile can be translated in our lang to other languages.
	 */
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container." + Reference.MODID + ".village_center");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	

}
