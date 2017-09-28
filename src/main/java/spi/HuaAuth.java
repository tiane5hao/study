package spi;

import java.util.Map;

/**
 * Created by 听风 on 2017/7/22.
 */
public class HuaAuth implements Auth {

    private Auth auth;


    public int auth(Map<String, Object> map) {
        System.out.println("HuaAuth auth......");
        if(auth==null) return 0;
        return auth.auth(map);
    }

    public Auth setAuthChain(Auth auth) {
        this.auth = auth;
        return auth;
    }


}
