package com.example.movie.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }


    public Movie getMovie(Long movieId) {
        return movieRepository.findMovieById(movieId);
    }

    public void addNewMovie(Movie movie) {
        Optional<Movie> movieOptional = movieRepository.findMovieByTitle(movie.getTitle());
        if(movieOptional.isPresent()) {
            throw new IllegalArgumentException("title taken");
        }
        movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        boolean exists = movieRepository.existsById(movieId);
        if(!exists) {
            throw new IllegalArgumentException("movie with id " + movieId + " doesn't exist");
        }
        movieRepository.deleteById(movieId);
    }

    @Transactional
    public void updateMovie(Long movieId, String title, String director, LocalDate releaseDate) {
        Movie movie = movieRepository.getById(movieId);

        if(movie == null) {
            throw new IllegalArgumentException("movie with id " + movieId + " doesn't exist");
        }

        if(title != null && title.length() > 0 && !Objects.equals(movie.getTitle(), title))
            movie.setTitle(title);

        if(director != null && director.length() > 0 && !Objects.equals(movie.getDirector(), director))
            movie.setDirector(director);

        if(releaseDate != null && !Objects.equals(movie.getReleaseDate(), releaseDate))
            movie.setReleaseDate(releaseDate);
    }

}
