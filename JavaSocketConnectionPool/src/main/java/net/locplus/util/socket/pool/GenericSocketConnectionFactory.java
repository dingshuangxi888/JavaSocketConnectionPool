package net.locplus.util.socket.pool;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: hadoop Date: 13-10-18 Time: 下午4:27
 */
public class GenericSocketConnectionFactory implements SocketConnectionFactory {

    static final Logger logger = Logger.getLogger(GenericSocketConnectionFactory.class.getName());

    @Override
    public SocketConnection createConnection(String host, int port) {
        SocketConnection connection = null;
        try {
            connection = new GenericSocketConnection(host, port);
        } catch (IOException e) {
            logger.error("Create connection error:" + e.getMessage());
        }
        return connection;
    }
}
