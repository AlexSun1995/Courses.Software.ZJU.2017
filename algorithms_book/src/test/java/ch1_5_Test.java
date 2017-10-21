import ch1_5_union_find.QuickFindUnionFind;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * this is the test class for CH1-5 the union find set part
 * of algorithms 4th.
 */
public class ch1_5_Test {
    class Pair{
        int p;
        int q;
        public Pair(int p, int q){
            this.p = p;
            this.q = q;
        }
    }

    private int N;
    private List<Pair> paris= new ArrayList<Pair>();

    /**
     * read data(denotes connected pairs in txt file)
     */
    @Before
    public void setUp(){
        Charset charset = Charset.forName("US-ASCII");
        File file = new File("./src/test/java/uf_in.txt");
        try {
            BufferedReader reader = Files.newBufferedReader(file.toPath(),charset);
            String line = reader.readLine();
            N = Integer.parseInt(line);
            while((line = reader.readLine()) != null){
                String tmp[] = line.split(" ");
                paris.add(new Pair(Integer.parseInt(tmp[0]),
                        Integer.parseInt(tmp[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFileReader(){
        System.out.println(N);
        for(Pair p : paris){
            System.out.println(p.p +" " + p.q);
        }
    }

    @Test
    public void quickFind(){
        QuickFindUnionFind uf = new QuickFindUnionFind(N);
        for(Pair p : paris){
            uf.union(p.p,p.q);
        }
        System.out.println(uf.connected(3,4));
        int tmp[] = uf.getId();
        for(int i : tmp) System.out.print(i + " ");
        System.out.println();
    }

    @Test
    public void quickUnion(){

    }

}
