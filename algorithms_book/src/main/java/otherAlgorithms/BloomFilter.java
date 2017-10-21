package otherAlgorithms;

import java.util.BitSet;

/**
 * implementation of simple Bloom Filter in java.
 *
 */
public class BloomFilter {

    // default capacity of the bit set.
    private static final int default_cap = 2 << 24;
    private static final int[] seeds = {11,13,17,19,29,37,47};
    private BitSet bitSet = new BitSet(default_cap);
    private mHash func[] = new mHash[seeds.length];

    public BloomFilter(){
        for(int i=0;i<func.length;i++){
            func[i] = new mHash(default_cap, seeds[i]);
        }
    }

    public void add(String str){
        for(mHash f : func){
            bitSet.set(f.hash(str), true);
        }
    }

    public boolean contains(String str){
        if(str == null) return false;
        boolean res = true;
        for(mHash f : func){
            res = res && bitSet.get(f.hash(str));
        }
        return res;
    }


    public static void main(String args[]){
        BloomFilter test = new BloomFilter();
        test.add("suncun1995@gmail.com");
        test.add("alex sun");
        System.out.println(test.contains("suncun1994@gmail.com")); //false
        System.out.println(test.contains("alex sun")); // true
        System.out.println(test.contains("suncun1995@gmail.com")); // true
    }

    public static class mHash{

        private int cap;
        private int seed;

        public mHash(int cap, int seed){
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String str){
            int len = str.length();
            int hashcode = 0;
            for(int i=0;i<len;i++){
                hashcode = (this.seed * hashcode + str.charAt(i)) %cap;
            }
            return hashcode % cap;
        }
    }

}
