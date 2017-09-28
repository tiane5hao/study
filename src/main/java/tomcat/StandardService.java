package tomcat;

import java.beans.PropertyChangeSupport;

/**
 * Created by 听风 on 2017/8/19.
 */
public class StandardService extends LifeBaseService implements Service {


    PropertyChangeSupport support = new PropertyChangeSupport(this);

    private BaseContainer container;

    private Connector[] connectors = new Connector[0];

    private Server server;

    @Override
    public void setContainer(BaseContainer container) {
        this.container = container;
    }

    @Override
    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public void addConnector(Connector connector) {
        connector.setService(this);
        synchronized (this){
            Connector[] results = new Connector[connectors.length + 1];
            System.arraycopy(connectors, 0, results, 0, connectors.length);
            results[connectors.length] = connector;
            connectors = results;
            support.firePropertyChange("connector", null, connector);
        }
    }

    @Override
    protected void initInternal() {
        if(container!=null){
            container.init();
        }

        synchronized (this){
            for(Connector connector : connectors){
                connector.init();
            }
        }
    }

    @Override
    protected void startInternal() {
        synchronized (this){
            container.start();
        }

        synchronized (this){
            for(Connector connector : connectors){
                connector.start();
            }
        }
    }
}
