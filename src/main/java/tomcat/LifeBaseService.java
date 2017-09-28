package tomcat;

/**
 * Created by 听风 on 2017/8/19.
 */
public abstract class LifeBaseService implements LifeCycle{
    @Override
    public void init() {
        initInternal();
    }

    protected abstract void initInternal();

    @Override
    public void start() {
        startInternal();
    }

    protected abstract void startInternal();

    @Override
    public void stop() {

    }

    @Override
    public void distroy() {

    }
}
