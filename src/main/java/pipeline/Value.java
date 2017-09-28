package pipeline;

/**
 * Created by 听风 on 2017/8/22.
 */
public abstract class Value {

    private String name;

    protected Value pre;

    protected Value next;

    public Value(String name) {
        this.name = name;
    }

    public Value addPreValue(Value value) {
        this.pre = value;
        value.next = this;
        return value;
    }

    public String getName(){
        return name;
    }

    /**
     * 执行下一个value
     */
    public abstract void fireProcess() ;
}
