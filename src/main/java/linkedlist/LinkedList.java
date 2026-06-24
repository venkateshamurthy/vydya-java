package  linkedlist;


import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class LinkedList implements List<Node, LinkedList> {
    Node head;

    public LinkedList() {
        this(null);
    }

    public LinkedList(int headValue) {
        this(new Node(headValue));
    }

    public LinkedList(Node headNode) {
        head = headNode;
    }

    public Node head() {
        return head;
    }

    public static LinkedList make(int val, int... values) {
        LinkedList list = new LinkedList(val);
        //list.add(val);
        for (int value : values) {
            list.add(value);
        }
        return list;
    }

    @Override
    public void add(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        node.next = null; //always clean up this node's next since it is getting added to this linked list
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
        if (head == null) {
            head = node;
            node.next = null;//always clear links
            return;
        }
        node.next = head;  // just point nodes next to the current head
        head = node;       // Move back the current head to the node
    }

    /**
     * Get nth node from head where nth index is passed
     * @param index is the index of the node in the linked list from head
     * @return node at index.
     */
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
        while (temp != null) {
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
        return head == null; // size() == 0; is the worst way of computing empty ness
    }

    //n1-->n2-->n3-->n4
    //h
    //n1   n2-->n3-->n4
    //     h
    @Override
    public Node removeHead() {
        if (head == null) return null;
        Node temp = head; //Node* t = head
        head = head.next;
        temp.next = null; // t->next = null;
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
        //                       t.n->n5-->null
        Node t = head;
        while (t.next != null && t.next.next != null) { //as long as 3 or more nodes exist
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

    public  Node createNode(int value) { return new Node(value); }
    public LinkedList createList() {return new LinkedList();}

    /* n0----->n1----->n2----->n3----->n4
           c.      n
      <----n0<-----n1----->n2----->n3----->n4
           p       c       n
           n0<-----n1<-----n2----->n3----->n4
        .          p       c.      n
           n0<-----n1<-----n2<-----n3----->n4
        .                  p       c       n
           n0<-----n1<-----n2----->n3<-----n4.     null
        .                          p       c.      n
                                           p       c
         */
    @Override
    public void reverse() {
        Node prev = null;
        Node current = head;
        while (current != null) {
            Node next = current.next; // save the next
            current.next = prev;      // reverse current.next
            prev = current;           // advance prev
            current = next;           // advance current
        }
        head = prev;
    }

    @Override
    public Node findMidNode() {
        if (head == null) return null;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Override
    public Node findNthFromLast(int n) {
        if (head == null || n < 0) return null;

        Node slow = head, fast = head;
        int count = 0;

        while (count++ < n) {            // Until count reaches n check if list itself is upto n size
            if (fast.next == null) {     //if you find fast.next = null then list is falling short so return null
                return null;             //return null as there is no nth node from last
            }
            fast = fast.next;            //otherwise keep fast jumping once
        }

        while (fast.next != null) {      //Now keep both slow and fast jumping together
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    @Override
    public Node findLoop() {
        if (head == null) return null;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;     //fast: Jump twice
            slow = slow.next;          //slow: jump once

            if (slow == fast) {        //if they meet then loop is detected
                slow = head;           //reset slow to head

                while (slow != fast) { //Until slow meets fast loop through
                    slow = slow.next;  //both of them take just only one step
                    fast = fast.next;  //both of them take just only one step
                }
                return slow;           //at this point they are at the start of loop
            }
        }
        return null;                   // No loop detected hence null
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node temp = head;
        while (temp != null) {
            sb.append(temp.value).append(",");
            temp = temp.next;
        }
        if (sb.charAt(sb.length()-1)==',') sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }


}
