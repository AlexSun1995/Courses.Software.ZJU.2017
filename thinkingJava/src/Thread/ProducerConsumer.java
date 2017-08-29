package Thread;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Resource{
    public static LinkedList<String> data = new LinkedList<>();
}

class Producer implements Runnable{

    public synchronized void produce(){
        while(Resource.data.size() == 0) {
            synchronized (Resource.data) {
                for (int i = 0; i < 5; i++) {
                    Resource.data.add("gou");
                    System.out.println("added, size:" + Resource.data.size());
                }
                Thread.yield();
                // notifyAll();
            }
        }
    }
    @Override
    public void run() {
        produce();
    }
}

class Consumer implements Runnable{
    public synchronized void consume(){
        while(Resource.data.size() > 0){
            synchronized (Resource.data) {
                for (int i = 0; i < 5; i++) {
                    Resource.data.poll();
                    System.out.println("pitched, size:" + Resource.data.size());
                }
                Thread.yield();
            }
        }
    }
    @Override
    public void run() {
        consume();
    }
}
public class ProducerConsumer {
    public static void main(String args[]){
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Producer());
        es.execute(new Consumer());
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdownNow();
    }

}
