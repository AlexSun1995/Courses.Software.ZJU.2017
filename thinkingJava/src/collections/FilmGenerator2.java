package collections;
import java.util.*;

public class FilmGenerator2 implements Generator<String> {
    static final String[] films = {"少林足球","大话西游","迷失太空","星际迷航","星际迷航"};
    private static int index = 0;
    @Override
    public String next() {
        int len = films.length;
        String ans = films[(index++) % len];
        return ans;
    }
    public static void main(String args[]){
        FilmGenerator2 fg = new FilmGenerator2();
        Collection<String> arr = new ArrayList<>();
        Stack<String> sta = new Stack<>();
        Queue<String> que = new LinkedList<>();
        for(int i=0;i<4;i++){
            arr.add(fg.next());
        }
        sta.addAll(arr);
        que.addAll(arr);
        Iterator iterator;
        iterator = arr.iterator();
        System.out.println(arr.size());
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("******************");
        iterator = sta.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        while(!sta.empty()){
            System.out.println(sta.pop());
        }
        // Testing Tree Set
        TreeSet<String> ts = new TreeSet<>();
        ts.addAll(arr);
        iterator = ts.iterator();
        System.out.println("TreeSet iterator output");
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        // test hashSet
        HashSet<String> hs = new HashSet<>();
        hs.addAll(arr);
        System.out.println("HashSet iterator test");
        iterator = hs.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
