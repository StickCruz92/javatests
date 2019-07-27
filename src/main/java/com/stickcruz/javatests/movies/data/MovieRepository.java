package com.stickcruz.javatests.movies.data;

import java.util.Collection;

import com.stickcruz.javatests.movies.model.Movie;

public interface MovieRepository {

    Movie findById(long id);
    Collection<Movie> findAll();
    void saveOrUpdate(Movie movie);
    
}