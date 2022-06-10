package com.example.project.api.controller;

import com.example.project.api.model.themovie.BodyMovies;
import com.example.project.api.model.themovie.Poster;
import com.example.project.api.service.MovieService;
import com.example.project.api.service.exceptions.MovieNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("app/movie")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private MovieService movieService;

    @GetMapping("/{city}")
    public Long getCity(@PathVariable String city) {
        return this.movieService.getTempCity(city);
    }

    @GetMapping("/on-playing")
    public Poster getMovieOnPlayingNow() {
        return this.movieService.getMovieOnPlayingNow();
    }

    @GetMapping("/on-playing/{city}")
    public List<BodyMovies> getMoviePerCity(@PathVariable String city) {
        return this.movieService.getMovieOnPlayingNowPerCity(city);
    }

    @GetMapping("/db")
    public List<BodyMovies> getAllMoviesOnDatabase() {
        List<BodyMovies> allMovieOnDatabase = this.movieService.getMovies();

        return allMovieOnDatabase;
    }

    @GetMapping("/db/{id}")
    public BodyMovies getAllMoviesOnDatabase(@PathVariable Integer id) throws MovieNotFoundException {
        BodyMovies allMovieOnDatabase = this.movieService.getMovieByIdOnDatabase(id);

        return allMovieOnDatabase;
    }

    @PostMapping("/db")
    @ResponseStatus(HttpStatus.CREATED)
    public BodyMovies AddMovie(@RequestBody @Valid BodyMovies movie) {
        return this.movieService.save(movie);
    }
}
