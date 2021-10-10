package Liang._chpt24;



import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

public class MyLinkedList<E> implements MyList<E> {

/**
 * Structure used to form the list.
 * @author bobgils
 * @param <E> - type of data element stored in node.
 */
class Node<E> {
    E element;
    Node<E> next, prev;

    /**
     * @param element
     */
    public Node(E element) {
      this.element = element;
    }

  public E getElement() {
    return element;
  }
}
  private Node<E> head, tail, rear;
  private int size = 0; // Number of elements in the list

  public int getSize(){
    return size;
  }
  /** Create an empty list */
  public MyLinkedList() {
  }


  /** Create a list from an array of objects */
  public MyLinkedList(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

  @Override
  public boolean contains(Object e) {
    if (e == this) return true;
    else return false;
  }

  /** Return the element at the specified index */
  @Override
  public E get(int index) {
    Node<E> found = rear;
    int current = 0;


    if (index > size || index < 0){
      throw new IndexOutOfBoundsException();
    }
    while (current != index){
      found = found.next;
      current++;
    }

    return found.getElement();
  }

  /** Return the index of the head matching element in
   *  this list. Return -1 if no match. */
  @Override
  public int indexOf(Object e) {

    int index = 0;
    Node<E> current = rear;

    while (current.getElement() !=e && current.getElement()!= null){
      if (current.equals(e)){
        return index;
      }
      index++;
      current=current.next;
    }
    // Left as an exercise
    return index;
  }

  /** Return the index of the last matching element in
   *  this list. Return -1 if no match. */
  @Override
  public int lastIndexOf(E e) {
    // Left as an exercise
    //point node to rear
    Node<E> current = rear;

    int found = 0;



    //iterate over nodes in list,
    for (int i = 0; current.next != null; i++){
      if (current.getElement() == e){
        found += i;
      }
      current = current.next;
    }
    return found;

  }

  /** Replace the element at the specified position
   *  in this list with the specified element. */
  @Override
  //wasn't sure wether to approach index in traditional 0 = 1 format or logical real number n => 1 format for index,
  //used n >= 1, ex: set(1, "hi") = ["hi","world"]
  public E set(int index, E e) {
    Node <E> current = rear;
    Node <E> setNode = new Node<E>(e);
    index = index -1;

    if (index == 0 || index == -1){
     this.addFirst(e);

    }
    else if (index == this.size){
      this.addLast(e);
    }
    else {
      for (int i = 0; current != null && i < (index - 1); i++){
        current = current.next;
      }

      Node<E> temp = current.next;
      current.next = setNode;
      setNode.next =temp;
    }


    return setNode.getElement();
  }

  /** Return the head element in the list */
  public E getFirst() {
    if (size == 0) {
      return null;
    }
    else {
      rear.element = head.element;
      return rear.element;
    }
  }

  /** Return the last element in the list */
  public E getLast() {
    if (size == 0) {
      return null;
    }
    else {
      rear.element = tail.element;
      return rear.element;
    }
  }

  /** Add an element to the beginning of the list */
  public void addFirst(E e) {
    Node<E> newNode = new Node<>(e); // Create a new node
    newNode.next = head; // link the new node with the head
    head = newNode; // head points to the new node
    rear = head;
    size++; // Increase list size

    if (tail == null) // the new node is the only node in list
      tail = rear;
  }

  /** Add an element to the end of the list */
  public void addLast(E e) {
    Node<E> newNode = new Node<>(e); // Create a new for element e

    //bug occuring here
    if (tail == null) {
      head = tail = newNode;
      rear = newNode;// The new node is the only node in list
    }
    else {
      tail.next = newNode; // Link the new with the last node
      tail = newNode; // tail now points to the last node
    }

    size++; // Increase size
  }

  /** Add a new element at the specified index
   * in this list. The index of the head element is 0 */
  @Override
  public void add(int index, E e) {
    if (index == 0) {
      addFirst(e);
    }
    else if (index >= size) {
      addLast(e);
    }
    else {
      Node<E> current = head;
      for (int i = 1; i < index; i++) {
        current = current.next;
      }
      Node<E> temp = current.next;
      current.next = new Node<>(e);
      (current.next).next = temp;
      size++;
    }
  }

  /** Remove the head node and
   *  return the object that is contained in the removed node. */
  public E removeFirst() {
    if (size == 0) {
      return null;
    } else {
      Node<E> temp = head;
      head = head.next;
      size--;
      if (head == null) {
        return temp.element;
      }
    }
    return null;
  }
//    else {
//      E temp = rear.element;
//      head = rear.next;
//      size--;
//      if (head == null) {
//        tail = null;
//      }
//      return temp;
//    }


  /** Remove the last node and
   * return the object that is contained in the removed node. */
  public E removeLast() {
    if (size == 0) {
      return null;
    }
    else if (size == 1) {
      E temp = head.element;
      head = tail = null;
      size = 0;
      return temp;
    }
    else {
      Node<E> current = head;

      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }

      E temp = tail.element;
      tail = current;
      tail.next = null;
      size--;
      return temp;
    }
  }

  /** Remove the element at the specified position in this
   *  list. Return the element that was removed from the list. */
  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    else if (index == 0) {
      return removeFirst();
    }
    else if (index == size - 1) {
      return removeLast();
    }
    else {
      Node<E> previous = head;

      for (int i = 1; i < index; i++) {
        previous = previous.next;
      }

      Node<E> current = previous.next;
      previous.next = current.next;
      size--;
      return current.element;
    }
  }

  /** Override toString() to return elements in the list */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      }
      else {
        result.append("]"); // Insert the closing ] in the string
      }
    }

    return result.toString();
  }

  /** Clear the list */
  @Override
  public void clear() {
    size = 0;
    head = tail = null;
  }

  /** Return true if this list contains the element e */


  /** Override iterator() defined in Iterable */
  @Override
  public java.util.Iterator<E> iterator() {
    return new LinkedListIterator();
  }

  private class LinkedListIterator
      implements java.util.Iterator<E> {
    private Node<E> current = head; // Current index

    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public E next() {
      E e = current.element;
      current = current.next;
      return e;
    }

    @Override
    public void remove() {
      E e = current.element;
      current = current.next;
      e = null;
    }
  }

  /** Return the number of elements in this list */
  @Override
  public int size() {
    return size;
  }
}

