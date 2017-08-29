package Thread;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LiftOff implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;
    private static Object obj = new Object();
    private static int printCount = 0;
    public LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    public String status() {

        synchronized(obj){
            printCount++;
            if(printCount % 5 == 0 ){
                System.out.println();
            }
        }
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "关闭!") + "), ";
    }
    public void run() {
        while(countDown-- > 0) {
            System.out.print(status());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class RunnableTest {
     public static void ExecutorTest(){
         // ExecutorService es = Executors.newCachedThreadPool();
         // ExecutorService es = Executors.newFixedThreadPool(3);
         ExecutorService es = Executors.newSingleThreadExecutor();
         for(int i=0;i<5;i++){
             es.execute(new LiftOff());
         }
         es.shutdown();
     }
     public static void main(String args[]){
         /**
          *  both test1 and test2 are running in main thread
          */
         ExecutorTest();
         // LiftOff test1 = new LiftOff();
         // test1.run();
         // LiftOff test2 = new LiftOff();
         // test2.run();
         // *****************************

         /** Thread thread1 = new Thread(new LiftOff());
         Thread thread2 = new Thread(new LiftOff());
         thread1.start();
         thread2.start();
         */
         /* Above codes output.
          * #0(9), #1(9), #0(8), #1(8), #0(7), #1(7), #0(6), #1(6),
          * #0(5), #1(5), #0(4), #1(4), #0(3), #1(3), #0(2), #1(2),
          * #0(1), #1(1), #0(关闭!), #1(关闭!),
          */
     }
}
