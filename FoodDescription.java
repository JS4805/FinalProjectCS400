// --== CS400 File Header Information ==--
// Name: James Scully
// Email: jjscully@wisc.edu
// Notes to Grader: None

/**
 * This Food Description class is used as a key in our program and it contains all of the information
 * for the food items we are adding to our fridge 
 * @author jimmyscully
 *
 */
public class FoodDescription {
  
  private String name;
  private double amount;
  private String unit;
  private String type;
  private String purchaseDate;
  private String expirationDate;
  
  /**
   * This getter method returns the name of the item
   * @return String- name of the item
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * This setter method sets the name of the food item
   * @param name- the new name we are trying to set
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * This getter method returns the amount of the food item
   * @return double- the amount of the item
   */
  public double getAmount() {
    return this.amount;
  }
  /**
   * This setter method sets the amount of the food item
   * @param amount- the new amount we are trying to set
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  /**
   * This getter method returns the units for the food item
   * @return String- the unit of the item 
   */
  public String getUnit() {
    return this.unit;
  }
  
  /**
   * This setter method sets the units for the food item
   * @param unit- the new unit we are trying to set
   */
  public void setUnit(String unit) {
    this.unit = unit;
  }
  
  /**
   * This getter method returns the type the food item is classified as
   * @return String- the type of the item
   */
  public String getType() {
    return this.type;
  }
  
  /**
   * This setter method sets the type for the food item
   * @param type- the new type we are trying to set
   */
  public void setType(String type) {
    this.type = type;
  }
  
  /**
   * This getter method gets the purchase date of the food item
   * @return String- the purchase date of the food item
   */
  public String getPurchaseDate() {
    return this.purchaseDate;
  }
  
  /**
   * This setter method sets the purchase date of the food item
   * @param purchaseDate- the new purchase date to set
   */
  public void setPurchaseDate(String purchaseDate) {
    this.purchaseDate = purchaseDate;
  }
  /**
   * This getter method gets the expiration date of the food item
   * @return String- the expiration date of the food item
   */
  public String getExpirationDate() {
    return this.expirationDate;
  }
  /**
   * This setter method sets the expiration date of the food item
   * @param expirationDate- the new expiration date to set
   */
  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }
  
  /**
   * This toString creates a string using all of the items information in this class
   */
  @Override
  public String toString() {
    return name + ": " + amount + " " + unit + "\n" +
        "Purchase Date: " + purchaseDate + "\n" +
        "Expiration Date: " + expirationDate + "\n" +
        "------------";
  }
}