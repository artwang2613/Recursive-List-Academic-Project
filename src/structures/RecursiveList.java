package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {
  private int size;
  private LLNode<T> head;
  private LLNode<T> tail;

  /**
   * Constructor.
   */
  public RecursiveList() {
    head = null;
    tail = null;
    size = 0;
  }

  class LLNode<T> {
    public T data;
    public LLNode<T> next;

    public LLNode(T data) {
      this.data = data;
    }

    public LLNode(T data, LLNode<T> next) {
      this.data = data;
      this.next = next;
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public ListInterface<T> insertFirst(T elem) {
    if (elem == null) {
      throw new NullPointerException("Element to be inserted is null");
    }
    LLNode<T> oldHead = head;
    head = new LLNode<T>(elem, oldHead);
    if (size == 0) {
      tail = head;
    }
    size++;
    return this;
  }

  @Override
  public ListInterface<T> insertLast(T elem) {
    if (elem == null) {
      throw new NullPointerException("Elem is null for tail insertion.");
    }
    if (head == null) {
      return insertFirst(elem);
    } else {
      tail.next = new LLNode<T>(elem, null);
      tail = tail.next;
    }
    size++;
    return this;
  }

  @Override
  public ListInterface<T> insertAt(int index, T elem) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException("Index is larger than the size of the list.");
    }
    if (elem == null) {
      throw new NullPointerException("Elem is null for insert@");
    }
    if (index == 0) {
      return insertFirst(elem);
    }
    if (index == size()) {
      return insertLast(elem);
    }
    LLNode<T> nodeBeforeIndex = nodeAtIndex(index - 1, head);
    nodeBeforeIndex.next = new LLNode<T>(elem, nodeBeforeIndex.next);
    size++;
    return this;
  }

  /**
   * Iterates to the index, then returns the node at that index with respect to
   * the currentNode which should start as the head.
   * 
   * @param index       node index
   * @param currentNode currentNode in list
   * @return
   */
  private LLNode<T> nodeAtIndex(int index, LLNode<T> currentNode) {
    if (index == 0) {
      return currentNode;
    }
    return nodeAtIndex(index - 1, currentNode.next);
  }

  @Override
  public T removeFirst() {
    if (isEmpty()) {
      throw new IllegalStateException("This list is empty");
    }
    LLNode<T> returnNode = head;
    head = null;
    head = returnNode.next;
    if (head == null) {
      tail = head;
    }
    size--;
    return returnNode.data;
  }

  @Override
  public T removeLast() {
    if (isEmpty()) {
      throw new IllegalStateException("This list is empty");
    }
    if (size == 1) {
      final T returnVal = tail.data;
      tail = null;
      head = tail;
      size--;
      return returnVal;
    }
    LLNode<T> secondToLast = nodeAtIndex(size - 2, head);
    final LLNode<T> returnNode = tail;
    tail = secondToLast;
    tail.next = null;
    size--;
    return returnNode.data;
  }

  @Override
  public T removeAt(int i) {
    if (i >= size || i < 0) {
      throw new IndexOutOfBoundsException("Index is larger than the size of the list.");
    }
    if (i == 0) {
      return removeFirst();
    }
    if (i == size - 1) {
      return removeLast();
    }
    LLNode<T> beforeRemovedNode = nodeAtIndex(i - 1, head);
    final T returnVal = beforeRemovedNode.next.data;
    beforeRemovedNode.next = beforeRemovedNode.next.next;
    size--;
    return returnVal;
  }

  @Override
  public T getFirst() {
    if (isEmpty()) {
      throw new IllegalStateException("This list is empty");
    }
    return head.data;
  }

  @Override
  public T getLast() {
    if (isEmpty()) {
      throw new IllegalStateException("This list is empty");
    }
    return tail.data;
  }

  @Override
  public T get(int i) {
    if (i >= size || i < 0) {
      throw new IndexOutOfBoundsException("Index is larger than the size of the list or is negative.");
    }
    return nodeAtIndex(i, head).data;
  }

  @Override
  public boolean remove(T elem) {
    int removalIndex = indexOf(elem);
    T removed = null;
    if (removalIndex >= 0) {
      removed = removeAt(removalIndex);
    } else {
      return false;
    }
    if (removed != null) {
      return true;
    }
    return false;
  }

  @Override
  public int indexOf(T elem) {
    if (elem == null) {
      throw new NullPointerException("Elem is null");
    }
    return indexOfRecursionHelper(elem, head, 0);
  }

  /**
   * Recursive helper to find index of an element if it exists.
   * 
   * @param elem    an element of type T
   * @param node    starts with head
   * @param counter starts at 0 and counts up for indexing
   * @return
   */
  public int indexOfRecursionHelper(T elem, LLNode<T> node, int counter) {
    if (node == null) {
      return -1;
    }
    if (node.data.equals(elem)) {
      return counter;
    }
    return indexOfRecursionHelper(elem, node.next, counter + 1);
  }

  @Override
  public boolean isEmpty() {
    if (head == null) {
      return true;
    }
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return new RecursiveListIterator<T>(this);
  }
}