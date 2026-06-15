package linkedlist;

public class DoubleLinkedList implements List<DNode> {
    DNode head;
    DNode tail;

    @Override
    public void add(DNode dnode) {
        if (dnode == null) {
            throw new IllegalArgumentException("Cannot add a null value");
        }
        if (head == null) {
            head = tail = dnode;
            return;
        }

        tail.next = dnode;
        dnode.prev = tail;
        tail = dnode;
    }

    @Override
    public void insertHead(DNode dnode) {
        if (head == null) {
            head = tail = dnode;
            return;
        }

        dnode.next = head;
        head.prev = dnode;
        head = dnode;
    }

    @Override
    public DNode getByIndex(int index) {
        DNode temp = head;
        for (int i = 0; i < index; i++) {
            if (temp == null) {
                return null;
            }
            temp = (DNode) temp.next;
        }
        return temp;
    }

    @Override
    public DNode getByValue(int value) {
        DNode temp = head;

        while (temp != null) {
            if (temp.value == value) {
                return temp;
            }
            temp =  temp.next;
        }

        return null;
    }

    @Override
    public int size() {

        int count = 0;
        DNode temp = head;

        while (temp != null) {
            count++;
            temp = (DNode) temp.next;
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public DNode removeHead() {

        if (head == null) {
            return null;
        }

        DNode removed = head;

        if (head == tail) {
            head = tail = null;
        } else {
            head = (DNode) head.next;
            head.prev = null;
            removed.next = null;
        }

        return removed;
    }

    public DNode removeTail() {
        if (tail == null) {
            return null;
        }

        DNode removed = tail;

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            removed.prev = null;
        }

        return removed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DNode temp = head;

        if (temp != null) {
            sb.append("[");
        }

        while (temp != null) {
            sb.append(temp.value).append(",");
            temp = (DNode) temp.next;
        }

        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
        }

        return sb.toString();
    }
}