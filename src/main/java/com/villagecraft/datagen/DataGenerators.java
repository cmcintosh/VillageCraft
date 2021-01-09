package com.villagecraft.datagen;

import com.villagecraft.VillageCraft;
import com.villagecraft.data.datagen.BlockStates;
import com.villagecraft.data.datagen.OreBlockStates;
import com.villagecraft.data.datagen.OreLootTableProvider;
import com.villagecraft.data.datagen.ProfessionLootTableProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
    	DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            // generator.addProvider(new Recipes(generator));
            generator.addProvider(new OreLootTableProvider(generator));
            generator.addProvider(new ProfessionLootTableProvider(generator));
        }
        if (event.includeClient()) {
            generator.addProvider(new OreBlockStates(generator, event.getExistingFileHelper()));  
         //   generator.addProvider(new Items(generator, event.getExistingFileHelper()));
        }
    }
}
