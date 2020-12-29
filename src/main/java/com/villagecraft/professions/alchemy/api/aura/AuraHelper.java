package com.villagecraft.professions.alchemy.api.aura;

import com.villagecraft.professions.alchemy.api.aspects.Aspect;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AuraHelper {

	public static boolean drainAura (World world, BlockPos pos, Aspect aspect, int amount) {
		return VillageCraftAlchemyApi.internalMethods.drainAura(world, pos, aspect, amount);
	}
	
	public static int drainAuraAvailable(World world, BlockPos pos, Aspect aspect, int amount) {
		return VillageCraftAlchemyApi.internalMethods.drainAuraAvailable(world, pos, aspect, amount);
	}
	
	public static void addAura(World world, BlockPos pos, Aspect aspect, int amount) {
		VillageCraftAlchemyApi.internalMethods.addAura(world, pos, aspect, amount);
	}
	
	public static int getAura(World world, BlockPos pos, Aspect aspect) {
		return VillageCraftAlchemyApi.internalMethods.getAura(world, pos, aspect);
	}
	
	 public static int getAuraBase(World world, BlockPos pos) {
		 return VillageCraftAlchemyApi.internalMethods.getAuraBase(world, pos);
	}
	 
	 public static boolean shouldPreserveAura(World world, PlayerEntity player, BlockPos pos, Aspect aspect) {
		 return VillageCraftAlchemyApi.internalMethods.shouldPreserveAura(world, player, pos, aspect);
	}
	 
	 public static void pollute(World world, BlockPos pos, int amount, boolean showEffect) {
		 VillageCraftAlchemyApi.internalMethods.pollute(world, pos, amount, showEffect);
	}
}
