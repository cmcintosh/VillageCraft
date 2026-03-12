package com.villagecraft.item;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

/**
 * Spawn egg for Village Golem - exists to work around registration order
 */
public class ItemSpawnGolem extends SpawnEggItem {

	private final Supplier<? extends EntityType<? extends net.minecraft.world.entity.Mob>> entityTypeSupplier;
	public static Item.Properties properties = new Item.Properties();
	
	public ItemSpawnGolem(int primaryColor, int secondaryColor, Item.Properties properties, Supplier<? extends EntityType<? extends net.minecraft.world.entity.Mob>> entityTypeSupplier) {
		super(null, properties);
		this.entityTypeSupplier = entityTypeSupplier;
	}

	@Override
	public EntityType<?> getType(@Nullable final CompoundTag nbt) {
		return entityTypeSupplier.get();
	}

}