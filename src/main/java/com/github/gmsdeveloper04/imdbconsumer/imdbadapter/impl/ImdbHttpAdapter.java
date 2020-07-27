package com.github.gmsdeveloper04.imdbconsumer.imdbadapter.impl;

import java.net.URLEncoder;

import com.github.gmsdeveloper04.imdbconsumer.global.entities.Movie;
import com.github.gmsdeveloper04.imdbconsumer.imdbadapter.ImdbAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.Restfulie;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImdbHttpAdapter implements ImdbAdapter
{
	@Inject
	private Gson gson;

	public Movie getMovieByTitle(String title) {

		//ESTAVA TRABALHANDO E CODANDO O TESTE, USEI O RESTFULIE POR SER MAIS PRÁTICO XD =), PÓDERIA TER USADO FEIGN CLIENT, REST TEMPLATE, APACHE HTTP CALL
		
		try {
			StringBuilder url = new StringBuilder("http://www.omdbapi.com/?i=tt3896198&apikey=9c4e170&t=").append(URLEncoder.encode(title,"UTF-8")); //CHUMBADO MAS PODERIA SER DINAMICO VINDO DO PROPERTIES

			Response response = Restfulie.at(url.toString()).get();

			Movie returnedMovie = gson.fromJson(response.getContent(), Movie.class);

			return returnedMovie;
			
		}catch(JsonSyntaxException e) {
			log.error(new StringBuilder()
					.append("Falha ao deserializar json provido pelo IMBD. e=").append(e.getMessage())
					.append(" Movie title=").append(title)
					.toString());
		}catch(Exception e) {
			log.error(new StringBuilder()
					.append("Falha ao tentar obter dados da base IMDB. e=").append(e.getMessage())
					.append(" Movie title=").append(title)
					.toString());
		}
		return null;
	}


}