package Liang._chpt24;

public class MyLinkedList<E> implements MyList<E> {

  /**
   * Structure used to form the list.
   *
   * @param <E> - type of data element stored in node.
   */

  static class Node<E> {
    E element;
    Node<E> next;

    /**
     * @param element
     */
    public Node(E element) {
      this.element = element;
    }
  }

  private Node<E> rear;//create ref node

  private int size = 0; // Number of elements in the list

  /** Create an empty list */
  public MyLinkedList() {
  }

  /** Create a list from an array of objects */
  public MyLinkedList(E[] objects) {
    for (E e : objects)
      add(e);
  }

  /** Return the head element in the list */
  public E getFirst() {
    if (size == 0) {
      return null;
    }
    else {
      return rear.next.element;
    }
  }

  /** Return the last element in the list */
  public E getLast() {
    if (size == 0) {
      return null;
    }
    else {
      return rear.element;
    }
  }

  /** Add an element to the beginning of the list */
  public void addFirst(E e) {
    Node<E> newNode = new Node<>(e); // Create a new node

    if (rear == null) { // the new node is the only node in list
      rear = newNode;
      rear.next = rear;
      size++;
      return;
    }

    newNode.next = rear.next; // link the new node with the head
    rear.next = newNode; // head points to the new node
    size++; // Increase list size
  }

  /** Add an element to the end of the list */
  public void addLast(E e) {
    Node<E> newNode = new Node<>(e); // Create a new for element e
    Node<E> head = rear.next;

    if (rear == null) {
      rear.next = rear = newNode; // The new node is the only node in list
    }
    else {
      rear.next = newNode; // Link the new with the last node
      rear = rear.next; // tail now points to the last node
      rear.next = head;
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
      Node<E> current = rear.next;
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
    }
    else {
      E temp = rear.next.element;
      rear.next = rear.next.next;
      size--;
      if (rear.next == null) {
        rear = null;
      }
      return temp;
    }
  }

  /** Remove the last node and
   * return the object that is contained in the removed node. */
  public E removeLast() {
    if (size == 0) {
      return null;
    }
    else if (size == 1) {
      E temp = rear.next.element;
      rear.next = rear = null;
      size = 0;
      return temp;
    }
    else {
      Node<E> current = rear.next;
      Node <E> head = rear.next;

      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }

      E temp = rear.element;
      rear = current;
      rear.next = head;
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
      Node<E> previous = rear.next;

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

    Node<E> current = rear.next;

    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != rear.next) {
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
    rear.next = rear = null;
  }

  /** Return true if this list contains the element e */
  @Override
  public boolean contains(Object e) {
    for (E t : this) {
      if (e == t) {
        return true;
      }
    }
    return false;
  }

  /** Return the element at the specified index */
  @Override
  public E get(int index) {
    //Iterate index times then return
    Node<E> current = rear.next;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.element;
  }

  /** Return the index of the head matching element in
   *  this list. Return -1 if no match. */
  @Override
  public int indexOf(Object e) {
    Node<E> current = rear.next;
    for (int i = 0; i < this.size(); i++) {
      if (current.element == e) {
        return i;
      } else {
        current = current.next;
      }
    }
    return -1;
  }

  /** Return the index of the last matching element in
   *  this list. Return -1 if no match. */
  @Override
  public int lastIndexOf(E e) {
    Node<E> current = rear.next;
    int lastIndexFound = -1;
    for (int i = 0; i < this.size(); i++) {
      if (current.element == e) {
        lastIndexFound = i;
      }
      current = current.next;
    }
    return lastIndexFound;
  }

  /** Replace the element at the specified position
   *  in this list with the specified element. */
  @Override
  public E set(int index, E e) {
    Node<E> current = rear.next;
    Node<E> previous = rear.next;
    Node<E> temp = rear.next;
    Node<E> newNode = new Node<>(e);
    if (rear.next == null && rear == null) {
      return null;
    } else if (index >= this.size()) {
      return null;
    } else if (rear.next == rear && index == 0) {
      newNode.next = null;
      rear.next = rear = newNode;
      return temp.element;
    } else {
      for (int i = 0; i < index - 1; i++) {
        previous = previous.next;
      }
      current = previous.next;
      temp = current;
      newNode.next = current.next;
      previous.next = newNode;
      return temp.element;
    }
  }

  /** Override iterator() defined in Iterable */
  @Override
  public java.util.Iterator<E> iterator() {
    return new LinkedListIterator();
  }

  private class LinkedListIterator
          implements java.util.Iterator<E> {
    private Node<E> current = rear.next; // Current index
    private boolean atStart = true;

    @Override
    public boolean hasNext() {
      if (current == rear.next && !atStart) {
        return false;
      }
      return true;
    }

    @Override
    public E next() {
      E e = current.element;
      atStart = false;
      current = current.next;
      return e;
    }

    @Override
    public void remove() {
      // Left as an exercise
      if (rear.next == null) {
        return;
      } else if (current == rear.next && current == rear) {
        current = null;
        size = 0;
      } else {
        Node<E> previous = rear.next;
        while (previous.next != current) {
          previous = previous.next;
        }
        previous.next = current.next;
        current = null;
        size--;
      }
    }
  }

  /** Return the number of elements in this list */
  @Override
  public int size() {
    return size;
  }
}

