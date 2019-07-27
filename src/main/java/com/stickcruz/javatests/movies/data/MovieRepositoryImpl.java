package com.stickcruz.javatests.movies.data;

import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.stickcruz.javatests.movies.model.Genre;
import com.stickcruz.javatests.movies.model.Movie;

public class MovieRepositoryImpl implements MovieRepository {

	private JdbcTemplate jdbcTemplate;

	public MovieRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Movie findById(long id) {
		// TODO Auto-generated method stub
		Object[] args = { id };
		return jdbcTemplate.queryForObject("select * from movies where id = ?", args, movieMapper);
	}

	@Override
	public Collection<Movie> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from movies", movieMapper);
	}

	@Override
	public void saveOrUpdate(Movie movie) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into movies (name, minutes, genre) values (?, ?, ?)", 
				movie.getName(), movie.getMinutes(), movie.getGenre().toString());

	}
	
	private static RowMapper<Movie> movieMapper = (rs, rowNum) ->
	        new Movie(rs.getInt("id"), 
	        		rs.getString("name"),
	        		rs.getInt("minutes"),
					Genre.valueOf(rs.getString("genre")));



}
