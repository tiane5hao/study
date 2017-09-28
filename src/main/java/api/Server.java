package api;

import java.io.IOException;

/**
 * Created by 听风 on 2017/6/23.
 */
public interface Server {
    public void stop();

    public void start() throws IOException;

    public void register(Class serviceInterface, Class impl);

    public boolean isRunning();

    public int getPort();
}
