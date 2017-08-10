
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.io.InputStreamReader;

/**
 * Created by alexsun on 8/10/17.
 */
public class TestClient implements Runnable{
    private Socket s = null;
    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    public void startClient() throws IOException {
        String serverAddress = "127.0.0.1";
        s = new Socket(serverAddress, 1235);
        System.out.println("Start client ");
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        String tmp;
        while((tmp=input.readLine())!=null){
            System.out.println(tmp);
        }
        System.out.println("hello");
        s.close();
        System.exit(0);
    }

    @Override
    public void run() {
        try {
            startClient();
            System.out.println("the test client started!");
        } catch (IOException e) {
            System.err.println("test client start failed!");
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread testThread = new Thread(new TestClient());
            testThread.start();
            break;
        }
    }
}
