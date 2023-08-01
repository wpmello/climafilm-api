package com.example.project.api.service;

import com.example.project.api.model.themovie.BodyMovies;
import com.example.project.api.model.themovie.Poster;
import com.example.project.api.model.weather.BodyWeather;
import com.example.project.api.repository.MovieRepository;
import com.example.project.api.service.exceptions.MovieNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {
    private final RestTemplate restTemplate;
    private MovieRepository movieRepository;
    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getUrlMovie() {
        String apiUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
        String apiKey = "43690bf9a399137442f8bb73b262f447";
        String language = "pt-BR";

        return apiUrl +
                apiKey +
                "&language=" +
                language;
    }

    private String getUrlWeather(String city) {
        String apiKey = "bb85471d2221957c640336916cec2bf7";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
        String units = "metric";

        return apiUrl +
                city +
                "&appid=" +
                apiKey +
                "&units=" +
                units;
    }

    private BodyMovies verifyIfExits(int id) throws MovieNotFoundException {
        return this.movieRepository
                .findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    private List<BodyMovies> getMoviesByTemperatureAndGenre(Long temp) {
        List<BodyMovies> results = restTemplate
                .getForEntity(getUrlMovie(), Poster.class)
                .getBody()
                .getResults();

        Predicate<BodyMovies> genreFilter;

        if (temp > 40) {
            genreFilter = movie -> movie.getGenre_ids().contains(28);
        } else if (temp >= 36) {
            genreFilter = movie -> movie.getGenre_ids().contains(35);
        } else if (temp >= 20) {
            genreFilter = movie -> movie.getGenre_ids().contains(16);
        } else if (temp >= 0) {
            genreFilter = movie -> movie.getGenre_ids().contains(53);
        } else {
            genreFilter = movie -> movie.getGenre_ids().contains(99);
        }

        List<BodyMovies> filteredMovies = results.stream()
                .filter(genreFilter)
                .collect(Collectors.toList());

        log.info("The temp returned was {}", temp);
        return filteredMovies;
    }

    public Long getTempCity(String city) {
        return restTemplate
                .exchange(getUrlWeather(city),
                        HttpMethod.GET,
                        null,
                        BodyWeather.class)
                .getBody()
                .getMain()
                .getTemp();
    }

    public Poster getMovieOnPlayingNow() {
        return restTemplate.getForObject(getUrlMovie(), Poster.class);
    }

    public List<BodyMovies> getMovieOnPlayingNowPerCity(String city) {
        Long temp = restTemplate
                .getForObject(getUrlWeather(city), BodyWeather.class)
                .getMain()
                .getTemp();
        return getMoviesByTemperatureAndGenre(temp);
    }

    public List<BodyMovies> getMovies() {
        return this.movieRepository.findAll();
    }

    public BodyMovies getMovieById(int id) throws MovieNotFoundException {
        return verifyIfExits(id);
    }

    public BodyMovies save(BodyMovies movie) {
        return this.movieRepository.save(movie);
    }

    public BodyMovies update(int id, BodyMovies movie) throws MovieNotFoundException {
        BodyMovies movieToUpdate = verifyIfExits(id);

        movieToUpdate.setTitle(movie.getTitle());

        return this.movieRepository.save(movieToUpdate);
    }

    public void delete(int id) throws MovieNotFoundException {
        verifyIfExits(id);
        this.movieRepository.deleteById(id);
    }
}
