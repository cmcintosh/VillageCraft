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
import net.minecraft.tileentity.LockableTileEntity;
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

public class TileEntityVillageCenter extends TileBasicVillageBlock implements ITickableTileEntity, INamedContainerProvider, IInventory {
	
	public static String UUID_VILLAGE = "VILLAGE_TAG";
	public static String UUID_TAG = "UUID_TAG";
    public static String ENABLED_TAG = "VILLAGE_ENABLED_TAG";
    public static String VILLAGE_LEVEL = "VILLAGE_LEVEL";
    public static String VILLAGE_DAMAGE = "VILLAGE_DAMAGE";
	

	protected int size = 15;
	private NonNullList<ItemStack> inventory = NonNullList.withSize(11, ItemStack.EMPTY);
	
	protected String dataType = "village_center"; 
	
	private boolean enabled  = true;
	private UUID ownerId = null;
	private UUID villageId;
	private int level;
	private int damage = 100;
	
	protected int ticksPerDay = 24000;
	protected int ticksPerHour = 1000;
	protected int ticksPerSecond = 20;
	
	protected int maxFuelTicks = 100;
	protected int currentFuelTick = 0;
	
	
	public TileEntityVillageCenter(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, 10);
		
		inventory = NonNullList.withSize(size, ItemStack.EMPTY);
		this.villageId = UUID.randomUUID();
		this.markDirty();
		 
	}
	
	public TileEntityVillageCenter() {
		this(ModTiles.TILE_VILLAGE_CENTER.get());
		
		inventory = NonNullList.withSize(size, ItemStack.EMPTY);
		this.villageId = UUID.randomUUID();
		this.enabled = true;
		this.markDirty();
	}
		
    public void read(CompoundNBT compound)
    {
        villageId = compound.getUniqueId(UUID_VILLAGE);
        enabled = compound.getBoolean(ENABLED_TAG);
        level = compound.getInt(VILLAGE_LEVEL);
        damage = compound.getInt(VILLAGE_DAMAGE);
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
    	compound.putUniqueId(UUID_VILLAGE, villageId);
        compound.putBoolean(ENABLED_TAG, enabled);
        compound.putInt(VILLAGE_LEVEL, level);
        compound.putInt(VILLAGE_DAMAGE, damage);
        
        return super.write(compound);
    }
    
    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new VillageCenterContainer(i, playerInventory, this);
    }
    
    
	protected Container createMenu(int id, PlayerInventory playerInventory) {
		// TODO Auto-generated method stub
    	return new VillageCenterContainer(id, playerInventory, this);
	}
    
    @Override
	public void clear() {
		inventory.clear();
	}

	@Override
	public int getSizeInventory() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.inventory.isEmpty();
	}
    
	@Override
	public ItemStack getStackInSlot(int index) {
		if (index > size - 1 || inventory == null)
			return ItemStack.EMPTY;
		return inventory.get(index);
	}
	

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (index > size - 1)
			return ItemStack.EMPTY;
		ItemStack stack = inventory.get(index);
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
		ItemStack stack = inventory.get(index).copy();
		inventory.set(index, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index > size - 1)
			return;
		inventory.set(index, stack);
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
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 14 || index == 15) {
			if (stack.getItem().getRegistryName() == Items.EMERALD.getRegistryName() || stack.getItem().getRegistryName() == Items.EMERALD_BLOCK.getRegistryName()) {
				return true;
			}
			return false;	
		}
		return true;
	}
	
	@Override
	public void tick() {
	
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
	
	public int getVillageLevel() {
		return this.level;
	}
	
	/**
	 * Creates an array of boolean values, each value represents a potion input slot, value is true if the slot is not
	 * null.
    */
   public boolean[] createFilledSlotsArray() {
      boolean[] aboolean = new boolean[3];

      for(int i = 0; i < 3; ++i) {
         if (!this.inventory.get(i).isEmpty()) {
            aboolean[i] = true;
         }
      }

      return aboolean;
   }

	
	protected ITextComponent getDefaultName() {
		// TODO Auto-generated method stub
		return new StringTextComponent(getType().getRegistryName().getPath());
	}

}
