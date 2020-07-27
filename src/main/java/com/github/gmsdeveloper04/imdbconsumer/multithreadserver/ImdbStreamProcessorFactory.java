package com.github.gmsdeveloper04.imdbconsumer.multithreadserver;

import java.net.Socket;

public interface ImdbStreamProcessorFactory {

	public ImdbStreamProcessor create(Socket socket);
}
