JavaSocketConnectionPool
========================

1.Copy the socket-pool.properties from JavaSocketConnectionPool/src/main/resources/ to the src folder in your project.

2.Modify the SERVER_HOST and SERVER_PORT.

3.Using java socket connection pool project is so simple, just write codes as follows:

```java
  SocketConnectionPool connectionPool = SocketConnectionManager.getInstance().getSocketConnectionPool();
  SocketConnection connection = connectionPool.getConnection();
  String msg = "Hello World!";
  connection.sendData(msg);
  String receiveData = connection.receiveData();
  System.out.println(receiveData);
```

4.To close the socket connection pool, you can invoke as follows:

```java
  SocketConnectionManager.getInstance().getSocketConnectionPool().destory();
```
  
5.Invoke `connection.close()` just put the connection back to pool, not really to close the socket connection

6.If you want to really close the socket connection you can invoke `connection.destory()` to do it.
