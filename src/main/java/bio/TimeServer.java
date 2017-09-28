package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 听风 on 2017/7/2.
 */
public class TimeServer {

    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8080);
            while (true){
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).run();
            }
        }catch (Exception e){

        }finally {
            if(serverSocket!=null){
                serverSocket.close();

            }
        }

    }


}

