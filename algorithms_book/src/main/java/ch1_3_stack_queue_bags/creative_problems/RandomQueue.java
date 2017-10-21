package ch1_3_stack_queue_bags.creative_problems;
import ch1_3_stack_queue_bags.mQueue;
import java.util.Iterator;
import java.util.Random;
import static java.lang.Math.min;
/**
 * problems set 1.3.38
 * Delete Kth element
 * @param <Item>
 */
public class RandomQueue<Item> extends mQueue<Item> {

    private int N;
    private int capacity=5;
    private Item[] items;

    public RandomQueue(){
        // why can't use new Item[capacity]?
        items = (Item[]) new Object[capacity];
        N = 0;
    }

    @Override
    public RandomQueue enqueue(Item item) {
        if((N + 1) == items.length) resize(items.length * 2);
        items[N++] = item;
        return this;
    }

    /**
     * return a random element and delete it
     * @return
     */
    @Override
    public Item dequeue() {
        if(isEmpty()){
            System.err.println("Already Empty!");
            return null;
        }
        Random random = new Random();
        int pos = random.nextInt(N);
        Item tmp = items[pos];
        items[pos] = items[N-1];
        // items[N-1] = tmp;
        N--;
        if(N>0 && N == items.length / 4) resize(items.length / 2);
        return tmp;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new mIterator();
    }

    /**
     * return a random element but do not delete it.
     * @return
     */
    public Item sample(){
        Random random = new Random();
        int pos = random.nextInt(N);
        Item tmp = items[pos];
        items[pos] = items[N-1];
        items[N-1] = tmp;
        return tmp;
    }

    private void resize(int max_size){
        Item[] tmp = (Item[]) new Object[max_size];
        for(int i=0;i<min(items.length,max_size);i++){
            tmp[i] = items[i];
        }
        items = tmp;
    }

    /**
     * problem 1.3.36
     * return a random iterator
     * 需要解决的一个基础问题是，给定一个数组，如何将数组中的数字随机排序
     * 如果解决了这个问题，就可以把这个随机化以后的数组当做迭代器访问的索引
     *
     * @author alexsun
     * @Info 2017-10-12 10:12 pm cst.zju
     */
    private class mIterator implements Iterator<Item>{
        Item[] a = items;
        int N = 0;
        int indexs[] = randomArray();

        /**
         * @Target 将索引数据[0-n]数据打乱，返回一个随机排序的,
         * 数字范围在[0-n]的数组（随机给定范围内N个不重复的数）
         * 其实也可以理解为一个java的shuffle问题.
         * @Solution 记录产生过的随机数
         * <herf>http://blog.csdn.net/albertfly/article/details/51383410</herf>
         * @return
         */
        private int[] randomArray(){
            int len = a.length;
            int source[] = new int[len];
            for(int i=0;i<len;i++) source[i] = i;
            int ans[] = new int[len];
            Random random = new Random();
            for(int i=0;i<a.length;i++){
               int index = Math.abs(random.nextInt() % len--);
               ans[i] = source[index];
               source[index] = source[len];
            }
            System.out.println(ans);
            return ans;
        }

        @Override
        public boolean hasNext() {
            return N!=a.length;
        }

        @Override
        public Item next() {
            if(isEmpty()){
               throw new ArrayIndexOutOfBoundsException();
            }
            else{
                return a[indexs[N++]];
            }
        }

        @Override
        public void remove() {}
    }
}
