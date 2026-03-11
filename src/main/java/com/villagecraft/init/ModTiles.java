package com.villagecraft.init;

import com.villagecraft.tile.TileEntityVillageCenter;
import com.villagecraft.util.Reference;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModTiles {

	
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MODID);
	
	public static final RegistryObject<BlockEntityType<TileEntityVillageCenter>> TILE_VILLAGE_CENTER = TILES
			.register("village_center", () -> BlockEntityType.Builder
					.of(TileEntityVillageCenter::new, ModBlocks.BLOCK_VILLAGE_CENTER.get()).build(null));
}
