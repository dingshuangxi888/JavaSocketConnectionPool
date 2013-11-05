JavaSocketConnectionPool
========================

1.Copy the socket-pool.properties from JavaSocketConnectionPool/src/main/resources/ to the src folder in your project

2.Modify the SERVER_HOST and SERVER_PORT

3.Using java socket connection pool project is so simple, just write codes as follows:

  SocketConnectionPool connectionPool = SocketConnectionManager.getInstance().getSocketConnectionPool();
  SocketConnection connection = connectionPool.getConnection();
  String msg = "Hello World!";
  connection.sendData(msg);
  String receiveData = connection.receiveData();
  System.out.println(receiveData);
