package com.villagecraft.block;

import java.util.List;

import javax.annotation.Nullable;

import com.villagecraft.init.ModItems;
import com.villagecraft.init.ModTiles;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.tile.AlchemistTableTile;
import com.villagecraft.tile.TileEntityVillageCenter;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockAlchemistTable extends ContainerBlock {
	
	public static Properties properties = Properties.create(Material.WOOD).hardnessAndResistance(3.5F).sound(SoundType.CLOTH).notSolid();
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static net.minecraft.item.Item.Properties item_properties = new net.minecraft.item.Item.Properties().group(ModVillagerProfessions.PROFESSION_BLOCKS).maxStackSize(64);
	protected AlchemistTableTile tile;

	public BlockAlchemistTable(Properties properties) {
		super(properties);
		setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.NORTH));
	}
	
	// Defines the properties needed for the blockstate
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) { 
		super.fillStateContainer(builder);
		builder.add(FACING);
	}
	
	@Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }
	
	public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
	
	// When activated we will have the player sit
	// @TODO: learn how to do this
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult blockRayTraceResult) { 
		if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof INamedContainerProvider) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
            return ActionResultType.SUCCESS;
        }
		return ActionResultType.FAIL;
	}
	
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityVillageCenter) {
        	InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
        	worldIn.updateComparatorOutputLevel(pos, this);
        }
    }
	
	@OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
//        tooltip.add(new TranslationTextComponent("block.vcm.village_center.desc0").func_240699_a_(TextFormatting.GRAY));
//        tooltip.add(new TranslationTextComponent("block.vcm.village_center.desc1").func_240699_a_(TextFormatting.GRAY));
    }
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	public TileEntity getTileEntity() { 
		return this.tile;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		// TODO Auto-generated method stub
		tile = ModTiles.TILE_ALCHEMIST_TABLE.get().create();
		return tile;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		tile = ModTiles.TILE_ALCHEMIST_TABLE.get().create();
		return tile;
	}
}
