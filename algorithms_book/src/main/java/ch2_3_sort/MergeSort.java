package ch2_3_sort;

import java.util.Random;

class Element implements Comparable{

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}

public class MergeSort{
    private static Comparable[] aux;
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        Sort(a, 0, a.length -1 );
    }

    public static boolean less(Comparable a ,Comparable b){
        // a is less than b, return true, else return false
        if(a.compareTo(b) < 0) return true;
        else return false;
    }

    public static void Sort(Comparable[] a, int low, int high){
        if(low >= high) return;
        int mid = low + (high - low)/2;
        Sort(a, low, mid);
        Sort(a, mid+1, high);
        Merge(a, low, mid ,high);
    }

    public static void Merge(Comparable[] a, int low, int mid, int high){
        int i = low;
        int j = mid + 1;
        for(int k=low;k<=high;k++){
            aux[k] = a[k];
        }

        for(int k=low;k<=high;k++){
            if(i>mid) a[k] = aux[j++];
            else if(j > high) a[k] = aux[i++];
            else if(less(a[j],a[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void main(String args[]){
        Random random = new Random();
        Integer[] randList = new Integer[10];
        for(int i=0;i<randList.length;i++){
            randList[i] = random.nextInt() % 100;
        }
        //Integer[] randList = {1,3,5,7,9,2,4,6,8,10};
        for(int i=0;i<randList.length;i++) System.out.print(randList[i] + " ");
        sort(randList);
        System.out.println();
        for(int i=0;i<randList.length;i++) System.out.print(randList[i] + " ");
    }

}
