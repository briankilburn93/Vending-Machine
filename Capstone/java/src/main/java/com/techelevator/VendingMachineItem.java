package com.techelevator;

public abstract class VendingMachineItem {
	
	private String slotLocation;
	private String productName;
	private double price;
	private String type;
	private String sound;
	
	public VendingMachineItem(String productName, double price, String type) {
		//this.slotLocation = slotLocation;
		this.productName = productName;
		this.price = price;
		this.type = type;
	}
	
	public String getSlotLocation() {
		return slotLocation;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public String getSound() {
		return sound;
	}

	public String toString() {
		return(productName + ": " + price + "\n" + "Remaining balance" + "balance goes here" + "/n" + sound + "\n"); 
	}

}
