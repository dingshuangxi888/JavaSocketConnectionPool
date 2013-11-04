package net.locplus.util.socket.pool;

import org.apache.log4j.Logger;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 13-10-18
 * Time: 上午10:47
 */
public class GenericSocketConnectionPool implements SocketConnectionPool {

    static final Logger logger = Logger.getLogger(GenericSocketConnectionPool.class.getName());
    private SocketConnectionFactory connectionFactory;

    private String HOST;
    private int PORT;
    private int MAX_SIZE;
    private int MIN_SIZE;

    private Vector<SocketConnection> connections = null;

    public GenericSocketConnectionPool(SocketConnectionFactory connectionFactory, String HOST, int PORT, int MAX_SIZE, int MIN_SIZE) {
        this.connectionFactory = connectionFactory;
        this.HOST = HOST;
        this.PORT = PORT;
        this.MAX_SIZE = MAX_SIZE;
        this.MIN_SIZE = MIN_SIZE;
    }

    public void setConnectionFactory(SocketConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public synchronized SocketConnection getConnection() {
        SocketConnection result = null;

        //traversal the pool to get free connections
        for (SocketConnection connection : connections) {
            if (connection != null) {
                if (!connection.isBusy()) {
                    result = connection;
                    logger.info("Using connection from pool.");
                    break;
                }
            } else {
                connection = connectionFactory.createConnection(HOST, PORT);
                logger.info("Connection from pool has closed and create again.");
                result = connection;
            }
        }
        if (result == null) {
            //if the pool size < max pool size, then create; else wait and try again

            if (connections.size() < MAX_SIZE) {
                SocketConnection newConnection = connectionFactory.createConnection(HOST, PORT);
                connections.addElement(newConnection);
                logger.info("No free connections in the pool, and pool size < max, so create more.");
                result = newConnection;
            } else {
                logger.info("No free connnection in the pool, and pool size = max, so wait for free.");
                while (result == null) {
                    try {
                        Thread.sleep(500);
                        result = this.getConnection();
                    } catch (InterruptedException e) {
                        logger.error("Get connection thread error:" + e.getMessage());
                    }
                }
            }
            logger.info("the size of pool size " + connections.size());
        }

        result.setBusy(true);
        return result;
    }

    @Override
    public void init() {
        connections = new Vector<SocketConnection>();
        for (int i = 0; i < MIN_SIZE; i++) {
            connections.addElement(connectionFactory.createConnection(HOST, PORT));
        }
        logger.info("Socket connection created " + connections.size());
    }

    @Override
    public void restart() {
        destory();
        init();
    }

    @Override
    public void destory() {
        for (SocketConnection connection : connections) {
            if (connection != null) {
                connection.destory();
                connection = null;
            }
        }
    }
}
