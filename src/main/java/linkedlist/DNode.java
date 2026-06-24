package linkedlist;

public class DNode  implements INode<Integer, DNode> {
    final int value;
    DNode prev;
    DNode next;

    DNode(int value) {
        this.value = value;
        next = null;
        prev = null;
    }

    public Integer value() { return value;}
    public DNode   next()  { return next;}
    public String toString() {return  value + "";}
}
