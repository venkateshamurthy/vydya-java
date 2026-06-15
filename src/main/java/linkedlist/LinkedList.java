package  linkedlist;


public class LinkedList implements List<Node> {
    Node head;

    public LinkedList() {
        head = null;
    }

    public static List create(int... values) {
        List list = new LinkedList();
        for (int value : values) {
            list.add(new Node(value));
        }
        return list;
    }

    @Override
    public void add(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        if (head == null) {
            head = node;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    @Override
    public void insertHead(Node node) {
        Node temp = head;
        if (temp == null) {
            head = node;
        } else {
            node.next = temp;
            head = node;
        }
    }


    @Override
    public Node getByIndex(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            if (temp == null) return null;
            temp = temp.next;
        }
        return temp;
    }

    public Node getByValue(int value){
        Node temp = head;
        while(temp != null){
            if (temp.value==value) return temp;
            temp = temp.next;
        }
        return null;
    }


    @Override
    public int size() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Node removeHead() {
        if (head == null) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        return temp;
    }

    public Node removeTail() {
        // h = null
        if (head == null) {
            return null; // empty list
        }

        if (head.next == null) {
            Node last = head; // just only one node
            head = null;      // so nullify the list
            return last;      // return the stored last node
        }
        // head-->n1-->n2-->n3-->n4-->n5-->null
        //                       t.n->n--->n = null
        Node t = head;
        while (t.next.next != null) { //as long as 3 or more nodes exist
            t = t.next;
        }
        // head-->n1-->n2-->n3-->n4-->n5-->null
        //                       t.n->n5 ==> tail isnt it?
        //                       t.n->null. // desired result would be to remove n5
        // head-->n1-->n2-->n3-->n4-->null. // meaning; n4 should become the new tail
        Node tail = t.next;    // the tail most node store it to last
        t.next = null;         // unlink tail - or remove the last node by nulling previous nodes next
        return tail;           // return the stored last node
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        boolean exists = temp != null;
        if (exists) {sb.append("[");}
        while (temp != null) {
            sb.append(temp.value).append(",");
            temp = temp.next;
        }
        sb.deleteCharAt(sb.length()-1);
        if (exists) {sb.append("]");}
        return sb.toString();
    }
}
