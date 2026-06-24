package linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void shouldCreateNodeEmptyList() {
        LinkedList list = new LinkedList();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertNull(list.head());
        assertEquals("[]", list.toString()); // reason why we are checking with [] is string builder adds a open-close brackets
    }

    @Test
    void shouldAddNodes() {
        LinkedList list = new LinkedList();

        list.add(new Node(1));
        list.add(new Node(2));
        list.add(new Node(3));

        assertEquals(3, list.size());
        assertEquals("[1,2,3]", list.toString());
    }

    @Test
    void shouldRejectNullNode() {
        LinkedList list = new LinkedList();

        assertThrows(
                IllegalArgumentException.class,
                () -> list.add(null)
        );
    }

    @Test
    void shouldInsertAtHead() {
        LinkedList list = new LinkedList();

        list.insertHead(new Node(2));
        list.insertHead(new Node(1));

        assertEquals("[1,2]", list.toString());
    }

    @Test
    void shouldGetByIndex() {
        LinkedList list =  LinkedList.make(10, 20, 30, 40);

        assertEquals(10, list.getByIndex(0).value);
        assertEquals(20, list.getByIndex(1).value);
        assertEquals(30, list.getByIndex(2).value);
        assertEquals(40, list.getByIndex(3).value);
    }

    @Test
    void shouldReturnNullForInvalidIndex() {
        LinkedList list =  LinkedList.make(10, 20);

        assertNull(list.getByIndex(5));
    }

    @Test
    void shouldGetByValue() {
        LinkedList list =  LinkedList.make(10, 20, 30);

        assertEquals(20, list.getByValue(20).value);
        assertNull(list.getByValue(99));
    }

    @Test
    void shouldRemoveHead() {
        LinkedList list = LinkedList.make(1, 2, 3);

        Node removed = list.removeHead();

        assertEquals(1, removed.value);
        assertNull(removed.next);
        assertEquals("[2,3]", list.toString());
    }

    @Test
    void shouldRemoveHeadFromSingleElementList() {
        LinkedList list =  new LinkedList();
        list.add(1);
        Node removed = list.removeHead();

        assertEquals(1, removed.value);
        assertTrue(list.isEmpty());
    }

    @Test
    void shouldReturnNullWhenRemovingHeadFromEmptyList() {
        LinkedList list = new LinkedList();

        assertNull(list.removeHead());
    }

    @Test
    void shouldRemoveTail() {
        LinkedList list =  LinkedList.make(1, 2, 3, 4);

        Node tail = list.removeTail();

        assertEquals(4, tail.value);
        assertEquals("[1,2,3]", list.toString());
    }

    @Test
    void shouldRemoveTailFromSingleElementList() {
        LinkedList list = new LinkedList();
        list.add(1);
        Node tail = list.removeTail();

        assertEquals(1, tail.value);
        assertTrue(list.isEmpty());
    }

    @Test
    void shouldReturnNullWhenRemovingTailFromEmptyList() {
        LinkedList list = new LinkedList();

        assertNull(list.removeTail());
    }

    @Test
    void shouldReverseList() {
        LinkedList list =  LinkedList.make(1, 2, 3, 4, 5);

        list.reverse();

        assertEquals("[5,4,3,2,1]", list.toString());
    }

    @Test
    void shouldReverseSingleNodeList() {
        LinkedList list = LinkedList.make(1);
        list.reverse();

        assertEquals("[1]", list.toString());
    }

    @Test
    void shouldFindMidNodeOddLength() {
        LinkedList list =  LinkedList.make(1, 2, 3, 4, 5);

        assertEquals(3, list.findMidNode().value);
    }

    @Test
    void shouldFindMidNodeEvenLength() {
        LinkedList list =  LinkedList.make(1, 2, 3, 4, 5, 6);

        assertEquals(4, list.findMidNode().value);
    }

    @Test
    void shouldFindNthFromLast() {
        LinkedList list =  LinkedList.make(1, 2, 3, 4, 5);

        assertEquals(5, list.findNthFromLast(0).value);
        assertEquals(4, list.findNthFromLast(1).value);
        assertEquals(3, list.findNthFromLast(2).value);
    }

    @Test
    void shouldReturnHeadWhenNthEqualsLastIndex() {
        LinkedList list =  LinkedList.make(1, 2, 3, 4, 5);

        assertEquals(1, list.findNthFromLast(4).value);
    }

    @Test
    void shouldReturnNullWhenNthExceedsLength() {
        LinkedList list =  LinkedList.make(1, 2, 3);

        assertNull(list.findNthFromLast(3));
        assertNull(list.findNthFromLast(10));
    }

    @Test
    void shouldReturnNullForNegativeNth() {
        LinkedList list =  LinkedList.make(1, 2, 3);

        assertNull(list.findNthFromLast(-1));
    }

    @Test
    void shouldMergeSortedLists() {
        LinkedList l1 = LinkedList.make(1,3,5);
        LinkedList l2 = LinkedList.make(2,4,6);
        LinkedList merged = List.merge(l1, l2);
        assertEquals("[1,2,3,4,5,6]", merged.toString());
        merged = List.merge(LinkedList.make(1),  LinkedList.make(2));
        // Single node lists merging
        assertEquals("[1,2]", merged.toString());
    }

    @Test
    void mergeNonNullAndNullLists() {
        LinkedList l1 = LinkedList.make(1,3,5);
        LinkedList l2 = LinkedList.make(2,4,6);

        // this is checking for both linkedlists itself being null
        assertEquals("[1,3,5]", List.merge(l1, null).toString());
        assertEquals("[2,4,6]", List.merge(null, l2).toString());

        // this is checking for both list are not null; however one of the list's head is null
        assertEquals("[1,3,5]", List.merge(l1, new LinkedList()).toString());
        assertEquals("[2,4,6]", List.merge(new LinkedList(), l2).toString());
    }

    @Test
    void shouldDetectNoLoop() {
        LinkedList list =  LinkedList.make(1, 2, 3, 4);

        assertNull(list.findLoop());
    }

    @Test
    void shouldDetectLoopAndReturnLoopStart() {
        LinkedList list = new LinkedList();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        list.add(n5);

        n5.next = n3;

        Node loopStart = list.findLoop();

        assertNotNull(loopStart);
        assertSame(n3, loopStart);
        assertEquals(3, loopStart.value);
    }

    @Test
    void shouldDetectLoopAtHead() {
        LinkedList list = new LinkedList();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        list.add(n1);
        list.add(n2);
        list.add(n3);

        n3.next = n1;

        assertSame(n1, list.findLoop());
    }
}