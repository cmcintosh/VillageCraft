package com.villagecraft.item;

import java.util.function.Supplier;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

// TODO: Reimplement for 1.20.2 - SpawnEggItem constructor changed
public class ItemSpawnGolem extends Item {

	private final Supplier<? extends EntityType<? extends net.minecraft.world.entity.Mob>> entityTypeSupplier;
	public static Item.Properties properties = new Item.Properties();
	
	public ItemSpawnGolem(int primaryColor, int secondaryColor, Item.Properties properties, Supplier<? extends EntityType<? extends net.minecraft.world.entity.Mob>> entityTypeSupplier) {
		super(properties);
		this.entityTypeSupplier = entityTypeSupplier;
	}

	public EntityType<? extends net.minecraft.world.entity.Mob> getType(final CompoundTag nbt) {
		return entityTypeSupplier.get();
	}

}