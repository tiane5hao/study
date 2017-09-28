package bio;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 听风 on 2017/9/17.
 */
public class ThreadMain implements Runnable {

    private CountDownLatch cdl;
    private int n;

    public ThreadMain(CountDownLatch cdl, int n){
        this.cdl = cdl;
        this.n = n;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(n);
        }catch (Exception e){

        } finally{
            System.out.println(Thread.currentThread().getName() + "执行完成，sleep time : "+n);
            cdl.countDown();
        }
    }

    public static void main(String[] args) {
        int n = 5;
        CountDownLatch cdl = new CountDownLatch(n);

        for(int i=0; i<n; i++){
            new Thread(new ThreadMain(cdl, i*1000)).start();
        }
        try {
            cdl.await();
        }catch (Exception e){

        }
        System.out.println("所有子线程全部执行完成");


    }
}
