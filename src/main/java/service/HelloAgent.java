package service;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * Created by 听风 on 2017/7/7.
 */
public class HelloAgent {
    public static void main(String[] args) throws Exception {
        MBeanServer server = MBeanServerFactory.createMBeanServer();

        ObjectName helloName = new ObjectName("chengang:name=HelloWorld");
        server.registerMBean(new Hello(), helloName);

        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter, adapterName);

        adapter.start();
        System.out.println("start.....");

    }
}
