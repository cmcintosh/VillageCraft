package com.villagecraft.block;

import com.villagecraft.init.ModItems;
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
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.block.HorizontalBlock;

public class BlockSawMill extends HorizontalBlock {
	
	public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
	public static Properties properties = Properties.create(Material.ROCK).sound(SoundType.WOOD).hardnessAndResistance(5f, 2000f);
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	protected static final VoxelShape field_220176_c = Block.makeCuboidShape(0.0D, 3.0D, 0.0D, 16.0D, 9.0D, 16.0D);
    protected static final VoxelShape field_220177_d = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 3.0D, 3.0D);
    protected static final VoxelShape field_220178_e = Block.makeCuboidShape(0.0D, 0.0D, 13.0D, 3.0D, 3.0D, 16.0D);
    protected static final VoxelShape field_220179_f = Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 3.0D, 3.0D);
    protected static final VoxelShape field_220180_g = Block.makeCuboidShape(13.0D, 0.0D, 13.0D, 16.0D, 3.0D, 16.0D);
    protected static final VoxelShape WEST_FACING_SHAPE = VoxelShapes.or(field_220176_c, field_220177_d, field_220179_f);
    protected static final VoxelShape EAST_FACING_SHAPE = VoxelShapes.or(field_220176_c, field_220178_e, field_220180_g);
    protected static final VoxelShape NORTH_FACING_SHAPE = VoxelShapes.or(field_220176_c, field_220177_d, field_220178_e);
    protected static final VoxelShape SOUTH_FACING_SHAPE = VoxelShapes.or(field_220176_c, field_220179_f, field_220180_g);
	
	public static final EnumProperty<SawMillPart> PART = EnumProperty.create("part", SawMillPart.class);
	
	public static net.minecraft.item.Item.Properties item_properties = new net.minecraft.item.Item.Properties().group(ModItems.PROFESSION_BLOCKS).maxStackSize(64);

	public BlockSawMill(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(PART, SawMillPart.FOOT));
	}
	
	@Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (facing == getDirectionToOther(stateIn.get(PART), stateIn.get(HORIZONTAL_FACING))) {
	         return facingState.isIn(this) && facingState.get(PART) != stateIn.get(PART) ? stateIn.with(OCCUPIED, facingState.get(OCCUPIED)) : Blocks.AIR.getDefaultState();
	      } else {
	         return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	      }
    }
	
	/**
     * Given a bed part and the direction it's facing, find the direction to move to get the other bed part
     */
    private static Direction getDirectionToOther(SawMillPart sawMillPart, Direction direction) {
      return sawMillPart == SawMillPart.FOOT ? direction : direction.getOpposite();
    }
	
    @Override
    public void harvestBlock(World world, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(world, player, pos, Blocks.AIR.getDefaultState(), te, stack);
    }
    
    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote && player.isCreative()) {
        	SawMillPart bedpart = state.get(PART);
           if (bedpart == SawMillPart.FOOT) {
              BlockPos blockpos = pos.offset(getDirectionToOther(bedpart, state.get(HORIZONTAL_FACING)));
              BlockState blockstate = worldIn.getBlockState(blockpos);
              if (blockstate.getBlock() == this && blockstate.get(PART) == SawMillPart.HEAD) {
                 worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
                 worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
              }
              
              BlockPos blockpos2 = pos.offset(state.get(HORIZONTAL_FACING).rotateY().getOpposite());
              BlockState blockstate2 = worldIn.getBlockState(blockpos2);
              if (blockstate2.getBlock() == this && blockstate2.get(PART) == SawMillPart.FOOT_1) {
                 worldIn.setBlockState(blockpos2, Blocks.AIR.getDefaultState(), 35);
                 worldIn.playEvent(player, 2001, blockpos2, Block.getStateId(blockstate2));
              }
              
              BlockPos blockpos3 = blockpos2.offset(state.get(HORIZONTAL_FACING));
              BlockState blockstate3 = worldIn.getBlockState(blockpos3);
              if (blockstate3.getBlock() == this && blockstate3.get(PART) == SawMillPart.HEAD_1) {
                 worldIn.setBlockState(blockpos3, Blocks.AIR.getDefaultState(), 35);
                 worldIn.playEvent(player, 2001, blockpos3, Block.getStateId(blockstate3));
              }
              
              
           }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
     }
    
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = func_226862_h_(state).getOpposite();
        switch(direction) {
        case NORTH:
           return NORTH_FACING_SHAPE;
        case SOUTH:
           return SOUTH_FACING_SHAPE;
        case WEST:
           return WEST_FACING_SHAPE;
        default:
           return EAST_FACING_SHAPE;
        }
     }
    
    public static Direction func_226862_h_(BlockState state) {
        Direction direction = state.get(HORIZONTAL_FACING);
        return state.get(PART) == SawMillPart.HEAD ? direction.getOpposite() : direction;
     }
	
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, PART, OCCUPIED);
     }
    
    @Override
    public boolean hasTileEntity(BlockState state) {
        return false;
    }
    
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
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
        	
           BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING));
           worldIn.setBlockState(blockpos, state.with(PART, SawMillPart.HEAD), 3);
           
           BlockPos blockpos2 = pos.offset(state.get(HORIZONTAL_FACING).rotateY().getOpposite());
           worldIn.setBlockState(blockpos2, state.with(PART, SawMillPart.FOOT_1), 3);
           
           BlockPos blockpos3 = blockpos2.offset(state.get(HORIZONTAL_FACING));
           worldIn.setBlockState(blockpos3, state.with(PART, SawMillPart.HEAD_1), 3);
           
           worldIn.func_230547_a_(pos, Blocks.AIR);
           state.updateNeighbours(worldIn, pos, 3);
           
           
        }

     }
	
	// When activated we will have the player sit
	// @TODO: learn how to do this
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult blockRayTraceResult) { 
		return ActionResultType.SUCCESS;
	}
	
	public enum SawMillPart implements IStringSerializable {
		   HEAD("head"),
		   FOOT("foot"),
		   HEAD_1("head_1"),
		   FOOT_1("foot_1");

		   private final String name;

		   private SawMillPart(String name) {
		      this.name = name;
		   }

		   public String toString() {
		      return this.name;
		   }

		   public String getString() {
		      return this.name;
		   }
		}
}
