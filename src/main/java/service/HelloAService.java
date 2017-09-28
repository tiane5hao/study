package service;

import api.IHelloService;

/**
 * Created by 听风 on 2017/6/23.
 */
public class HelloAService implements IHelloService {
    public String sayHello(String name) {
        return "hi! HelloAService "+name;
    }
}
