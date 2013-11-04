package net.locplus.util.socket.pool;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 13-10-18
 * Time: 下午3:30
 */
public interface SocketConnection {

    public boolean isBusy();

    public void setBusy(boolean busy);

    public void sendData(String data) throws IOException;

    public String receiveData() throws IOException;

    public void close();

    public void destory();
}
