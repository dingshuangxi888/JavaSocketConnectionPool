package net.locplus.util.socket.pool;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 13-10-18
 * Time: 下午3:36
 */
public interface SocketConnectionPoolFactory {

    public abstract SocketConnectionPool createConnectionPool(Properties properties);

    public abstract SocketConnectionPool createConnectionPool(String host, int port, int maxSize, int minSize);
}
