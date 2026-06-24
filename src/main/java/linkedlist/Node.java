package linkedlist;

public class Node implements INode<Integer, Node> {
    final int value;
    Node next;

    Node(int value) {
        this.value = value;
        next = null;
    }

    public String toString() {
        return value + "";
    }
    public Node next()       { return next;}
    public Integer value()   { return value;}
}
