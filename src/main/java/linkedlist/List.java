package  linkedlist;

public interface List<N> {
    N create(int value);

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
    default void add(int value) {add(create(value));}

    N head();
    default N tail(){
        throw new UnsupportedOperationException("Not supported tail operation.");
    };

    //Problems
    void reverse();
    N findMidNode();
    N findNthFromLast(int n);
    N findLoop();
}

