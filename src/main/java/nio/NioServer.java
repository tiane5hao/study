package nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by 听风 on 2017/7/30.
 */
public class NioServer {

    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    Selector selector;
    String receiveText;

    public static void main(String[] args) {
        NioServer nioServer = new NioServer();
        nioServer.init();
        nioServer.createServer();
    }

    private void init(){
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(8080));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start --8080");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createServer(){
        while(true){
            try {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    handleKey(key);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void handleKey(SelectionKey key) {
        SocketChannel socketChannel;
        try {
            if(key.isAcceptable()){
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }

            if(key.isReadable()){
                socketChannel = (SocketChannel)key.channel();
                byteBuffer.clear();
                int count = socketChannel.read(byteBuffer);
                if(count > 0){
                    receiveText = new String(byteBuffer.array(),0,count);
                    System.out.println("服务器接受到的信息" + receiveText);
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                }
            }

            if(key.isWritable()){
                byteBuffer.clear();
                socketChannel = (SocketChannel)key.channel();
                String sendText = "server send message ";
                byteBuffer.put(sendText.getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                System.out.println(sendText);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        }catch (Exception e){

        }

    }
}
