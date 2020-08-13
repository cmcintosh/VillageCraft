package com.villagecraft.data;

import java.io.Serializable;

/**
 * Used to track player karma for a nation.
 * @author chris
 *
 */
public class Karma implements Serializable {

	private String player;
	private int karma;
	private VillageCraftNation nation;
	
	public Karma(String player, VillageCraftNation nation, int karma) { 
		this.player = player;
		this.nation = nation;
		this.karma = karma;
	}
	
	public String getPlayer() { 
		return this.player;
	}
	
	public VillageCraftNation getNation() {
		return this.nation;
	}
	
	public int getKarma() { 
		return this.karma;
	}
	
	public void addKarma(int point) {
		this.karma += point;
	}
	
	public void removeKarma(int point) {
		this.karma -= point;
	}
	
	public void setKarma(int point) {
		this.karma = point;
	}
}
