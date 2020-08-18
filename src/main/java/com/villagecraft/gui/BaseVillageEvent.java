package com.villagecraft.gui;

import com.villagecraft.container.VillageCenterContainer;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.inventory.container.Container;

class BaseVillageEvent implements Button.IPressable { 
	 protected Container container;
	 
	 public BaseVillageEvent(Container container) { 
		 this.container = container;
	 }

	@Override
	public void onPress(Button p_onPress_1_) {
		
	} 
}