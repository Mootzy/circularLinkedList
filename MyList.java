package Liang._chpt24;

import java.util.Collection;
import java.util.*;

/**
 * An interface that extends the Java interface Collections
 * <p>
 * new methods: add(), get(), indexOf(), lastIndexOf(), remove(), set().
 * <p>
 * overrides: add(), isEmpty(), remove(), containsAll(), addAll(), removedAll(), retainAll(), toArray()
 * <p>
 * @author bobgils
 * @param <E> -- the type of elements in this collection
 */
public interface MyList<E> extends Collection<E> {
  /** Add a new element at the specified index in this list 
   @param index
   * @param e
   */
 public void add(int index, E e);

  /** Return the element from this list at the specified index */
  public E get(int index);

  /** Return the index of the first matching element in this list.
   *  Return -1 if no match. */
  public int indexOf(Object e);

  /** Return the index of the last matching element in this list
   *  Return -1 if no match. */
  public int lastIndexOf(E e);

  /** Remove the element at the specified position in this list
   *  Shift any subsequent elements to the left.
   *  Return the element that was removed from the list. */
  public E remove(int index);

  /** Replace the element at the specified position in this list
   *  with the specified element and returns the new set. */
  public E set(int index, E e);
  
  /** Add a new element at the end of this list--
   overrides Collection.add() */
  @Override 
  public default boolean add(E e) {
    add(size(), e);
    return true;
  }

  /** Return true if this list contains no elements --
   overrides Collection.isEmpty() */
  @Override 
  public default boolean isEmpty() {
    return size() == 0;
  }

  /** Remove the first occurrence of the element e 
   *  from this list. Shift any subsequent elements to the left.
   *  Return true if the element is removed.--
   overrides Collection.remove()*/
  @Override
  public default boolean remove(Object e) {
    if (indexOf(e) >= 0) {
      remove(indexOf(e));
      return true;
    }
    else
      return false;
  }

  /** Returns true if this collection contains all of the elements in the specified collection.--
   * overrides Collection.containsAll()
   * @param c
   * @return boolean
   */
  @Override
  public default boolean containsAll(Collection<?> c) {
    Boolean foundit = true;
    
    //using the object iterator
    Iterator<?> iter = c.iterator();
    while (iter.hasNext()){
        Object w = iter.next();
        if (!this.contains(w)){
            return false;
        }
    }
    
    //using Stream API and lambda annonymous method
    long count = c.stream().filter(e->!this.contains(e)).count();
    
    return ((count > 0) ?  false :  true);
  }

  /** Adds all of the elements in the specified collection to this collection (optional operation).--
   * overrides Collection.addAll()
   * @param c
   * @return boolean
   */
  @Override
  public default boolean addAll(Collection<? extends E> c) {
    // Left as an exercise
    return true;
  }

  /**
   * Removes all of this collection's elements that are also contained in the specified collection (optional operation).--
   * overrides Collection.removeAll()
   * @param c
   * @return boolean
   */
  @Override
  public default boolean removeAll(Collection<?> c) {
    // Left as an exercise
    return true;
  }

  /**
   * Retains only the elements in this collection that are contained in the specified collection (optional operation)--
   * overrides Collection.retainAll()
   * @param c
   * @return boolean
   */
  @Override
  public default boolean retainAll(Collection<?> c) {
    // Left as an exercise
    return true;
  }

  /**
   * Returns an array containing all of the elements in this collection.--
   * overrides Collection.toArray()
   * @return 
   */
  @Override
  public default Object[] toArray() {

      ArrayList<Object> newArr = new ArrayList<>(); //init anew ArrayList of obj type
      this.stream().forEach((x) -> newArr.add(x));//Lambda expression, stream the contence of newArr, forEach(x) add x to newArr
      return newArr.toArray(); //return
  }

  /**
   * Returns an array containing all of the elements in this collection.--
   * overrides Collection.toArray()   * 
   * @param <T>
   * @param array
   * @return 
   */
  @Override
  public default <T> T[] toArray(T[] array) {
    // Left as an exercise

      // init new generic arr
      ArrayList<T> newArr = new ArrayList<>();//
      this.stream().forEach((x) -> newArr.add((T)x));//as above, stream each data point to new arr
    return (T[])newArr.toArray();//type case to generic <T> array
  }
}