package mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.net.URL;

/**
 * Created by 听风 on 2017/8/6.
 */
public class ActiveMQConnectionFactory implements ConnectionFactory {

    private URL url;
    public ActiveMQConnectionFactory(Object p0, Object p1, String s) {
        try {
            this.url = new URL(s);
        }catch (Exception e){

        }

    }

    @Override
    public Connection createConnection() throws JMSException {
        return null;
    }

    @Override
    public Connection createConnection(String s, String s1) throws JMSException {
        return null;
    }
}
