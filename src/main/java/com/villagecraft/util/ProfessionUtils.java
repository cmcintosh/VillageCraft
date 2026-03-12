package com.villagecraft.util;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;

// TODO: Reimplement for 1.20.2 - Profession API changed
public class ProfessionUtils {
	
	public static VillagerProfession villagerProfession(String p1, PoiType p2, ImmutableSet<Item> p3, ImmutableSet<Block> p4, @Nullable SoundEvent p5) {
		// TODO: Reimplement profession creation
		return null;
	}

}