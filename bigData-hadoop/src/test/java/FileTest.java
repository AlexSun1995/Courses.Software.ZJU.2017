import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileTest {
    public static void old_io_read() {

        File file = new File("src/test/java/input.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(fileInputStream);
            StringBuilder builder = new StringBuilder();
            int tmpData;
            while ((tmpData = reader.read()) != -1) {
                if ((char) tmpData != '\r') {
                    // System.out.print((char)tmpData);
                    builder.append((char) tmpData);
                }

                if ((char) tmpData == '\n') {
                    String arr[] = builder.toString().split("\t", -3);
                    for (String str : arr)
                        System.out.println(str);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * using channel, nio buffer to read file
     *
     **/
    public static void nio_read_1() {
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile("src/test/java/input.txt","rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);

            while(bytesRead != -1)
            {
                buf.flip();
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                    // System.out.print(buf.get());
                }

                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
       //  old_io_read();
        nio_read_1();
    }
}
