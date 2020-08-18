package com.villagecraft.item;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.NonNullSupplier;


public class ItemVillageCenter extends BlockItem {
	
	public static Properties properties = new Properties().group(ItemGroup.MISC).maxStackSize(1);
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
	
	public static class LocationProperty implements IItemPropertyGetter {
        @Override
        public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
            // dont evaluate placement if the maximal radius isn't limited to half chunk size
            if(10 > 7)
                return 0;

            // only change color when this method is called from a player entity
            if(entity == null || !(stack.getItem() instanceof ItemVillageCenter) || !entity.equals(Minecraft.getInstance().player))
                return 0;

            // only change color when the item is held by the player
            if(!Minecraft.getInstance().player.getHeldItemMainhand().equals(stack) && !Minecraft.getInstance().player.getHeldItemOffhand().equals(stack))
                return 0;

            // only change color when player has focus on a block
            if(!Minecraft.getInstance().objectMouseOver.getType().equals(RayTraceResult.Type.BLOCK))
                return 0;

            // get the position where the block *would* be placed if the player places it now
            BlockRayTraceResult result = ((BlockRayTraceResult) Minecraft.getInstance().objectMouseOver);
            BlockPos placePosition = result.getPos().add(result.getFace().getDirectionVec());

            int radius = maxChunkRadius(placePosition);

            if(radius < 10)
                return 1;

            if(radius <= 10)
                return 2;

            return 3;
        }
    }
	
}