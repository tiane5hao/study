package tomcat;

/**
 * Created by 听风 on 2017/8/19.
 */
public class Connector extends LifeBaseService{

    private int port;

    private ProtocolHandler protocolHandler = null;

    private String protocolHandlerClassName;

    private Service service;

    @Override
    protected void initInternal() {
        protocolHandler.init();
    }

    @Override
    protected void startInternal() {

    }

    public Connector(String protocol){
        setProtocol(protocol);
        try {

            Class clazz = Class.forName(this.protocolHandlerClassName);
            protocolHandler =(ProtocolHandler)clazz.newInstance();
        }catch (Exception e){
        }

    }

    public void setProtocol(String protocol){
        if("HTTP/1.1".equals(protocol)){
            this.protocolHandlerClassName = "tomcat.Http11AprProtocol";
        }

    }


    public void setService(Service service){
        this.service = service;
    }


}
