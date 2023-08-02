package com.example.project.api.service;

import com.example.project.api.model.themovie.BodyMovie;
import com.example.project.api.model.themovie.BodyMovies;
import com.example.project.api.model.themovie.Poster;
import com.example.project.api.util.PosterCreator;
import com.example.project.api.util.WeatherCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private final String VALID_CITY_NAME = "são paulo";

    @InjectMocks
    private MovieService movieService;

    @Test
    @DisplayName("getTempCity Returns temperature by chosen city when successful")
    void getTempCity_ReturnsTemperatureByChosenCityWhenSuccessful() {
        Long tempCity = movieService.getTempCity(VALID_CITY_NAME);

        Assertions.assertThat(tempCity).isNotNull();

        Assertions.assertThat(tempCity.equals(WeatherCreator.createValidWeather().getMain().getTemp()));
    }

    @Test
    @DisplayName("getMovieOnPlayingNow Returns return movies is playing now when successful")
    void getMovieOnPlayingNow_ReturnsMoviesIsPlayingNowWhenSuccessful() {
        List<BodyMovie> results = PosterCreator.createValidPoster().getResults();
        Poster movieOnPlayingNow = movieService.getMovieOnPlayingNow();

        Assertions.assertThat(movieOnPlayingNow).isNotNull();
        Assertions.assertThat(movieOnPlayingNow.getResults()).isNotEmpty();
        Assertions.assertThat(movieOnPlayingNow.getResults().equals(results));
    }
}