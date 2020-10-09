package com.techelevator;

public class VendingMachineItem {
	
	private String productName;
	private double price;
	private String type;
	
	public VendingMachineItem(String productName, double price, String type) {
		this.productName = productName;
		this.price = price;
		this.type = type;
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
		
		switch(type) {
		case "Chip" : 
			return "chip noise";
		case "Gum" : 
			return "gum noise";
		case "Drink" : 
			return "drink noise";
		case "Candy" : 
			return "candy noise";
		}
		return "error noise";
	}

	public String toString() {
		return(productName + ": " + price + "\n" + "Remaining balance" + "balance goes here" + getSound() + "\n"); 
	}

}
