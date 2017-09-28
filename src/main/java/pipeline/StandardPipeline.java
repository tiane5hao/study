package pipeline;

/**
 * Created by 听风 on 2017/8/23.
 */
public class StandardPipeline implements Pipeline {

    private Value first;

    @Override
    public Pipeline addValue(Value value) {
        first.addPreValue(value);
        this.first = value;
        return this;
    }

    @Override
    public void fireProcess() {
        first.fireProcess();
    }

    public void setFirstValue(Value value){
        this.first = value;
    }
}
