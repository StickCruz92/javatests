package com.stickcruz.javatests.movie;

import static org.hamcrest.CoreMatchers.is;import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.stickcruz.javatests.movies.data.MovieRepositoryImpl;
import com.stickcruz.javatests.movies.model.Genre;
import com.stickcruz.javatests.movies.model.Movie;
import com.stickcruz.javatests.movies.service.MovieService;

public class MovieRepositoryIntegrationTest {

	private  MovieRepositoryImpl movieRepository;
	private DataSource dataSource;
	
	@Before
	public void setUp() throws Exception {
		
		    dataSource =  new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");

	        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sqlScripts/test-data.sql"));

	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	        movieRepository = new MovieRepositoryImpl(jdbcTemplate);
		
	}
	
	 @Test
	    public void load_all_movies() throws SQLException {

	        Collection<Movie> movies = movieRepository.findAll();

	        assertThat( movies, is(Arrays.asList(
	                new Movie(1, "Dark Knight", 152, Genre.ACTION),
	                new Movie(2, "Memento", 113, Genre.THRILLER),
	                new Movie(3, "Matrix", 136, Genre.ACTION)
	        )) );
	    }
	 
	 @Test
	    public void load_movies_by_id() throws SQLException {
		 Movie movie = movieRepository.findById(2);
		 assertThat(movie, is(new Movie(2, "Memento", 113, Genre.THRILLER)));
	 }
	 
	 
	 @Test
	    public void insert_a_movie() throws SQLException {
		 Movie movie = new Movie("Super 8", 112, Genre.THRILLER);
		 movieRepository.saveOrUpdate(movie);
		 Movie movieExpeted = movieRepository.findById(4);
		 assertThat(movieExpeted, is(new Movie(4, "Super 8", 112, Genre.THRILLER)));
	 }

	 @After
	 public void tearDown() throws Exception {
		 final Statement s = dataSource.getConnection().createStatement();
		 s.execute("drop all objects delete files");
	 }
}
