package com.villagecraft;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import com.villagecraft.util.Reference;

/**
 * Subscribe to events from the FORGE EventBus that should be handled on both PHYSICAL sides in this class
 *
 * @author Cadiboo
 */
@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {

}