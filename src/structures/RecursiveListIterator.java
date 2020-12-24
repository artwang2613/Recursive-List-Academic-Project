package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecursiveListIterator<T> implements Iterator<T> {
  private RecursiveList<T> list = null;
  private int index = 0;

  public RecursiveListIterator(RecursiveList<T> list) {
    this.list = list;
  }

  @Override
  public boolean hasNext() {
    if (list.isEmpty()) {
      return true;
    }
    return false;
  }

  @Override
  public T next() {
    if (list.get(index) == null) {
      throw new NoSuchElementException();
    }
    T elementData = list.get(index);
    index++;
    return elementData;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Not supported operation");
  }
}
