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
    private byte[] header;
    private byte[] content;
    private String contentType;
    public ConnectionHandler(Socket socket){
        this.socket = socket;
    }

    private void readResource(String filename, OutputStream os) throws
            IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(ROOT
                    + filename);
            this.content = new byte[fileInputStream.available()];
            fileInputStream.read(this.content);
        } catch (IOException e) {
            String header = "HTTP/1.0 200 OK\r\n"+
                    "Server: OneFile 1.0\r\n"+
                    "Content-length: "+20+"\r\n"+
                    "Content-type: "+this.contentType+"\r\n\r\n";
            os.write(header.getBytes());
            os.write("<h1> ERROR 404 </h1> <h1>NOT FOUND</h1>".getBytes());
            e.printStackTrace();
            return;
        }

        String header = "HTTP/1.0 200 OK\r\n"+
                "Server: OneFile 1.0\r\n"+
                "Content-length: "+this.content.length+"\r\n"+
                "Content-type: "+this.contentType+"\r\n\r\n";
        this.header = header.getBytes("ASCII");
        os.write(this.header);
        os.write(this.content);
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
                System.out.println(tmp);
            }
            String[] requestMsgs = request.toString().split(" ");

            /*
             * if you input 127.0.0.1:1234/hello.txt on your browser
             * requestMsgs[1] represents '/hello.txt' which is necessary
             * for callback for the client socket.
             */

            System.out.println(request.toString());
            String target = requestMsgs[1];

            if(target.endsWith(".html") || target.endsWith(".htm")){
                this.contentType = "text/html";
            }

            readResource(target, outputStream);
            outputStream.flush();
            printWriter.flush();
            String info = requestMsgs[1] + " delivered to client\n";
            info += Thread.currentThread().getName();
            System.out.println(info);
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket!=null) try {
                socket.close();
            } catch (IOException e) {
                System.err.println("can not close socket..");
                e.printStackTrace();
            }
        }
    }
}
