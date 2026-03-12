package com.villagecraft.block;

import javax.annotation.Nullable;

import com.villagecraft.VillageCraft;
import com.villagecraft.data.VillageCraftData;
import com.villagecraft.data.VillageCraftVillage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

/**
 * Town Hall Block - The heart of a village.
 * When placed, claims territory for the village.
 */
public class BlockTownHall extends Block {
	
	public static BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
			.strength(3.5F)
			.sound(SoundType.WOOD)
			.noOcclusion();
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
	
	public static net.minecraft.world.item.Item.Properties item_properties = 
			new net.minecraft.world.item.Item.Properties().stacksTo(1);

	public BlockTownHall(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(ACTIVATED, false));
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, ACTIVATED);
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(ACTIVATED, false);
	}
	
	/**
	 * Called when player right-clicks the Town Hall.
	 * First click: Claims territory and creates village.
	 * Subsequent clicks: Opens management GUI (TODO).
	 */
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, 
			Player player, InteractionHand hand, BlockHitResult hit) {
		
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		}
		
		if (!(level instanceof ServerLevel serverLevel)) {
			return InteractionResult.PASS;
		}
		
		// Check if already activated (village already established here)
		if (!state.getValue(ACTIVATED)) {
			// Try to claim territory
			if (claimTerritory(serverLevel, pos, player)) {
				level.setBlock(pos, state.setValue(ACTIVATED, true), 3);
				player.sendSystemMessage(Component.literal("§aTerritory claimed! Village established."));
				VillageCraft.LOGGER.info("Player {} established a village at {}", player.getName().getString(), pos);
			} else {
				player.sendSystemMessage(Component.literal("§cCannot establish village here. Too close to existing village."));
			}
		} else {
			// Already claimed - TODO: Open village management GUI
			player.sendSystemMessage(Component.literal("§eTown Hall already established. Management GUI coming soon!"));
		}
		
		return InteractionResult.SUCCESS;
	}
	
	/**
	 * Claims territory around the Town Hall.
	 * 
	 * Rules:
	 * - Minimum 200 blocks from any other village center
	 * - Creates new village entry in VillageCraftData
	 * 
	 * @param level The server level
	 * @param pos Town Hall position
	 * @param player Player who placed it
	 * @return true if territory claimed successfully
	 */
	private boolean claimTerritory(ServerLevel level, BlockPos pos, Player player) {
		// TODO: Proper data loading for 1.20.2
		// For now, always allow (debug mode)
		VillageCraft.LOGGER.info("Territory claim requested at {} by {}", pos, player.getName().getString());
		return true;
	}
	
	/**
	 * Called when block is broken.
	 * TODO: Handle territory unclaiming
	 */
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, 
			BlockState newState, boolean movedByPiston) {
		super.onRemove(state, level, pos, newState, movedByPiston);
		
		if (!level.isClientSide && state.getValue(ACTIVATED)) {
			// TODO: Implement territory unclaiming
			VillageCraft.LOGGER.info("Town Hall removed at {} - territory not yet unclaimed", pos);
		}
	}
}