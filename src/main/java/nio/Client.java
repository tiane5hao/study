package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by 听风 on 2017/7/30.
 */
public class Client {

    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    Selector selector= null;
    String receiveText;

    public static void main(String[] args) {
        Client client = new Client();
        client.init();
        client.connect();
    }

    public void init(){
        try {
            SocketChannel socketChannel = SocketChannel.open();
            selector = Selector.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("localhost",8080));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    boolean isFinish = false;

    SocketChannel socketChannel = null;
    public void connect(){

        SelectionKey key;
        while (true){

            if(isFinish){
                break;
            }
            try {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    key =  iterator.next();
                    if(!key.isValid()){
                        break;
                    }
                    iterator.remove();
                    if(key.isConnectable()){
                        socketChannel = (SocketChannel)key.channel();

                        if(socketChannel.isConnectionPending()){
                            socketChannel.finishConnect();
                            System.out.println("完成连接");

                        }
                        socketChannel.register(selector, SelectionKey.OP_WRITE);
                    }

                    if(key.isReadable()){
                        socketChannel = (SocketChannel)key.channel();
                        byteBuffer.clear();
                        int count = socketChannel.read(byteBuffer);
                        if(count > 0){
                            receiveText = new String(byteBuffer.array(),0,count);
                            System.out.println("client接受到的信息" + receiveText);
                            isFinish = true;

                        }
                    }

                    if(key.isWritable()){
                        byteBuffer.clear();
                        socketChannel = (SocketChannel)key.channel();
                        String sendText = "client send message ";
                        byteBuffer.put(sendText.getBytes());
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        System.out.println(sendText);

                        socketChannel.register(selector, SelectionKey.OP_READ);

                    }
                }
            }catch (Exception e) {
                //
            }finally {


            }
        }

        try {
            selector.close();
            socketChannel.close();
        }catch (Exception e){

        }

    }

    private void handlerKey(SelectionKey key) throws IOException{




    }
}
