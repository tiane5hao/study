package spi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * Created by 听风 on 2017/7/21.
 */
public class ExtensionLoader<T> {

    private final Class<T> type;
    private static final String SERVICES_DIRECTORY = "META-INF/mq/";




    public static <T>ExtensionLoader<T> getExtensionLoader(Class<T> type){
        if(!type.isInterface())
            throw new IllegalArgumentException();

        if(!type.isAnnotationPresent(SPI.class))
            throw new IllegalArgumentException();
        return new ExtensionLoader<T>(type);
    }

    private  ExtensionLoader(Class<T> type){
        this.type = type;
    }

    public List<T> getAdaptiveExtension(){
        List<T> list = new ArrayList<T>();
        loadFile(list, SERVICES_DIRECTORY);
        return list;
    }

    private void loadFile(List<T> list, String dir) {
        String fileName = dir+type.getName();
        ClassLoader classLoader = ExtensionLoader.class.getClassLoader();
        try {
            Enumeration<URL> urls = classLoader.getResources(fileName);
            while (urls.hasMoreElements()){
                URL url= urls.nextElement();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
                String line = null;
                while ((line = bufferedReader.readLine())!=null){
                    line = line.trim();
                    if(line.length()>0){
                        int i = line.indexOf("=");
                        String name = null;
                        if(i>0){
                            name = line.substring(0,i).trim();
                            line = line.substring(i+1).trim();

                        }
                        if(line.length()>0){
                            Class<?> clazz = Class.forName(line, true, classLoader);
                            T instance = (T)clazz.newInstance();
                            list.add(instance);
                        }
                    }
                }
            }
        }catch (IOException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }
}
