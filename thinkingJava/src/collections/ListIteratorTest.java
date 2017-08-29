package collections;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 创建并组装一个List<Integer>，然后创建第二个具有相同尺寸的List<Integer>，
 * 并使用 ListIterator读取第一个List中的元素，然后再次它们以反序插入到第二个集合。
 */
public class ListIteratorTest {
    public void Test(){
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        Collections.addAll(l1,1,2,3,4,5);
        System.out.println("this is l1: " + l1.toString());
        System.out.println("this is pre_state of l2: " + l2.toString());
        ListIterator<Integer> li = l1.listIterator(l1.size());
        while(li.hasPrevious()){
            l2.add(li.previous());
        }
        System.out.println("after insert: " + l2.toString());
    }
    public static void main(String args[]){
        ListIteratorTest listIteratorTest = new ListIteratorTest();
        listIteratorTest.Test();
    }
}
