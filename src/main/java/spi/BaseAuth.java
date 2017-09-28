package spi;

import java.util.List;
import java.util.Map;

/**
 * Created by 听风 on 2017/7/22.
 */
public class BaseAuth implements Auth{

    public static List<Auth> list = ExtensionLoader.getExtensionLoader(Auth.class).getAdaptiveExtension();

    public int auth(Map<String, Object> map) {
        System.out.println(" auth list size:"+list.size());
        if(list==null || list.size()==0)
            return 0;
        Auth auth0 = list.get(0);

        if(list.size()>1){
            for(int i=1; i<list.size(); i++){
                auth0.setAuthChain(list.get(i));
            }
        }

        auth0.auth(map);

        return 0;
    }

    public Auth setAuthChain(Auth auth) {
        return null;
    }

    public static void main(String[] args) {
        Auth auth = new BaseAuth();
        auth.auth(null);
    }
}
