package com.villagecraft.init;



import com.villagecraft.block.BlockChair;
import com.villagecraft.item.ItemBeerBucket;

import com.villagecraft.item.ItemHammer;
import com.villagecraft.item.ItemHeart;
import com.villagecraft.item.ItemLute;
import com.villagecraft.item.blockitems.ItemVillageCenter;
import com.villagecraft.item.ingredient.ItemWort;
import com.villagecraft.item.village.ItemNationCharter;

import com.villagecraft.item.ItemSpawnIronGolem;
import com.villagecraft.util.Reference;
import com.villagecraft.item.profession_tokens.ItemProfessionToken;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ModItems {
	//The ITEMS deferred register in which you can register items.
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);
	
	/**
	 * Village interaction Items
	 */
	public static final RegistryObject<Item> VILLAGE_CENTER = ITEMS.register("village_center", () -> ( (new ItemVillageCenter(  ModBlocks.BLOCK_VILLAGE_CENTER.get(), ModBlocks.BLOCK_VILLAGE_CENTER.get().item_properties))) );
	public static final RegistryObject<Item> NATION_CHARTER = ITEMS.register("nationcharter", () -> (Item) new ItemNationCharter(ItemNationCharter.properties) );
    public static final RegistryObject<Item> VILLAGECRAFT_CHAIR = ITEMS.register("chair", () -> ( (new BlockItem(  ModBlocks.BLOCK_CHAIR.get(), ModBlocks.BLOCK_CHAIR.get().item_properties))) );
    
    /**
     * Profession tokens
     */
    public static final RegistryObject<Item> PROF_WORKER = ITEMS.register("prof_worker", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
    public static final RegistryObject<Item> PROF_MERCHANT = ITEMS.register("prof_merchant", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
    public static final RegistryObject<Item> PROF_BRAWLER = ITEMS.register("prof_brawler", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
    public static final RegistryObject<Item> PROF_BARD = ITEMS.register("prof_bard", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
    
//    public static final RegistryObject<Item> PROF_BLACKSMITH = ITEMS.register("prof_blacksmith", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_BUTCHER = ITEMS.register("prof_butcher", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_CAPTAIN = ITEMS.register("prof_captain", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_CHEF = ITEMS.register("prof_chef", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_CLERIC = ITEMS.register("prof_cleric", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_ENCHANTER = ITEMS.register("prof_enchanter", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_FARMER = ITEMS.register("prof_farmer", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_GUARD = ITEMS.register("prof_guard", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_LUMBERJACK = ITEMS.register("prof_lumberjack", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_MINER = ITEMS.register("prof_miner", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_RANCHER = ITEMS.register("prof_rancher", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    public static final RegistryObject<Item> PROF_TEACHER = ITEMS.register("prof_teacher", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties) );
//    
    
    
    /**
     * Crafting components
     */
    public static final RegistryObject<Item> WORT = ITEMS.register("wort", () -> (Item) new ItemWort(ItemWort.properties) );
    public static final RegistryObject<Item> BEER_BUCKET = ITEMS.register("beer_bucket", () -> (Item) new ItemBeerBucket(ItemBeerBucket.properties) );
    
    /**
     * Tools & Weapons
     */
    
    public static final RegistryObject<Item> STONE_HAMMER = ITEMS.register("stone_hammer", () -> (Item) new ItemHammer(ModItemTier.STONE, 10, 0.1f, ItemHammer.properties) );
    public static final RegistryObject<Item> LUTE = ITEMS.register("lute", () -> (Item) new ItemLute(ModItemTier.STONE, 10, 0.1f, ItemLute.properties) );
    
    /**
     * Interactive blocks
     */
    // Profession Blocks
    
    public static final RegistryObject<Item> BARD_STAND = ITEMS.register("bard_stand", () -> ( (new BlockItem(  ModBlocks.BARD_STAND.get(), ModBlocks.BARD_STAND.get().item_properties))) );
    public static final RegistryObject<Item> ORE_BOX = ITEMS.register("ore_box", () -> ( (new BlockItem(  ModBlocks.ORE_BOX.get(), ModBlocks.ORE_BOX.get().item_properties))) );
    public static final RegistryObject<Item> DRAFTING_TABLE = ITEMS.register("drafting_table", () -> ((new BlockItem( ModBlocks.DRAFTING_TABLE.get(), ModBlocks.DRAFTING_TABLE.get().item_properties))));
    public static final RegistryObject<Item> TRADESMAN_HELMET = ITEMS.register("tradesman_helmet", () -> ((new BlockItem( ModBlocks.TRADESMAN_HELMET.get(), ModBlocks.TRADESMAN_HELMET.get().item_properties))));
    public static final RegistryObject<Item> BRALWER_BOX = ITEMS.register("brawler_box", () -> ( (new BlockItem(  ModBlocks.BRAWLER_BOX.get(), ModBlocks.BRAWLER_BOX.get().item_properties))) );
    public static final RegistryObject<Item> BAR = ITEMS.register("bar", () -> ( (new BlockItem(  ModBlocks.BAR.get(), ModBlocks.BAR.get().item_properties))) );
    public static final RegistryObject<Item> DRUMS = ITEMS.register("drums", () -> ( (new BlockItem(  ModBlocks.DRUMS.get(), ModBlocks.DRUMS.get().item_properties))) );
    public static final RegistryObject<Item> GUITAR_STAND = ITEMS.register("guitar_stand", () -> ( (new BlockItem(  ModBlocks.GUITAR_STAND.get(), ModBlocks.GUITAR_STAND.get().item_properties))) );
    public static final RegistryObject<Item> MICROPHONE_STAND = ITEMS.register("microphone_stand", () -> ( (new BlockItem(  ModBlocks.MICROPHONE_STAND.get(), ModBlocks.MICROPHONE_STAND.get().item_properties))) );
    public static final RegistryObject<Item> AUCTION_HOUSE = ITEMS.register("auction_house", () -> ( (new BlockItem(  ModBlocks.AUCTION_HOUSE.get(), ModBlocks.AUCTION_HOUSE.get().item_properties))) );
    public static final RegistryObject<Item> PYROTECHNIC = ITEMS.register("pyrotechnic_worktable", () -> ( (new BlockItem(  ModBlocks.PYROTECHNIC_TABLE.get(), ModBlocks.PYROTECHNIC_TABLE.get().item_properties))) );
    public static final RegistryObject<Item> CARAVAN_STOP = ITEMS.register("caravan_stop", () -> ( (new BlockItem(  ModBlocks.CARAVAN_STOP.get(), ModBlocks.CARAVAN_STOP.get().item_properties))) );
    public static final RegistryObject<Item> EMBASSY = ITEMS.register("embassy", () -> ( (new BlockItem(  ModBlocks.EMBASSY.get(), ModBlocks.EMBASSY.get().item_properties))) );
    public static final RegistryObject<Item> VILLAGE_MANAGER = ITEMS.register("village_manager", () -> ( (new BlockItem(  ModBlocks.VILLAGE_MANAGER.get(), ModBlocks.VILLAGE_MANAGER.get().item_properties))) );
    public static final RegistryObject<Item> SUPPLY_OFFICE = ITEMS.register("supply_office", () -> ( (new BlockItem(  ModBlocks.SUPPLY_OFFICE.get(), ModBlocks.SUPPLY_OFFICE.get().item_properties))) );
    public static final RegistryObject<Item> TOWN_HALL = ITEMS.register("town_hall", () -> ( (new BlockItem(  ModBlocks.TOWN_HALL.get(), ModBlocks.TOWN_HALL.get().item_properties))) );
    public static final RegistryObject<Item> INN = ITEMS.register("inn", () -> ( (new BlockItem(  ModBlocks.INN.get(), ModBlocks.INN.get().item_properties))) );


    /**
     * Spawn Eggs.
     */

}
