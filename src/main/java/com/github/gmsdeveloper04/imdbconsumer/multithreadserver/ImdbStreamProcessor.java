package com.github.gmsdeveloper04.imdbconsumer.multithreadserver;

import java.io.Closeable;
import java.net.Socket;

public abstract class ImdbStreamProcessor implements Runnable, Closeable{
	
	protected Socket socket;
	
	public ImdbStreamProcessor( Socket socket) {
		this.socket = socket;
	}
	
}
