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
    public static final RegistryObject<Item> ALCHEMIST_TABLE = ITEMS.register("alchemist_table", () -> ( (new BlockItem(  ModBlocks.BLOCK_ALCHEMIST_TABLE.get(), ModBlocks.BLOCK_ALCHEMIST_TABLE.get().item_properties))) );
    public static final RegistryObject<Item> DRAFTING_TABLE = ITEMS.register("drafting_table", () -> ((new BlockItem( ModBlocks.DRAFTING_TABLE.get(), ModBlocks.DRAFTING_TABLE.get().item_properties))));
    public static final RegistryObject<Item> BARD_STAND = ITEMS.register("bard_stand", () -> ( (new BlockItem(  ModBlocks.BARD_STAND.get(), ModBlocks.BARD_STAND.get().item_properties))) );
    public static final RegistryObject<Item> BAR = ITEMS.register("bar", () -> ( (new BlockItem(  ModBlocks.BAR.get(), ModBlocks.BAR.get().item_properties))) );
    public static final RegistryObject<Item> ORE_BOX = ITEMS.register("ore_box", () -> ( (new BlockItem(  ModBlocks.ORE_BOX.get(), ModBlocks.ORE_BOX.get().item_properties))) );
    public static final RegistryObject<Item> TRADESMAN_HELMET = ITEMS.register("tradesman_helmet", () -> ((new BlockItem( ModBlocks.TRADESMAN_HELMET.get(), ModBlocks.TRADESMAN_HELMET.get().item_properties))));
    public static final RegistryObject<Item> BRALWER_BOX = ITEMS.register("brawler_box", () -> ( (new BlockItem(  ModBlocks.BRAWLER_BOX.get(), ModBlocks.BRAWLER_BOX.get().item_properties))) );
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
    public static final RegistryObject<Item> TITLE_OFFICE = ITEMS.register("title_office", () -> ( (new BlockItem(  ModBlocks.TITLE_OFFICE.get(), ModBlocks.TITLE_OFFICE.get().item_properties))) );
    public static final RegistryObject<Item> POTTERS_WHEEL = ITEMS.register("potters_wheel", () -> ( (new BlockItem(  ModBlocks.POTTERS_WHEEL.get(), ModBlocks.POTTERS_WHEEL.get().item_properties))) );
    public static final RegistryObject<Item> BUILDERS_CHEST = ITEMS.register("builders_chest", () -> ( (new BlockItem(  ModBlocks.BUILDERS_CHEST.get(), ModBlocks.BUILDERS_CHEST.get().item_properties))) );


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
    
 	
 	/**
 	 * Trees
 	 */
    
    
}
