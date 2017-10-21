import ch1_3_stack_queue_bags.creative_problems.Deque;
import ch1_3_stack_queue_bags.creative_problems.GeneralizedQueue;
import ch1_3_stack_queue_bags.creative_problems.RandomQueue;
import ch1_3_stack_queue_bags.mQueue;
import ch1_3_stack_queue_bags.mStack;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ch1_3Test {
    private mStack<Integer> stack1;
    private mStack<Integer> stack2;
    private mQueue<Integer> queue1;
    @Before
    public void init(){
        stack1 = new mStack<Integer>();
        stack2 = new mStack<Integer>();
        queue1 = new mQueue<Integer>();
        for(int i=1;i<=5;i++){
            stack1.push(new Integer(i));
            stack2.push(new Integer(i+10));
            queue1.enqueue(new Integer(i));
        }
    }

    @Test
    public void stack1(){
        assertEquals(stack1.pop(),5);
        assertEquals(stack1.size(),4);
        assertEquals(stack1.pop(),4);
        assertEquals(stack1.pop(),3);
        assertEquals(stack1.pop(),2);
        assertEquals(stack1.pop(),1);
        assertEquals(stack1.pop(),null);
    }

    @Test
    public void stack2(){
        Iterator iterator = stack1.iterator();
        assertTrue(iterator.hasNext());
        int arr[] = new int[5];
        int i = 0;
        int test_arr[] = new int[5];
        for(Integer num : stack1) arr[i++] = num;
        test_arr[4] = 1;
        test_arr[3] = 2;
        test_arr[2] = 3;
        test_arr[1] = 4;
        test_arr[0] = 5;
        assertArrayEquals(arr,test_arr);
    }

    @Test
    public void stack_iterator(){
        Iterator iterator = stack1.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next());
            System.out.print(" ");
        }
    }

    @Test
    public void queue1(){
        for(Integer i : queue1){
            System.out.print(i + " ");
        }
        System.out.println();
        while(!queue1.isEmpty()){
            System.out.print(queue1.dequeue() + " ");
        }
    }

    @Test
    public void deque(){
        Deque<Integer> deque = new Deque();
        deque.pushLeft(3);
        deque.pushLeft(2);
        deque.pushLeft(1);
        deque.pushRight(10);
        deque.pushRight(20);
        System.out.println(deque.popLeft());
        System.out.println(deque.popRight());
        System.out.println(deque.popRight());
        System.out.println(deque.popRight());
        System.out.println(deque.popRight());
        // System.out.println(deque.popRight());
        // System.out.println(deque.popRight());
        //System.out.println(deque.popRight());
    }

    @Test
    public void jdk_deque(){
        java.util.Deque<Integer> deque = new LinkedList();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollFirst());
        // System.out.println(deque.getFirst());
    }

    @Test
    public void randomQueue(){
        RandomQueue<String> randomQueue = new RandomQueue<String>();
        randomQueue.enqueue("hello");
        randomQueue.enqueue("world");
        randomQueue.enqueue("zju");
        randomQueue.enqueue("test");
//        System.out.println(randomQueue.sample());
//        System.out.println(randomQueue.sample());
//        System.out.println(randomQueue.sample());
//        System.out.println(randomQueue.sample());
        for(int i=0;i<10;i++) randomQueue.enqueue("zju_" + i);
        for(int i=0;i<14;i++){
            System.out.println(randomQueue.dequeue());
        }
    }

    @Test
    public void minus(){
        int n = 10;
        System.out.println(30 % --n);
    }

    @Test
    public void iterator(){
        RandomQueue<String> randomQueue = new RandomQueue<String>();
        randomQueue.enqueue("hello");
        randomQueue.enqueue("world");
        randomQueue.enqueue("zju");
        randomQueue.enqueue("test");
        for(int i=0;i<10;i++) randomQueue.enqueue("zju_" + i);
//        Iterator<String> iterator = randomQueue.iterator();
//        System.out.println(randomQueue.dequeue());
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        for(String str : randomQueue){
            System.out.println(str);
        }
    }

    @Test
    public void deleteKthQueue(){
        GeneralizedQueue<Integer> gq = new GeneralizedQueue<Integer>();
        for(int i = 0;i<8;i++){
            gq.enqueue(i + 1);
        }
        assertEquals(8,gq.delete(1));
        for(Integer i : gq) System.out.print(i + " ");
        System.out.println();
        assertEquals(7,gq.delete(1));
        for(Integer i : gq) System.out.print(i + " ");
        System.out.println();
        assertEquals(4,gq.delete(3));
        for(Integer i : gq) System.out.print(i + " ");
        System.out.println();

        GeneralizedQueue<Integer> g1 = new GeneralizedQueue<Integer>();
        g1.enqueue(10);
        System.out.println(g1.delete(20));
        // System.out.println(g1.delete(1));
    }
}
