package  linkedlist;

import java.util.function.UnaryOperator;

public interface List<N, L extends List<N, L>> {
    L createList();
    N createNode(int value);

    //Get at certain index
    N getByIndex(int index);
    N getByValue(int value);

    //Size and Empty
    int size();
    boolean isEmpty();

    // Remove head and tail
    N removeHead();
    N removeTail();

    //Add at head and tail
    void insertHead(N node);
    void add (N node);
    default void add(int value) {add(createNode(value));}

    // head and tail methods
    N head();
    default N tail(){
        throw new UnsupportedOperationException("Not supported tail operation.");
    };

    //Problems
    void reverse();
    N findMidNode();
    N findNthFromLast(int n);
    N findLoop();

    static <T extends INode<Integer, T>, L extends List<T, L>> L merge(L list1, L list2) {
        if (list1 == null || list1.head() == null) return list2;
        if (list2 == null || list2.head() == null) return list1;
        var l1 = list1.head();
        var l2 = list2.head();

        L list = list1.createList();

        // A common code block to add
        final UnaryOperator<T> nodeAdder = n -> {
            final T saved = n.next(); // always store the next one in the list
            list.add(n);              // next add this node and clear up the links
            return saved;             // return the saved
        };

        while (l1 != null && l2 != null) {
            if (l1.value() <= l2.value()) {
                /*final T s = l1.next();
                list.add(l1);
                l1 = s;*/
                l1 = nodeAdder.apply(l1);
            } else {
                /*final T s = l2.next();
                list.add(l2);
                l2 = s;*/
                l2 = nodeAdder.apply(l2);
            }
        }

        while(l1 != null) {l1 = nodeAdder.apply(l1);}
        while(l2 != null) {l2 = nodeAdder.apply(l2);}
        return list;
    }
}

