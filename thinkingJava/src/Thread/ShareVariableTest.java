package Thread;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//帐户
class Account{
    // 余额
    private int balance = 500;
    private static Object obj = new Object();
    // 检查余额
    public int getBalance(){
        return balance;
    }
    // 取款
    public synchronized void withdraw(int amount){
        balance = balance - amount;
    }
}
//取款任务
class WithdrawalsTask implements Runnable{
    private Account account;
    WithdrawalsTask(Account account) {
        this.account = account;
    }

    public void run(){
        for (int x = 0; x < 5; x++) {
            makeWithdrawal(100); // 取款
            if (account.getBalance() < 0)
                System.out.println("账户透支了!");
        }
    }

    public void makeWithdrawal(int amt) {
        if (account.getBalance() >= amt) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
            }
            account.withdraw(amt); // 如果余额足够，则取款
            System.out.println(Thread.currentThread().getName() + " 完成取款");
        } else {// 余额不够给出提示
            System.out.println("余额不足以支付 " + Thread.currentThread().getName()
                    + " 的取款，余额为 " + account.getBalance());
        }
    }
}

class PrintValues implements Runnable{
    private static String[] data = {"你好", "世界", "Hello", "World"};
    private int index = 0;
    private int printTimes = 0;
    public PrintValues(int times){
        printTimes = times;
    }
    public void print(){
        synchronized (this){
        for(int i=0;i<printTimes;i++){
             synchronized (data) {
                System.out.print(Thread.currentThread().getName() + "  ");
                System.out.println(data[(index++) % data.length]);
             }
            try{
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }}
        System.out.println();
    }
    @Override
    public void run() {
        this.print();
    }
}
public class ShareVariableTest {
    public static void main(String[] args){
        ExecutorService es = Executors.newCachedThreadPool();
        /*Account account = new Account();
        for(int i=0;i<5;i++){
            es.execute(new WithdrawalsTask(account));
        }
        */
        for(int i=0;i<5;i++){
            es.execute(new PrintValues(4));
        }
        es.shutdown();
    }
}
