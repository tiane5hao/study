package observer;

import java.beans.PropertyChangeSupport;

/**
 * Created by 听风 on 2017/8/19.
 */
public class PropertiesSupportTest {

    PropertyChangeSupport support = new PropertyChangeSupport(this);

    private String name = "";

    public void setName(String name){
        if(this.name.equals(name)){
            System.out.println("name没有变化");
        }

        this.name = name;
        support.firePropertyChange("name", null, name);
    }

    public String getName(){
        return this.name;
    }
}
