package com.villagecraft.block;

import java.util.List;

import javax.annotation.Nullable;

import com.villagecraft.VillageCraft;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.init.ModContainer;
import com.villagecraft.init.ModTiles;
import com.villagecraft.tile.BlockEntityVillageCenter;
import com.villagecraft.util.Reference;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockRenderType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.ContainerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.AbstractBlock.Properties;
// Material removed - use BlockBehaviour
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainerHelper;
import net.minecraft.world.inventory.Container;
// TODO: MenuType import;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.BlockItemUseContext;
import net.minecraft.world.item.ItemGroup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.core.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.network.chat.TextFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.network.NetworkHooks;

public class BlockSupplyOffice extends AbstractContainerMenuBlock {
	
	public static Properties properties = Properties.create(Material.WOOD).hardnessAndResistance(3.5F).sound(SoundType.CLOTH).notSolid();
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static net.minecraft.item.Item.Properties item_properties = new net.minecraft.item.Item.Properties().group(ItemGroup.DECORATIONS).maxStackSize(64);
	private static final VoxelShape AABB = Block.makeCuboidShape(0, 0, 0, 16, 18, 16);
	
	public BlockSupplyOffice(Properties properties) {
		super(properties);
		setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.NORTH));	
	}
	
	
	
	public void onReplaced(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        
        if (tileentity instanceof BlockEntityVillageCenter) {
        	InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
        	worldIn.updateComparatorOutputLevel(pos, this);
        }
    }
	
	@OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<Component> tooltip, ITooltipFlag flagIn) {
//        tooltip.add(Component.translatable("block.vcm.village_center.desc0").func_240699_a_(TextFormatting.GRAY));
//        tooltip.add(Component.translatable("block.vcm.village_center.desc1").func_240699_a_(TextFormatting.GRAY));
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
	public ActionResultType onBlockActivated(BlockState state, Level worldIn, BlockPos pos, Player player, 
			Hand handIn, BlockRayTraceResult blockRayTraceResult) {
		
		if (!worldIn.isRemote) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof INamedContainerProvider) {
                NetworkHooks.openGui((ServerPlayer) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
            return ActionResultType.SUCCESS;
        }
		return ActionResultType.FAIL;
	}
		
	protected BlockEntityVillageCenter tile;
	
	public BlockEntity getBlockEntity() { 
		return this.tile;
	}

	@Override
	public boolean hasBlockEntity(BlockState state) {
		return true;
	}

	@Override
	public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
		BlockEntityVillageCenter tile = ModTiles.TILE_VILLAGE_CENTER.get().create();
		
		return tile;
	}

	@Override
	public BlockEntity createNewBlockEntity(IBlockReader worldIn) {
		return ModTiles.TILE_VILLAGE_CENTER.get().create();
	}
	
	
}
