package tmp;

/**
 * this is a Stack realization using Java Generic
 * Data in Stack are stored in a List data structure which
 * implemented by a class named Node.  
 * @author Sun Cun
 * @param <T>
 */


public class Stack<T>{
	class Node<T>{
		private Node<T> next = null;
		private T element;
		public Node(T ele) {
			this.element = ele;
		}
		
		public T getValue() {
			return this.element;
		}
		
		public Node<T> getNext() {
			return this.next;
		}
		
		public Node<T> add(T ele) {
			Node<T> head;
			head = this;
			Node<T> tmp = head.getNext(); 
			head.next = new Node<T>(ele);
			head.next.next = tmp;
			return this;
		}
		
		public boolean Empty() {
			if(this == null || this.next == null) {
				return true;
			}
			else return false;
		}
	    
	}
	
	private Node<T> linkedList = null;
	public Stack(){
		this.linkedList = new Node<T>(null);
	}
	
	public T top(){
		if(linkedList.Empty()) {
			System.out.println("Stack Empty! Can Not get top()");
			return null;
		}
		return linkedList.getNext().getValue();
	}
	
	public T pop() {
		if(linkedList.Empty()) {
			System.out.println("Stack Empty! Pop Failed!");
			return null;
		}
		Node<T>tmp = linkedList.getNext();
		linkedList.next = tmp.getNext();
		return tmp.getValue();
	}
	
	public void push(T elem) {
		linkedList.add(elem);
	}
	
	public void clear(){
		this.linkedList = new Node<T>(null);
		System.out.println("Stack Reseted!");
	}
	
	public boolean isEmpty() {
		if(this.getSize() == 0)
			return true;
		else 
			return false;
	}
	
	public int getSize() {
		int n = 0;
		Node<T> tmp = linkedList;
		if(tmp == null || tmp.next == null) {
			return 0;
		}
		tmp = tmp.getNext();
		while(tmp != null) {
			tmp = tmp.getNext();
			n++;
		}
		return n;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<String> test = new Stack<>();
		test.push("hello");
		test.push("world");
		test.push("hello");
		test.push("zju");
		test.push("cst");
		while(!test.isEmpty())
			System.out.println(test.pop());
		// System.out.println(test.top());
		// test.pop();
		// System.out.println(test.top());
		// System.out.println(test.getSize());
		// test.clear();
		// System.out.println(test.getSize());
	}

}
