package tomcat;

/**
 * Created by 听风 on 2017/8/19.
 */
public interface Service extends LifeCycle{

    void setContainer(BaseContainer container);

    void setServer(Server server);

    void addConnector(Connector connector);
}
