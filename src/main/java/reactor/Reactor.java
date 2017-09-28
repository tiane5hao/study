package reactor;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 听风 on 2017/6/29.
 */
public class Reactor implements Runnable {

    final Selector selector;
    final ServerSocketChannel serverSocket;

    public  Reactor(int port) throws Exception{
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),port));
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());

    }

    public void run() {
        try {
            while (!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> i = set.iterator();
                while(i.hasNext()){
                    dispatch(i.next());
                    i.remove();
                }
            }
        }catch (Exception e){

        }
    }

    void dispatch(SelectionKey selectionKey){
        Runnable runnable = (Runnable)(selectionKey.attachment());
        if(runnable!=null){
            runnable.run();
        }
    }

    class Acceptor implements Runnable{

        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if(c!=null){
                    new SocketReadDispatch(selector, c);
                }
            }catch (Exception e){

            }
        }
    }

    class SocketReadDispatch implements Runnable{

        final SocketChannel socket;
      final SelectionKey sk;

        public SocketReadDispatch(Selector selector, SocketChannel c) throws Exception{
            socket = c;
            sk = c.register(selector, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            selector.wakeup();

        }
        public void run() {
            try{
                ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                byteBuffer.clear();
                int byteRead = socket.read(byteBuffer);
                //requestHandle(new Request(socket,))
            }catch (Exception e){

            }
        }
    }
}
