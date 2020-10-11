package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	double salesTotal = 0;
	VendingMachineItem item = new VendingMachineItem();
	File logFile;
	PrintWriter logPrintWriter;
	/**********************************
				Constructors 
	 **********************************/
	
	public VendingMachineInventory() throws FileNotFoundException {

		inventory = new TreeMap<String, VendingMachineItem>(); //this is the inventory in slot order
		
		logFile = new File("log.txt");
		logPrintWriter = new PrintWriter(logFile);
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

	public BigDecimal getTotalMoney() {
		return BigDecimal.valueOf(totalMoney);
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
		vendingMachineFile.close(); // close the Scanner objects
		
		return inventory; // return the Tree Map
	}

	

	public BigDecimal takeMoney(double insertedMoney) {
		
		double currentMoney = totalMoney;
		
		if (insertedMoney == 1.00 || insertedMoney == 2.00 || insertedMoney == 5.00 || insertedMoney == 10.00) {
			totalMoney += insertedMoney;
		}
		
		LocalDateTime datetime1 = LocalDateTime.now();  
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");  
	    String formatDateTime = datetime1.format(format);  
		
		logPrintWriter.println(formatDateTime + " FEED MONEY: $" + currentMoney + " $" + totalMoney);
		
		return BigDecimal.valueOf(totalMoney);
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
		
		LocalDateTime datetime1 = LocalDateTime.now();  
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");  
	    String formatDateTime = datetime1.format(format);  
		
		logPrintWriter.println(formatDateTime + " GIVE CHANGE: $" + change + " $" + totalMoney);
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
						double originalMoney = totalMoney;
						
						inventory.get(currentKey).setStock(currentStock-1);
						totalMoney = totalMoney - priceOfItem;	// Decrease total money
						salesTotal = salesTotal + priceOfItem;
						
						System.out.println("Item Dispensed: " + nameOfItem + " |" + " Price: $" + priceOfItem + " |" + " Your remaining balance: $" + totalMoney + "\n" + item.getSound(currentType));
						
						LocalDateTime datetime1 = LocalDateTime.now();  
					    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");  
					    String formatDateTime = datetime1.format(format);  
						
						logPrintWriter.println(formatDateTime + " " + nameOfItem + " " + currentKey + " $" + originalMoney + " $" + totalMoney);
						
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
	
	public double getSalesTotal() {
		return salesTotal;
	}

	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		logPrintWriter.close();
	}
	
}