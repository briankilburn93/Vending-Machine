package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class VendingMachineInventory {
	
	/**********************************
	   			Data Members 
	**********************************/
	Map<String, VendingMachineItem> inventory;
	
	
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
	

	
}