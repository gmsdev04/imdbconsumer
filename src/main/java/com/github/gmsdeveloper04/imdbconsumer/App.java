package com.github.gmsdeveloper04.imdbconsumer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.gmsdeveloper04.imdbconsumer.di.DependencyInjectionModule;
import com.github.gmsdeveloper04.imdbconsumer.multithreadserver.MultiThreadServerFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App 
{	
	private static final Injector INJECTOR = Guice.createInjector(new DependencyInjectionModule());

	
	public static void main( String[] args )
	{	
		MultiThreadServerFactory threadFactory = INJECTOR.getInstance(MultiThreadServerFactory.class);
		int serverPort = 1998;
		
		log.info(new StringBuilder("Initializing consumer on port ").append(serverPort).toString());
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(serverPort);
			
			while(true) {
				Socket socketOfTime = serverSocket.accept();
				
				StringBuilder connectedMessage = new StringBuilder()
						.append("Connected: IP:").append(socketOfTime.getInetAddress())
						.append(" Port: ").append(socketOfTime.getPort());
				
				log.info(connectedMessage.toString());
				
				new Thread(threadFactory.create(socketOfTime)).start();
			}

		} catch (IOException e) {
			log.error(new StringBuilder("Falha ao iniciarlizar consumer na porta ").append(serverPort).toString());
			e.printStackTrace();
		}finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.error(new StringBuilder("Falha ao fechar conexão TCP serverSocket. e=").append(e.getMessage()).toString());
			}
		}
	}
}
