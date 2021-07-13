// --== CS400 File Header Information ==--
// Name: James Scully
// Email: jjscully@wisc.edu
// Notes to Grader: None

import java.util.NoSuchElementException;

@SuppressWarnings("hiding")
public class HashTableMap<FoodDescription> implements MapADT<FoodDescription>{
   // The array of LinkedHashNodes that contains our key-value pairs  
  private LinkedHashNode<FoodDescription>[] keyValueArr;
   // The length of the keyValueArr
  private int capacity;

  /**
   * this constructor method makes a HashTableMap with a custom capacity  
   * @param capacity- the capacity of the HashTableMap
   */
  @SuppressWarnings("unchecked")
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    keyValueArr = new LinkedHashNode[capacity];
  }
  
  /**
   * This no argument constructor makes a HashTableMap with a default capacity of 10
   */
  @SuppressWarnings("unchecked")
  public HashTableMap() {
    // The default capacity is 10
    capacity = 10;
    keyValueArr = new LinkedHashNode[capacity]; 
  }
  
  /**
   * This method adds a key-value pair to our HashTableMap, we don't allow for duplicates or pairs
   * with null keys to be added the HashTableMap
   * @param key- the key for the key-value pair
   * @param value- the value of the key-value pair, used for the hashcode
   * @return boolean- true when we were able to add a pair and false otherwise
   * @throws NoSuchElementException- when trying to add a duplicate item
   */
  @Override
  public boolean put(String key, FoodDescription value) {
    
    if (key == null) {
      return false;
    }
     // The node that contains our key-value pair
    FoodItemNode<FoodDescription> node = new FoodItemNode<FoodDescription>(key, value);
    
    int hashedValue = Math.abs(key.hashCode());
     // The remainder when dividing the hashedValue by the current capacity (index in array)
    int hashMod = hashedValue % capacity;
     // The position in the array we are trying to add the node
    LinkedHashNode<FoodDescription> hashMapPosition = keyValueArr[hashMod]; 
     // If we have a duplicate don't add the node and return false;
    try {
      while (hashMapPosition != null ) {
          if (keyValueArr[hashMod].getNode().getKey().equals(key)) {
             // If the item is already in the list throw an exception
            throw new NoSuchElementException("Duplicate item");
          }
          else {
            hashMapPosition = hashMapPosition.getNext();
          }
      }
    }
    // Catch an exception if it was throw and print out a warning, then return false
    catch (Exception e) {
      System.out.println("You are trying to add an item already in your fridge!");
      return false;
    }
    
    
     // Add the node at the beginning of the linked list at this array index and return true
    if (keyValueArr[hashMod] == null) {
      keyValueArr[hashMod] = new LinkedHashNode<FoodDescription>(node);
    }
    else {
     LinkedHashNode<FoodDescription> linkedNode = new LinkedHashNode<FoodDescription>(node); 
     linkedNode.setNext(keyValueArr[hashMod]);
     keyValueArr[hashMod] = linkedNode;
    }
     // Testing the capacity to see if we need to rehash (over or equal to 85% capacity)
    if (testLoadFactor() >= 0.85) {
      rehash();
    }

    return true;
  }
  
  /**
   * This method gets the value associated with a key in our HashtableMap, and throws an exception 
   * if the key cannot be found
   * @param key- the key we are searching for in the HashTableMap
   * @return FoodDescription- the value associated with the key
   * @throws NoSuchElementException- when key is not in HashTableMap
   */
  @Override
  public FoodDescription get(String key) throws NoSuchElementException {
     // If the key is null throw an Exception
    if (key == null) {
      throw new NoSuchElementException("Cannot get when key is null");
    }
    
    int hashedValue = Math.abs(key.hashCode());
    int hashMod = hashedValue % capacity;
    
    LinkedHashNode<FoodDescription> hashMapPosition = keyValueArr[hashMod]; 
     // Checks to see if the key can be found in the HashTableMap
    while (hashMapPosition != null) {
      if (hashMapPosition.getNode().getKey().equals(key)) {
        return hashMapPosition.getNode().getValue();
      }
      else {
        hashMapPosition = hashMapPosition.getNext();
      }
    }
     // If the key counldn't be found null is returned
    return null;
  }

  /**
   * This method gets the size of the HashTableMap by counting all of the elements in the
   * LinkedHashNode chains that are stored in the array
   * @return int- the size of the HashTableMap
   */
  @Override
  public int size() {
    
    int size = 0;
     // The current node we are checking 
    LinkedHashNode<FoodDescription> current;

    for (int i = 0; i < capacity; ++i) {
      current = keyValueArr[i];
       // If current isn't null add 1 to size and get the next node
      while (current != null) {
        size++;
        current = current.getNext();
      }
    }
    
    return size;
  }

  /**
   * This method checks to see if the HashtableMap contains a specific key, if the key is null
   * then we automatically return false
   * @param key- the key we are searching for in the HashTableMap
   * @return boolean- true if the key is in the HashtableMap and false otherwise
   */
  @Override
  public boolean containsKey(String key) {
    
    if (key == null) {
      return false;
    }
    
    int hashedValue = Math.abs(key.hashCode());
    int hashMod = hashedValue % capacity;
    
    LinkedHashNode<FoodDescription> hashMapPosition = keyValueArr[hashMod]; 
     // Checks to see if the key is in the HashTableMap
    while (hashMapPosition != null ) {
      if (hashMapPosition.getNode().getKey().equals(key) ) {
        return true;
      }
      else {
        hashMapPosition = hashMapPosition.getNext();
      }
    }
    
    return false;
  }

  /**
   * This method removes a key-value pair based on the specific key passed as input.
   * @param key- the key we are using to remove the key-value pair
   * @return FoodDescription- the value of the removed key-value pair
   */
  @Override
  public FoodDescription remove(String key) {
    
    int hashedValue = Math.abs(key.hashCode());
    int hashMod = hashedValue % capacity;
     // The current position in the HashTableMap
    LinkedHashNode<FoodDescription> hashMapPosition = keyValueArr[hashMod]; 
     // The previous position in the HashTableMap
    LinkedHashNode<FoodDescription> prev = null;
    
    while (hashMapPosition != null) {
      if (hashMapPosition.getNode().getKey().equals(key)) {
        // If we are removing the head of the LinkedHashNode chain set the second to the head
        if (prev == null) {
          // If we are removing the head its the only node set the head to null
          if (hashMapPosition.getNext() == null) {
            keyValueArr[hashMod] = null;
            return hashMapPosition.getNode().getValue();
          }
          keyValueArr[hashMod] = hashMapPosition.getNext(); 
          return hashMapPosition.getNode().getValue();
        }
         // If we are removing an element that isn't the head connect the previous and the next
        if (prev != null ) {
          prev.setNext(hashMapPosition.getNext()); 
          return hashMapPosition.getNode().getValue();
        }
      }
      else {
        prev = hashMapPosition;
        hashMapPosition = hashMapPosition.getNext();
      }
    }
    
    return null;
  }

  /**
   * This method clears the keyValueArr by setting all of the LinkedHashNodes to null
   */
  @Override
  public void clear() {
    
    for (int i = 0; i < capacity; ++i) {
      keyValueArr[i] = null;
    }
  }
  
  /**
   * This method test the capacity of the keyValueArr to know if we need to rehash (>=85%)
   * @return double- the current percent of the capacity that is not null
   */
  private double testLoadFactor() {
    
    if (capacity <= 0) {
      return 0;
    }
     // The percent of the capacity that currently isn't null
    return size() / (double) capacity;
  }
  
  
  /**
   * This method is used to rehash the keyValueArr when it reaches over 85% capacity
   */ 
  @SuppressWarnings("unchecked")
  private void rehash() {

    // The temporary array we are using to rehash that contains every node
    LinkedHashNode<FoodDescription>[] tempRehashArr = new LinkedHashNode[size()];
    // The index that each key-value pair in the keyValueArr is going in the temporary array
    int index = 0;
    for (int i = 0; i < capacity; ++i) {
      LinkedHashNode<FoodDescription> hashMapPosition = keyValueArr[i]; 
      while (hashMapPosition != null) {
        tempRehashArr[index] = hashMapPosition;
        index++;
        hashMapPosition = hashMapPosition.getNext();
      }
    }
    this.capacity = capacity * 2;
     // Recreating the keyValueArr with double the old capacity 
    this.keyValueArr = new LinkedHashNode[capacity];
     // Adding all of the key-value pairs to our new keyValueArr
    for (int i = 0; i < tempRehashArr.length; ++i) {
      put(tempRehashArr[i].getNode().getKey(), tempRehashArr[i].getNode().getValue());
    }
  }
  
  @Override
  /**
   * This toString method is used to print out everything in the HashTableMao
   */
  public String toString() {
    
    String finalString = "Fridge Items (" + size() + ")\n";
    finalString += "------------" + "\n";
    
    LinkedHashNode<FoodDescription> current;
    
    for (int i = 0; i < capacity; ++i) {
      current = keyValueArr[i];
       // If current isn't null add 1 to size and get the next node
      while (current != null) {
        finalString += current.getNode().getValue().toString() + "\n";
        current = current.getNext();
      }
    }   
    
    return finalString;
  }
  
  /**
   * This getter method gets the capacity of the HashTableMap which is used in testing
   * @return int- the capacity of the HashTableMap
   */
  public int getCapacity() {
    return capacity;
  }
  
  /**
   * This getter method returns the keyValueArr that holds the LinkedHashNodes
   * @return LinkedHashNode<FoodDescription>[]- the keyValueArr of the class
   */
  public LinkedHashNode<FoodDescription>[] getKeyValueArr() {
    return keyValueArr;
  }
}