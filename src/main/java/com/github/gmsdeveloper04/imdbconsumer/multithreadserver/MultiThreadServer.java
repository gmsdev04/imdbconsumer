package com.github.gmsdeveloper04.imdbconsumer.multithreadserver;

import java.net.Socket;

public abstract class MultiThreadServer implements Runnable{
	
	protected Socket socket;
	
	public MultiThreadServer( Socket socket) {
		this.socket = socket;
	}
	
}
