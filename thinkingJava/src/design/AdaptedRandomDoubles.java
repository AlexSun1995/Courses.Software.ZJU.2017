package design;
import java.util.*;
import java.nio.*;

/**
 * 产生随机产生double
 */
class RandomDoubles {
    private static Random rand = new Random(47);
    public double next() {
        return rand.nextDouble();
    }
    public int nextInt(){
        return rand.nextInt(30);
    }
}
/**
 * 随机适配器类
 */
public class AdaptedRandomDoubles extends RandomDoubles
        implements Readable {
    private int count;
    public AdaptedRandomDoubles(int count) {
        this.count = count;
    }
    //实现了Readable的read方法
    public int read(CharBuffer cb) {
        if(count-- == 0)
            return -1;
        String result = Integer.toString(nextInt()) + " ";
        cb.append(result);
        result = Double.toString(next()) + " ";
        cb.append(result);
        return result.length();
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(new AdaptedRandomDoubles(7));
        while(s.hasNext())
            System.out.print(s.next() + " ");
    }
}