package com.example.movie.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> hello() {
        return movieService.getMovies();
    }

    @PostMapping
    public void registerNewMovie(@RequestBody Movie movie) { movieService.addNewMovie(movie); }

    @DeleteMapping(path = "{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId) { movieService.deleteMovie(movieId); }

    @PutMapping(path = "{movieId}")
    public void updateMovie(
            @PathVariable("movieId") Long movieId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String director,
            @RequestParam(required = false) LocalDate releaseDate
            ) {
        movieService.updateMovie(movieId, title, director, releaseDate);
    }
}
