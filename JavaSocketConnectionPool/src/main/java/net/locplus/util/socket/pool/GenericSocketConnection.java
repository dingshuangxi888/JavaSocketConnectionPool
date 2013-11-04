package net.locplus.util.socket.pool;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 13-10-18
 * Time: 上午10:46
 */
public class GenericSocketConnection implements SocketConnection {

    static final Logger logger = Logger.getLogger(GenericSocketConnection.class.getName());
    private Socket socket;

    private DataInputStream in;
    private PrintWriter out;

    private String host;
    private int port;

    private boolean busy = false;

    public GenericSocketConnection(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.socket = new Socket(this.host, this.port);
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public void sendData(String data) throws IOException {
        if (this.socket == null || this.socket.isClosed()) {
            this.socket = new Socket(this.host, this.port);
            this.socket.setSoTimeout(5000);
            this.in = new DataInputStream(socket.getInputStream());
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        }

        out.println(data);
    }

    @Override
    public String receiveData() throws IOException {
        String result = null;

        byte[] bytes = new byte[1024];
        int len = in.read(bytes);
        result = new String(bytes, 0, len);
        return result;
    }

    public void close() {
        this.busy = false;
    }

    @Override
    public void destory() {
        if (this.in != null) {
            try {
                this.in.close();
            } catch (IOException e) {
                logger.info("destory inputstream error " + e.getMessage());
            }
        }
        if (this.out != null) {
            this.out.close();
        }
        if (this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException e) {
                logger.info("destory socket error " + e.getMessage());
            }
        }
        this.out = null;
        this.in = null;
        this.socket = null;
    }
}
