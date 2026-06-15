package  linkedlist;

public interface List<N> {
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
}

