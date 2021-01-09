package com.villagecraft.data.datagen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModOres;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.data.loot.FishingLootTables;
import net.minecraft.data.loot.GiftLootTables;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.DynamicLootEntry;
import net.minecraft.loot.ILootConditionConsumer;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.PiglinBarteringAddition;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.loot.functions.CopyName;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.loot.functions.SetContents;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ForgeLootTableProvider;
import net.minecraftforge.fml.RegistryObject;

public class ProfessionLootTableProvider extends ForgeLootTableProvider {
	
	
	public ProfessionLootTableProvider(DataGenerator gen) {
		super(gen);
	}
	
	@Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables()
    {
        return ImmutableList.of(Pair.of(ProfessionBlocks::new, LootParameterSets.BLOCK));
    }
	
	private static class ProfessionBlocks extends BlockLootTables {
		public final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
		
		protected static LootTable.Builder dropping(IItemProvider item) {
	      return LootTable.builder().addLootPool(
	    		  LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(item))
	     );
	   }

	   protected static LootTable.Builder dropping(Block block, ILootCondition.IBuilder conditionBuilder, LootEntry.Builder<?> p_218494_2_) {
	      return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block).acceptCondition(conditionBuilder).alternatively(p_218494_2_)));
	   }
	
		@Override
		protected void addTables() {
			
			for (RegistryObject<Block> entry : ModVillagerProfessions.BLOCKS.getEntries()) {
					this.registerDropSelfLootTable(entry.get());
					lootTables.put(entry.get(), createStandardTable(entry.getId().toString(), entry.get()));
			}
			
		}
		
		@Override
	    protected Iterable<Block> getKnownBlocks() {
			return ModVillagerProfessions.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
		}

		protected LootTable.Builder createStandardTable(String name, Block block) {
	        LootPool.Builder builder = LootPool.builder()
	                .name(name)
	                .rolls(ConstantRange.of(1))
	                .addEntry(ItemLootEntry.builder(block)
	                        .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
	                        .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
	                                .addOperation("inv", "BlockEntityTag.inv", CopyNbt.Action.REPLACE)
	                                .addOperation("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE))
	                        .acceptFunction(SetContents.builderIn()
	                                .addLootEntry(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents"))))
	                );
	        return LootTable.builder().addLootPool(builder);
	    }
		
	}
}
