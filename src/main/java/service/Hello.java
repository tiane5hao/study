package service;

/**
 * Created by 听风 on 2017/7/7.
 */
public class Hello implements HelloMBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String printHello() {
        return "Hello World, " + name;
    }
    public void printHello(String whoName) {
        System.out.println("Hello , " + whoName);
    }
}
