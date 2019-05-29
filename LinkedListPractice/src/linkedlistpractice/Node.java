
package linkedlistpractice;


public class Node<T> {

    private T value;
    private Node next;
    
    public Node(T value, Node next) {
        this.value = value;
        this.next = next;
    }
    
    public Node(T value) {
        this(value, null);
    }
    
    public T getValue() {
        return this.value;
    }
    
    public Node getNext() {
        return this.next;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }
}
