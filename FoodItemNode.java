// --== CS400 File Header Information ==--
// Name: James Scully
// Email: jjscully@wisc.edu
// Notes to Grader: None

/**
 * This HashMapNode is used to create nodes of the key-value pairs
 * @author jimmyscully
 *
 * @param <FoodDescription> the object that is being used for the value
 */
@SuppressWarnings("hiding")
public class FoodItemNode <FoodDescription> {
  
  private String key;
  private FoodDescription value;

  
  /**
   * This constructor method is used to create nodes of the key-value pair
   * @param key- the key that is being used for this node
   * @param value- the FoodDescription that is being used for this node
   */
  public FoodItemNode(String key, FoodDescription value) {
    
    this.value = value;
    this.key = key;
  }
  /**
   * This getter method is used to get the key for the node
   * @return KeyType- the key for this node
   */
  public String getKey() {
    return this.key;
  }
  /**
   * This getter method is used to get the value for the node
   * @return FoodDesciption- the FoodDescription for this node
   */
  public FoodDescription getValue() {
    return this.value;
  }
  
}
