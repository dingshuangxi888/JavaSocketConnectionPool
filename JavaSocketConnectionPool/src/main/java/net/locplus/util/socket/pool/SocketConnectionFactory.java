package net.locplus.util.socket.pool;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 13-10-18
 * Time: 下午3:35
 */
public interface SocketConnectionFactory {

    public abstract SocketConnection createConnection(String host, int port);
}
