package mq;

import javax.jms.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 听风 on 2017/8/6.
 */
public class ActiveMQConnection implements Connection{

    public static String DEFAULT_USER = "root";
    public static String DEFAULT_PASSWORD = "root";

    private URL url;

    SocketChannel socketChannel;

    Selector selector;

    public ActiveMQConnection(URL url){
        this.url = url;
    }

    @Override
    public Session createSession(boolean b, int i) throws JMSException {
        return new ActiveMQSession(url, b, i);
    }

    @Override
    public String getClientID() throws JMSException {
        return null;
    }

    @Override
    public void setClientID(String s) throws JMSException {

    }

    @Override
    public ConnectionMetaData getMetaData() throws JMSException {
        return null;
    }

    @Override
    public ExceptionListener getExceptionListener() throws JMSException {
        return null;
    }

    @Override
    public void setExceptionListener(ExceptionListener exceptionListener) throws JMSException {

    }

    @Override
    public void start() throws JMSException {

        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(InetAddress.getByName(url.getHost()),url.getPort()));
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true){
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();

                    if(key.isConnectable()){
                        socketChannel.finishConnect();
                        socketChannel.register(selector, SelectionKey.OP_WRITE|SelectionKey.OP_READ);
                        System.out.println("server is connecting......");
                    }

                    if(key.isWritable()){
                        String s = "i am client provider";
                        ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes());
                        socketChannel.write(byteBuffer);
                    }
                }
            }
        }catch (IOException e){

        }




    }

    @Override
    public void stop() throws JMSException {

    }

    @Override
    public void close() throws JMSException {

    }

    @Override
    public ConnectionConsumer createConnectionConsumer(Destination destination, String s, ServerSessionPool serverSessionPool, int i) throws JMSException {
        return null;
    }

    @Override
    public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String s, String s1, ServerSessionPool serverSessionPool, int i) throws JMSException {
        return null;
    }
}
