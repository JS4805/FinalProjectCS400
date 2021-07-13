// --== CS400 File Header Information ==--
// Name: James Scully
// Email: jjscully@wisc.edu
// Notes to Grader: None

/*** JUnit imports ***/
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class is used for the JUnit test of our final project
 * @author jimmyscully
 *
 */
public class FridgeTest {

  // The HashTableMap object we are using in each test
protected HashTableMap<FoodDescription> fridge;

 
 /**
  * This before each test creates a new fridge before each test so we can test individual things
  */
 @BeforeEach
 public void createInstane() {
    // The new fridge has a capacity of 6 for testing
   fridge = new HashTableMap<FoodDescription>(6);
 }

 /**
  *  This test method tests the growth of the HashTableMap when we get over 85% of the capacity
  *  5/6 = 83% so adding a 6th element gets us over the 85% threshold 
  */
 @Test
 public void testGrowth() {
    // This tests if the capacity doubles when we go over 85%
   fridge.put("test1", null);
   fridge.put("test2", null);
   fridge.put("test3", null);
   fridge.put("test4", null);
   fridge.put("test5", null);
   fridge.put("test6", null);

    // New capacity should be 12
   assertEquals(fridge.getCapacity(), 12);
 }

 /**
  * This test method tests the collision handling of the HashTableMap. The two selected keys have
  * been tested to know that the have the same hashcode mod 6. This means they should chain
  */
 @Test
 public void testCollisionHanding() {
   
    // "test2" and "test00" have the same hashCode with the initial capacity of 6 causing chaining 
   FoodItemNode<FoodDescription> food1 = new FoodItemNode<FoodDescription>("test2", null);
   FoodItemNode<FoodDescription> food2 = new FoodItemNode<FoodDescription>("test00", null);
   
   fridge.put(food1.getKey(), null);
   fridge.put(food2.getKey(), null);
   
    //These items should be chained so food2 should be the next value to the first value at index 0
   assertEquals(fridge.getKeyValueArr()[0].getNext().getNode().getKey() , food1.getKey());
 }

 /**
  * This test method tests if removing the first item in a chain, sets the next item to the head of
  * the chain after the original removal
  */
 @Test
 public void testRemove() {
   // "test2" and "test00" have the same hashCode with the initial capacity of 6 causing chaining 
  FoodItemNode<FoodDescription> food1 = new FoodItemNode<FoodDescription>("test2", null);
  FoodItemNode<FoodDescription> food2 = new FoodItemNode<FoodDescription>("test00", null);
  
  fridge.put(food1.getKey(), null);
  fridge.put(food2.getKey(), null);
  
  fridge.remove("test00");
  
  assertEquals(fridge.getKeyValueArr()[0].getNode().getKey(), food1.getKey());
        
 }
 
 /**
  * This tester method tests whether the change to an items information truly changes it
  */
 @Test
 public void testChangeDescription() {
   
    // Create a new item for the fridge
   FoodDescription testFood = new FoodDescription();
   testFood.setName("pizza");
   testFood.setAmount(2);
   testFood.setUnit("slices");
   testFood.setPurchaseDate("07/03/2021");
   testFood.setExpirationDate("07/15/2021");
    // Put that item in the fridge
   fridge.put(testFood.getName(), testFood);
    // Set the items amount to 1 as if someone has eaten a slice of the pizza
   fridge.get(testFood.getName()).setAmount(1);
    // Check to see if the amount was changed in the actual object
   assertEquals(testFood.getAmount(), 1);
        
 }
 
 /**
  * This tester method tests whether the toString method is working
  */
 @Test
 public void testPrint() {
   
    // Create a new item for the fridge
   FoodDescription testFood1 = new FoodDescription();
   testFood1.setName("pie");
   testFood1.setAmount(5);
   testFood1.setUnit("slices");
   testFood1.setPurchaseDate("07/04/2021");
   testFood1.setExpirationDate("07/15/2021");
    // Put that item in the fridge
   fridge.put(testFood1.getName(), testFood1);
   
   // Create a new item for the fridge
  FoodDescription testFood2 = new FoodDescription();
  testFood2.setName("cake");
  testFood2.setAmount(1);
  testFood2.setUnit("whole");
  testFood2.setPurchaseDate("07/01/2021");
  testFood2.setExpirationDate("07/21/2021");
   // Put that item in the fridge
  fridge.put(testFood2.getName(), testFood2);
  
    // Create a new item for the fridge
  FoodDescription testFood3 = new FoodDescription();
  testFood3.setName("cookies");
  testFood3.setAmount(3);
  testFood3.setUnit("dozen");
  testFood3.setPurchaseDate("07/03/2021");
  testFood3.setExpirationDate("07/11/2021");
   // Put that item in the fridge
  fridge.put(testFood3.getName(), testFood3);
   // Check to see if the string equals what it is supposed too
  assertEquals(fridge.toString() , 
       
       "Fridge Items (" + fridge.size() + ")\n"
       + "------------\n"
       + testFood1.getName() + ": " + testFood1.getAmount() + " " + testFood1.getUnit() + "\n"
       + "Purchase Date: " + testFood1.getPurchaseDate() + "\n"
       + "Expiration Date: " + testFood1.getExpirationDate() + "\n" 
       + "------------\n"
       + testFood2.getName() + ": " + testFood2.getAmount() + " " + testFood2.getUnit() + "\n"
       + "Purchase Date: " + testFood2.getPurchaseDate() + "\n"
       + "Expiration Date: " + testFood2.getExpirationDate() + "\n" 
       + "------------\n"
       + testFood3.getName() + ": " + testFood3.getAmount() + " " + testFood3.getUnit() + "\n"
       + "Purchase Date: " + testFood3.getPurchaseDate() + "\n"
       + "Expiration Date: " + testFood3.getExpirationDate() + "\n" 
       + "------------\n");
 }
}