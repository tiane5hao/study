package bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by 听风 on 2017/7/3.
 */
public class TimeServerHandler implements Runnable{
    private Socket socket;
    public TimeServerHandler(Socket socket){
        this.socket = socket;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String body = in.readLine();
            System.out.println("the time server receive order:"+body);
            out = new PrintWriter(socket.getOutputStream(),true);
            String serverTime = "query time order".equals(body)?String.valueOf(System.currentTimeMillis()):"error order";
            out.println(serverTime);
        }catch (Exception e){

        }finally {
            if(socket!=null){
                try {
                    socket.close();
                }catch (Exception e){

                }

            }
            if(in!=null){
                try {
                    in.close();
                }catch (Exception e){

                }

            }
            if(out!=null){
                try {
                    out.close();
                }catch (Exception e){

                }

            }

        }

    }
}
