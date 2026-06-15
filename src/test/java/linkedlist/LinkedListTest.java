package linkedlist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    private LinkedList list;

    @BeforeEach
    void setUp() {
        list = new LinkedList();
    }

    @Test
    void testIsEmptyOnCreation() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testAddSingleNode() {
        list.add(new Node(10));
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(10, list.getByIndex(0).value);
    }

    @Test
    void testAddMultipleNodes() {
        list.add(new Node(10));
        list.add(new Node(20));
        list.add(new Node(30));
        
        assertEquals(3, list.size());
        assertEquals(10, list.getByIndex(0).value);
        assertEquals(20, list.getByIndex(1).value);
        assertEquals(30, list.getByIndex(2).value);
    }

    @Test
    void testAddNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.add(null);
        });
    }

    @Test
    void testCreateStaticFactory() {
        List<Node> createdList = LinkedList.create(1, 2, 3);
        assertEquals(3, createdList.size());
        assertEquals(1, createdList.getByIndex(0).value);
    }

    @Test
    void testInsertHeadOnEmptyList() {
        list.insertHead(new Node(5));
        assertEquals(1, list.size());
        assertEquals(5, list.getByIndex(0).value);
    }

    @Test
    void testInsertHeadOnExistingList() {
        list.add(new Node(10));
        list.insertHead(new Node(5));
        
        assertEquals(2, list.size());
        assertEquals(5, list.getByIndex(0).value);
        assertEquals(10, list.getByIndex(1).value);
    }

    @Test
    void testGetByIndexOutOfBounds() {
        list.add(new Node(10));
        assertNull(list.getByIndex(5));
    }

    @Test
    void testGetByValue() {
        list.add(new Node(10));
        list.add(new Node(20));
        
        Node found = list.getByValue(20);
        assertNotNull(found);
        assertEquals(20, found.value);
        
        assertNull(list.getByValue(99));
    }

    @Test
    void testRemoveHead() {
        list.add(new Node(10));
        list.add(new Node(20));
        
        Node removed = list.removeHead();
        assertEquals(10, removed.value);
        assertNull(removed.next); // Ensure it is unlinked
        assertEquals(1, list.size());
        assertEquals(20, list.getByIndex(0).value);
    }

    @Test
    void testRemoveHeadOnEmptyList() {
        assertNull(list.removeHead());
    }

    @Test
    void testRemoveTailEmptyAndSingleNode() {
        // Case 1: Empty list
        assertNull(list.removeTail());
        
        // Case 2: One node
        list.add(new Node(10));
        Node removed = list.removeTail();
        assertEquals(10, removed.value);
        assertTrue(list.isEmpty());
    }

    @Test
    void testRemoveTailMultipleNodes() {
        list.add(new Node(10));
        list.add(new Node(20));
        list.add(new Node(30));
        
        Node removed = list.removeTail();
        assertEquals(30, removed.value);
        assertEquals(2, list.size());
        assertEquals(20, list.getByIndex(1).value);
    }

    @Test
    void testToStringLayout() {
        list.add(new Node(1));
        list.add(new Node(2));
        assertEquals("[1,2]", list.toString());
    }
}
