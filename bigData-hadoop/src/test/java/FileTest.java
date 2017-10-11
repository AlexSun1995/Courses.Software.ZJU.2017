import java.io.*;

public class FileTest {
    public static void main(String args[]){
        File file = new File("/home/alexsun/input.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(fileInputStream);
            StringBuilder builder = new StringBuilder();
            int tmpData;
            while((tmpData = reader.read())!=-1){
                if((char)tmpData!='\r'){
                    // System.out.print((char)tmpData);
                    builder.append((char)tmpData);
                }

                if((char)tmpData == '\n'){
                    String arr[] = builder.toString().split("\t",-3);
                    for(String str:arr)
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
}
