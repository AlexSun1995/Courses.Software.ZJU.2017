import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by alexsun on 8/9/17.
 */
public class Server implements Runnable {
    private ThreadPoolExecutor executor;
    private void Start() throws IOException {
        executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
        int port = 1235;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("ServerSocket Start");
        while(true){
            /*
             * Listens for a connection to be made to this socket and accepts
             * it. The method blocks until a connection is made.
             */
            Socket socket = serverSocket.accept();
            executor.execute(new ConnectionHandler(socket));
        }

    }

    @Override
    public void run() {
        try {
            Start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Server thisServer = new Server();
        Thread listenThread = new Thread(thisServer);
        listenThread.start();
    }

}
