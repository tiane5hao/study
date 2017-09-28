package service;

/**
 * Created by 听风 on 2017/7/7.
 */
public interface HelloMBean {
    public String getName();
    public void setName(String name);
    public String printHello();
    public void printHello(String whoName);
}
