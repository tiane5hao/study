package tomcat;

/**
 * Created by 听风 on 2017/8/19.
 */
public interface Server extends LifeCycle{

    void addService(Service service);
}
