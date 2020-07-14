package com.villagecraft.init;

import com.villagecraft.util.Reference;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.villagecraft.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;


/**
 * This class holds all our ItemGroups (Formerly called CreativeTabs).
 * Static initialisers are fine here.
 *
 * @author cmcintosh
 */
public class ModItemGroups {
	
	// public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(Reference.MODID, () -> new ItemStack(ModItems.NATION_CHARTER.get()));

	public static final class ModItemGroup extends ItemGroup {

		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		@Nonnull
		public ItemStack createIcon() {
			return iconSupplier.get();
		}

	}
}
