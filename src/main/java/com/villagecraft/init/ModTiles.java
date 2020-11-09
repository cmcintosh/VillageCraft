package com.villagecraft.init;

import com.villagecraft.alchemy.entity.tile.AlchemistTableTile;
import com.villagecraft.tile.TileEntityVillageCenter;
import com.villagecraft.util.Reference;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModTiles {

	
	public static final DeferredRegister TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MODID);
	
	
//	public static final RegistryObject<TileEntityType<?>> TILE_VILLAGE_CENTER = TILES.register("village_center", 
//			() -> {
//			return new TileEntityType(TileEntityVillageCenter::create);
//			// return TileEntityType.Builder.create(TileEntityVillageCenter::new, ModBlocks.BLOCK_VILLAGE_CENTER.get()).build(null);	
//	});
	
	public static final RegistryObject<TileEntityType<TileEntityVillageCenter>> TILE_VILLAGE_CENTER = TILES
			.register("village_center", () -> TileEntityType.Builder
					.create(TileEntityVillageCenter::new, ModBlocks.BLOCK_VILLAGE_CENTER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<AlchemistTableTile>> TILE_ALCHEMIST_TABLE = TILES
			.register("alchemist_table", () -> TileEntityType.Builder
					.create(AlchemistTableTile::new, ModBlocks.BLOCK_ALCHEMIST_TABLE.get()).build(null));
}
