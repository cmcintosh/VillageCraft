package com.villagecraft.item.blockitems;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.world.level.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.client.multiplayer.ClientLevel;
import net.neoforged.neoforge.common.util.NonNullSupplier;

public class ItemVillageCenter extends BlockItem {
	
	// ItemGroup.MISC removed in 1.20.2 - need to use CREATIVE_MODE_TABS registration
	public static Properties properties = new Properties().stacksTo(1);
	private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Client Mod Event Subscriber");
	
	public ItemVillageCenter(Block block, Properties properties) {
		super(block, properties);
	}
	
	 public static int maxChunkRadius(BlockPos pos){
        int x = pos.getX();
        int z = pos.getZ();

        x = Math.abs(x < 0 ? x+1 : x) % 16;
        z = Math.abs(z < 0 ? z+1 : z) % 16;

        return Math.min(Math.min(15-x, x), Math.min(15-z, z));
    }
	
	// TODO: Reimplement LocationProperty for 1.20.2 - ItemPropertyFunction API changed
	/*
	public static class LocationProperty implements IItemPropertyGetter {
        @Override
        public float call(ItemStack stack, ClientLevel world, LivingEntity entity) {
            // ... implementation needs BlockHitResult and InteractionHand update
        }
    }
    */
	
}