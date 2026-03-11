package com.villagecraft.init;

import com.villagecraft.tile.TileEntityVillageCenter;
import com.villagecraft.util.Reference;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModTiles {

	
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Reference.MODID);
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileEntityVillageCenter>> TILE_VILLAGE_CENTER = TILES
			.register("village_center", () -> BlockEntityType.Builder
					.of(TileEntityVillageCenter::new, ModBlocks.BLOCK_VILLAGE_CENTER.get()).build(null));
}
