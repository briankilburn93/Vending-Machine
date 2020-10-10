package com.techelevator;

public class VendingMachineItem {
	
	private String productName;
	private double price;
	private String type;
	private int stock;
	
	public VendingMachineItem() {
		
	}
	
	public VendingMachineItem(String productName, double price, String type, int stock) {
		this.productName = productName;
		this.price = price;
		this.type = type;
		this.stock = stock;
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
			return "Crunch Crunch, Yum!";
		case "Gum" : 
			return "Chew Chew, Yum!";
		case "Drink" : 
			return "Glug Glug, Yum!";
		case "Candy" : 
			return "Munch Munch, Yum!";
		}
		return "error noise";
	}

	public String toString() {
		return(productName + ": " + price + "\n" + "Remaining balance" + "balance goes here" + getSound() + "\n"); // this is format to display items
	}

}
