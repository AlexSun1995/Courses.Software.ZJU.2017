package ch1_3_stack_queue_bags;

import java.util.Iterator;

public class mStack<Item> implements mCollection,Iterable<Item> {
    private Node first;
    private int N = 0;
    class Node{
        Item item;
        Node next;
    }

    public mStack(){ }

    public void push(Item item){
        Node older = first;
        first = new Node();
        first.item = item;
        first.next = older;
        N++;
    }

    public Item pop(){
        if(isEmpty()) {
            System.out.println("Already Empty!");
            return null;
        }else{
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
        return new mIterator<Item>();
    }

    private class mIterator<Item> implements Iterator<Item> {
        Node current = first;
        public boolean hasNext() {
            return current!=null;
        }

        public Item next() {
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }

        public void remove() {}
    }
}
