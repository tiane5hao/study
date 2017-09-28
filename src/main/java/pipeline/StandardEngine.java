package pipeline;

/**
 * Created by 听风 on 2017/8/22.
 */
public class StandardEngine implements Pipeline,Container {

    private Container[] hosts = new Container[0];

    private Pipeline pipeline = new StandardPipeline();


    public StandardEngine(){
        setFirstValue(new StandardEngineValue("StandardEngineValue"));
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
        if(container instanceof StandardHost)
            throw new IllegalArgumentException();

        Container[] newCon = new Container[hosts.length+1];
        System.arraycopy(hosts, hosts.length, newCon, 0, hosts.length);
        hosts = newCon;
        return this;
    }

    @Override
    public Container[] getContainer() {
        return hosts;
    }

    @Override
    public void fire() {
        fireProcess();
    }

    class StandardEngineValue extends Value{

        public StandardEngineValue(String name) {
            super(name);
        }

        @Override
        public void fireProcess() {
            System.out.println(getName() + "start...");
            for(Container container : getContainer()){
                container.fire();
            }
        }
    }
}
