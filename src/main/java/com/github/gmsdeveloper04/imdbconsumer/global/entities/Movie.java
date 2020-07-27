package com.github.gmsdeveloper04.imdbconsumer.global.entities;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Movie {
	
	@SerializedName("Title")
	private String title;
	@SerializedName("Year")
	private String year;
	@SerializedName("Rated")
	private String rated;
	@SerializedName("Released")
	private String released;
	@SerializedName("Runtime")
	private String runtime;
	@SerializedName("Genre")
	private String genre;
	@SerializedName("Director")
	private String director;
	@SerializedName("Writer")
	private String writer;
	@SerializedName("Actors")
	private String actor;
	@SerializedName("Plot")
	private String plot;
	@SerializedName("Language")
	private String language;
	@SerializedName("Country")
	private String country;
	@SerializedName("Awards")
	private String awards;
	@SerializedName("Poster")
	private String posters;
	@SerializedName("Ratings")
	private List<Rating> ratings;
	@SerializedName("Metascore")
	private String metascore;
	@SerializedName("imdbRating")
	private String imdbRating;
	@SerializedName("imdbVotes")
	private String imdbVotes;
	@SerializedName("imdbID")
	private String imdbid;
	@SerializedName("Type")
	private String type;
	@SerializedName("DVD")
	private String dvd;
	@SerializedName("BoxOffice")
	private String boxoffice;
	@SerializedName("Production")
	private String production;
	@SerializedName("Website")
	private String website;
	@SerializedName("Response")
	private boolean response;
	@SerializedName("Error")
	private String error;
}
