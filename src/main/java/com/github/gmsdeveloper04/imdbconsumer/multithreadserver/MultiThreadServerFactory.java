package com.github.gmsdeveloper04.imdbconsumer.multithreadserver;

import java.net.Socket;

public interface MultiThreadServerFactory {

	public MultiThreadServer create(Socket socket);
}
