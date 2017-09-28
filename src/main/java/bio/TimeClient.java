package bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by 听风 on 2017/7/2.
 */
public class TimeClient {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", 8080);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("query time order");
            String body = in.readLine();
            System.out.println("the time server resp:"+body);
        }catch (Exception e){

        }finally {
            if(socket!=null){

            }
        }

    }
}
