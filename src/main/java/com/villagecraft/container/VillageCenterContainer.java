package com.villagecraft.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModContainer;
import com.villagecraft.tile.TileEntityVillageCenter;
import com.villagecraft.util.Reference;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraft.client.gui.screen.inventory.ChestScreen;

public class VillageCenterContainer extends Container {
	
	public static ContainerType containerType;
	protected TileEntityVillageCenter tileEntity;
	private VillageCenterInventory inventory;
	
	/**
	 * Starting variables for positioning.
	 */
	protected int startX = 0;
	protected int startY = 0;
	int startVillageInventoryX = 117;
	int startVillageInventoryY = 19;
	int startInventoryX = 8;
	int startInventoryY = 84;
	protected int slotSizePlus2 = 18;
	
   public VillageCenterContainer(PlayerInventory playerInventory, ItemStack stack){	
	   
       super(ModContainer.VILLAGE_CENTER_CONTAINER.get(), ModContainer.VILLAGE_CENTER_GUI_ID);
              
	   this.createMainInventory();
	    // Main Player Inventory
		int startPlayerInvY = startY * 5 + 12;
		this.bindPlayerInventory(playerInventory, startInventoryX, startInventoryY);
   }
   
   public VillageCenterContainer(final int windowId, final PlayerInventory playerInventory, TileEntityVillageCenter tileEntity) {
	   super(ModContainer.VILLAGE_CENTER_CONTAINER.get(), windowId);
	   this.tileEntity = tileEntity;
	   this.createMainInventory();
	   // Main Player Inventory
	   int startPlayerInvY = startY * 5 + 12;
	   this.bindPlayerInventory(playerInventory, startInventoryX, startInventoryY);
	}
	
	public VillageCenterContainer(final int windowId, final PlayerInventory playerInventory) {
		// Called during container registry
	   super(ModContainer.VILLAGE_CENTER_CONTAINER.get(), windowId);
	   this.tileEntity = (TileEntityVillageCenter) Reference.getRefrencedTE();
	   this.createMainInventory();
	   int startPlayerInvY = startY * 5 + 12;
	   this.bindPlayerInventory(playerInventory, startInventoryX, startInventoryY);
	
	}
	
	public static TileEntityVillageCenter getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof TileEntityVillageCenter) {
			return (TileEntityVillageCenter) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}
	
	public TileEntityVillageCenter tileEntity() { 
		return this.tileEntity;
	}
	
	public VillageCenterContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}


	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
	    super.onContainerClosed(playerIn);
	}
	
	protected void createMainInventory() { 		
		for (int row = 0; row < 3; ++row) { 
			for (int column = 0; column < 3; ++column) {
				int inventoryIndex = (row * 3) + column;
				Slot slot = new Slot(tileEntity, inventoryIndex, 
						this.startVillageInventoryX + (column * slotSizePlus2), 
						this.startVillageInventoryY + (row * slotSizePlus2)) {
				            public void onSlotChanged() {
				            		this.inventory.markDirty();
					            }
			
					            @Override
					            public boolean isItemValid(ItemStack stack) {
					                return super.isItemValid(stack);
					            }
			        };
					this.addSlot(slot);
			}
		}
	}
	
	protected void bindPlayerInventory(PlayerInventory inventoryPlayer, int x, int y) {
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(inventoryPlayer, j + i * 9 + 9, x + j * 18, y + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(inventoryPlayer, i, x + i * 18, y + 58));
        }
    }
	
	protected void bindHotbar(PlayerInventory playerInventory, int startX, int hotbarY, int slotSizePlus2) { 
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizePlus2), hotbarY));
		}
	}	
	
	@Override
    public ItemStack transferStackInSlot(PlayerEntity player, int slot){
        Slot sourceSlot = getSlot(slot);

        for(Slot targetSlot : getInventorySlots(tileEntity, true))
            if(transferToSlot(sourceSlot, targetSlot) == 0) break;

        return ItemStack.EMPTY; //this has to return empty itemstack, otherwise it runs as loop
    }
	
	protected List<Slot> getInventorySlots(IInventory inventory, boolean invertRule){
        ArrayList<Slot> slots = new ArrayList<>();
        for(Slot slot : inventorySlots) {
            if (invertRule && slot.inventory.getClass().equals(inventory.getClass()))
                continue;

            if (!invertRule && !slot.inventory.equals(inventory))
                continue;

            slots.add(slot);
        }

        return slots;
    }
	
	protected int transferToSlot(Slot source, Slot target){
        if(source.getStack().isEmpty())
            return 0;

        if(!target.isItemValid(source.getStack()))
            return source.getStack().getCount();

        //return if stack in slot is not equal to input

        ItemStack stack = source.getStack().copy();
        ItemStack stackOld = source.getStack().copy();

        int transfer = Math.min(target.getSlotStackLimit(), source.getStack().getCount());

        if(target.getHasStack()) {
            if (!ItemHandlerHelper.canItemStacksStack(source.getStack(), target.getStack()))
                return source.getStack().getCount();

            transfer = Math.min(transfer, Math.min(target.getStack().getMaxStackSize(), target.getSlotStackLimit()) - target.getStack().getCount());
            stackOld.shrink(transfer);
            stack = target.getStack();
            stack.grow(transfer);
        }
        else{
            stack.setCount(transfer);
            stackOld.shrink(transfer);
        }
        if(transfer > 0) {
            source.putStack(stackOld);
            target.putStack(stack);
        }

        return source.getStack().getCount();
    }
}
