package com.villagecraft.init;



import com.villagecraft.block.BlockChair;
import com.villagecraft.item.ItemNationCharter;
import com.villagecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

public final class ModItems {
	//The ITEMS deferred register in which you can register items.
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);
	

    //Register the tutorial dust with "tutorial_dust" as registry name and default properties
    public static final RegistryObject<Item> NATION_CHARTER = ITEMS.register("nationcharter", () -> (Item) new ItemNationCharter(ItemNationCharter.properties) );
    
    // Register new Chair block
    public static final RegistryObject<Item> VILLAGECRAFT_CHAIR = ITEMS.register("chair", () -> ( (Item) new BlockItem( (Block) new BlockChair(BlockChair.properties), BlockChair.item_properties)) );

}
