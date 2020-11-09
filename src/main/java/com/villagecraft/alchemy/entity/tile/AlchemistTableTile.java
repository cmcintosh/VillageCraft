package com.villagecraft.alchemy.entity.tile;

import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.Maps;
import com.villagecraft.alchemy.container.AlchemistTableContainer;
import com.villagecraft.data.VillageDataHelper;
import com.villagecraft.init.ModItems;
import com.villagecraft.init.ModTiles;

import net.minecraft.block.BlockState;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.tileentity.BrewingStandTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class AlchemistTableTile extends LockableTileEntity implements ITickableTileEntity, INamedContainerProvider, IInventory {

	
	protected int size = 15;
	protected VillageDataHelper dataHelper;
	protected String dataType = "AlchemistTable";
	private boolean[] filledSlots;
	
	public static final int INPUT_SLOT_1 = 0;
	public static final int INPUT_SLOT_2 = 1;
	public static final int INPUT_SLOT_3 = 2; 
	public static final int OUTPUT_SLOT = 3;
	
	private int fuel = 0;
	private int timer;
	
	private int transmuteTime;
	private int currentItemTransmuteTime;
	private int totalCookTime = this.getCookTime();
	
	private Item ingredientID;
	private Item ingredient2ID;
	private Item ingredient3ID;
	
	private final Map<ResourceLocation, Integer> recipeUseCounts = Maps.newHashMap();
//	private IRecipeType<ITransmutationRecipe> recipeType;
	private NonNullList<ItemStack> inventory = NonNullList.withSize(12, ItemStack.EMPTY);
	
	public Map<ResourceLocation, Integer> getRecipeUseCounts() {
        return this.recipeUseCounts;
    }
	
	
	public AlchemistTableTile(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		dataHelper = VillageDataHelper.nextDataEntity(dataType);
		inventory = NonNullList.withSize(size, ItemStack.EMPTY);
	}
	
	public AlchemistTableTile() {
		this(ModTiles.TILE_ALCHEMIST_TABLE.get());
		dataHelper = VillageDataHelper.nextDataEntity(dataType);
		inventory = NonNullList.withSize(size, ItemStack.EMPTY);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		this.dataHelper.write(compound);
		ItemStackHelper.saveAllItems(compound, inventory);
		return super.write(compound);
	}
	
	@Override
	public void read(BlockState blockState, CompoundNBT nbt) {
		super.read(blockState, nbt);
		this.dataHelper.read(nbt);
		ItemStackHelper.loadAllItems(nbt, inventory);
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		// TODO Auto-generated method stub
		return new AlchemistTableContainer(id, playerInventory, this); 
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
		// TODO Auto-generated method stub
		return new StringTextComponent("");
	}
	
	public boolean isTransmuting() {
        return this.transmuteTime > 0;
    }
	
	/**
	 * Consumes the regeant from slot id 2
	 * @return
	 */
	protected boolean consumeFuel() { 
		return true;
	}
	
	/**
	 * Returns if we can continue crafting or not.
	 */
	protected boolean canTransmute() { 

		return false;
	}

	@Override
	public void tick() {
		ItemStack itemstack = this.inventory.get(2);
	      if (this.fuel <= 0 && this.consumeFuel()) {
	         this.fuel = 20;
	         itemstack.shrink(1);
	         this.markDirty();
	      }

	      boolean flag = this.canTransmute();
	      boolean flag1 = this.transmuteTime > 0;
	      
	      ItemStack itemstack1 = this.inventory.get(0);
	      ItemStack itemstack2 = this.inventory.get(1);
	      ItemStack itemstack3 = this.inventory.get(2);
	      
	      if (flag1) {
	         --this.transmuteTime;
	         boolean flag2 = this.transmuteTime == 0;
	         if (flag2 && flag) {
	            this.transmuteItems();
	            this.markDirty();
	         } else if (!flag) {
	            this.transmuteTime = 0;
	            this.markDirty();
	         } else if ( this.ingredientID != itemstack1.getItem() || this.ingredient2ID != itemstack2.getItem() || this.ingredient3ID != itemstack3.getItem()) {
	            this.transmuteTime = 0;
	            this.markDirty();
	         }
	      } else if (flag && this.fuel > 0) {
	         --this.fuel;
	         this.transmuteTime = 400;
	         this.ingredientID = itemstack1.getItem();
	         this.markDirty();
	      }

	      if (!this.world.isRemote) {
	         boolean[] aboolean = this.createFilledSlotsArray();
	         if (!Arrays.equals(aboolean, this.filledSlots)) {
	            this.filledSlots = aboolean;
	            BlockState blockstate = this.world.getBlockState(this.getPos());
	            if (!(blockstate.getBlock() instanceof BrewingStandBlock)) {
	               return;
	            }

	            for(int i = 0; i < BrewingStandBlock.HAS_BOTTLE.length; ++i) {
	               blockstate = blockstate.with(BrewingStandBlock.HAS_BOTTLE[i], Boolean.valueOf(aboolean[i]));
	            }

	            this.world.setBlockState(this.pos, blockstate, 2);
	         }
	      }
	}
	
	// Transmute items 
	private void transmuteItems() { 
		 
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
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) { 
		return true;
	}
	
	protected int getCookTime() {
        ItemStack stack = this.getStackInSlot(3);
        if (!stack.isEmpty()) {
            return getCookTimeConfig() / 2;
        }
        return getCookTimeConfig();
    }

	protected int defaultCookTime = 200;
	protected int cookTime = 200;
    protected int getCookTimeConfig() {
        return cookTime;
    }
    
    public final IIntArray fields = new IIntArray() {
        public int get(int index) {
            switch (index) {
                case 0:
                    return AlchemistTableTile.this.transmuteTime;
                case 1:
                    return AlchemistTableTile.this.currentItemTransmuteTime;
                case 2:
                    return AlchemistTableTile.this.getCookTime();
                case 3:
                    return AlchemistTableTile.this.totalCookTime;
                default:
                    return 0;
            }
        }

		@Override
		public void set(int index, int value) {
			// TODO Auto-generated method stub
			switch (index) {
            case 0:
                AlchemistTableTile.this.transmuteTime = value;
            case 1:
                AlchemistTableTile.this.currentItemTransmuteTime = value;
            case 2:
                AlchemistTableTile.this.cookTime = value;
            case 3:
                AlchemistTableTile.this.totalCookTime = value;

          }
		}

		@Override
		public int size() {
			return 4;
		}
    };

	@Override
	protected ITextComponent getDefaultName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
