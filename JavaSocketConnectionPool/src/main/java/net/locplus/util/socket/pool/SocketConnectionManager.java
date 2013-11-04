package net.locplus.util.socket.pool;

import com.nc.loc.LocStar2;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA. User: hadoop Date: 13-10-18 Time: 下午5:05
 */
public class SocketConnectionManager {
    
    static final Logger logger = Logger.getLogger(LocStar2.class.getName());

    private SocketConnectionManager() {
        if (socketConnectionPool == null) {
            try {
                InputStream is = new FileInputStream("socket-pool.properties");
                Properties prop = new Properties();

                prop.load(is);
                SocketConnectionPoolFactory socketConnectionPoolFactory = new GenericSocketConnectionPoolFactory();
                socketConnectionPool = socketConnectionPoolFactory.createConnectionPool(prop);
                System.out.println("Java Socket connection pool created.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private SocketConnectionPool socketConnectionPool;

    public static SocketConnectionManager getInstance() {
        return SocketConnectionManagerSingletonHolder.instance;
    }

    public synchronized SocketConnectionPool getSocketConnectionPool() {

        return socketConnectionPool;
    }

    private static class SocketConnectionManagerSingletonHolder {

        private static SocketConnectionManager instance = new SocketConnectionManager();
    }
}
