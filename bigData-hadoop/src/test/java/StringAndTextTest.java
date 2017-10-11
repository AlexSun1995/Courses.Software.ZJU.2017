import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.Text;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StringAndTextTest {

    @Test
    public void TestTextLen(){
        Text t = new Text("hadoop中国");
        assertEquals(t.getLength(), 12);
        assertEquals(t.getBytes().length,17);
        // okay, why there are different?
        // getLength is the length of the byte array
        // while getByte.length is the capacity of that array.
    }

    @Test
    public void Text1(){
        Text t = new Text("hadoop");
        assertThat("find a substring",t.find("do"),is(2));
        // yes! the chinese are 3bytes in utf-8
        Text t2 = new Text("浙江大学");
        assertThat("find chinese",t2.find("大"),is(6));
    }

    @Test
    public void Test2(){
        Text t = new Text("hadoop");
        t.set(new Text("hive"));
        assertThat(t.getLength(),is(4));
        assertThat("real byte length", t.getBytes().length,is(6));
        // the reason is simple, when set a new text to a old one,
        // and the new one's length is smaller than the old one
        // it will not create a new byte array, see the source code.
    }

    @Test
    public void byteTest(){
        // ByteWritable bw = new ByteWritable(new byte[] {3, 5});
    }
}
