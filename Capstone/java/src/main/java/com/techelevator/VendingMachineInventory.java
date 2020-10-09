package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class VendingMachineInventory {
	
	Map<String,List<VendingMachineItem>> inventory;
	
	public VendingMachineInventory() throws FileNotFoundException {

		inventory = new TreeMap<String, List<VendingMachineItem>>(); //this is the inventory in slot order
		
		loadItemsFromFile();	// load the Map from the file
	}
	private void loadItemsFromFile() throws FileNotFoundException  {
		
		// TODO - add code to verify the file actually exists and is is a file
		
		File itemFile = new File("vendingmachine.csv");  
		Scanner vendingMachineFile = new Scanner(itemFile); 
	
		while (vendingMachineFile.hasNextLine()) {  
			String itemLine = vendingMachineFile.nextLine();  
			String lineElements[] = itemLine.split("\\|");  
			
			String slotID = lineElements[0];
			List<VendingMachineItem> itemInformation = new ArrayList<>();
			VendingMachineItem item = new VendingMachineItem(lineElements[1], Double.parseDouble(lineElements[2]), lineElements[3]);
			
			for(int i=0; i<5;i++) {
			itemInformation.add(item);
			}
			
			inventory.put(slotID, itemInformation);
			
		}
		vendingMachineFile.close();
	}
	
}