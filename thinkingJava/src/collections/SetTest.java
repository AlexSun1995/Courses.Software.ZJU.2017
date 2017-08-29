package collections;
import java.util.*;

class Language implements Comparable<Language>{

    private String name;
    public Language(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public int compareTo(Language obj) {
        return obj.getName().toLowerCase().compareTo(this.name.toLowerCase());
    }
}
public class SetTest {

    public void printClass(Object obj){
        System.out.println(obj.getClass());
    }

    public static void main(String args[]){
        // SetTest st = new SetTest();
        // System.out.println(st.getClass());
        TreeSet<Language> ts = new TreeSet<>();
        ts.add(new Language("C++"));
        ts.add(new Language("c++"));
        ts.add(new Language("Python"));
        ts.add(new Language("PYTHON"));

        /**
         *  the output is:
         *  Python
         *  C++
         */
        for(Language value:ts){
            System.out.println(value.getName());
        }
    }
}
