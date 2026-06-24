package linkedlist;

public interface INode<K, N extends INode<K, N>> {
    K value();
    N next();
    default N prev(){
        throw new UnsupportedOperationException("prev operation is not supported by default");
    };
}
