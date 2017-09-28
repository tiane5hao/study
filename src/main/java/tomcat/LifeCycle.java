package tomcat;

/**
 * Created by 听风 on 2017/8/19.
 */
public interface LifeCycle {

    void init();

    void start();

    void stop();

    void distroy();
}
