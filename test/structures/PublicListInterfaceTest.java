package structures;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PublicListInterfaceTest {

  private ListInterface<String> list;

  @Before
  public void setup() {
    list = new RecursiveList<String>();
  }

  @Test
  public void testRemoveAt() {
    list.insertLast("a");
    list.insertLast("b");
    list.insertLast("c");
    try {
      list.removeAt(-1);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }
    try {
      list.removeAt(20);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }
    try {
      list.removeAt(list.size());
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }

    // assertEquals("b", list.removeAt(1));
    assertEquals("b", list.removeAt(1));
    try {
      list.removeAt(3);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = NullPointerException.class)
  public void testIndexOf() {
    list.insertFirst("a");
    list.insertFirst("b");
    list.insertFirst("c");
    list.insertFirst("d");
    list.insertFirst("e");

    assertEquals(4, list.indexOf("a"));
    assertEquals(3, list.indexOf("b"));
    assertEquals(2, list.indexOf("c"));
    assertEquals(1, list.indexOf("d"));
    assertEquals(0, list.indexOf("e"));
    assertEquals(-1, list.indexOf(null));

  }

  @Test
  public void testGetAt() {
    list.insertFirst("a");
    list.insertFirst("b");
    list.insertFirst("c");
    list.insertFirst("d");
    list.insertFirst("e");
    String[] getArray = new String[list.size()];
    String[] removedArray = new String[list.size()];

    for (int i = -2; i < 5 + 2; i++) {
      try {
        getArray[i] = list.get(0);
        removedArray[i] = list.removeAt(0);
      } catch (IndexOutOfBoundsException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  @Test
  public void testInsertAt() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    list.insertFirst("a");
    list.insertFirst("b");
    list.insertFirst("c");
    list.insertFirst("d");
    list.insertFirst("e");
    try {
      assertEquals(list, list.insertAt(list.size(), "z"));
    } catch (IndexOutOfBoundsException i) {
      System.out.println(i.getMessage());
    } catch (NullPointerException n) {
      System.out.println(n.getMessage());
    }

    list.removeAt(0);
    try {
      assertEquals("d", list.get(0));
    } catch (IndexOutOfBoundsException i) {
      System.out.println(i.getMessage());
    }
    assertFalse("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 5, list.size());

  }

  @Test(timeout = 500)
  public void testInsertFirstIsEmptySizeAndGetFirst1() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
    assertFalse("List should now have elements.", list.isEmpty());
    assertEquals("List should now have 1 element.", 1, list.size());
    assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
    list.insertFirst("world");
    assertEquals(2, list.size());
    list.insertFirst("foo");
    assertEquals(3, list.size());
    assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
  }

  @Test
  public void testInsertLastRemoveLastSizeAndIsEmpty() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    assertEquals("Insert last should return instance of self", list, list.insertLast("hello"));
    assertEquals("Insert last should return instance of self", list, list.insertLast("hello1"));

    assertEquals("Insert last should return instance of self", list, list.insertLast("hello2"));

    assertEquals("Insert last should return instance of self", list, list.insertLast("hello3"));

    assertEquals("hello3", list.removeLast());
    assertEquals("hello2", list.removeLast());

    assertEquals("hello1", list.removeLast());

    assertEquals("hello", list.removeLast());

    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    assertTrue("Newly constructed list should be empty.", list.isEmpty());

  }

  @Test
  public void testInsertFirstRemoveFirstSizeAndIsEmpty() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
    assertEquals("Remove first should return hello", "hello", list.removeFirst());
    assertEquals("List should be size 0.", 0, list.size());
    assertTrue("List should be empty.", list.isEmpty());
  }

  @Test
  public void testRemove() throws IndexOutOfBoundsException {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    list.insertFirst("a");
    list.insertFirst("a");
    list.insertFirst("b");
    list.insertFirst("b");
    list.insertFirst("d");

    list.removeAt(4);
    list.removeAt(2);
    list.removeAt(0);
    list.removeAt(1);
    try {
      list.removeAt(-1);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }
    try {
      list.removeAt(100);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }
    try {
      list.removeAt(list.size());
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    }
    list.removeAt(0);

    assertEquals("List should be size 1.", 0, list.size());

  }

  @Test // (expected = IndexOutOfBoundsException.class)
  public void testInsertLast() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    list.insertLast("a");
    list.insertLast("b");
    list.insertLast("c");
    list.insertLast("d");
    list.insertLast("e");

    assertEquals("b", list.removeAt(1));
    list.removeLast();
    assertEquals("List should be size 1.", 3, list.size());

  }

  @Test
  public void testInsertsGetsRemovesSize() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    list.insertFirst("c");
    list.insertFirst("b");
    list.insertFirst("a");

    assertEquals(3, list.size());
    assertFalse(list.isEmpty());

    assertEquals("a", list.getFirst());

    assertEquals(3, list.size());
    assertFalse(list.isEmpty());

    assertEquals("a", list.removeFirst());
    assertEquals("b", list.removeFirst());
    assertEquals("c", list.removeFirst());

    assertEquals(0, list.size());
    assertTrue(list.isEmpty());

    list.insertLast("a");
    list.insertLast("b");
    list.insertLast("c");

    assertEquals(3, list.size());
    assertFalse(list.isEmpty());

    assertEquals("c", list.getLast());

    assertEquals(3, list.size());
    assertFalse(list.isEmpty());

    assertEquals("c", list.removeLast());
    assertEquals("b", list.removeLast());
    assertEquals("a", list.removeLast());

    assertEquals(0, list.size());
    assertTrue(list.isEmpty());

    list.insertAt(0, "c");
    list.insertAt(0, "b");
    list.insertAt(0, "a");

    assertEquals(3, list.size());
    assertFalse(list.isEmpty());

    assertEquals("a", list.get(0));
    assertEquals("b", list.get(1));
    assertEquals("c", list.get(2));

    assertEquals(3, list.size());
    assertFalse(list.isEmpty());

    assertEquals("c", list.removeAt(2));
    assertEquals("b", list.removeAt(1));
    assertEquals("a", list.removeAt(0));

    assertEquals(0, list.size());
    assertTrue(list.isEmpty());

    try {
      list.get(list.size());
    } catch (IndexOutOfBoundsException e) {
      System.out.println("get exception works");
    }
    try {
      list.get(-1);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("get exception works");
    }
    try {
      list.get(20);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("get exception works");
    }

    try {
      list.getLast();
    } catch (IllegalStateException e) {
      System.out.println("getLast exception works");
    }
    try {
      list.getFirst();
    } catch (IllegalStateException e) {
      System.out.println("getFirst exception works");
    }

    try {
      list.insertAt(-1, "");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("insert@, index exception works");
    }
    try {
      list.insertAt(list.size(), "");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("insert@, index exception works");
    }
    try {
      list.insertAt(list.size() + 1, "");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("insert@, index exception works");
    }
    try {
      list.insertAt(-1, null);
    } catch (NullPointerException e) {
      System.out.println("insert@, null exception works");
    } catch (IndexOutOfBoundsException a) {
      System.out.println("insert@, index exception works");
    }
    try {
      list.insertAt(0, null);
    } catch (NullPointerException e) {
      System.out.println("insert@, null exception works");
    } catch (IndexOutOfBoundsException a) {
      System.out.println("insert@, index exception works");
    }
    try {
      list.insertAt(list.size(), null);
    } catch (NullPointerException e) {
      System.out.println("insert@, null exception works");
    } catch (IndexOutOfBoundsException a) {
      System.out.println("insert@, index exception works");
    }
    try {
      list.insertAt(list.size() + 1, null);
    } catch (NullPointerException e) {
      System.out.println("insert@, null exception works");
    } catch (IndexOutOfBoundsException a) {
      System.out.println("insert@, index exception works");
    }

    try {
      list.removeAt(0);

    } catch (IndexOutOfBoundsException a) {
      System.out.println("remove@, index exception works");
    }
    try {
      list.removeAt(list.size());

    } catch (IndexOutOfBoundsException a) {
      System.out.println("remove@, index exception works");
    }
    try {
      list.removeAt(-1);

    } catch (IndexOutOfBoundsException a) {
      System.out.println("remove@, index exception works");
    }
    try {
      list.removeAt(list.size() + 1);

    } catch (IndexOutOfBoundsException a) {
      System.out.println("remove@, index exception works");
    }

    try {
      assertFalse(list.remove("hi"));

    } catch (NullPointerException a) {
      System.out.println("remove, index exception works");
    }
    try {
      assertFalse(list.remove(null));

    } catch (NullPointerException a) {
      System.out.println("remove, null exception works");
    }

    try {
      list.removeFirst();

    } catch (IllegalStateException a) {
      System.out.println("removeF, index exception works");
    }
    try {
      list.removeLast();

    } catch (IllegalStateException a) {
      System.out.println("removeF, index exception works");
    }

    assertEquals(0, list.size());
    assertTrue(list.isEmpty());

    list.insertAt(0, "a");
    System.out.println(list.size());

    System.out.println(list.get(0));

    list.remove("a");

    System.out.println(list.size());

  }

  @Test
  public void testInsertsGetsRemovesSize2() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    list.insertFirst("a");
    assertEquals("a", list.getFirst());
    assertEquals("a", list.removeFirst());
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    list.insertLast("a");
    assertEquals("a", list.getLast());
    assertEquals("a", list.removeLast());
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    list.insertAt(0, "a");
    list.insertAt(0, "b");
    list.insertAt(0, list.get(list.size() - 1));
    assertEquals("a", list.get(list.indexOf("a")));
    assertEquals("b", list.get(1));
    assertTrue(list.remove("a"));
    try {
      assertFalse(list.remove(null));
    } catch (NullPointerException n) {
      System.out.println(n.getMessage());
    }
    assertTrue(list.remove("b"));

    assertFalse("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 1, list.size());
  }

}