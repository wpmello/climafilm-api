package com.example.project.api.service;

import com.example.project.api.model.dto.BodyMovieDTO;
import com.example.project.api.model.dto.PosterDTO;
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
        List<BodyMovieDTO> results = PosterCreator.createValidPoster().results();
        PosterDTO movieOnPlayingNow = movieService.getMovieOnPlayingNow();

        Assertions.assertThat(movieOnPlayingNow).isNotNull();
        Assertions.assertThat(movieOnPlayingNow.results()).isNotEmpty();
        Assertions.assertThat(movieOnPlayingNow.results().equals(results));
    }
}