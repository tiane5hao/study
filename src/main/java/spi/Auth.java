package spi;

import java.util.Map;

/**
 * Created by 听风 on 2017/7/22.
 */
@SPI
public interface Auth {


    public int auth(Map<String,Object> map);

    public Auth setAuthChain(Auth auth);
}
