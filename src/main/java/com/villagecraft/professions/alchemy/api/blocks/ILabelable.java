package com.villagecraft.professions.alchemy.api.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public interface ILabelable {
	
	boolean applyLabel(PlayerEntity player, BlockPos pos, Direction direction, ItemStack itemStack);

}
