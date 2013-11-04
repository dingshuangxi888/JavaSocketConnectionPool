package net.locplus.util.socket.pool;

import com.nc.loc.LocStar2;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA. User: hadoop Date: 13-10-18 Time: 下午4:35
 */
public class GenericSocketConnectionPoolFactory implements SocketConnectionPoolFactory {

    static final Logger logger = Logger.getLogger(GenericSocketConnectionPoolFactory.class.getName());

    @Override
    public SocketConnectionPool createConnectionPool(Properties properties) {
        String host = (String) properties.getProperty("SERVER_HOST");
        int port = Integer.parseInt(properties.getProperty("SERVER_PORT"));
        String max_size_str = properties.getProperty("MAX_SIZE");
        String min_size_str = properties.getProperty("MIN_SIZE");

        int max_size = 20;
        int min_size = 10;
        if (max_size_str != null && !max_size_str.isEmpty()) {
            max_size = Integer.parseInt(max_size_str);
        }
        if (min_size_str != null && !min_size_str.isEmpty()) {
            min_size = Integer.parseInt(min_size_str);
        }

        return this.createConnectionPool(host, port, max_size, min_size);
    }

    @Override
    public SocketConnectionPool createConnectionPool(String host, int port, int maxSize, int minSize) {
        SocketConnectionFactory connectionFactory = new GenericSocketConnectionFactory();

        SocketConnectionPool socketConnectionPool = new GenericSocketConnectionPool(connectionFactory, host, port, maxSize, minSize);
        socketConnectionPool.init();
        return socketConnectionPool;
    }
}
