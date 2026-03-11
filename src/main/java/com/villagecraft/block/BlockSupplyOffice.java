package com.villagecraft.block;

import java.util.List;

import javax.annotation.Nullable;

import com.villagecraft.VillageCraft;
import com.villagecraft.container.VillageCenterContainer;
import com.villagecraft.init.ModContainer;
import com.villagecraft.init.ModTiles;
import com.villagecraft.tile.TileBasicVillageBlock;
import com.villagecraft.util.Reference;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BlockSupplyOffice extends BaseEntityBlock {
	
	public static BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.WOOD).noOcclusion();
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static net.minecraft.world.item.Item.Properties item_properties = new net.minecraft.world.item.Item.Properties().tab(net.minecraft.world.item.CreativeModeTabs.BUILDING_BLOCKS).stacksTo(64);
	private static final VoxelShape AABB = Block.box(0, 0, 0, 16, 18, 16);
	
	public BlockSupplyOffice(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));	
	}
	
	
	
	public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
				
			if (tileentity instanceof TileBasicVillageBlock) {
				net.minecraft.world.Containers.dropContents(worldIn, pos, ((TileBasicVillageBlock) tileentity).content);
				worldIn.updateNeighbourForOutputSignal(pos, this);
			}
			
			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
    }
	
	@OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, net.minecraft.world.item.TooltipFlag flagIn) {
//        tooltip.add(Component.translatable("block.vcm.village_center.desc0").withStyle(ChatFormatting.GRAY));
//        tooltip.add(Component.translatable("block.vcm.village_center.desc1").withStyle(ChatFormatting.GRAY));
    }
	
	// Defines the properties needed for the blockstate
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { 
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}
	
	@Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
	
	// When activated we will have the player sit
	// @TODO: learn how to do this
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, 
			InteractionHand handIn, BlockHitResult blockRayTraceResult) {
		
		if (!worldIn.isClientSide) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof MenuProvider) {
                ((ServerPlayer) player).openMenu((MenuProvider) tileEntity);
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
            return InteractionResult.SUCCESS;
        }
		return InteractionResult.FAIL;
	}
		
	protected TileBasicVillageBlock tile;
	
	public BlockEntity getBlockEntity() { 
		return this.tile;
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int id, int param) {
		return true;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return ModTiles.TILE_VILLAGE_CENTER.get().create(pos, state);
	}
	
	
}
