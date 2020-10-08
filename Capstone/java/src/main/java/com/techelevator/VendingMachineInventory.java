package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class VendingMachineInventory {

	//public static enum ITEM_TYPE {CHIP, CANDY, DRINK, GUM, INVALID}; 
	
	//Map<ITEM_TYPE,List<VendingMachineItem>> itemsInVendingMachine;
	
	public VendingMachineInventory() throws FileNotFoundException {

		//itemsInVendingMachine = new HashMap<ITEM_TYPE,List<VendingMachineItem>>(); 
		loadItemsFromFile();	
	}


	private void loadItemsFromFile() throws FileNotFoundException  {
		
		// TODO - add code to verify the file actually exists and is is a file
		
		File itemFile = new File("vendingmachine.csv");  // Define a file object for the disk file
		Scanner vendingMachineFile = new Scanner(itemFile);  // Define a Scanner for the file object
	
		while (vendingMachineFile.hasNextLine()) {  // Loop as long as (while) there are lines in the file
			String itemLine = vendingMachineFile.nextLine();  // Read a line from the file and store it in theLine variable 
			String lineElement[] = itemLine.split("\\|");  // spilt the line into individual String values based on the pipe
														 // because the pipe character (|) has a special meaning in Java, 
			System.out.println(itemLine);										 // 		we had to use the escape sequence for the pipe \\
			
			switch (lineElement[3]) {  				 // Look at linesField[0]
				case "Chip":			  				 // if it's a "C" 
					addChipToVending(lineElement);  // add it to the collection as a coin passing the array of fields from the line
					break; 							 // and go to the end of the switch
				case "Candy":							 // if it's a "W"
					addCandyToVending(lineElement);  // do this
					break;							 // and go to the end of the switch
				case "Drink":
					addDrinkToVending(lineElement);
					break;
				case "Gum":
					addGumToVending(lineElement);
					break;
			}
		
		System.out.println("--------------------------");
		for (String anElement : lineElement) {
			System.out.println(anElement);
		}
		}
			vendingMachineFile.close();
	}
	
	public void addChipToVending(String[] lineElement) {
		// TODO - Add Wine item to collection
	} 	
	
	private void addCandyToVending(String[] lineElement) {
		// TODO - Add Wine item to collection
	}
	
	private void addDrinkToVending(String[] lineElement) {
		// TODO - Add Vinyl item to collection
	}
	
	private void addGumToVending(String[] lineElement) {
		// TODO - Add Vinyl item to collection
	}
}

//Add the data from the file as a Coin to the item collections
		// Convert the data from the file from a String to the type required by the Coin class before I can create the Coin
		// 		purchaseDate from file 
		/*String[] itemCost = lineElement[2].split("/");  // separate the month/date/year into separate Strings
		// LocalDate.of() will create a LocalDate object from integers passed in as year, month, day
		LocalDate purchaseDate =  LocalDate.of(Integer.parseInt(dateParts[2])  // Convert the year as a String to an int
				                             , Integer.parseInt(dateParts[0])  // Convert the month as a String to an int
				                            , Integer.parseInt(dateParts[1]));  // Convert the day as a String to an int 
		
		int purchaseAmt = (int) (Double.parseDouble(fieldsInLine[3]) * 100);  // Convert amt from "3.00" (double value)
		
		// Instantiate a Coin object using the data from the file to add to the item collection Map
		
		Coin aCoin = new Coin (fieldsInLine[1]
				              ,purchaseDate
				              ,purchaseAmt
				              ,Boolean.parseBoolean(fieldsInLine[4])  // You may be able to do some conversions in the argument
				              ,Integer.parseInt(fieldsInLine[5])
				              ,fieldsInLine[6].charAt(0)  // charAt(index) return character at the index of the String
				              ,fieldsInLine[7]
				              ,fieldsInLine[8]);	
		
		addItem(fieldsInLine[0].charAt(0), aCoin);  // Call addItem() with the item type character ("C", "V", "W")
													// 		and CollectionItem object (Coin in this case)
		
		return aCoin;
	}
	
	String[] dateParts = lineElement[2].split("/");  // separate the month/date/year into separate Strings
	// LocalDate.of() will create a LocalDate object from integers passed in as year, month, day
	LocalDate purchaseDate =  LocalDate.of(Integer.parseInt(dateParts[2])  // Convert the year as a String to an int
			                             , Integer.parseInt(dateParts[0])  // Convert the month as a String to an int
			                            , Integer.parseInt(dateParts[1]));  // Convert the day as a String to an int 
	
	int purchaseAmt = (int) (Double.parseDouble(fieldsInLine[3]) * 100);  // Convert amt from "3.00" (double value)
	
	// Instantiate a Coin object using the data from the file to add to the item collection Map
	
	Coin aCoin = new Coin (fieldsInLine[1]
			              ,purchaseDate
			              ,purchaseAmt
			              ,Boolean.parseBoolean(fieldsInLine[4])  // You may be able to do some conversions in the argument
			              ,Integer.parseInt(fieldsInLine[5])
			              ,fieldsInLine[6].charAt(0)  // charAt(index) return character at the index of the String
			              ,fieldsInLine[7]
			              ,fieldsInLine[8]);	
	
	addItem(fieldsInLine[0].charAt(0), aCoin);  // Call addItem() with the item type character ("C", "V", "W")
												// 		and CollectionItem object (Coin in this case)
	
	return aCoin;*/
