package com.stickcruz.javatests.movies.service;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.stickcruz.javatests.movies.data.MovieRepository;
import com.stickcruz.javatests.movies.model.Genre;
import com.stickcruz.javatests.movies.model.Movie;

public class MovieService {

	 private MovieRepository movieRepository;

	    public MovieService(MovieRepository movieRepository) {
	        this.movieRepository = movieRepository;
	    }

	    public Collection<Movie> findMoviesByGenre(Genre genre) {

	        return movieRepository.findAll().stream()
	                .filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());
	    }

	    public Collection<Movie> findMoviesByLength(int length) {

	        return movieRepository.findAll().stream()
	                .filter(movie -> movie.getMinutes() <= length).collect(Collectors.toList());
	    }
	
	    
	    public Collection<Movie> findMoviesByName(String name) {

	        return movieRepository.findAll().stream()
	                .filter(movie -> movie.getName().toLowerCase().contains(name.toLowerCase()))
	                .collect(Collectors.toList());
	    }
}
