
/**
 * this work is not done yet..
 */
package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Employee{
    private String content;
    private int talkFlag;
    private static Integer turn = 0;
    public Employee(String content, int talkFlag){
       this.content = content;
       this.talkFlag = talkFlag;
    }

    public void say(){
        synchronized (this) {
            System.out.println(content);
            turn = (talkFlag + 1) % 2;
            notifyAll();
        }
    }

    public void waitForTalk(){
        synchronized (this) {
            if(true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class Talk implements Runnable{
    private Employee employee;
    public Talk (String content,int talkFlag){
        employee = new Employee(content,talkFlag);
    }
    @Override
    public void run() {
        while(!Thread.interrupted()){
            employee.waitForTalk();
            employee.say();
        }
    }
}
public class TalkTest {
    public static void main(String args[]) throws InterruptedException{
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Talk("苟利",0));
        // es.execute(new Talk("国家",1));
        TimeUnit.SECONDS.sleep(10);
        es.shutdownNow();
    }
}
