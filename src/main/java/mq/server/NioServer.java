package mq.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by 听风 on 2017/8/7.
 */
public class NioServer implements Runnable{

    private InetAddress hostAddress;

    private ServerSocketChannel serverSocketChannel;

    private SocketChannel socketChannel;

    private int port;

    private Selector selector;

    private StoreData storeData;

    private ByteBuffer byteBuffer = ByteBuffer.allocate(8192);

    public NioServer(InetAddress hostAddress, int port, StoreData storeData) throws IOException{
        this.hostAddress = hostAddress;
        this.port = port;
        this.storeData = storeData;
        this.selector = this.initSelector();
    }

    public static void main(String[] args) throws Exception{
        StoreData storeData = new StoreData();
        Thread store = new Thread(storeData);
        store.setDaemon(true);
        store.start();

        Thread server = new Thread(new NioServer(InetAddress.getLocalHost(),8080, storeData));
        server.start();
    }

    private Selector initSelector() throws IOException{
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(hostAddress,port));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        return selector;
    }


    @Override
    public void run() {
        while (true){
            try {
                selector.select();
                Iterator<SelectionKey> selectedKeys = this.selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()){
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();
                    if(key.isValid()){
                        continue;
                    }

                    if(key.isAcceptable()){
                        this.accept(key);
                    }else if(key.isReadable()){
                        this.read(key);
                    }
                }
            }catch (Exception e){

            }


        }
    }

    private void accept(SelectionKey key) throws Exception{
        System.out.println("连接客户端成功");
        serverSocketChannel = (ServerSocketChannel)key.channel();
        socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

    }

    private void read(SelectionKey key)throws IOException {
        this.byteBuffer.clear();
        socketChannel = (SocketChannel)key.channel();
        int num = socketChannel.read(byteBuffer);
        String str = new String(byteBuffer.array());
        installData(str);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    private void installData(String str) {
        storeData.store(str);
    }
}
