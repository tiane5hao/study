package tomcat;

import java.beans.PropertyChangeSupport;

/**
 * Created by 听风 on 2017/8/19.
 */
public class StandardServer extends LifeBaseService implements Server{


    private Service[] services = new Service[0];

    PropertyChangeSupport support = new PropertyChangeSupport(this);

    private int port= 8005;

    private String address;



    @Override
    protected void initInternal() {
        for(Service service : services){
            service.init();
        }
    }

    @Override
    protected void startInternal() {
        for(Service service : services){
            service.start();
        }
    }


    @Override
    public void addService(Service service) {

        service.setServer(this);

        synchronized (this){
            Service[] results = new Service[services.length + 1];
            System.arraycopy(services, 0, results, 0, services.length);
            results[services.length] = service;
            services = results;
            support.firePropertyChange("service", null, service);
        }
    }


}
