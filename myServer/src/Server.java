import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by alexsun on 8/9/17.
 */
public class Server {
    private ThreadPoolExecutor executor;
    private void Start() throws IOException {
        executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
        // Start a port 1235
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
    public static void main(String args[]){
        Server thisServer = new Server();
        try {
            thisServer.Start();
        } catch (IOException e) {
            System.out.println("Error occurs when start Server");
            e.printStackTrace();
        }
    }

}
