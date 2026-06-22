package linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

    @Test
    void shouldCreateEmptyList() {
        DoubleLinkedList list = new DoubleLinkedList();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertNull(list.head());
        assertNull(list.tail());
        assertEquals("", list.toString());
    }

    @Test
    void shouldAddNodes() {
        DoubleLinkedList list = DoubleLinkedList.make(1,2,3);
                /*new DoubleLinkedList();

        list.add(new DNode(1));
        list.add(new DNode(2));
        list.add(new DNode(3));*/

        assertEquals("[1,2,3]", list.toString());
        assertEquals(3, list.size());

        assertEquals(1, list.head().value);
        assertEquals(3, list.tail().value);

        assertNull(list.head().prev);
        assertNull(list.tail().next);
    }

    @Test
    void shouldRejectNullNode() {
        DoubleLinkedList list = new DoubleLinkedList();

        assertThrows(
                IllegalArgumentException.class,
                () -> list.add(null)
        );
    }

    @Test
    void shouldInsertHead() {
        DoubleLinkedList list = new DoubleLinkedList();
        //DOnt use make as youe are adding this in reverse way
        list.insertHead(new DNode(2));
        list.insertHead(new DNode(1));

        assertEquals("[1,2]", list.toString());

        assertEquals(1, list.head().value);
        assertEquals(2, list.tail().value);

        assertNull(list.head().prev);
        assertSame(list.head(), list.tail().prev);
    }

    @Test
    void shouldGetByIndex() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(10));
        list.add(new DNode(20));
        list.add(new DNode(30));

        assertEquals(10, list.getByIndex(0).value);
        assertEquals(20, list.getByIndex(1).value);
        assertEquals(30, list.getByIndex(2).value);
    }

    @Test
    void shouldReturnNullForInvalidIndex() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(10));

        assertNull(list.getByIndex(10));
    }

    @Test
    void shouldGetByValue() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(10));
        list.add(new DNode(20));
        list.add(new DNode(30));

        assertEquals(20, list.getByValue(20).value);
        assertNull(list.getByValue(99));
    }

    @Test
    void shouldRemoveHead() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(1));
        list.add(new DNode(2));
        list.add(new DNode(3));

        DNode removed = list.removeHead();

        assertEquals(1, removed.value);
        assertEquals("[2,3]", list.toString());

        assertNull(list.head().prev);
    }

    @Test
    void shouldRemoveOnlyHead() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(1));

        DNode removed = list.removeHead();

        assertEquals(1, removed.value);
        assertTrue(list.isEmpty());
        assertNull(list.head());
        assertNull(list.tail());
    }

    @Test
    void shouldRemoveTail() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(1));
        list.add(new DNode(2));
        list.add(new DNode(3));

        DNode removed = list.removeTail();

        assertEquals(3, removed.value);
        assertEquals("[1,2]", list.toString());

        assertEquals(2, list.tail().value);
        assertNull(list.tail().next);
    }

    @Test
    void shouldRemoveOnlyTail() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(1));

        DNode removed = list.removeTail();

        assertEquals(1, removed.value);
        assertTrue(list.isEmpty());
        assertNull(list.head());
        assertNull(list.tail());
    }

    @Test
    void shouldReverseList() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(1));
        list.add(new DNode(2));
        list.add(new DNode(3));
        list.add(new DNode(4));
        list.add(new DNode(5));

        list.reverse();

        assertEquals("[5,4,3,2,1]", list.toString());

        assertEquals(5, list.head().value);
        assertEquals(1, list.tail().value);

        assertNull(list.head().prev);
        assertNull(list.tail().next);
    }

    @Test
    void shouldReverseSingleNodeList() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(1));

        list.reverse();

        assertEquals("[1]", list.toString());
        assertEquals(1, list.head().value);
        assertEquals(1, list.tail().value);
    }

    @Test
    void shouldFindMidNodeOddLength() {
        DoubleLinkedList list = new DoubleLinkedList();

        for (int i = 1; i <= 5; i++) {
            list.add(new DNode(i));
        }

        assertEquals(3, list.findMidNode().value);
    }

    @Test
    void shouldFindMidNodeEvenLength() {
        DoubleLinkedList list = new DoubleLinkedList();

        for (int i = 1; i <= 6; i++) {
            list.add(new DNode(i));
        }

        assertEquals(4, list.findMidNode().value);
    }

    @Test
    void shouldFindNthFromLast() {
        DoubleLinkedList list = new DoubleLinkedList();

        for (int i = 1; i <= 5; i++) {
            list.add(new DNode(i));
        }

        assertEquals(5, list.findNthFromLast(0).value);
        assertEquals(4, list.findNthFromLast(1).value);
        assertEquals(3, list.findNthFromLast(2).value);
        assertEquals(1, list.findNthFromLast(4).value);
    }

    @Test
    void shouldReturnNullWhenNthExceedsLength() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(1));
        list.add(new DNode(2));

        assertNull(list.findNthFromLast(2));
        assertNull(list.findNthFromLast(10));
    }

    @Test
    void shouldMergeSortedLists() {
        DoubleLinkedList l1 = DoubleLinkedList.make(1,3,5);
        DoubleLinkedList l2 = DoubleLinkedList.make(2,4,6);
        List<DNode> merged = DoubleLinkedList.merge(l1, l2);
        assertEquals("[1,2,3,4,5,6]", merged.toString());
    }

    @Test
    void shouldDetectNoLoop() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(1));
        list.add(new DNode(2));
        list.add(new DNode(3));

        assertNull(list.findLoop());
    }

    @Test
    void shouldDetectLoopStart() {
        DoubleLinkedList list = new DoubleLinkedList();

        DNode n1 = new DNode(1);
        DNode n2 = new DNode(2);
        DNode n3 = new DNode(3);
        DNode n4 = new DNode(4);
        DNode n5 = new DNode(5);

        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        list.add(n5);

        n5.next = n3;

        DNode loopStart = list.findLoop();

        assertSame(n3, loopStart);
    }

    @Test
    void shouldMaintainPrevLinks() {
        DoubleLinkedList list = new DoubleLinkedList();

        DNode n1 = new DNode(1);
        DNode n2 = new DNode(2);
        DNode n3 = new DNode(3);

        list.add(n1);
        list.add(n2);
        list.add(n3);

        assertNull(n1.prev);
        assertSame(n1, n2.prev);
        assertSame(n2, n3.prev);
    }

    @Test
    void shouldMaintainPrevLinksAfterReverse() {
        DoubleLinkedList list = new DoubleLinkedList();

        list.add(new DNode(1));
        list.add(new DNode(2));
        list.add(new DNode(3));

        list.reverse();

        DNode h = list.head();

        assertEquals(3, h.value);
        assertNull(h.prev);

        assertEquals(2, h.next.value);
        assertSame(h, h.next.prev);

        assertEquals(1, h.next.next.value);
        assertSame(h.next, h.next.next.prev);
    }
}