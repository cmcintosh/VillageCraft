package com.villagecraft.block;

import javax.annotation.Nullable;

import com.villagecraft.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockKnightStand extends Block {
	
	public static final VoxelShape UPPER_SHAPE = VoxelShapes.or(
			makeCuboidShape(5, 21, 6, 11, 25, 12), // Helmet
			makeCuboidShape(5, 16, 7, 11, 20, 12), // Top Chest
			makeCuboidShape(2, 16, 7, 4, 21, 10), // top arm
			makeCuboidShape(12, 16, 7, 14, 21, 10) // top arm
	).simplify();
	
	public static final VoxelShape LOWER_SHAPE = VoxelShapes.or(
			makeCuboidShape(9, 1, 7, 11, 12, 10), // Leg
			makeCuboidShape(12, 13, 7, 14, 16, 10), // lower arm
			makeCuboidShape(2, 10, 6, 4, 16, 9), // 
			makeCuboidShape(5, 1, 7, 7, 12, 10), // leg
			makeCuboidShape(2, 13, 7, 4, 16, 10), // lower arm
			makeCuboidShape(5, 12, 7, 11, 16, 12), // lower chest
			makeCuboidShape(1, 0, 1, 15, 1, 15) // stand
	).simplify();
	
	public static Properties properties = Properties.create(Material.ROCK).sound(SoundType.METAL).hardnessAndResistance(5f, 2000f).doesNotBlockMovement();
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	
	public static net.minecraft.item.Item.Properties item_properties = new net.minecraft.item.Item.Properties().group(ModItems.PROFESSION_BLOCKS).maxStackSize(64);

	public BlockKnightStand(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER));
	}
	
	@Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighbor, IWorld world, BlockPos pos, BlockPos offset) {
        DoubleBlockHalf half = state.get(HALF);
        if ((facing.getAxis() != Direction.Axis.Y) || ((half == DoubleBlockHalf.LOWER) != (facing == Direction.UP)) || ((neighbor.getBlock() == this) && (neighbor.get(HALF) != half))) {
            if ((half != DoubleBlockHalf.LOWER) || (facing != Direction.DOWN) || state.isValidPosition(world, pos)) {
                return state;
            }
        }

        return Blocks.AIR.getDefaultState();
    }
	
    @Override
    public void harvestBlock(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(world, player, pos, Blocks.AIR.getDefaultState(), te, stack);
    }
    
    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf half = state.get(HALF);
        BlockPos offset = half == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
        BlockState other = world.getBlockState(offset);
        if (other.getBlock() == this && other.get(HALF) != half) {
            world.destroyBlock(half == DoubleBlockHalf.LOWER ? pos : offset, false, player);
            if (!world.isRemote && !player.abilities.isCreativeMode) {
                spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());
                spawnDrops(other, world, offset, null, player, player.getHeldItemMainhand());
            }
        }

        super.onBlockHarvested(world, pos, state, player);
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return state.get(HALF) == DoubleBlockHalf.UPPER ? UPPER_SHAPE : LOWER_SHAPE;
    }
	
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(HALF);
    }
    
    @Override
    public boolean hasTileEntity(BlockState state) {
        return false;
    }
    
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        if (state.get(HALF) == DoubleBlockHalf.LOWER) {
            return true;
        }

        BlockState below = world.getBlockState(pos.down());
        return below.getBlock() == this && below.get(HALF) == DoubleBlockHalf.LOWER;
    }
	
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        
        BlockPos pos = context.getPos();
        if (pos.getY() < context.getWorld().getHeight() - 1) {
            if (context.getWorld().getBlockState(pos.up()).isReplaceable(context)) {
                return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
            }
        }

        return null;
    }
    
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        BlockPos posAbove = pos.up();
        world.setBlockState(posAbove, state.with(HALF, DoubleBlockHalf.UPPER));
    }
	
	// When activated we will have the player sit
	// @TODO: learn how to do this
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult blockRayTraceResult) { 
		return ActionResultType.SUCCESS;
	}
}
