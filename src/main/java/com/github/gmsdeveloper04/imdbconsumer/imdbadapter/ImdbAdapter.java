package com.github.gmsdeveloper04.imdbconsumer.imdbadapter;

import com.github.gmsdeveloper04.imdbconsumer.global.entities.Movie;

public interface ImdbAdapter {
	public Movie getMovieByTitle(String title);
}
