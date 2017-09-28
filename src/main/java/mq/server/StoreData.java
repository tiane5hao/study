package mq.server;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 听风 on 2017/8/9.
 */
public class StoreData implements Runnable{

    private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

    @Override
    public void run() {
        while (true){
            if(queue.size() == 0){
                continue;
            }
            String data = queue.poll();
            System.out.println(data);
        }

    }

    public void store(String str){
        queue.add(str);
    }


}
