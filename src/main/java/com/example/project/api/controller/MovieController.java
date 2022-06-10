package com.example.project.api.controller;

import com.example.project.api.model.themovie.Poster;
import com.example.project.api.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
