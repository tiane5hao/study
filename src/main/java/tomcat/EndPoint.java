package tomcat;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by 听风 on 2017/8/19.
 */
public class EndPoint {

    private ServerSocketChannel serverChannel;
    private int port;

    private InetAddress address;

    public void init() {

        bind();
    }

    private void bind() {
        try {

            serverChannel = ServerSocketChannel.open();
            serverChannel.socket().bind(new InetSocketAddress(getAddress(), getPort()));
            serverChannel.configureBlocking(true);
            serverChannel.socket().setSoTimeout(10000);
        }catch (Exception e){

        }
    }

    private int getPort() {
        return port;
    }

    private InetAddress getAddress() {
        return address;
    }
}
