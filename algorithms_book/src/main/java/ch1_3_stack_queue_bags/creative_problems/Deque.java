package ch1_3_stack_queue_bags.creative_problems;
import java.util.Iterator;

/**
 * a deque class, using two-point linked list
 * problem set 1.3.33 page.169
 * think: what's the difference between the standard
 * jdk version?
 * @author alexsun
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    private int N = 0;
    private Node left;
    private Node right;

    class Node{
      Item item;
      Node next;
      Node previous;
      public Node(Item item){
          this.item = item;
          this.next = null;
          this.previous = null;
      }
    }

    public Deque(){
        this.right = null;
        this.left = this.right;
    }

    public boolean isEmpty(){
        return this.left == null;
    }

    public void pushLeft(Item item){
        if(isEmpty()){
            left = right = new Node(item);
        }
        else {
            Node node = new Node(item);
            node.next = left;
            left.previous = node;
            this.left = node;
        }
        N++;
    }

    public void pushRight(Item item){
        if(isEmpty()){
            left = right = new Node(item);
        }
        else {
            Node node = new Node(item);
            this.right.next = node;
            node.previous = this.right;
            this.right = node;
        }
        N++;
    }

    public Item popLeft(){
        if(!isEmpty()) {
            Node res = left;
            left = left.next;
            N--;
            return res.item;
        }
        else{
            System.err.println("Already Empty");
            throw new NullPointerException();
        }
    }

    public Item popRight(){
        if(!isEmpty()) {
            Node res = right;
            right = res.previous;
            N--;
            return res.item;
        }
        else{
            System.err.println("Already Empty");
            throw new NullPointerException();
        }
    }

    public int size(){
        return N;
    }

    public Iterator<Item> iterator() {
        return new mIterator();
    }

    private class mIterator implements Iterator<Item> {
        private Node current = left;
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Node tmp = current;
            current = current.next;
            return tmp.item;
        }

        public void remove() {

        }
    }

}
