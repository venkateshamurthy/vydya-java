package linkedlist;

public class DNode  {
    final int value;
    DNode prev;
    DNode next;

    DNode(int value) {
        this.value = value;
        next = null;
        prev = null;
    }

    @Override
    public String toString() {
        return  value + "";
    }
}
