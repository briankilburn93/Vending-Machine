package com.techelevator;

public class VendingMachineItem {
	/**********************************
				Data Members 
	 **********************************/
	private String productName;
	private double price;
	private String type;
	private int stock;
	
	
	/**********************************
				Constructors 
	 **********************************/
	public VendingMachineItem() {
		
	}
	
	public VendingMachineItem(String productName, double price, String type, int stock) {
		this.productName = productName;
		this.price = price;
		this.type = type;
		this.stock = stock;
	}

	
	/**********************************
		Getters and Settlers 
	 **********************************/
	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public String getSound(String type) {
		
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
	public int getStock() {
		return stock;
	}
	public int setStock(int stock) {
		return this.stock = stock;
	}

	/**********************************
				Methods
	 **********************************/
	public String toString() {
		if(stock == 0) {
			return("| " + productName + " |" + " $" + price + " | SOLD OUT \n");
		}
		else {
		return("| " + productName + " |" + " $" + price + " | " + stock + " in stock \n"); // this is format to display items
		}
	}

}
