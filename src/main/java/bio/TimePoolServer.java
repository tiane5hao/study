package bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 听风 on 2017/7/2.
 */
public class TimePoolServer {

    public static final ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),50,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10000));
    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8080);
            while (true){
                socket = serverSocket.accept();

                executor.execute(new TimeServerHandler(socket));

            }
        }catch (Exception e){

        }finally {
            if(serverSocket!=null){
                serverSocket.close();

            }
        }

    }


}

