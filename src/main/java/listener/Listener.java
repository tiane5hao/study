package listener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 听风 on 2017/9/8.
 */
public class Listener {

    public static void main(String[] args) {
        Timer timer = new Timer(true);
        People people = new People();
        TimerTask timerTask = new MyTask(people);
        timer.scheduleAtFixedRate( timerTask, 0,5000);

        try {
            Thread.sleep(3000);
            people.changeStatus(People.Sign.END);
            Thread.sleep(3000);
        }catch (Exception e){

        }


    }

    static class MyTask extends TimerTask{

        private People people;

        public MyTask(People people) {
            this.people = people;
        }

        @Override
        public void run() {
            if(People.Sign.END == people.status){
                System.out.println("监听结束事件");
            }
        }
    }
}
