package api;



/**
 * Created by 听风 on 2017/6/23.
 */
@SPI("dubbo")
public interface IHelloService {

    public String sayHello(String name);
}
