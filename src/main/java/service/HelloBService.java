package service;

import api.IHelloService;

/**
 * Created by 听风 on 2017/6/23.
 */
public class HelloBService implements IHelloService {
    public String sayHello(String name) {
        return "hi! HelloBService "+name;
    }
}
