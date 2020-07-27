package com.github.gmsdeveloper04.imdbconsumer.multithreadserver.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.gmsdeveloper04.imdbconsumer.global.entities.Movie;
import com.github.gmsdeveloper04.imdbconsumer.imdbadapter.ImdbAdapter;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImdbStreamProcessor extends com.github.gmsdeveloper04.imdbconsumer.multithreadserver.ImdbStreamProcessor{

	@Inject
	private ImdbAdapter imbdbAdapter;

	@Inject
	private Gson gson;

	@Inject
	public ImdbStreamProcessor(@Assisted Socket socket) {
		super(socket);
	}

	public void run() {
		List<Movie> movies = new ArrayList<>();

		//LENDO DADOS DO STREAM
		welcomeMessage();


		List<String> titles = readInputAndReturnTitles();

		titles.forEach(x -> movies.add(imbdbAdapter.getMovieByTitle(x)));

		generateOutput(movies);

	}

	private void welcomeMessage() {

		writeOutputMessage("|----------------------------------------------------------------------------|\n",
				"|         -> Bem vindo ao consumer IMDB a base de dados de filmes! <-        |\n",
				"|                             Developer: GMSDEV04                            |\n",
				"|                              Created: 22/07/2020                           |\n",
				"|                                                                            |\n",
				"|----------------------------------------------------------------------------|\n",
				"Digite abaixo os filmes desejados. ( Para fechar a lista digite !sair ) \n",
				"-> ");

	}

	private void writeOutputMessage(String ... messages) {
		PrintWriter outputWriter = null;
		try {
			outputWriter = new PrintWriter(this.socket.getOutputStream());

			for(int i = 0 ;i < messages.length ; i++) {
				outputWriter.write(messages[i]);
			}

		} catch (IOException e) {
			log.error(new StringBuilder("Falha ao enviar output message e=").append(e.getMessage()).toString());
			e.printStackTrace();
		}finally {
			outputWriter.flush();
		}

	}

	private List<String> readInputAndReturnTitles()  {
		List<String> titles = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readLine = "";
			do {
				readLine = br.readLine();
				if(!readLine.equals("!sair")) {
					titles.add(readLine);
					writeOutputMessage("-> ");
				}
			}while(!readLine.equals("!sair"));

		} catch (IOException e) {
			log.error(new StringBuilder("Falha ao realizar a leitura do inputStream, leitura da lista de filmes. e=").append(e.getMessage()).toString());
		}
		return titles;
	}

	private void generateOutput(List<Movie> movies) {
		
		writeOutputMessage(" Abaixo está a lista com os filmes. \n \n");
		writeOutputMessage("Encontrados. \n");
		
		List<Movie> encontrados = movies.stream().filter(x -> x.isResponse()).collect(Collectors.toList());
		
		encontrados.forEach(x -> writeOutputMessage("Nome:"+x.getTitle(),"\n","Dados:",x.toString(),"\n \n"));
		

		writeOutputMessage("\n \n (Os titulos não presentes aqui não foram encontrados na base de dados) \n");
		
	}

	public void close() throws IOException {

		this.socket.close();
	}



}
