package com.villagecraft.gui;

import com.villagecraft.container.VillageCenterContainer;

import net.minecraft.client.gui.components.Button;
import net.minecraft.world.inventory.AbstractContainerMenu;

class BaseVillageEvent { 
	 protected AbstractContainerMenu container;
	 
	 public BaseVillageEvent(AbstractContainerMenu container) { 
		 this.container = container;
	 }
}