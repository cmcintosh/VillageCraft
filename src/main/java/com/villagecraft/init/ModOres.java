package com.villagecraft.init;

import com.villagecraft.block.BlockOre;
import com.villagecraft.item.tiers.VillageCraftTiers;
import com.villagecraft.item.tool.ItemLute;
import com.villagecraft.util.Reference;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModOres {
	
	public static final DeferredRegister<Block> ORES = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);
	public static final ItemGroup ORES_GROUP = new ItemGroup("villagecraft_ores") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(net.minecraft.block.Blocks.IRON_ORE);
		}
	};
	
	// Ore Blocks
	public static final  RegistryObject<BlockOre> ALUMINUM_ORE = (RegistryObject<BlockOre>) ORES.register("ore_aluminum", () -> new BlockOre( AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3.0F, 3.0F).harvestLevel(1).setRequiresTool(), "ore_aluminum"));
//	public static final  RegistryObject<BlockOre> BISMUTHINITE_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_bismuthinite", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "bismuthinite"));
//	public static final  RegistryObject<BlockOre> CASSITERITE_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_cassiterite", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "cassiterite"));
//	public static final  RegistryObject<BlockOre> COPPER_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_copper", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "copper"));
//	public static final  RegistryObject<BlockOre> GALENA_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_galena", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "galena"));
//	public static final  RegistryObject<BlockOre> GARNIERITE_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_garnierite", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "garnierite"));
//	public static final  RegistryObject<BlockOre> HORN_SILVER_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_horn_silver", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "horn_silver"));
//	public static final  RegistryObject<BlockOre> MALACHITE_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_malachite", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "malachite"));
//	public static final  RegistryObject<BlockOre> PLATINUM_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_platinum", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "platinum"));
//	public static final  RegistryObject<BlockOre> SILVER_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_silver", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "silver"));
//	public static final  RegistryObject<BlockOre> SPHALERITE_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_sphalerite", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "sphalerite"));
//	public static final  RegistryObject<BlockOre> TETRAHEDRIATE_ORE = (RegistryObject<BlockOre>) BLOCKS.register("ore_tetrahedriate", () -> new BlockOre(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F), "tetrahedriate"));
//	
	// Ore BlockItems
	public static final Properties ORE_ITEMS = new Properties().group(ORES_GROUP);
    public static final RegistryObject<BlockItem> ALUMINUM = ITEMS.register("ore_aluminum", () -> new BlockItem(  ALUMINUM_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> BISMUTHINITE = ITEMS.register("ore_bismuthinite", () -> new BlockItem(  BISMUTHINITE_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> CASSITERITE = ITEMS.register("ore_cassiterite", () -> new BlockItem(  CASSITERITE_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> COPPER = ITEMS.register("ore_copper", () -> new BlockItem(  COPPER_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> GALENA = ITEMS.register("ore_galena", () -> new BlockItem(  COPPER_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> GARNIERITE = ITEMS.register("ore_garnierite", () -> new BlockItem(  GALENA_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> HORN_SILVER = ITEMS.register("ore_horn_sliver", () -> new BlockItem(  HORN_SILVER_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> MALACHITE = ITEMS.register("ore_malachite", () -> new BlockItem(  MALACHITE_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> PLATINUM = ITEMS.register("ore_platinum", () -> new BlockItem(  PLATINUM_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> SILVER = ITEMS.register("ore_silver", () -> new BlockItem(  SILVER_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> SPHALERITE = ITEMS.register("ore_sphalerite", () -> new BlockItem(  SPHALERITE_ORE.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> TETRAHEDRIATE = ITEMS.register("ore_tetrahedriate", () -> new BlockItem(  TETRAHEDRIATE_ORE.get(), ORE_ITEMS));
//    
    /**
     * Ore Ingots
     */
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> BISMUTHINITE_INGOT = ITEMS.register("bismuthinite_ingot", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    
    /**
     * Ore Nuggets
     */
    public static final RegistryObject<Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> BISMUTHINITE_NUGGET = ITEMS.register("bismuthinite_nugget", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register("lead_nugget", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> NICKEL_NUGGET = ITEMS.register("nickel_nugget", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    public static final RegistryObject<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget", () -> new Item((new Item.Properties()).group(ItemGroup.MATERIALS)));
//    
    /**
     * Ore Ingot Blocks
     */
    public static final  RegistryObject<Block> ALUMINUM_BLOCK = (RegistryObject<Block>) BLOCKS.register("aluminum_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
//    public static final  RegistryObject<Block> BISMUTHINITE_BLOCK = (RegistryObject<Block>) BLOCKS.register("bismuthinite_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
//    public static final  RegistryObject<Block> TIN_BLOCK = (RegistryObject<Block>) BLOCKS.register("tin_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
//    public static final  RegistryObject<Block> COPPER_BLOCK = (RegistryObject<Block>) BLOCKS.register("copper_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
//    public static final  RegistryObject<Block> LEAD_BLOCK = (RegistryObject<Block>) BLOCKS.register("lead_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
//    public static final  RegistryObject<Block> NICKEL_BLOCK = (RegistryObject<Block>) BLOCKS.register("nickel_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
//    public static final  RegistryObject<Block> SILVER_BLOCK = (RegistryObject<Block>) BLOCKS.register("silver_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
//    public static final  RegistryObject<Block> ZINC_BLOCK = (RegistryObject<Block>) BLOCKS.register("zinc_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
//    
	
    
    public static final RegistryObject<BlockItem> ALUMINUM_BLOCK_ITEM = ITEMS.register("aluminum_block", () -> new BlockItem(  ALUMINUM_BLOCK.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> BISMUTHINITE_BLOCK_ITEM = ITEMS.register("bismuthinite_block", () -> new BlockItem(  BISMUTHINITE_BLOCK.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> TIN_BLOCK_ITEM = ITEMS.register("tin_block", () -> new BlockItem(  TIN_BLOCK.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> COPPER_BLOCK_ITEM = ITEMS.register("copper_block", () -> new BlockItem(  COPPER_BLOCK.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> LEAD_BLOCK_ITEM = ITEMS.register("lead_block", () -> new BlockItem(  LEAD_BLOCK.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> NICKEL_BLOCK_ITEM = ITEMS.register("nickel_block", () -> new BlockItem(  NICKEL_BLOCK.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> SILVER_BLOCK_ITEM = ITEMS.register("silver_block", () -> new BlockItem(  SILVER_BLOCK.get(), ORE_ITEMS));
//    public static final RegistryObject<BlockItem> ZINC_BLOCK_ITEM = ITEMS.register("zinc_block", () -> new BlockItem(  ZINC_BLOCK.get(), ORE_ITEMS));
//    
    /**
     * Ore Material Color(s)
     */
    
    /**
     * Ore Material(S)
     */
    public static final Material ALUMINUM_MATERIAL = (new Material.Builder(MaterialColor.IRON)).doesNotBlockMovement().notSolid().replaceable().liquid().build();
}
