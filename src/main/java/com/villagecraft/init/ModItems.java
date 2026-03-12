package com.villagecraft.init;



import com.villagecraft.block.BlockChair;
import com.villagecraft.item.ItemBeerBucket;

import com.villagecraft.item.ItemHammer;
import com.villagecraft.item.ItemHeart;
import com.villagecraft.item.ItemLute;
import com.villagecraft.item.blockitems.ItemVillageCenter;
import com.villagecraft.item.ingredient.ItemWort;
import com.villagecraft.item.village.ItemNationCharter;

import com.villagecraft.item.ItemSpawnGolem;
import com.villagecraft.util.Reference;
import com.villagecraft.item.profession_tokens.ItemProfessionToken;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
	//The ITEMS deferred register in which you can register items.
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Reference.MODID);
	
	/**
	 * Village interaction Items
	 */
	public static final DeferredHolder<Item, Item> VILLAGE_CENTER = ITEMS.register("village_center", () -> ( (new ItemVillageCenter(  ModBlocks.BLOCK_VILLAGE_CENTER.get(), ModBlocks.BLOCK_VILLAGE_CENTER.get().item_properties))) );
	public static final DeferredHolder<Item, Item> NATION_CHARTER = ITEMS.register("nationcharter", () -> (Item) new ItemNationCharter(ItemNationCharter.properties) );
    public static final DeferredHolder<Item, Item> VILLAGECRAFT_CHAIR = ITEMS.register("chair", () -> ( (new BlockItem(  ModBlocks.BLOCK_CHAIR.get(), ModBlocks.BLOCK_CHAIR.get().item_properties))) );
    
    
    /**
     * Crafting components
     */
    public static final DeferredHolder<Item, Item> WORT = ITEMS.register("wort", () -> (Item) new ItemWort(ItemWort.properties) );
    public static final DeferredHolder<Item, Item> BEER_BUCKET = ITEMS.register("beer_bucket", () -> (Item) new ItemBeerBucket(ItemBeerBucket.properties) );
    

    /**
     * Professions
     */
    public static final DeferredHolder<Item, Item> WORKER_PROFESSION = ITEMS.register("profession_worker", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Worker") );
    public static final DeferredHolder<Item, Item> TRADESMAN_PROFESSION = ITEMS.register("profession_tradesman", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Tradesman") );
    public static final DeferredHolder<Item, Item> TRADER_PROFESSION = ITEMS.register("profession_trader", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Trader") );
    public static final DeferredHolder<Item, Item> MERCHANT_PROFESSION = ITEMS.register("profession_merchant", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Merchant") );
    public static final DeferredHolder<Item, Item> BUILDER_PROFESSION = ITEMS.register("profession_builder", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Builder") );
    public static final DeferredHolder<Item, Item> INNKEEPER_PROFESSION = ITEMS.register("profession_innkeeper", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Innkeeper") );
    public static final DeferredHolder<Item, Item> ALCHEMIST_PROFESSION = ITEMS.register("profession_alchemist", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Alchemist") );
    public static final DeferredHolder<Item, Item> ARCHITECT_PROFESSION = ITEMS.register("profession_architect", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Architect") );
    public static final DeferredHolder<Item, Item> BARKEEPER_PROFESSION = ITEMS.register("profession_barkeeper", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Barkeeper") );
    public static final DeferredHolder<Item, Item> BARD_PROFESSION = ITEMS.register("profession_bard", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Bard") );
    public static final DeferredHolder<Item, Item> BEEKEEPER_PROFESSION = ITEMS.register("profession_beekeeper", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Beekeeper") );
    public static final DeferredHolder<Item, Item> BRAWLER_PROFESSION = ITEMS.register("profession_brawler", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Brawler") );
    public static final DeferredHolder<Item, Item> CARAVANEER_PROFESSION = ITEMS.register("profession_caravaneer", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Caravaneer") );
    public static final DeferredHolder<Item, Item> DIPLOMAT_PROFESSION = ITEMS.register("profession_diplomat", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Diplomat") );
    public static final DeferredHolder<Item, Item> DRUMMER_PROFESSION = ITEMS.register("profession_drummer", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Drummer") );
    public static final DeferredHolder<Item, Item> FARMER_PROFESSION = ITEMS.register("profession_farmer", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Farmer") );
    public static final DeferredHolder<Item, Item> LANDLORD_PROFESSION = ITEMS.register("profession_landlord", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Landlord") );
    public static final DeferredHolder<Item, Item> MANAGER_PROFESSION = ITEMS.register("profession_manager", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Manager") );
    public static final DeferredHolder<Item, Item> MAYOR_PROFESSION = ITEMS.register("profession_mayor", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Mayor") );
    public static final DeferredHolder<Item, Item> MINER_PROFESSION = ITEMS.register("profession_miner", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Miner") );
    public static final DeferredHolder<Item, Item> OUTPOST_LIASON_PROFESSION = ITEMS.register("profession_outpostliason", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "OutpostLiasion") );
    public static final DeferredHolder<Item, Item> POTTER_PROFESSION = ITEMS.register("profession_potter", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Potter") );
    public static final DeferredHolder<Item, Item> PYROTECHNIC_PROFESSION = ITEMS.register("profession_pyrotechnic", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Pyrotechnic") );
    public static final DeferredHolder<Item, Item> SINGER_PROFESSION = ITEMS.register("profession_singer", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Singer") );
    public static final DeferredHolder<Item, Item> BASSIST_PROFESSION = ITEMS.register("profession_bassist", () -> (Item) new ItemProfessionToken(ItemProfessionToken.properties, "Bassist") );

    /**
     * Professions buildings
     */
    public static final DeferredHolder<Item, Item> TITLE_OFFICE = ITEMS.register("title_office", () -> ( (new BlockItem(  ModBlocks.TITLE_OFFICE.get(), ModBlocks.TITLE_OFFICE.get().item_properties))) );
    public static final DeferredHolder<Item, Item> TOWN_HALL = ITEMS.register("town_hall", () -> ( (new BlockItem(  ModBlocks.TOWN_HALL.get(), ModBlocks.TOWN_HALL.get().item_properties))) );
    // public static final DeferredHolder<Item, Item> SUPPLY_OFFICE = ITEMS.register("supply_office", () -> ( (new BlockItem(  ModBlocks.BLOCK_SUPPLY_OFFICE.get(), ModBlocks.BLOCK_SUPPLY_OFFICE.get().item_properties))) );
    public static final DeferredHolder<Item, Item> EMBASSY = ITEMS.register("embassy", () -> ( (new BlockItem(  ModBlocks.EMBASSY.get(), ModBlocks.EMBASSY.get().item_properties))) );
    public static final DeferredHolder<Item, Item> INN = ITEMS.register("inn", () -> ( (new BlockItem(  ModBlocks.INN.get(), ModBlocks.INN.get().item_properties))) );
    public static final DeferredHolder<Item, Item> PYROTECHNIC = ITEMS.register("pyrotechnic_worktable", () -> ( (new BlockItem(  ModBlocks.PYROTECHNIC_TABLE.get(), ModBlocks.PYROTECHNIC_TABLE.get().item_properties))) );
    public static final DeferredHolder<Item, Item> AUCTION_HOUSE = ITEMS.register("auction_house", () -> ( (new BlockItem(  ModBlocks.BLOCK_AUCTION_HOUSE.get(), ModBlocks.BLOCK_AUCTION_HOUSE.get().item_properties))) );
    public static final DeferredHolder<Item, Item> GUITAR_STAND = ITEMS.register("guitar_stand", () -> ( (new BlockItem(  ModBlocks.GUITAR_STAND.get(), ModBlocks.GUITAR_STAND.get().item_properties))) );
    public static final DeferredHolder<Item, Item> MICROPHONE_STAND = ITEMS.register("microphone_stand", () -> ( (new BlockItem(  ModBlocks.MICROPHONE_STAND.get(), ModBlocks.MICROPHONE_STAND.get().item_properties))) );
    public static final DeferredHolder<Item, Item> DRUMS = ITEMS.register("drums", () -> ( (new BlockItem(  ModBlocks.DRUMS.get(), ModBlocks.DRUMS.get().item_properties))) );
    public static final DeferredHolder<Item, Item> POTTER_WHEEL = ITEMS.register("potter_wheel", () -> ( (new BlockItem(  ModBlocks.POTTER_WHEEL.get(), ModBlocks.POTTER_WHEEL.get().item_properties))) );
    public static final DeferredHolder<Item, Item> BAR = ITEMS.register("bar", () -> ( (new BlockItem(  ModBlocks.BAR.get(), ModBlocks.BAR.get().item_properties))) );
    public static final DeferredHolder<Item, Item> VILLAGE_MANAGER = ITEMS.register("village_manager", () -> ( (new BlockItem(  ModBlocks.VILLAGE_MANAGER.get(), ModBlocks.VILLAGE_MANAGER.get().item_properties))) );
    public static final DeferredHolder<Item, Item> AUCTION_HOUSE_ITEM = ITEMS.register("auction_house", () -> ( (new BlockItem(  ModBlocks.AUCTION_HOUSE.get(), ModBlocks.AUCTION_HOUSE.get().item_properties))) );
    public static final DeferredHolder<Item, Item> AUCTION_HOUSE_BLOCK = ITEMS.register("auction_house_block", () -> ( (new BlockItem(  ModBlocks.AUCTION_HOUSE.get(), ModBlocks.AUCTION_HOUSE.get().item_properties))) );
    
    public static final DeferredHolder<Item, Item> INN_BLOCK = ITEMS.register("inn_block", () -> ( (new BlockItem(  ModBlocks.INN.get(), ModBlocks.INN.get().item_properties))) );
    public static final DeferredHolder<Item, Item> INN_ITEM = ITEMS.register("inn_item", () -> ( (new BlockItem(  ModBlocks.INN.get(), ModBlocks.INN.get().item_properties))) );    
    
    public static final DeferredHolder<Item, Item> EMSSY_BLOCK = ITEMS.register("emssy_block", () -> ( (new BlockItem(  ModBlocks.EMBASSY.get(), ModBlocks.EMBASSY.get().item_properties))) );
    public static final DeferredHolder<Item, Item> EMBASSY_ITEM = ITEMS.register("embassy_item", () -> ( (new BlockItem(  ModBlocks.EMBASSY.get(), ModBlocks.EMBASSY.get().item_properties))) );
    
    public static final DeferredHolder<Item, Item> TOWN_HALL_BLOCK = ITEMS.register("town_hall_block", () -> ( (new BlockItem(  ModBlocks.TOWN_HALL.get(), ModBlocks.TOWN_HALL.get().item_properties))) );
    public static final DeferredHolder<Item, Item> TOWN_HALL_ITEM = ITEMS.register("town_hall_item", () -> ( (new BlockItem(  ModBlocks.TOWN_HALL.get(), ModBlocks.TOWN_HALL.get().item_properties))) );    

    public static final DeferredHolder<Item, Item> CARAVAN_STOP = ITEMS.register("caravan_stop", () -> ( (new BlockItem(  ModBlocks.CARAVAN_STOP.get(), ModBlocks.CARAVAN_STOP.get().item_properties))) );

    

}