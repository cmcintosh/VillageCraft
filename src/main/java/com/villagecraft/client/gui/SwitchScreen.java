package com.villagecraft.client.gui;

import net.minecraft.client.gui.widget.button.Button;

class SwitchScreen implements Button.IPressable {
	protected int mode;
	protected AlchemistTableScreen screen;
	
	public SwitchScreen(int mode, AlchemistTableScreen screen) {
		this.mode = mode;
		this.screen = screen;
	}

	@Override
	public void onPress(Button p_onPress_1_) {
		screen.screenMode = this.mode;
	}
}
