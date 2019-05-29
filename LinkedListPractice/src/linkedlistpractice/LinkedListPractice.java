package linkedlistpractice;

public class LinkedListPractice {

    private Node<Integer> head;

    /*
    * pre:  none
    * post: val is added as the first element in the list
    *       the size of the list increased by one
     */
    public void addToFront(int val) {
        Node<Integer> temp = new Node(val);
        temp.setNext(this.head);
        this.head = temp;
    }

    /*
    * pre:  size is at least one
    * post: val is added as the second element in the list
    *       the size of the list increased by one
     */
    public void addAtSecondFromFront(int val) {
        Node<Integer> temp = new Node(val);
        temp.setNext(this.head.getNext());
        this.head.setNext(temp);
    }

    /*
    * pre: size is at least one
    * post: the first element is the list is replaced by val
    *       the size of the list is unchanged
     */
    public void replaceFront(int val) {
        Node<Integer> temp = new Node(val);
        temp.setNext(this.head.getNext());
        this.head = temp;
    }

    /*
    * pre: size is at least one
    * post: the last element is the list is replaced by val
    *       the size of the list is unchanged
     */
    public void replaceLast(int val) {
        Node<Integer> temp = new Node(val);
        Node<Integer> searchingNode = this.head;

        if (searchingNode.getNext() == null) {
            this.head = temp;
        } else {
            while (searchingNode.getNext().getNext() != null) {
                searchingNode = searchingNode.getNext();
            }
            searchingNode.setNext(temp);
        }
    }

    /*
    * pre:  none
    * post: val is added as the last element in the list
    *       the size of the list increased by one
     */
    public void addToBack(int val) {
        Node<Integer> temp = new Node(val);
        Node<Integer> searchingNode = this.head;
        if (searchingNode == null) {
            this.head = temp;
        } else {
            while (searchingNode.getNext() != null) {
                searchingNode = searchingNode.getNext();
            }
            searchingNode.setNext(temp);
        }
    }

    /*
    * pre:  size is at least one
    * post: the first element is removed. The size of the list decreased by one
     */
    public void removeFirst() {
        this.head = this.head.getNext();
    }

    /*
    * pre:  size is at least one
    * post: the last element is removed. The size of the list decreased by one
     */
    public void removeLast() {
        Node<Integer> searchingNode =this.head;

        if (searchingNode.getNext() == null) {
            this.head = null;
        } else {
            while (searchingNode.getNext().getNext() != null) {
                searchingNode = searchingNode.getNext();
            }
            searchingNode.setNext(null);
        }
    }

    /*
    * pre:  none
    * post: returns the number of elements in the list    
     */
    public int size() {
        int size = 0;
        Node<Integer> searchingNode = head;
        while (searchingNode != null) {
            searchingNode = searchingNode.getNext();
            size++;
        }
        return size;
    }

    /*
    * pre:  none
    * post: the list is printed on the screen
     */
    public void printList() {
        if (this.head == null) {
            System.out.println("[EMPTY LIST]");
        } else {
            Node temp = this.head;
            while (temp != null) {
                System.out.print("[" + temp.getValue() + "]");
                temp = temp.getNext();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // add more code here to test your methods as you code them
        // don't delete your tests! I'll be marking this as well
        
        /*ADD TO FRONT*/
        
        LinkedListPractice l = new LinkedListPractice();
        l.addToFront(15);
        System.out.println("TEST: ADD TO FRONT");
        System.out.println("Expected: [15]"); // 1 thing in list
        System.out.print("Actual: ");
        l.printList();
        l.addToFront(10);
        System.out.println("TEST: ADD TO FRONT");
        System.out.println("Expected: [10][15]"); // 2 things in list
        System.out.print("Actual: ");
        l.printList();
        l.addToFront(5);
        System.out.println("TEST: ADD TO FRONT");
        System.out.println("Expected: [5][10][15]"); // many things in list
        System.out.print("Actual: ");
        l.printList();

        /*ADD AT SECOND FROM FRONT*/
        
        l = new LinkedListPractice();
        l.addToFront(5);
        l.addAtSecondFromFront(15);
        System.out.println("TEST: ADD AT SECOND FROM FRONT");
        System.out.println("Expected: [5][15]"); // >1 thing in list
        System.out.print("Actual: ");
        l.printList();
        l.addAtSecondFromFront(10);
        System.out.println("TEST: ADD AT SECOND FROM FRONT");
        System.out.println("Expected: [5][10][15]"); // 1 thing in list
        System.out.print("Actual: ");
        l.printList();

        /*REPLACE FRONT*/
        
        //case with >1 items
        l = new LinkedListPractice();
        l.addToFront(0);
        l.addToFront(5);
        l.replaceFront(100);
        System.out.println("TEST: REPLACE FRONT");
        System.out.println("Expected: [100][0]");
        System.out.print("Actual: ");
        l.printList();
        
        //case with 1 item
        l = new LinkedListPractice();
        l.addToFront(0);
        l.replaceFront(100);
        System.out.println("TEST: REPLACE FRONT");
        System.out.println("Expected: [100]");
        System.out.print("Actual: ");
        l.printList();

        /*REPLACE LAST*/
        
        //case with many items
        l = new LinkedListPractice();
        l.addToFront(0);
        l.addToFront(5);
        l.addToFront(10);
        l.replaceLast(100);
        System.out.println("TEST: REPLACE LAST");
        System.out.println("Expected: [10][5][100]");
        System.out.print("Actual: ");
        l.printList();

        //case with 1 item
        l = new LinkedListPractice();
        l.addToFront(0);
        l.replaceLast(100);
        System.out.println("TEST: REPLACE LAST");
        System.out.println("Expected: [100]");
        System.out.print("Actual: ");
        l.printList();

        /*ADD TO BACK*/
        
        //case with many items
        l = new LinkedListPractice();
        l.addToFront(0);
        l.addToFront(5);
        l.addToFront(10);
        l.addToBack(100);
        System.out.println("TEST: ADD TO BACK");
        System.out.println("Expected: [10][5][0][100]");
        System.out.print("Actual: ");
        l.printList();

        //case with no items
        l = new LinkedListPractice();
        l.addToBack(100);
        System.out.println("TEST: ADD TO BACK");
        System.out.println("Expected: [100]");
        System.out.print("Actual: ");
        l.printList();

        //case with 1 item
        l = new LinkedListPractice();
        l.addToFront(0);
        l.addToBack(100);
        System.out.println("TEST: ADD TO BACK");
        System.out.println("Expected: [0][100]");
        System.out.print("Actual: ");
        l.printList();
        
        /*REMOVE FIRST*/
        
        //case with many items
        l = new LinkedListPractice();
        l.addToFront(5);
        l.addToFront(10);
        l.addToFront(15);
        l.removeFirst();
        System.out.println("TEST: REMOVE FIRST");
        System.out.println("Expected: [10][5]");
        System.out.print("Actual: ");
        l.printList();
        
        //case with 1 item
        l = new LinkedListPractice();
        l.addToFront(5);
        l.removeFirst();
        System.out.println("TEST: REMOVE FIRST");
        System.out.println("Expected: [EMPTY LIST]");
        System.out.print("Actual: ");
        l.printList();
        
        /*REMOVE LAST*/
        
        //case with many items
        l = new LinkedListPractice();
        l.addToFront(5);
        l.addToFront(10);
        l.addToFront(15);
        l.removeLast();
        System.out.println("TEST: REMOVE LAST");
        System.out.println("Expected: [15][10]");
        System.out.print("Actual: ");
        l.printList();
        
        //case with 1 item
        l = new LinkedListPractice();
        l.addToFront(5);
        l.removeLast();
        System.out.println("TEST: REMOVE LAST");
        System.out.println("Expected: [EMPTY LIST]");
        System.out.print("Actual: ");
        l.printList();
        
        /*SIZE*/
        
        //case with many items
        l = new LinkedListPractice();
        l.addToFront(15);
        l.addToFront(10);
        l.addToFront(5);
        System.out.println("TEST: SIZE");
        System.out.println("Expected: 3");
        System.out.print("Actual: ");
        System.out.println(l.size());
        
        //case with 1 item
        l = new LinkedListPractice();
        l.addToFront(15);
        System.out.println("TEST: SIZE");
        System.out.println("Expected: 1");
        System.out.print("Actual: ");
        System.out.println(l.size());
        
        //case with no items
        l = new LinkedListPractice();
        System.out.println("TEST: SIZE");
        System.out.println("Expected: 0");
        System.out.print("Actual: ");
        System.out.println(l.size());
    }

}
