// --== CS400 File Header Information ==--
// Name: James Scully
// Email: jjscully@wisc.edu
// Notes to Grader: None

import java.util.NoSuchElementException;

/**
 * This interface is used to help us implement methods in out HashTableMap
 * @author jimmyscully
 *
 * @param <KeyType> The object that is being used for the key
 * @param <ValueType> The object that is being used for the value
 */
@SuppressWarnings("hiding")
public interface MapADT<FoodDescription> {

    public boolean put(String key, FoodDescription value);
    public FoodDescription get(String key) throws NoSuchElementException;
    public int size();
    public boolean containsKey(String key);
    public FoodDescription remove(String key);
    public void clear();
    
}