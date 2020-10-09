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


public class VendingMachineInventory {
	
	Map<String,List<VendingMachineItem>> itemsInVendingMachine;
	
	public VendingMachineInventory() throws FileNotFoundException {

		itemsInVendingMachine = new HashMap<String, List<VendingMachineItem>>(); 
		
		loadItemsFromFile();	
	}
	private void loadItemsFromFile() throws FileNotFoundException  {
		
		// TODO - add code to verify the file actually exists and is is a file
		
		File itemFile = new File("vendingmachine.csv");  
		Scanner vendingMachineFile = new Scanner(itemFile); 
	
		while (vendingMachineFile.hasNextLine()) {  
			String itemLine = vendingMachineFile.nextLine();  
			String lineElements[] = itemLine.split("\\|");  
													
			System.out.println(itemLine);							
			
		}
		vendingMachineFile.close();
	}
	public void addItem(String slotLocation, VendingMachineItem anItem) {
		
		List<VendingMachineItem> listOfItems = new ArrayList<>();
		//List<String> itemInventory = new ArrayList<>();
		
		if(itemsInVendingMachine.containsKey(slotLocation)) {
			listOfItems = itemsInVendingMachine.get(slotLocation); 
		}
		listOfItems.add(anItem);
		itemsInVendingMachine.put(slotLocation, listOfItems); 
		System.out.println(listOfItems); 
	}
	
}














	/*public VendingMachineItem addItem(String[] lineElement) {
		Chip aChip = new Chip(lineElement[1], Double.parseDouble(lineElement[2]), lineElement[3]);
		addItem(lineElement[0], aChip);
		return aChip;
	} 	
	
	private void addCandyToVending(String[] lineElement) {
		
	}
	
	private void addDrinkToVending(String[] lineElement) {

	}
	
	private void addGumToVending(String[] lineElement) {

	}
}*/



/*File itemFile = new File("vendingmachine.csv");  
Scanner vendingMachineFile = new Scanner(itemFile); 

while (vendingMachineFile.hasNextLine()) {  
	String itemLine = vendingMachineFile.nextLine();  
	String lineElements[] = itemLine.split("\\|");  
											
	System.out.println(itemLine);							
	
	switch (lineElements[3]) {  				
		case "Chip":			  				
			addChipToVending(lineElements); 
			break; 							
		case "Candy":						
			addCandyToVending(lineElements);  
			break;							 
		case "Drink":
			addDrinkToVending(lineElements);
			break;
		case "Gum":
			addGumToVending(lineElements);
			break;
	}
}
vendingMachineFile.close();*/

