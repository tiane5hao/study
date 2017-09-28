package pipeline;

/**
 * Created by 听风 on 2017/8/22.
 */
public interface Pipeline {

    Pipeline addValue(Value value);

    void fireProcess();

    void setFirstValue(Value value);
}
