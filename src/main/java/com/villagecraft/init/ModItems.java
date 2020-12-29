package com.villagecraft.init;



import java.util.ArrayList;

import com.villagecraft.block.BlockChair;
import com.villagecraft.fluid.FluidBeer;
import com.villagecraft.item.blockitems.ItemVillageCenter;
import com.villagecraft.item.ingredient.ItemBeerBucket;
import com.villagecraft.item.ingredient.ItemWort;
import com.villagecraft.item.village.ItemNationCharter;
import com.villagecraft.util.PotionList;
import com.villagecraft.util.Reference;
import com.villagecraft.item.profession_tokens.ItemProfessionToken;
import com.villagecraft.item.recipe.ITransmutationRecipe;
import com.villagecraft.item.recipe.TransmutationRecipe;
import com.villagecraft.item.recipe.TransmutationRecipe.Serializer;
import com.villagecraft.item.spawnegg.ItemSpawnIronGolem;
import com.villagecraft.item.tool.ItemHammer;
import com.villagecraft.item.tool.ItemHeart;
import com.villagecraft.item.tool.ItemLute;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.ForgeFlowingFluid.Flowing;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ModItems {
	//The ITEMS deferred register in which you can register items.
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);
	
	// Group our profession block items together.
	public static final ItemGroup PROFESSION_BLOCKS = new ItemGroup("professions") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.NATION_CHARTER.get());
		}
	};
	
	/**
     * Alchemy
     */
 	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MODID); 
 	public static RegistryObject<Serializer> TRANSMUTATION_SERIALIZER = RECIPE_SERIALIZERS.register("transmutation", () -> (new TransmutationRecipe.Serializer()) );
 	
	
	/**
	 * Village interaction Items
	 */
	public static final RegistryObject<Item> VILLAGE_CENTER = ITEMS.register("village_center", () -> ( (new ItemVillageCenter(  ModBlocks.BLOCK_VILLAGE_CENTER.get(), ModBlocks.BLOCK_VILLAGE_CENTER.get().item_properties))) );
	public static final RegistryObject<Item> NATION_CHARTER = ITEMS.register("nationcharter", () -> (Item) new ItemNationCharter(ItemNationCharter.properties) );
    public static final RegistryObject<Item> VILLAGECRAFT_CHAIR = ITEMS.register("chair", () -> ( (new BlockItem(  ModBlocks.BLOCK_CHAIR.get(), ModBlocks.BLOCK_CHAIR.get().item_properties))) );
    
    
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
    
    
    // Profession Blocks
    
    // @ALCHEMIST
    public static final RegistryObject<Item> ALCHEMIST_TABLE = ITEMS.register("alchemist_table", () -> ( (new BlockItem(  ModBlocks.BLOCK_ALCHEMIST_TABLE.get(), ModBlocks.BLOCK_ALCHEMIST_TABLE.get().item_properties))) );
    
    // @ARCHITECT
    public static final RegistryObject<Item> DRAFTING_TABLE = ITEMS.register("drafting_table", () -> ((new BlockItem( ModBlocks.DRAFTING_TABLE.get(), ModBlocks.DRAFTING_TABLE.get().item_properties))));
    
    // @BARD
    public static final RegistryObject<Item> BARD_STAND = ITEMS.register("bard_stand", () -> ( (new BlockItem(  ModBlocks.BARD_STAND.get(), ModBlocks.BARD_STAND.get().item_properties))) );
    
    // @BARKEEPER
    public static final RegistryObject<Item> BAR = ITEMS.register("bar", () -> ( (new BlockItem(  ModBlocks.BAR.get(), ModBlocks.BAR.get().item_properties))) );
    
    // @BASSIST
    public static final RegistryObject<Item> GUITAR_STAND = ITEMS.register("guitar_stand", () -> ( (new BlockItem(  ModBlocks.GUITAR_STAND.get(), ModBlocks.GUITAR_STAND.get().item_properties))) );
    
    // @BEEKEEPER
    public static final RegistryObject<Item> BEEKEEPER_ITEM = ITEMS.register("beekeeper_station", () -> ( (new BlockItem(  net.minecraft.block.Blocks.BEEHIVE, ModBlocks.GUITAR_STAND.get().item_properties))) );
    
    // @BLACKSMITH
    public static final RegistryObject<Item> ANVIL_ITEM = ITEMS.register("blacksmith_station", () -> ( (new BlockItem(  net.minecraft.block.Blocks.ANVIL, ModBlocks.GUITAR_STAND.get().item_properties))) );
    
    // @BRAWLER
    public static final RegistryObject<Item> BRALWER_BOX = ITEMS.register("brawler_box", () -> ( (new BlockItem(  ModBlocks.BRAWLER_BOX.get(), ModBlocks.BRAWLER_BOX.get().item_properties))) );
    
    // @BREWMASTER
    public static final RegistryObject<Item> KEG = ITEMS.register("keg", () -> ( (new BlockItem(  ModBlocks.BLOCK_KEG.get(), ModBlocks.BLOCK_KEG.get().item_properties))) );
    
    // @BUILDER
    public static final RegistryObject<Item> BUILDERS_CHEST = ITEMS.register("builders_chest", () -> ( (new BlockItem(  ModBlocks.BUILDERS_CHEST.get(), ModBlocks.BUILDERS_CHEST.get().item_properties))) );
    
    // @BUTCHER
    public static final RegistryObject<Item> BUTCHERS_BLOCK = ITEMS.register("butchers_block", () -> ( (new BlockItem(  ModBlocks.BUTCHERS_BLOCK.get(), ModBlocks.BUTCHERS_BLOCK.get().item_properties))) );
    
    // @CAPTAIN
    public static final RegistryObject<Item> CAPTAINS_ARMORSTAND_BLOCK = ITEMS.register("captains_armorstand_block", () -> ( (new BlockItem(  ModBlocks.CAPTAINS_ARMORSTAND_BLOCK.get(), ModBlocks.CAPTAINS_ARMORSTAND_BLOCK.get().item_properties))) );
    
    // @CARAVANEER
    public static final RegistryObject<Item> CARAVAN_STOP = ITEMS.register("caravan_stop", () -> ( (new BlockItem(  ModBlocks.CARAVAN_STOP.get(), ModBlocks.CARAVAN_STOP.get().item_properties))) );
    
    // @CARPENTER
    public static final RegistryObject<Item> SAWHORSE = ITEMS.register("sawhorse", () -> ( (new BlockItem(  ModBlocks.SAWHORSE.get(), ModBlocks.SAWHORSE.get().item_properties))) );
    
    // @CLERIC
    public static final RegistryObject<Item> ALTAR = ITEMS.register("altar", () -> ( (new BlockItem(  ModBlocks.ALTAR.get(), ModBlocks.ALTAR.get().item_properties))) );
    
    // @COOK
    public static final RegistryObject<Item> BRICK_OVEN = ITEMS.register("brick_oven", () -> ( (new BlockItem(  ModBlocks.BRICK_OVEN.get(), ModBlocks.BRICK_OVEN.get().item_properties))) );
    
    // @DIPLOMAT
    public static final RegistryObject<Item> EMBASSY = ITEMS.register("embassy", () -> ( (new BlockItem(  ModBlocks.EMBASSY.get(), ModBlocks.EMBASSY.get().item_properties))) );
    
    // @DRUID
    public static final RegistryObject<Item> FOREST_SHRINE = ITEMS.register("forest_shrine", () -> ( (new BlockItem(  ModBlocks.FOREST_SHRINE.get(), ModBlocks.FOREST_SHRINE.get().item_properties))) );
    
    // @DRUMMER
    public static final RegistryObject<Item> DRUMS = ITEMS.register("drums", () -> ( (new BlockItem(  ModBlocks.DRUMS.get(), ModBlocks.DRUMS.get().item_properties))) );
    
    // @ENCHANTER
    public static final RegistryObject<Item> ENCHANTED_STONE = ITEMS.register("enchanted_stone", () -> ( (new BlockItem(  ModBlocks.ENCHANTED_STONE.get(), ModBlocks.ENCHANTED_STONE.get().item_properties))) );
    
    // @ENGINEER
    public static final RegistryObject<Item> RAIL_MACHINE = ITEMS.register("rail_machine", () -> ( (new BlockItem(  ModBlocks.RAIL_MACHINE.get(), ModBlocks.RAIL_MACHINE.get().item_properties))) );
    
    // @FOOTMAN
    public static final RegistryObject<Item> WEAPON_STAND = ITEMS.register("weapon_stand", () -> ( (new BlockItem(  ModBlocks.WEAPON_STAND.get(), ModBlocks.WEAPON_STAND.get().item_properties))) );
    
    // @GEOMANCER
    public static final RegistryObject<Item> STONE_ALTAR = ITEMS.register("stone_altar", () -> ( (new BlockItem(  ModBlocks.STONE_ALTAR.get(), ModBlocks.STONE_ALTAR.get().item_properties))) );
    
    // @GUILDMASTER
    public static final RegistryObject<Item> GUILD_TASKBOARD = ITEMS.register("guild_taskboard", () -> ( (new BlockItem(  ModBlocks.GUILD_TASKBOARD.get(), ModBlocks.GUILD_TASKBOARD.get().item_properties))) );
    
    // @HERBALIST
    public static final RegistryObject<Item> HERBALIST_PESTLE = ITEMS.register("herbalist_pestle", () -> ( (new BlockItem(  ModBlocks.HERBALIST_PESTLE.get(), ModBlocks.HERBALIST_PESTLE.get().item_properties))) );
    
    // @HUNTER
    public static final RegistryObject<Item> TAMING_PEN = ITEMS.register("taming_pen", () -> ( (new BlockItem(  ModBlocks.TAMING_PEN.get(), ModBlocks.TAMING_PEN.get().item_properties))) );
    
    // @INNKEEPER
    public static final RegistryObject<Item> INN = ITEMS.register("inn", () -> ( (new BlockItem(  ModBlocks.INN.get(), ModBlocks.INN.get().item_properties))) );
    
    // @KNIGHT
    public static final RegistryObject<Item> KNIGHT_STAND = ITEMS.register("knight_stand", () -> ( (new BlockItem(  ModBlocks.KNIGHT_STAND.get(), ModBlocks.KNIGHT_STAND.get().item_properties))) );
    
    // @LANDLORD
    public static final RegistryObject<Item> TITLE_OFFICE = ITEMS.register("title_office", () -> ( (new BlockItem(  ModBlocks.TITLE_OFFICE.get(), ModBlocks.TITLE_OFFICE.get().item_properties))) );
    
    // @LEATHERWORKER
    public static final RegistryObject<Item> TANNERY = ITEMS.register("tannery", () -> ( (new BlockItem(  ModBlocks.TANNERY.get(), ModBlocks.TANNERY.get().item_properties))) );
    
    // @LUMBERJACK
    public static final RegistryObject<Item> SAW_MILL = ITEMS.register("sawmill", () -> ( (new BlockItem(  ModBlocks.SAW_MILL.get(), ModBlocks.SAW_MILL.get().item_properties))) );
    
    // @MAGE
    public static final RegistryObject<Item> ELEMENTAL_ALTAR = ITEMS.register("elemental_altar", () -> ( (new BlockItem(  ModBlocks.ELEMENTAL_ALTAR.get(), ModBlocks.ELEMENTAL_ALTAR.get().item_properties))) );
    
    // @MANAGER
    public static final RegistryObject<Item> VILLAGE_MANAGER = ITEMS.register("village_manager", () -> ( (new BlockItem(  ModBlocks.VILLAGE_MANAGER.get(), ModBlocks.VILLAGE_MANAGER.get().item_properties))) );
    
    // @MARKSMAN
    public static final RegistryObject<Item> TARGET_BLOCK = ITEMS.register("target_block", () -> ( (new BlockItem(  ModBlocks.TARGET_BLOCK.get(), ModBlocks.TARGET_BLOCK.get().item_properties))) );
    
    // @MAYOR
    public static final RegistryObject<Item> TOWN_HALL = ITEMS.register("town_hall", () -> ( (new BlockItem(  ModBlocks.TOWN_HALL.get(), ModBlocks.TOWN_HALL.get().item_properties))) );
    
    // @MERCHANT
    public static final RegistryObject<Item> AUCTION_HOUSE = ITEMS.register("auction_house", () -> ( (new BlockItem(  ModBlocks.AUCTION_HOUSE.get(), ModBlocks.AUCTION_HOUSE.get().item_properties))) );
    
    // @MINER
    public static final RegistryObject<Item> ORE_BOX = ITEMS.register("ore_box", () -> ( (new BlockItem(  ModBlocks.ORE_BOX.get(), ModBlocks.ORE_BOX.get().item_properties))) );
    
    // @NURSE
    public static final RegistryObject<Item> AID_STATION = ITEMS.register("aid_station", () -> ( (new BlockItem(  ModBlocks.AID_STATION.get(), ModBlocks.AID_STATION.get().item_properties))) );
    
    // @OUTPOST_LIASON
    public static final RegistryObject<Item> SUPPLY_OFFICE = ITEMS.register("supply_office", () -> ( (new BlockItem(  ModBlocks.SUPPLY_OFFICE.get(), ModBlocks.SUPPLY_OFFICE.get().item_properties))) );
    
    // @POTTER
    public static final RegistryObject<Item> POTTERS_WHEEL = ITEMS.register("potters_wheel", () -> ( (new BlockItem(  ModBlocks.POTTERS_WHEEL.get(), ModBlocks.POTTERS_WHEEL.get().item_properties))) );
    
    // @PYROTECHNIC
    public static final RegistryObject<Item> PYROTECHNIC = ITEMS.register("pyrotechnic_worktable", () -> ( (new BlockItem(  ModBlocks.PYROTECHNIC_TABLE.get(), ModBlocks.PYROTECHNIC_TABLE.get().item_properties))) );
    
    // @RANGER
    public static final RegistryObject<Item> RANGER_TABLE = ITEMS.register("ranger_table", () -> ( (new BlockItem(  ModBlocks.RANGER_TABLE.get(), ModBlocks.RANGER_TABLE.get().item_properties))) );
    
    // @REDSTONE_ENGINEER
    public static final RegistryObject<Item> REDSTONE_TABLE = ITEMS.register("redstone_table", () -> ( (new BlockItem(  ModBlocks.REDSTONE_TABLE.get(), ModBlocks.REDSTONE_TABLE.get().item_properties))) );
    
    // @ROGUE
    public static final RegistryObject<Item> POISON_STATION = ITEMS.register("poison_station", () -> ( (new BlockItem(  ModBlocks.POISON_STATION.get(), ModBlocks.POISON_STATION.get().item_properties))) );
    
    // @SAPPER
    public static final RegistryObject<Item> DEMOLITION_STATION = ITEMS.register("demolition_station", () -> ( (new BlockItem(  ModBlocks.DEMOLITION_STATION.get(), ModBlocks.DEMOLITION_STATION.get().item_properties))) );
    
    // @SINGER
    public static final RegistryObject<Item> MICROPHONE_STAND = ITEMS.register("microphone_stand", () -> ( (new BlockItem(  ModBlocks.MICROPHONE_STAND.get(), ModBlocks.MICROPHONE_STAND.get().item_properties))) );
    
    // @TEACHER
    public static final RegistryObject<Item> LECTURE_STAND = ITEMS.register("lecture_stand", () -> ( (new BlockItem(  ModBlocks.LECTURE_STAND.get(), ModBlocks.LECTURE_STAND.get().item_properties))) );
    
    // @TRADER
    public static final RegistryObject<Item> MERCHANT_STALL = ITEMS.register("merchant_stall", () -> ( (new BlockItem(  ModBlocks.MERCHANT_STALL.get(), ModBlocks.MERCHANT_STALL.get().item_properties))) );
    
    // @TRADESMAN
    public static final RegistryObject<Item> TRADESMAN_HELMET = ITEMS.register("tradesman_helmet", () -> ((new BlockItem( ModBlocks.TRADESMAN_HELMET.get(), ModBlocks.TRADESMAN_HELMET.get().item_properties))));
    
    // @TRAPPER
    public static final RegistryObject<Item> TRAPPER_STATION = ITEMS.register("trapper_station", () -> ((new BlockItem( ModBlocks.TRAPPER_STATION.get(), ModBlocks.TRAPPER_STATION.get().item_properties))));
    
    // @UNDERAKER
    public static final RegistryObject<Item> COFFIN = ITEMS.register("coffin", () -> ((new BlockItem( ModBlocks.COFFIN.get(), ModBlocks.COFFIN.get().item_properties))));
    
    // @WARLOCK
    public static final RegistryObject<Item> WARPED_ALTAR = ITEMS.register("warped_altar", () -> ((new BlockItem( ModBlocks.WARPED_ALTAR.get(), ModBlocks.WARPED_ALTAR.get().item_properties))));
    
    // @WEAVER
    public static final RegistryObject<Item> WEAVING_STATION = ITEMS.register("weaving_station", () -> ((new BlockItem( ModBlocks.WEAVING_STATION.get(), ModBlocks.WEAVING_STATION.get().item_properties))));
    
    // @WORKER
    public static final RegistryObject<Item> DELIVERY_STATION = ITEMS.register("delivery_station", () -> ((new BlockItem( ModBlocks.DELIVERY_STATION.get(), ModBlocks.DELIVERY_STATION.get().item_properties))));
    

    /**
     * Spawn Eggs.
     */
    
    
    /**
     * Fluids
     */
    
    @SubscribeEvent
    public static void registerFluids(final RegistryEvent.Register<Fluid> event) {
    	event.getRegistry().registerAll(
    			
    	);
    }
    
    
    
    /**
     * Potions & Effects
     */
    @SubscribeEvent
    public static void registerPotion(final RegistryEvent.Register<Potion> event) { 
    	event.getRegistry().registerAll(
    			PotionList.GROWTH_POTION = new Potion(new EffectInstance(PotionList.GROWTH_EFFECT, 3600)).setRegistryName("Growth")
    	);
    }
    
    public static void registerEffect(final RegistryEvent.Register<Effect> event) { 
    	
    	event.getRegistry().registerAll(
    			PotionList.GROWTH_EFFECT = new PotionList.VillageCraftEffects(EffectType.BENEFICIAL, 2039713){
    			      /**
    			       * checks if Potion effect is ready to be applied this tick.
    			       */
    			      public boolean isReady(int duration, int amplifier) {
    			         return true;
    			      }

    			      public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
    			         if (entityLivingBaseIn instanceof ServerPlayerEntity && !entityLivingBaseIn.isSpectator()) {
    			            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLivingBaseIn;
    			            ServerWorld serverworld = serverplayerentity.getServerWorld();
    			               			            
    			         }

    			      }
    			   }.setRegistryName("Growth")
    	);
    }
}
