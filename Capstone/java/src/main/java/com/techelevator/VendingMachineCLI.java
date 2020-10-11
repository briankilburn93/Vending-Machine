package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone

public class VendingMachineCLI {

    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT          = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT,
													    MAIN_MENU_OPTION_SALES_REPORT
													    };
	
	private static final String PURCHASE_MENU_FEED_MONEY 			= "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT     	= "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION	= "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {	PURCHASE_MENU_FEED_MONEY,
															PURCHASE_MENU_SELECT_PRODUCT,
															PURCHASE_MENU_FINISH_TRANSACTION
															};
	
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	private VendingMachineInventory aVendingMachine;
	File salesFile;
	PrintWriter salesWriter;
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	public VendingMachineCLI(Menu menu) throws FileNotFoundException {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
		this.aVendingMachine = new VendingMachineInventory();	// instantiate a new vending Machine object
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	 * @throws FileNotFoundException 
	*
	***************************************************************************************************************************/

	public void run() throws FileNotFoundException{
		aVendingMachine.setInventory(aVendingMachine.loadItemsFromFile()); // this loads inventory on startup ONLY
		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems(aVendingMachine);   // invoke method to display items in Vending Machine
					break;                   		 // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();         		 // invoke method to purchase items from Vending Machine
					break;                    		// Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    		// Invoke method to perform end of method processing
					shouldProcess = false;    		// Set variable to end loop
					aVendingMachine.endMethodProcessing();
					break;                    		// Exit switch statement
					
				case MAIN_MENU_OPTION_SALES_REPORT:
					salesReport(aVendingMachine);
					
					break;
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems(VendingMachineInventory vendingMachine) {      // static attribute used as method is not associated with specific object instance
		
		Set<String> keys = vendingMachine.getInventory().keySet();
			for (String key : keys) {
				VendingMachineItem currentItem = vendingMachine.getInventory().get(key);
				System.out.println(key + " " + currentItem.toString());
			}
	}
	
	public void salesReport(VendingMachineInventory vendingMachine) throws FileNotFoundException {      // static attribute used as method is not associated with specific object instance
		
		Set<String> keys = vendingMachine.getInventory().keySet();
		
		LocalDateTime datetime1 = LocalDateTime.now();  
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy hh.mm.ss a");  
	    String formatDateTime = datetime1.format(format); 
	    
	    String salesFileName = formatDateTime + " Sales Report.txt";
	    
	    salesFile = new File(salesFileName);
		salesWriter = new PrintWriter(salesFile);
		
			for (String key : keys) {
				VendingMachineItem currentItem = vendingMachine.getInventory().get(key);
				salesWriter.println(currentItem.salesToString());
			}
			salesWriter.println("Total Sales: $" + df.format(aVendingMachine.getSalesTotal()));
			salesWriter.close();
	}
	
	public void purchaseItems(){	 // static attribute used as method is not associated with specific object instance
		boolean shouldProcess = true; 
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);  // Display menu and get choice
			Scanner theKeyboard = new Scanner(System.in);
			switch(choice) {                  // Process based on user menu choice
			
				case PURCHASE_MENU_FEED_MONEY:
					System.out.print("The machine accepts $1s $2s $5s or $10s. Please insert bills here: $");
					double insertedMoney =  theKeyboard.nextDouble();
					aVendingMachine.takeMoney(insertedMoney);
					System.out.print("Current money provided: $" + df.format(aVendingMachine.getTotalMoney())); 
					break; // Exit switch statement
			
				case PURCHASE_MENU_SELECT_PRODUCT:
					displayItems(aVendingMachine);
					System.out.print("Please enter desired item code: ");
					String selectedItem = theKeyboard.nextLine().toUpperCase();
					aVendingMachine.buyItem(selectedItem);
					// invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case PURCHASE_MENU_FINISH_TRANSACTION:
					shouldProcess = false;    // Set variable to end loop
					aVendingMachine.returnChange();
					endMethodProcessing();
					break;                    // Exit switch statement
			}	
		}
		return;                               // End method and return to caller
	}	
	
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		System.out.println("Thanks for using the Vendo-Matic 800!"); // Any processing that needs to be done before method ends
	}
	
}
