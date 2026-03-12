package com.villagecraft.init;

import com.villagecraft.util.Reference;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * This class holds the custom CreativeModeTab for VillageCraft.
 * Uses the Builder pattern for 1.20.2+ NeoForge.
 */
public class ModItemGroups {
	
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MODID);
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VILLAGE_CRAFT_TAB = CREATIVE_MODE_TABS.register("village_craft", 
		() -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.village_craft"))
			.icon(() -> new ItemStack(ModItems.NATION_CHARTER.get()))
			.build());
	
}
