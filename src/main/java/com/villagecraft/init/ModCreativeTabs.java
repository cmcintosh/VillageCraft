package com.villagecraft.init;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import com.villagecraft.util.Reference;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        // Register items to their appropriate creative tabs
        
        // Building Blocks tab
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.VILLAGE_CENTER);
            event.accept(ModItems.TITLE_OFFICE);
            event.accept(ModItems.TOWN_HALL);
            event.accept(ModItems.EMBASSY);
            event.accept(ModItems.INN);
            event.accept(ModItems.PYROTECHNIC);
            event.accept(ModItems.AUCTION_HOUSE);
            event.accept(ModItems.GUITAR_STAND);
            event.accept(ModItems.MICROPHONE_STAND);
            event.accept(ModItems.DRUMS);
            event.accept(ModItems.POTTER_WHEEL);
            event.accept(ModItems.BAR);
            event.accept(ModItems.VILLAGE_MANAGER);
            event.accept(ModItems.INN_BLOCK);
            event.accept(ModItems.INN_ITEM);
            event.accept(ModItems.EMSSY_BLOCK);
            event.accept(ModItems.EMBASSY_ITEM);
            event.accept(ModItems.TOWN_HALL_BLOCK);
            event.accept(ModItems.TOWN_HALL_ITEM);
            event.accept(ModItems.CARAVAN_STOP);
            event.accept(ModItems.AUCTION_HOUSE_ITEM);
            event.accept(ModItems.AUCTION_HOUSE_BLOCK);
        }
        
        // Functional Blocks tab (Decorations)
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            // Add functional blocks here if needed
        }
        
        // Combat/Weapons tab
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            // Add combat items here if needed
        }
        
        // Food tab
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.BEER_BUCKET);
        }
        
        // Ingredients tab
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.WORT);
        }
        
        // Spawn Eggs tab
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            // Add spawn eggs here if needed
        }
    }
}
