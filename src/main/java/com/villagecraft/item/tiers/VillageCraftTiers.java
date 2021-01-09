package com.villagecraft.item.tiers;

import java.util.function.Supplier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum VillageCraftTiers implements IItemTier {
	
	WOOD(1, 200, 1.0F, 1.0f, 2, () -> {
		return Ingredient.fromItems(
				net.minecraft.item.Items.ACACIA_WOOD, 
				net.minecraft.item.Items.BIRCH_WOOD,
				net.minecraft.item.Items.DARK_OAK_WOOD,
				net.minecraft.item.Items.JUNGLE_WOOD,
				net.minecraft.item.Items.OAK_WOOD,
				net.minecraft.item.Items.SPRUCE_WOOD);
	}),	
	STONE(3, 400, 3.0F, 3.0F, 4, () -> {
		return Ingredient.fromItems(
				net.minecraft.item.Items.COBBLESTONE,
				net.minecraft.item.Items.STONE,
				net.minecraft.item.Items.SMOOTH_STONE
				);
	}),
	IRON(3, 400, 3.0F, 3.0F, 4, () -> {
		return Ingredient.fromItems(net.minecraft.item.Items.IRON_NUGGET, net.minecraft.item.Items.IRON_INGOT);
	}),
	GOLD(3, 400, 3.0F, 3.0F, 4, () -> {
		return Ingredient.fromItems(net.minecraft.item.Items.GOLD_NUGGET, net.minecraft.item.Items.GOLD_INGOT);
	}),
	EMERALD(3, 400, 3.0F, 3.0F, 4, () -> {
		return Ingredient.fromItems(
				net.minecraft.item.Items.EMERALD,
				net.minecraft.item.Items.EMERALD_BLOCK
				);
	}),
	DIAMOND(3, 400, 3.0F, 3.0F, 4, () -> {
		return Ingredient.fromItems(net.minecraft.item.Items.DIAMOND);
	}),
	;
	
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;
	
	private VillageCraftTiers(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyValue<>(repairMaterialIn);
	}

	
	public int getMaxUses() {
		return this.maxUses;
	}

	public float getEfficiency() {
		return this.efficiency;
	}

	public float getAttackDamage() {
		return this.attackDamage;
	}

	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

}
