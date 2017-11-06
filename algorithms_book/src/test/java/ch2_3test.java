import ch2_3_sort.MergeSort;
import org.junit.Test;

public class ch2_3test {
    @Test
    public void testMerge(){
        Integer a[] = {3,7,4,6};
        MergeSort.sort(a);
        for(int i = 0;i<a.length;i++)System.out.print(a[i] + " ");
    }
}
