package linkedlist;


public class DoubleLinkedList implements List<DNode, DoubleLinkedList> {
    DNode head;
    DNode tail;

    public DoubleLinkedList() {
        this(null);
    }
    public DoubleLinkedList(int headValue) {
        this(new DNode(headValue));
    }
    public DoubleLinkedList(DNode headNode) {
        head = tail = headNode;
    }

    @Override  public DNode head() { return head;}
    @Override  public DNode tail() { return tail;}

    public static DoubleLinkedList make(int val, int... values) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(val);
        for (int value : values) {
            list.add(value);
        }
        return list;
    }

    @Override
    public void add(DNode dnode) {
        if (dnode == null) {
            throw new IllegalArgumentException("Cannot add a null value");
        }

        //clear up next and prev pointers of input to avoid any confusion
        dnode.prev = null;
        dnode.next = null;

        //Next
        if (head == null) {       // check if head is null
            head = tail = dnode;  // if so just set the head and tail as same as input
            return;               //return as the job for adding single node to an empty list is done
        }

        //Otherwise
        tail.next = dnode;       //just add the dnode to existing tail's next
        dnode.prev = tail;       //dnode prev should point back to the existing tail
        tail = dnode;            //tail should progress to the dnode now isnt it??
    }

    public static DoubleLinkedList merge(DoubleLinkedList  list1, DoubleLinkedList  list2) {
        if (list1 == null || list1.head == null) return list2;
        if (list2 == null || list2.head == null) return list1;
        var l1 = list1.head();
        var l2 = list2.head();

        DoubleLinkedList list = new DoubleLinkedList();

        while (l1 != null && l2 != null) {
            if (l1.value <= l2.value) {
                DNode saved = l1.next;
                list.add(l1);
                l1 = saved;
            } else {
                DNode saved = l2.next;
                list.add(l2);
                l2 = saved;
            }
        }
        while (l1 != null) {DNode saved = l1.next; list.add(l1); l1 = saved;}
        while (l2 != null) {DNode saved = l2.next; list.add(l2); l2 = saved;}
        return list;
    }

    @Override
    public void insertHead(DNode dnode) {
        if (head == null) {
            head = tail = dnode;
            return;
        }

        dnode.next = head;
        head.prev = dnode;
        head = dnode;
    }

    @Override
    public DNode getByIndex(int index) {
        DNode temp = head;
        for (int i = 0; i < index; i++) {
            if (temp == null) {
                return null;
            }
            temp = (DNode) temp.next;
        }
        return temp;
    }

    @Override
    public DNode getByValue(int value) {
        DNode temp = head;

        while (temp != null) {
            if (temp.value == value) {
                return temp;
            }
            temp =  temp.next;
        }

        return null;
    }

    @Override
    public int size() {

        int count = 0;
        DNode temp = head;

        while (temp != null) {
            count++;
            temp = (DNode) temp.next;
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public DNode removeHead() {

        if (head == null) {
            return null;
        }

        DNode removed = head;

        if (head == tail) {
            head = tail = null;
        } else {
            head = (DNode) head.next;
            head.prev = null;
            removed.next = null;
        }

        return removed;
    }

    public DNode removeTail() {
        if (tail == null) {
            return null;
        }

        DNode removed = tail;

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            removed.prev = null;
        }

        return removed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DNode temp = head;

        if (temp != null) {
            sb.append("[");
        }

        while (temp != null) {
            sb.append(temp.value).append(",");
            temp = (DNode) temp.next;
        }

        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
        }

        return sb.toString();
    }
    @Override
    public DoubleLinkedList createList() {
        return new DoubleLinkedList();
    }
    public DNode createNode(int value) { return new DNode(value); }
    //no----->n1----->n2----->n3----->n4
    //n0<-----n1<-----n2<-----n3<-----n4
    //h                               t
    //----------------------------------
    //t                               h
    //n0<-----n1<-----n2<-----n3<-----n4
    //no----->n1----->n2----->n3----->n4
    @Override
    public void reverse() {
        DNode current = head;
        while (current != null) {
            DNode next = current.next;  // save the next
            current.next = current.prev;// reset current next to its prev (so reversing)
            current.prev = next;        // reverse cur.prev with stored next
            current = next;             // move forward naturally
        }
        DNode temp = head;
        head = tail;
        tail = temp;
    }
    @Override
    public DNode findMidNode() {
        if (head == null) return null;
        DNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Override
    public DNode findNthFromLast(int n) {
        if (head == null || n < 0) return null;

        DNode slow = head, fast = head;
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
    public DNode findLoop() {
        if (head == null) return null;
        DNode slow = head, fast = head;
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
}