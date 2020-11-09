package com.villagecraft.tile;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.villagecraft.VillageCraft;
import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.container.VillageCenterInventory;
import com.villagecraft.init.ModTiles;
import com.villagecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ChunkManager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TileEntityVillageCenter extends TileBasicVillageBlock {

	protected String dataType = "village_center"; 
	protected boolean enabled  = false;
	private UUID ownerId = null;
	private UUID villageId;
	
	protected int ticksPerDay = 24000;
	protected int ticksPerHour = 1000;
	protected int ticksPerSecond = 20;
	
	protected int maxFuelTicks = 100;
	protected int currentFuelTick = 0;
	
	private final String UUID_VILLAGE = "VILLAGE_TAG";
	private final String UUID_TAG = "UUID_TAG";
    private final String ENABLED_TAG = "VILLAGE_ENABLED_TAG";
	
	public TileEntityVillageCenter(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, 9);
		this.villageId = UUID.randomUUID();
		this.enabled = true;
		this.markDirty();
		 
	}
	
	public TileEntityVillageCenter() {
		this(ModTiles.TILE_VILLAGE_CENTER.get());
		this.villageId = UUID.randomUUID();
		this.enabled = true;
		this.markDirty();
	}

	@Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new VillageCenterContainer(i, playerInventory, this);
    }

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
	
	
    public void read(CompoundNBT compound)
    {
        villageId = compound.getUniqueId(UUID_VILLAGE);
        enabled = compound.getBoolean(ENABLED_TAG);
        VillageCraft.LOGGER.debug("Village ID: " + villageId + ", Enabled: " + enabled);
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
    	compound.putUniqueId(UUID_VILLAGE, villageId);
        compound.putBoolean(ENABLED_TAG, enabled);
        
        return super.write(compound);
    }
    

	@Override
	public void tick() {
		// 
		
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) { 
		if (stack.getItem().getRegistryName() == Items.EMERALD.getRegistryName()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the UUID for this village.
	 * @return
	 */
	public UUID getVillageId() {
		return this.villageId;
	}
	
	/**
	 * Returns if the village center is enabled.
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	
	/**
	 * Returns the village center owner.
	 */
	public UUID getOwner() {
		return this.ownerId;	
	}

}
