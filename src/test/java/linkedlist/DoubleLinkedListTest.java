package linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

    private DoubleLinkedList list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList();
    }

    @Test
    void testIsEmptyOnCreation() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testAddSingleNode() {
        list.add(new DNode(10));

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());

        DNode node = list.getByIndex(0);

        assertEquals(10, node.value);
        assertNull(node.prev);
        assertNull(node.next);
    }

    @Test
    void testAddMultipleNodes() {

        list.add(new DNode(10));
        list.add(new DNode(20));
        list.add(new DNode(30));

        assertEquals(3, list.size());

        DNode n1 = list.getByIndex(0);
        DNode n2 = list.getByIndex(1);
        DNode n3 = list.getByIndex(2);

        assertEquals(10, n1.value);
        assertEquals(20, n2.value);
        assertEquals(30, n3.value);

        assertNull(n1.prev);
        assertEquals(n2, n1.next);

        assertEquals(n1, n2.prev);
        assertEquals(n3, n2.next);

        assertEquals(n2, n3.prev);
        assertNull(n3.next);
    }

    @Test
    void testInsertHeadEmptyList() {

        list.insertHead(new DNode(5));

        assertEquals(1, list.size());
        assertEquals(5, list.getByIndex(0).value);
    }

    @Test
    void testInsertHeadExistingList() {

        list.add(new DNode(20));
        list.add(new DNode(30));

        list.insertHead(new DNode(10));

        DNode head = list.getByIndex(0);
        DNode second = list.getByIndex(1);

        assertEquals(10, head.value);
        assertNull(head.prev);

        assertEquals(20, second.value);
        assertEquals(head, second.prev);
    }

    @Test
    void testGetByIndex() {

        list.add(new DNode(10));
        list.add(new DNode(20));
        list.add(new DNode(30));

        assertEquals(10, list.getByIndex(0).value);
        assertEquals(20, list.getByIndex(1).value);
        assertEquals(30, list.getByIndex(2).value);
    }

    @Test
    void testGetByIndexOutOfBounds() {

        list.add(new DNode(10));

        assertNull(list.getByIndex(5));
        assertNull(list.getByIndex(100));
    }

    @Test
    void testGetByValue() {

        list.add(new DNode(10));
        list.add(new DNode(20));
        list.add(new DNode(30));

        DNode found = list.getByValue(20);

        assertNotNull(found);
        assertEquals(20, found.value);

        assertNull(list.getByValue(999));
    }

    @Test
    void testRemoveHead() {

        list.add(new DNode(10));
        list.add(new DNode(20));
        list.add(new DNode(30));

        DNode removed = list.removeHead();

        assertEquals(10, removed.value);

        DNode newHead = list.getByIndex(0);

        assertEquals(20, newHead.value);
        assertNull(newHead.prev);

        assertEquals(2, list.size());
    }

    @Test
    void testRemoveHeadSingleNode() {

        list.add(new DNode(10));

        DNode removed = list.removeHead();

        assertEquals(10, removed.value);
        assertTrue(list.isEmpty());
    }

    @Test
    void testRemoveHeadEmptyList() {
        assertNull(list.removeHead());
    }

    @Test
    void testRemoveTailEmptyList() {
        assertNull(list.removeTail());
    }

    @Test
    void testRemoveTailSingleNode() {

        list.add(new DNode(10));

        DNode removed = list.removeTail();

        assertEquals(10, removed.value);
        assertTrue(list.isEmpty());
    }

    @Test
    void testRemoveTailMultipleNodes() {

        list.add(new DNode(10));
        list.add(new DNode(20));
        list.add(new DNode(30));

        DNode removed = list.removeTail();

        assertEquals(30, removed.value);

        assertEquals(2, list.size());

        DNode tail = list.getByIndex(1);

        assertEquals(20, tail.value);
        assertNull(tail.next);
    }

    @Test
    void testBackwardTraversal() {

        list.add(new DNode(10));
        list.add(new DNode(20));
        list.add(new DNode(30));

        DNode tail = list.getByValue(30);

        assertEquals(30, tail.value);
        assertEquals(20, tail.prev.value);
        assertEquals(10, tail.prev.prev.value);
        assertNull(tail.prev.prev.prev);
    }

    @Test
    void testToString() {

        list.add(new DNode(1));
        list.add(new DNode(2));
        list.add(new DNode(3));

        assertEquals("[1,2,3]", list.toString());
    }

    @Test
    void testAddNullThrowsException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> list.add(null)
        );
    }
}