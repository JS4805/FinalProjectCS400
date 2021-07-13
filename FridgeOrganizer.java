// --== CS400 File Header Information ==--
// Name: James Scully
// Email: jjscully@wisc.edu
// Notes to Grader: None

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FridgeOrganizer {

  // The Hash-Table that will be holding all of our food items
  private static HashTableMap<FoodDescription> fridge = new HashTableMap<FoodDescription>(5);
  private static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    // Welcoming statements and instructions on how to use the application
    System.out.println("Welcome to Fridge Oragnizer a new app to help you organize your fridge!");
    System.out.println("Would you like to load in your previous data (y/n)?");
    
    char testChar = scan.next().charAt(0);
    
    if (testChar == 'y') {
       testData();
    }
    else if (testChar != 'n') {
      System.out.println("Invalid Character! preceding to app without external data.");
    }
    else {
      System.out.println("Preceding to app without external data.");
    }

    System.out.println("Would you like to add(a), remove(r), change(c), or find(f) an item.");
    System.out.println("You can also print(p), clear(x), or save(s) your entire inventory, and quit(q) the app.\n");
    // The scanner for the app that takes in user input
    //Scanner scan = new Scanner(System.in);

    System.out.println("Please input a character from above to start an action!");
    // The first inputed character that we are using to tell the app what to do
    char inputChar = scan.next().charAt(0);
    // While the input character isn't a 'q' we keep running the application
    while (inputChar != 'q') {
      // Adding an item when input is 'a'
      if (inputChar == 'a') {
        inputChar = addItem();
      }
      // Removing an item when input is 'r'
      else if (inputChar == 'r') {
        inputChar = removeItem();
      }
      // Changing an item when input is 'c'
      else if (inputChar == 'c') {
        inputChar = changeItem();
      }
      // Finding an item when input is 'f'
      else if (inputChar == 'f') {
        inputChar = findItem();
      }
      // Printing fridge when input is 'p'
      else if (inputChar == 'p') {
        inputChar = printFridge();
      }
      // Quitting app when input is 'q'
      else if (inputChar == 'q') {
        break;
      }
      // Clear fridge when input is 'x'
      else if (inputChar == 'x') {
        inputChar = clearFridge();
      }
      // Save all item when input is 's'
      else if (inputChar == 's') {
        inputChar = saveData();
      }
      // Other character print an error message
      else {
        inputChar = invalidChar();
      }
    }
    // When the character is 'q' the app will be quit and a goodbye message will be printed
    System.out.println("Thank you for using the Fridge Organizer!");
    // Close the scanner to stop any leakage that might occur
    scan.close();
  }

  /**
   * This method adds an item to the HashTableMap when called using the user input prompted for
   * inside the method
   * @return char- the new char that dictates our command
   */
  public static char addItem() {

    String itemName = null;
    Double itemAmount = null;
    String itemUnit = null;
    String itemPurchase = null;
    String itemExpiration = null;
    // Using a try block here to catch any unexpected input the user might add  
    try {  
      System.out.println("What is the name of your new item (hypenate spaces)?");
      itemName = scan.next();
      System.out.println("What is the amount of you item (digits only no units)?");
      itemAmount = scan.nextDouble();
      System.out.println("What is the unit of your item?");
      itemUnit = scan.next();
      System.out.println("What was the purchase date (No spaces)?");
      itemPurchase = scan.next();
      System.out.println("Was is the expiration date (No spaces)?");
      itemExpiration = scan.next();
    } 
    // Catching the exception and letting them change information if there was an error
    catch (Exception e) {
      System.out.println("There has been an error adding the item please try again");
      return '*';
    }
    // Make the new FoodDescription item with the information from the user
    FoodDescription foodStats = new FoodDescription();

    foodStats.setName(itemName);
    foodStats.setAmount(itemAmount);
    foodStats.setUnit(itemUnit);
    foodStats.setPurchaseDate(itemPurchase);
    foodStats.setExpirationDate(itemExpiration);

    // If the item was successfully added let the user know
    if (fridge.put(itemName, foodStats) == true) {
      System.out.println("Item added successfully!");
    }
    // If the item wasn't added let the user know to try again
    else {
      System.out.println("There was an error adding the item please try again.");
    }
    // Tell user they can now choose their next step for the application
    System.out.println("Please input another command, add(a), remove(r), change(c), find(f), "
        + "print(p), clear(x), save(s), quit(q)");
    // Get the input character and check it to see what the app needs to do
    return scan.next().charAt(0);
  }

  /**
   * This method removes an item from out HashTableMap with the input the user gives us
   * @return char- the new char that dictates our command
   */
  public static char removeItem() {
    
    int originalSize = fridge.size();

    // Get the item the user wants to remove
    System.out.println("What food item would you like to remove from your fridge?");
    String foodKey = scan.next();

    // If we can't find the item print out that message to the user
    if (fridge.containsKey(foodKey) == false) {
      System.out.println("Couldn't find item to remove please try again");
    }
    // Otherwise let them know it was successfully removed
    else {
      fridge.remove(foodKey);
      if (originalSize > fridge.size()) {
        System.out.println("Item was successfully removed!");
      }
      else {
        System.out.println("An error with removing has occured, please try again later");
      }
    }
    // Tell user they can now choose their next step for the application
    System.out.println("Please input another command, add(a), remove(r), change(c), find(f), "
        + "print(p), clear(x), save(s), quit(q)");
    // Get the input character and check it to see what the app needs to do
    return scan.next().charAt(0);
  }

  /**
   * This method changes a piece of information about one of the items in our HashTableMap
   * @return char- the new char that dictates our command
   */
  public static char changeItem() {
    // Find the item we are trying to make a change too
    System.out.println("What item would you like to change?");
    String itemName = scan.next();

    if (fridge.containsKey(itemName) != true) {
      System.out.println("Food item you are trying to change cannot be found.");
      return '*';
    }

    System.out.println("what part of the item would you like to change? Name(n), "
        + "Amount(a), Unit(u), Purchase Date(p), or Expiration Date(e)?");
    char changeChar = scan.next().charAt(0);

    FoodDescription changingItem = fridge.get(itemName);
    // If they want to change the name prompt them for what they want the new name to be then change it 
    if (changeChar == 'n') {
      System.out.println("Current name is: " + changingItem.getName() + 
          ". What would you like to change it too (Hypenate spaces)?");
      String changed = scan.next();
      changingItem.setName(changed);
      System.out.println("Item named successfully changed!");

    }
    // If they want to change the amount prompt them for what they want the new amount to be then change it
    else if (changeChar == 'a') {
      System.out.println("Current amount is: " + changingItem.getAmount() + 
          ". What would you like to change it too?");
      Double changed = null;
      try {
        changed = scan.nextDouble();
      }
      catch (Exception e) {
        System.out.println("Item amount couldn't be changed because the amount you were trying to"
            + " change to wasn't numerical");
        return '*';                                     
      }
      changingItem.setAmount(changed);
      System.out.println("Item amount successfully changed!");

    }
    // If they want to change the units prompt them for what they want the new unit to be then change it
    else if (changeChar == 'u') {
      System.out.println("Current unit is: " + changingItem.getUnit() + 
          ". What would you like to change it too?");
      String changed = scan.next();
      changingItem.setUnit(changed);
      System.out.println("Item unit successfully changed!");

    }
    // If they want to change the purchase date prompt them for what they want the new purchase date to be then change it
    else if (changeChar == 'p') {
      System.out.println("Current purchase date is: " + changingItem.getPurchaseDate() + 
          ". What would you like to change it too? (No spaces)");
      String changed = scan.next();
      changingItem.setPurchaseDate(changed);
      System.out.println("Item purchase date successfully changed!");

    }
    // If they want to change the expiration date prompt them for what they want the new expiration date to be then change it
    else if (changeChar == 'e') {
      System.out.println("Current expiration date is: " + changingItem.getExpirationDate() + 
          ". What would you like to change it too? (No spaces)");
      String changed = scan.next();
      changingItem.setExpirationDate(changed);
      System.out.println("Item expiration date successfully changed!");

    }    
    // Tell user they can now choose their next step for the application
    System.out.println("Please input another command, add(a), remove(r), change(c), find(f), "
        + "print(p), clear(x), save(s), quit(q)");
    // Get the input character and check it to see what the app needs to do
    return scan.next().charAt(0);       
  }

  /**
   * This method allows us to find items in our HashTableMap 
   * @return char- the new char that dictates our command
   */
  public static char findItem() {
    // Prompt the user for which item they want to find
    System.out.println("What item would you like to find?");
    String itemToFind = scan.next();
    // If we can't find the item tell the user that
    if (fridge.get(itemToFind) == null) {
      System.out.println("Could not find item in your fridge.");
    }
    // Otherwise print out all of the food information
    else {
      System.out.println("------------");
      System.out.println(fridge.get(itemToFind).toString());
    }
    // Tell user they can now choose their next step for the application
    System.out.println("Please input another command, add(a), remove(r), change(c), find(f), "
        + "print(p), clear(x), save(s), quit(q)");
    // Get the input character and check it to see what the app needs to do
    return scan.next().charAt(0); 
  }

  /**
   * This method allows us to print all of the items in our HashTableMap in a formatted string
   * @return char- the new char that dictates our command
   */
  public static char printFridge() {  
    // Print out the string from the method in out HashTableMap class
    System.out.print(fridge.toString());
    // Tell user they can now choose their next step for the application
    System.out.println("Please input another command, add(a), remove(r), change(c), find(f), "
        + "print(p), clear(x), save(s), quit(q)");
    // Get the input character and check it to see what the app needs to do
    return scan.next().charAt(0); 
  }

  /**
   * This method is used when an invalid character is entered or a general error has occured
   * @return char- the new char that dictates our command
   */
  public static char invalidChar() {
    // Used to clear the buffer and avoid any errors from previous user command
    scan.nextLine();
    System.out.println("Error has occured! Please input another command, add(a), remove(r), "
        + "change(c), find(f), print(p), clear(x), save(s), quit(q)");
    return scan.next().charAt(0);
  }
  
  /**
   * This method is used to clear all items out of the fridge leaving it empty
   * @return char- the new char that dictates our command
   */
  public static char clearFridge() {
    
    fridge.clear();
    System.out.println("Your fridge has been successfully cleared!");
    System.out.println("Please input another command, add(a), remove(r), change(c), find(f), "
        + "print(p), clear(x), save(s), quit(q)");
    
    return scan.next().charAt(0);
  }
  
  /**
   * This method reads in the data from our external file and adds it to our fridge
   */
  public static void testData() {
    
    // The file object we will be reading in
    File testFile = new File("TestData.txt");
    
    try {
       // The scanner for the test file
      Scanner test = new Scanner(testFile);
      
       // Skipping the first line because it contains our formatting 
      //test.nextLine();
      
       // While the file has more lines make more objects from them and add to the fridge 
      while (test.hasNextLine()) {
        
        String name = test.next();
        Double amount = test.nextDouble();
        String unit = test.next();
        String purchase = test.next();
        String expiration = test.next();
        
        FoodDescription foodStats = new FoodDescription();

        foodStats.setName(name);
        foodStats.setAmount(amount);
        foodStats.setUnit(unit);
        foodStats.setPurchaseDate(purchase);
        foodStats.setExpirationDate(expiration);
        
        fridge.put(name, foodStats);
        
      }
     // Print out the message that all 10 items were added successfully if no error was thrown
    System.out.println("We have added " + fridge.size() + " items to your fridge use the print command to see them.");
     // Close the scanner to prevent leakage
    test.close();
    } 
     // Catch any FileNotFoundExceptions and print an error message if this occurs
    catch (FileNotFoundException e) {
    System.out.println("File with external data wasn't found. No items were added to your fridge.");
    }
  }
  
  public static char saveData() {
    
    int currentLegnth = 0;
    
    File externalData = new File("TestData.txt");
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(externalData);
      
      for (int i = 0; i < fridge.getKeyValueArr().length; ++i) {
        
        LinkedHashNode<FoodDescription> current = fridge.getKeyValueArr()[i];
        
        while (current != null) {
          String line = null;
          line = current.getNode().getValue().getName() + " " + 
                 current.getNode().getValue().getAmount() + " " +
                 current.getNode().getValue().getUnit() + " " +
                 current.getNode().getValue().getPurchaseDate() + " " +
                 current.getNode().getValue().getExpirationDate();
          currentLegnth++;
          if (fridge.size() ==  currentLegnth) {
            writer.print(line);
          }
          else  {
            writer.println(line);
          }
          current = current.getNext();
        }  
      }
      System.out.println("Your items have been saved successfully!");
    }
    catch (FileNotFoundException e) {
      System.out.println("Error saving data please try again later");     
    }
    finally {
      if (writer != null) {
        writer.close();
      }
    }
    
    System.out.println("Please input another command, add(a), remove(r), change(c), find(f), "
        + "print(p), clear(x), save(s), quit(q)");
    
    return scan.next().charAt(0);
  }
}