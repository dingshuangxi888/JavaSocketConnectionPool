package net.locplus.util.socket.pool;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 13-10-18
 * Time: 下午3:30
 */
public interface SocketConnectionPool {

    public SocketConnection getConnection();

    public void init();

    public void restart();

    public void destory();
}
