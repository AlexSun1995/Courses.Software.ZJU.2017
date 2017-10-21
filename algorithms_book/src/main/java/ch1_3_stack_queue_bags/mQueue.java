package ch1_3_stack_queue_bags;

import java.util.Iterator;

public class mQueue<Item> implements mCollection, Iterable<Item> {
    protected int N = 0;
    protected Node first = null;

    protected class Node {
        public Item item;
        public Node next;

        Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    public mQueue enqueue(Item item) {
        if (first == null) {
            first = new Node(item);
        } else {
            Node last = first;
            while(last.next != null){
                last = last.next;
            }
            last.next = new Node(item);
        }
        N++;
        return this;
    }

    public Item dequeue() {
        if (first == null) {
            System.err.println("Already Empty!");
            // throw new NullPointerException();
            return null;
        } else {
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<Item> iterator() {
        return new mIterator();
    }

    protected class mIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current!=null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {}
    }
}
