package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Pet{
    String name;
    Pet(String name){
        this.name = name;
    }
    public String toString(){
        return (name == null ? "":name);
    }
}

public class SimpleIteration {
    public static void main(String[] args) {
        List<Pet> pets = new ArrayList<>();
        Collections.addAll(pets,new Pet("京巴"),new Pet("波斯猫"),new Pet("巴哥"));
        Iterator<Pet> it = pets.iterator();
        System.out.println("using iterator");
        while(it.hasNext()) {
            Pet p = it.next();
            System.out.print( p + " ");
        }
        System.out.println();
        // 简单的遍历:
        System.out.println("Simple traverse");
        for(Pet p : pets) {
            System.out.print(p + " ");
        }
        System.out.println();
        // 用 Iterator 移除元素:
        it = pets.iterator();
        for(int i = 0; i < 3; i++) {
            it.next();
            it.remove();
        }
        System.out.println(pets);
    }
}