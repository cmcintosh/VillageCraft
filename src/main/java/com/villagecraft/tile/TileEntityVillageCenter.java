package com.villagecraft.tile;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.villagecraft.VillageCraft;
import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.init.ModTiles;
import com.villagecraft.util.Reference;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class TileEntityVillageCenter extends TileBasicVillageBlock {

	protected String dataType = "village_center"; 
	protected boolean enabled  = false;
	private UUID ownerId = null;
	
	protected int ticksPerDay = 24000;
	protected int ticksPerHour = 1000;
	protected int ticksPerSecond = 20;
	
	protected int maxFuelTicks = 100;
	protected int currentFuelTick = 0;
	
	private final String UUID_TAG = "UUID_TAG";
    private final String ENABLED_TAG = "VILLAGE_ENABLED_TAG";
	
	// Constructor for BlockEntityType.Builder - now matches 1.20.2 signature
	public TileEntityVillageCenter(BlockPos pos, BlockState state) {
		super(ModTiles.TILE_VILLAGE_CENTER.get(), 9);
		// BlockEntity constructor automatically stores pos and state
	}
	
	// Default constructor for manual creation
	public TileEntityVillageCenter() {
		super(ModTiles.TILE_VILLAGE_CENTER.get(), 9);
	}

	@Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
        return new VillageCenterContainer(i, playerInventory, this);
    }

	@Override
	public Component getDisplayName() {
		return Component.literal("Village Center");
	}
	
	
    public void read(CompoundTag compound)
    {
        ownerId = compound.getUUID(UUID_TAG);
        enabled = compound.getBoolean(ENABLED_TAG);
    }

    @Nonnull
    @Override
    public CompoundTag saveWithoutMetadata(@Nonnull CompoundTag compound)
    {
        compound.putUUID(UUID_TAG, ownerId);
        compound.putBoolean(ENABLED_TAG, enabled);
        return super.saveWithoutMetadata(compound);
    }
    

	@Override
	public void tick() {
		
	}
	
}
