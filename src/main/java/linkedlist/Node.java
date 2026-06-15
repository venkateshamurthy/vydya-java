package linkedlist;

public class Node {
    final int value;
    Node next;

    Node(int value) {
        this.value = value;
        next = null;
    }

    @Override
    public String toString() {
        return  value + "";
    }
}
