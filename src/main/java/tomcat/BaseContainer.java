package tomcat;

/**
 * Created by 听风 on 2017/8/19.
 */
public class BaseContainer extends LifeBaseService implements  Container{

    private Thread thread;

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {
        threadStart();
    }

    private void threadStart() {
        if(thread == null){
            return;
        }
        thread = new Thread(new ContainerBackgroundProcessor());
        thread.setDaemon(true);
        thread.start();
    }

    class ContainerBackgroundProcessor implements Runnable{

        @Override
        public void run() {

        }
    }
}
