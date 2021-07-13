// --== CS400 File Header Information ==--
// Name: James Scully
// Email: jjscully@wisc.edu
// Notes to Grader: None

/**
 * This class is used to link the FoodItemNodes together in a singly linkedList way
 * @author jimmyscully
 *
 * @param <FoodDescription> the FoodDescription that is being used for the value
 */
@SuppressWarnings("hiding")
public class LinkedHashNode <FoodDescription> {
  
  private FoodItemNode<FoodDescription> node;
  private LinkedHashNode<FoodDescription> next;
  
  /**
   * This constructor method is used to create linked node when we know the next element
   * @param node- the current node we are using to link
   * @param next- the next node that is being linked
   */
  public LinkedHashNode(FoodItemNode<FoodDescription> node, 
      LinkedHashNode<FoodDescription> next) {
    
    this.node = node;
    this.next = next;
    
  }
  /**
   * This constructor method is used when we don't know the node that will be linked next
   * @param node- the current node we are using to link
   */
  public LinkedHashNode(FoodItemNode<FoodDescription> node) {
    
    this.node = node;
    this.next = null;
    
  }
  
  /**
   * This getter method is used to get he next node that is linked
   * @return LinkedHashNode<KeyType, ValueType> - the linkedNode that is next from the current node
   */
  public LinkedHashNode<FoodDescription> getNext() {
    return this.next;
  }
  
  /**
   * This setter method is used to set the next node and link it to the current
   * @param next- the node we want to link to the current linked node
   * @return LinkedHashNode<KeyType, FoodDescription> - the next node that is being linked
   */
  public LinkedHashNode<FoodDescription> setNext(LinkedHashNode <FoodDescription> next) {
    return this.next = next;
  }
  
  /**
   * This getter method gets the base node that is part of the linkedNode
   * @return LinkedHashNode<KeyType, FoodDescription>- the node that is being called
   */
  public FoodItemNode<FoodDescription> getNode() {
    return this.node;
  }
}
