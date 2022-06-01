package com.example.movie.user;

import com.example.movie.movie.Movie;
import com.example.movie.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;
    private final MovieService movieService;

    @Autowired
    public UserController(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void newUser(@RequestBody User user) { userService.newUser(user); }

    @PostMapping(path = "{userId}")
    public void addMovie(@RequestBody Movie movie, @PathVariable("userId") Long userId) {
        movie.setForeignKey(userId);
        movieService.addNewMovie(movie);
    }
}
