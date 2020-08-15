package com.villagecraft;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.villagecraft.block.BlockChair;
import com.villagecraft.data.VillageCraftData;
import com.villagecraft.entity.goal.HealGolemGoal;
import com.villagecraft.entity.goal.VillagerGoalGotoVillageCenter;
import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.entity.professions.MerchantProfession;
import com.villagecraft.entity.professions.TradesmanProfession;
import com.villagecraft.entity.professions.WorkerProfession;
import com.villagecraft.gui.RenderVillageCenter;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModContainer;
import com.villagecraft.init.ModFoods;
import com.villagecraft.init.ModItems;
import com.villagecraft.init.ModTiles;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.item.ItemNationCharter;
import com.villagecraft.util.RandomTradeBuilder;
import com.villagecraft.util.Reference;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.VillagerTradingManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(Reference.MODID)
public class VillageCraft {

	public static final String MODID = Reference.MODID;
	public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
	public static VillageCraftData data = new VillageCraftData();
	
	
	public static VillageCraft instance;
	
	
	public VillageCraft() { 
		LOGGER.debug("VillageCraft, building villages since 1902.");
		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		
		// Registering mod blocks for VillageCraft
		ModBlocks.BLOCKS.register(modEventBus);
		
		ModContainer.CONTAINER_TYPE.register(modEventBus);
		
		// Registering mod tile entity
		ModTiles.TILES.register(modEventBus);
		
		// Registering mod items for VillageCraft
		ModItems.ITEMS.register(modEventBus);
		
		// Registering the mod foods for VillageCraft
		ModFoods.ITEMS.register(modEventBus);
		
		// Registering the mod villager Points of interest
		ModVillagerProfessions.POINTS_OF_INTEREST.register(modEventBus);
		
		// Registering the villager professions
		ModVillagerProfessions.PROFESSIONS.register(modEventBus);
		
		// Registering the villager trades
		MinecraftForge.EVENT_BUS.addListener(this::villagerTrades);
		MinecraftForge.EVENT_BUS.addListener(this::wandererTrades);
		MinecraftForge.EVENT_BUS.addListener(this::entityJoinWorldEvent);
		
		// Register GUI handlers
		MinecraftForge.EVENT_BUS.register(new RenderVillageCenter());
		
		
		// EntityJoinWorldEvent
		this.LOGGER.debug(this.data.getName() + " Is created");
		
		
		
	}
	
    public void villagerTrades(VillagerTradesEvent event)
    {
        BardProfession.RegisterVillagerTrades(event);
        WorkerProfession.RegisterVillagerTrades(event);
        MerchantProfession.RegisterVillagerTrades(event);
        TradesmanProfession.RegisterVillagerTrades(event);
    }
    
    public void wandererTrades(WandererTradesEvent event)
    {
        List<ITrade> genericList = event.getGenericTrades();
        RandomTradeBuilder.forEachWanderer((tradeBuild) -> genericList.add(tradeBuild.build()));
        
        List<ITrade> rareList = event.getRareTrades();
        RandomTradeBuilder.forEachWandererRare((tradeBuild) -> rareList.add(tradeBuild.build()));
    }
    
    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
  	  Entity entity = event.getEntity();
        if (entity instanceof VillagerEntity) {
          
          VillagerEntity villager = (VillagerEntity)event.getEntity();
          
      	  if (villager.isServerWorld()) {
      		if (this.data.initialized == false) {
      			ServerWorld world = (ServerWorld) villager.world;
          		world.getSavedData().getOrCreate(() -> { VillageCraft.data.setWorld(world); return VillageCraft.data; }, "VillageCraftData");
          		VillageCraft.data.initialize();
      		}
      		
      		villager.goalSelector.addGoal(1, new HealGolemGoal(villager));
        	
        	// Register goals for each villager type
        	TradesmanProfession.RegisterVillagerGoals(event);
        	WorkerProfession.RegisterVillagerGoals(event);
        	
        	BardProfession.RegisterVillagerGoals(event);
      	  }
        }
    }
    
    protected TileEntity refTE;
    public TileEntity getRefrencedTE() {
        return refTE;
    }

    public void setRefrencedTE(TileEntity te) {
    	this.refTE = te;
    }
    
}
