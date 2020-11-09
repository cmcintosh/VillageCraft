package com.villagecraft.client.gui;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import com.villagecraft.util.*;
import net.minecraftforge.api.distmarker.Dist;

/**
 * Subscribe to events from the FORGE EventBus that should be handled on the PHYSICAL CLIENT side in this class
 *
 * @author Cadiboo
 */
@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEventSubscriber {

}