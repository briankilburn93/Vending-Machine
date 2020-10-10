package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class VendingMachineInventory {
	
	/**********************************
	   			Data Members 
	**********************************/
	Map<String, VendingMachineItem> inventory;
	double totalMoney = 0;
	VendingMachineItem item = new VendingMachineItem();
	/**********************************
				Constructors 
	 **********************************/
	
	public VendingMachineInventory() throws FileNotFoundException {

		inventory = new TreeMap<String, VendingMachineItem>(); //this is the inventory in slot order
	}
	
	/**********************************
			Getters and Settlers 
	 **********************************/
	
	public Map<String, VendingMachineItem> getInventory() {
		return inventory;
	}


	public void setInventory(Map<String, VendingMachineItem> inventory) {
		this.inventory = inventory;
	}

	public double getTotalMoney() {
		return totalMoney;
	}
	
	/**********************************
				Methods
	 **********************************/
	
	
	public Map<String, VendingMachineItem> loadItemsFromFile() throws FileNotFoundException  { // this method is set to run on startup
		
		// TODO - add code to verify the file actually exists and is is a file
		
		File itemFile = new File("vendingmachine.csv");  // Call in the CSV file
		Scanner vendingMachineFile = new Scanner(itemFile); // called a scanner to read the CSV file
	
		while (vendingMachineFile.hasNextLine()) {  // loop through each line in file
			String itemLine = vendingMachineFile.nextLine();  
			String lineElements[] = itemLine.split("\\|");  // Split up the file into separate words
			
			String slotID = lineElements[0]; // A1, B2, etc.
			VendingMachineItem item = new VendingMachineItem(lineElements[1], Double.parseDouble(lineElements[2]), lineElements[3], 5);
			// creating a Class Object that stores the NAME, PRICE, TYPE(Chip, Gum, etc.), and QUANTITY
			
			inventory.put(slotID, item); // putting the slotID (key) and the item (value) in the Tree Map called inventory
		}
		vendingMachineFile.close(); // close the Scanner object
		
		return inventory; // return the Tree Map
	}
	
	public void appendToLog() throws IOException {
		File logFile = new File("log.txt");
		FileWriter logWriter = null;
		BufferedWriter bufferedLog = null;
		PrintWriter logPrintWriter = null;
		
		try {
			logWriter = new FileWriter(logFile, true);
			bufferedLog = new BufferedWriter(logWriter);
			logPrintWriter = new PrintWriter(bufferedLog);
			logPrintWriter.println("01/01/2016 12:00:00 PM FEED MONEY: $5.00 $5.00");
			} finally {
				logWriter.close();
				bufferedLog.close();
				logPrintWriter.close();
			}
	}

	

	public double takeMoney(double insertedMoney) {
		
		if (insertedMoney == 1.00 || insertedMoney == 2.00 || insertedMoney == 5.00 || insertedMoney == 10.00) {
			totalMoney += insertedMoney;
		}
		return totalMoney;
	}
	
	public void returnChange() {
		
		double change = totalMoney;
		
		totalMoney = (totalMoney * 100);
		
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;
		
		quarters = (int)(totalMoney / 25);
		totalMoney = totalMoney % 25;
		dimes = (int)(totalMoney) / 10;
		totalMoney = totalMoney % 10;
		nickels = (int)(totalMoney) / 5;
		totalMoney = totalMoney % 5;
		
		totalMoney = 0;
		
		System.out.println("Your total change is $" + change + " |  Quarters: " + quarters + " |  Dimes: " + dimes + " |  Nickels: " + nickels);
	}
	
	public void buyItem(String selectedItem){
		
		Set<String> theKeys = inventory.keySet();
		
		for(String currentKey : theKeys) {
			if(selectedItem.equals(currentKey)) {	// This validates selected Item value
				double priceOfItem = inventory.get(currentKey).getPrice();
				String nameOfItem =  inventory.get(currentKey).getProductName();
				int currentStock = (int) inventory.get(currentKey).getStock();
				String currentType = inventory.get(currentKey).getType();
						
				if(currentStock > 0) {
					if(totalMoney - priceOfItem >= 0) {
						inventory.get(currentKey).setStock(currentStock-1);
						totalMoney = totalMoney - priceOfItem;	// Decrease total money
						System.out.println("Item Dispensed: " + nameOfItem + " |" + " Price: $" + priceOfItem + " |" + " Your remaining balance: $" + totalMoney + "\n" + item.getSound(currentType));
						break;
					}
					else if(totalMoney - priceOfItem < 0) {
						System.out.println("Insufficient funds | " + " Your remaining balance: $" + totalMoney);
						break;
					}
				}
				else if(currentStock == 0) {
					System.out.println(currentKey + " is currently out of stock | " + " Your remaining balance: $" + totalMoney);
					break;
				}
			}

		}
	}
}