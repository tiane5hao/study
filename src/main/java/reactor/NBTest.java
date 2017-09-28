package reactor;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 听风 on 2017/6/28.
 */
public class NBTest {

    public  Selector selector;
    public  ServerSocketChannel serverSocketChannel;
    int nkey;
    int channals;


    public void startServer() throws Exception{
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(),9000));
        serverSocketChannel.configureBlocking(false);
        SelectionKey key = serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        while(true){
            nkey = selector.select();
            System.out.println("NBTest: Number of keys after select operation: " +key);
            if(nkey>0){
                Set set = selector.selectedKeys();
                Iterator i = set.iterator();
                while (i.hasNext()){
                    key = (SelectionKey) i.next();
                    i.remove();
                    if(key.isAcceptable()){
                        Socket socket = ((ServerSocketChannel)key.channel()).accept().socket();
                        SocketChannel socketChannel = socket.getChannel();

                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                        System.out.println(channals++);
                    }
                }
            }
        }


    }

    public static void main(String[] args) throws Exception{
        NBTest reactor = new NBTest();
        reactor.startServer();
    }
}
