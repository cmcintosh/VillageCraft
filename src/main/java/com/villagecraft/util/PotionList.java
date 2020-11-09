package com.villagecraft.util;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;

public class PotionList {

	public static Potion GROWTH_POTION = null;
	public static Effect GROWTH_EFFECT = null;
	
	public static class VillageCraftEffects extends Effect {
		public VillageCraftEffects(EffectType type, int liquidColor) {
			super(type, liquidColor);
		}
		
	}
}
