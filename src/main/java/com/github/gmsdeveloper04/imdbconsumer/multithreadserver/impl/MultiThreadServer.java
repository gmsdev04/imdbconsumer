package com.github.gmsdeveloper04.imdbconsumer.multithreadserver.impl;

import java.net.Socket;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class MultiThreadServer extends com.github.gmsdeveloper04.imdbconsumer.multithreadserver.MultiThreadServer{
	
	@Inject
	public MultiThreadServer(@Assisted Socket socket) {
		super(socket);
	}

	public void run() {
		
	}

}
