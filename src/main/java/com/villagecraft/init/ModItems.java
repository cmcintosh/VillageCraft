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
import com.villagecraft.item.recipe.AlchemyRecipe;
import com.villagecraft.item.recipe.ITransmutationRecipe;
import com.villagecraft.item.recipe.TransmutationRecipe;
import com.villagecraft.item.recipe.TransmutationRecipe.Serializer;
import com.villagecraft.item.spawnegg.ItemSpawnIronGolem;
import com.villagecraft.item.tiers.VillageCraftTiers;
import com.villagecraft.item.tool.ItemHammer;
import com.villagecraft.item.tool.ItemHeart;
import com.villagecraft.item.tool.ItemLute;
import com.villagecraft.item.tool.ItemWandCap;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
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
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
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
	
	
	// Group the Magic Items together
	public static final ItemGroup MAGIC_ITEMS = new ItemGroup("villagecraft_magic") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.WAND_CAP.get());
		}
	};
	
	
	
	// Group the Crafting Ingredient Items together
	public static final ItemGroup CRAFING_ITEM_GROUP = new ItemGroup("villagecraft_crafting") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.STONE_HAMMER.get());
		}
	};
	
	/**
     * Alchemy
     */
 	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MODID);
 	public static RegistryObject<com.villagecraft.item.recipe.AlchemyRecipe.Serializer> ALCHEMY_SERIALIZER = RECIPE_SERIALIZERS.register("alchemy", () -> (new AlchemyRecipe.Serializer()));
 	public static RegistryObject<Serializer> TRANSMUTATION_SERIALIZER = RECIPE_SERIALIZERS.register("transmutation", () -> (new TransmutationRecipe.Serializer()) );
 	
	
	/**
	 * Village interaction Items
	 */
	
	public static final RegistryObject<Item> NATION_CHARTER = ITEMS.register("nationcharter", () -> (Item) new ItemNationCharter(ItemNationCharter.properties) );
    
    /**
     * Crafting components
     */
    public static final RegistryObject<Item> WORT = ITEMS.register("wort", () -> (Item) new ItemWort(ItemWort.properties) );
    public static final RegistryObject<Item> BEER_BUCKET = ITEMS.register("beer_bucket", () -> (Item) new ItemBeerBucket(ItemBeerBucket.properties) );
    
    public static final Properties GENERIC_ITEMS = new Properties().group(ModItems.CRAFING_ITEM_GROUP);
    public static final RegistryObject<Item> WHEEL = ITEMS.register("wheel", () -> (Item) new Item(GENERIC_ITEMS));
    public static final RegistryObject<Item> SAW_BLADE_SPUR = ITEMS.register("saw_blade_spur", () -> (Item) new Item(GENERIC_ITEMS));
    public static final RegistryObject<Item> CIRCULAR_SAW_BLADE = ITEMS.register("circular_saw_blade", () -> (Item) new Item(GENERIC_ITEMS));
    
    /**
     * Tools & Weapons
     */
    
    // Magic Related tools
    public static final RegistryObject<ItemWandCap> BRASS_WAND_CAP = ITEMS.register("wand_cap_brass", () -> new ItemWandCap(ItemWandCap.properties, "brass") );
    public static final RegistryObject<ItemWandCap> GOLD_WAND_CAP = ITEMS.register("wand_cap_gold", () -> new ItemWandCap(ItemWandCap.properties, "gold") );
    public static final RegistryObject<ItemWandCap> IRON_WAND_CAP = ITEMS.register("wand_cap_iron", () -> new ItemWandCap(ItemWandCap.properties, "iron") );
    public static final RegistryObject<ItemWandCap> THAUMIUM_WAND_CAP = ITEMS.register("wand_cap_thaumium", () -> new ItemWandCap(ItemWandCap.properties, "thaumium") );
    public static final RegistryObject<ItemWandCap> VOID_INERT_WAND_CAP = ITEMS.register("wand_cap_void_inert", () -> new ItemWandCap(ItemWandCap.properties, "void_inert") );
    public static final RegistryObject<ItemWandCap> WAND_CAP = ITEMS.register("wand_cap", () -> new ItemWandCap(ItemWandCap.properties, "default") );
    
    public static final RegistryObject<Item> STONE_HAMMER = ITEMS.register("stone_hammer", () -> (Item) new ItemHammer(VillageCraftTiers.STONE, 10, 0.1f, ItemHammer.properties) );
    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer", () -> (Item) new ItemHammer(VillageCraftTiers.IRON, 10, 0.1f, ItemHammer.properties) );
    public static final RegistryObject<Item> GOLD_HAMMER = ITEMS.register("gold_hammer", () -> (Item) new ItemHammer(VillageCraftTiers.GOLD, 10, 0.1f, ItemHammer.properties) );
    public static final RegistryObject<Item> EMERALD_HAMMER = ITEMS.register("emerald_hammer", () -> (Item) new ItemHammer(VillageCraftTiers.EMERALD, 10, 0.1f, ItemHammer.properties) );
    public static final RegistryObject<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer", () -> (Item) new ItemHammer(VillageCraftTiers.DIAMOND, 10, 0.1f, ItemHammer.properties) );
    
    public static final RegistryObject<Item> WOOD_LUTE = ITEMS.register("lute", () -> (Item) new ItemLute(VillageCraftTiers.WOOD, 10, 0.1f, ItemLute.properties) );
    public static final RegistryObject<Item> STONE_LUTE = ITEMS.register("stone_lute", () -> (Item) new ItemLute(VillageCraftTiers.STONE, 10, 0.1f, ItemLute.properties) );
    public static final RegistryObject<Item> IRON_LUTE = ITEMS.register("iron_lute", () -> (Item) new ItemLute(VillageCraftTiers.IRON, 10, 0.1f, ItemLute.properties) );
    public static final RegistryObject<Item> GOLD_LUTE = ITEMS.register("gold_lute", () -> (Item) new ItemLute(VillageCraftTiers.GOLD, 10, 0.1f, ItemLute.properties) );
    public static final RegistryObject<Item> EMERALD_LUTE = ITEMS.register("emerald_lute", () -> (Item) new ItemLute(VillageCraftTiers.EMERALD, 10, 0.1f, ItemLute.properties) );
    public static final RegistryObject<Item> DIAMOND_LUTE = ITEMS.register("diamond_lute", () -> (Item) new ItemLute(VillageCraftTiers.DIAMOND, 10, 0.1f, ItemLute.properties) );
    
    
    
    
    
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
