package com.villagecraft.init;

import com.villagecraft.util.Reference;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.villagecraft.init.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;


/**
 * This class holds all our ItemGroups (Formerly called CreativeTabs).
 * Static initialisers are fine here.
 *
 * @author cmcintosh
 */
public class ModItemGroups {
	
	// public static final CreativeModeTab MOD_ITEM_GROUP = new ModItemGroup(Reference.MODID, () -> new ItemStack(ModItems.NATION_CHARTER.get()));

	// TODO: Fix for 1.20.1 - CreativeModeTab now uses Builder pattern
	public static final class ModItemGroup extends CreativeModeTab {

		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			//super(name); // Old constructor - needs Builder pattern
			super(CreativeModeTab.Row.BOTTOM, 0);
			this.iconSupplier = iconSupplier;
		}

		@Override
		@Nonnull
		public ItemStack createIcon() {
			return iconSupplier.get();
		}

	}
	
	
}
