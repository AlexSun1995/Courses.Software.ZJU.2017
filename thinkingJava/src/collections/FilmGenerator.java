package collections;

import java.util.LinkedList;
import java.util.Queue;

public class FilmGenerator implements Generator<String>{
    static final String[] films = {"少林足球","大话西游","迷失太空","星际迷航"};
    private static Queue<String> que = new LinkedList<>();
    private static Queue<String> backup = new LinkedList<>();
    static{
        int len = films.length;
        for(int i=0;i<len;i++)
            que.add(films[i]);
    }
    @Override
    public String next(){
        String peek;
        if(!que.isEmpty()){
            peek = que.peek();
            backup.offer(peek);
            que.poll();
        }
        else{
            peek = backup.peek();
            que.offer(peek);
            backup.poll();
        }
        System.out.println(peek);
        return peek;
    }
    public static void main(String args[]){
        FilmGenerator fg = new FilmGenerator();
        for(int i=0;i<20;i++){
            fg.next();
        }
    }
}