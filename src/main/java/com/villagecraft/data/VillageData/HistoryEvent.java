package com.villagecraft.data.VillageData;


public class HistoryEvent { 
	protected String title;
	protected int value;
	
	public HistoryEvent(String title, int value) {
		this.title = title;
		this.value = value;
	}
	
	/**
	 * Converts the History event into a nbt string.
	 * @return
	 */
	public String toNBT() { 
		return "{ name:" + this.title + ", value:" + value +" }";
	}
	
	public static HistoryEvent parseNBT(String data) {
		String[] arrOfStr = data.split(",", 2);
		String[] nameArr = arrOfStr[0].split(":", 2);
		String[] valArr = arrOfStr[1].split(":", 2);
		return new HistoryEvent(nameArr[1], Integer.parseInt(valArr[1]));
	}
}
