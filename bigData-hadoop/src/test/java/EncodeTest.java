import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 *   there are only bytes in computer memory
 *   encoder : change (specific encoded)chars to bytes
 *   decoder : change bytes to chars so that you can read.
 */
public class EncodeTest {
    private static CharsetEncoder encoder = Charset.forName("GBK").newEncoder();
    private static CharsetDecoder decoder = Charset.forName("GBK").newDecoder();

    public static void gbk2utf_8Test() throws FileNotFoundException {
        CharBuffer cb = CharBuffer.allocate(256);
        // FileInputStream fs = new FileInputStream("src/test/java/gbk.txt");
        RandomAccessFile ras = new RandomAccessFile("src/test/java/gbk.txt","rw");
        FileChannel fileChannel = ras.getChannel();
        try{
            ByteBuffer bb = ByteBuffer.allocate(16);
            while((fileChannel.read(bb)) != -1){
                bb.flip();
                System.out.println(decoder.decode(bb));
                bb.clear();
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        /**
         *  write something to the gbk.txt file
         */
        try{
            CharBuffer charBuffer = CharBuffer.allocate(14);
            charBuffer.put("苟利国家生死以");
            charBuffer.flip();
            ByteBuffer byteBuffer = encoder.encode(charBuffer);
            for(int i=0;i<byteBuffer.capacity();i++){
                System.out.print(byteBuffer.get(i) + ' ');
            }
            System.out.println("******");
            System.out.println(decoder.decode(byteBuffer));
            fileChannel.write(byteBuffer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        try {
            gbk2utf_8Test();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
