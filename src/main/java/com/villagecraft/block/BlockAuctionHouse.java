package com.villagecraft.block;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
// Material removed - use BlockBehaviour
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

public class BlockAuctionHouse extends Block {
	
	public static Properties properties = BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.CLOTH).noOcclusion();
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static net.minecraft.world.item.Item.Properties item_properties = new net.minecraft.world.item.Item.Properties().stacksTo(64);

	public BlockAuctionHouse(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
	
	// When activated we will have the player sit
	// @TODO: learn how to do this
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult blockHitResult) { 
		return InteractionResult.SUCCESS;
	}
}
