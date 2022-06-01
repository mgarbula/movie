package com.example.movie.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> { // Long - typ ID w Movie

    @Query("SELECT s FROM Movie s WHERE s.title = ?1")
    Optional<Movie> findMovieByTitle(String title);

    @Query("SELECT s FROM Movie s WHERE s.id = ?1")
    Movie findMovieById(Long movieId);
}
