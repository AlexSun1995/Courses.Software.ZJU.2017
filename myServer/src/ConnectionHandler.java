import java.io.*;
import java.net.Socket;

/**
 * Created by alexsun on 8/9/17.
 */
public class ConnectionHandler implements Runnable {
    private Socket socket;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;
    private BufferedReader bufferedReader = null;
    private String ROOT = "/home/alexsun/123";
    private boolean over = false;
    public ConnectionHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            /*bufferedReader to read messages sent by client socket*/
            bufferedReader = new BufferedReader(new
                    InputStreamReader(inputStream));
            printWriter = new PrintWriter(new
                    OutputStreamWriter(outputStream));
            /*get request information sent by client socket*/
            StringBuffer request = new StringBuffer();
            String tmp;
            while((tmp = bufferedReader.readLine())!=null && tmp.length()>0){
                request.append(tmp);
            }
            String[] reqestMsgs = request.toString().split(" ");
            // System.out.println(reqestMsgs[1]);
            /*
             * if you input 127.0.0.1:1234/hello.txt on your browser
             * requestMsgs[1] represents '/hello.txt' which is necessary
             * for callback for the client socket.
             */
            FileInputStream fileInputStream = new FileInputStream(ROOT
                    + reqestMsgs[1]);
            byte[] buffer = new byte[1024];
            int length;
            while((length = fileInputStream.read(buffer))!=-1){
                outputStream.write(buffer,0, length);
            }
            printWriter.write("@@@@@@@@");
            printWriter.flush();
            String info = reqestMsgs[1] + " delivered to client\n";
            info += Thread.currentThread().getName();
            System.out.println(info);
            outputStream.close();
            inputStream.close();
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
