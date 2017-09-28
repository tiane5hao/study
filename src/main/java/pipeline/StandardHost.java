package pipeline;

/**
 * Created by 听风 on 2017/8/22.
 */
public class StandardHost implements Pipeline,Container {

    private Container[] hosts = new Container[0];

    private Pipeline pipeline = new StandardPipeline();


    public StandardHost(){
        setFirstValue(new StandardHostValue("StandardHostValue"));
    }

    @Override
    public Pipeline addValue(Value value) {
        return pipeline.addValue(value);
    }

    @Override
    public void fireProcess() {
        pipeline.fireProcess();
    }

    @Override
    public void setFirstValue(Value value) {
        pipeline.setFirstValue(value);
    }

    @Override
    public Container addContainer(Container container) {
        return null;
    }

    @Override
    public Container[] getContainer() {
        return new Container[0];
    }

    @Override
    public void fire() {

    }

    class StandardHostValue extends Value{

        public StandardHostValue(String name) {
            super(name);
        }

        @Override
        public void fireProcess() {

        }
    }
}
