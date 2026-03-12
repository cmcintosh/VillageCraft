package com.villagecraft.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import com.villagecraft.util.Reference;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        // Use getTabKey() which returns ResourceKey<CreativeModeTab>
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        
        // Building Blocks tab
        if (tabKey == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.VILLAGE_CENTER.get());
            event.accept(ModItems.TITLE_OFFICE.get());
            event.accept(ModItems.TOWN_HALL.get());
            event.accept(ModItems.EMBASSY.get());
            event.accept(ModItems.INN.get());
            event.accept(ModItems.PYROTECHNIC.get());
            event.accept(ModItems.AUCTION_HOUSE.get());
            event.accept(ModItems.GUITAR_STAND.get());
            event.accept(ModItems.MICROPHONE_STAND.get());
            event.accept(ModItems.DRUMS.get());
            event.accept(ModItems.POTTER_WHEEL.get());
            event.accept(ModItems.BAR.get());
            event.accept(ModItems.VILLAGE_MANAGER.get());
            event.accept(ModItems.INN_BLOCK.get());
            event.accept(ModItems.INN_ITEM.get());
            event.accept(ModItems.EMSSY_BLOCK.get());
            event.accept(ModItems.EMBASSY_ITEM.get());
            event.accept(ModItems.TOWN_HALL_BLOCK.get());
            event.accept(ModItems.TOWN_HALL_ITEM.get());
            event.accept(ModItems.CARAVAN_STOP.get());
            event.accept(ModItems.AUCTION_HOUSE_ITEM.get());
            event.accept(ModItems.AUCTION_HOUSE_BLOCK.get());
        }
        
        // Functional Blocks tab (Decorations)
        if (tabKey == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            // Add functional blocks here if needed
        }
        
        // Combat/Weapons tab
        if (tabKey == CreativeModeTabs.COMBAT) {
            // Add combat items here if needed
        }
        
        // Food tab
        if (tabKey == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.BEER_BUCKET.get());
        }
        
        // Ingredients tab
        if (tabKey == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.WORT.get());
        }
        
        // Spawn Eggs tab
        if (tabKey == CreativeModeTabs.SPAWN_EGGS) {
            // Add spawn eggs here if needed
        }
    }
}