package com.example.project.api.service;

import com.example.project.api.model.themovie.BodyMovies;
import com.example.project.api.model.themovie.Poster;
import com.example.project.api.model.weather.BodyWeather;
import com.example.project.api.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieService {

    private MovieRepository movieRepository;

    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }

    // WEATHER - GET
    public Long getTempCity(String city) {
        String urlFinal = getUrlWeather(city);
        return template()
                .getForObject(urlFinal, BodyWeather.class)
                .getMain()
                .getTemp();
    }

    // MOVIE - GET | Playing now
    public Poster getMovieOnPlayingNow() {
        String finalUrl = getUrlMovie();
        Poster entity = template()
                .getForObject(finalUrl, Poster.class);
        return entity;
    }

    // MOVIE - GET | Playing now + city
    public List<BodyMovies> getMovieOnPlayingNowPerCity(String city) {
        String urlFinalWeather = getUrlWeather(city);
        String urlFinalMovie = getUrlMovie();

        Long temp = template()
                .getForObject(urlFinalWeather, BodyWeather.class)
                .getMain()
                .getTemp();


        if (temp > 40) {
            List<BodyMovies> results = template()
                    .getForEntity(urlFinalMovie, Poster.class)
                    .getBody()
                    .getResults()
                    .stream()
                    .filter(movie -> movie.getGenre_ids().contains(28))
                    // I didn't use this method below 'cause it was hard to return some movie with this condition
                    //.filter(movie -> movie.getGenre_ids().size() == 1 && movie.getGenre_ids().contains(28))
                    .collect(Collectors.toList());
            log.info("The temp returned was {}", temp);
            return results;
        } else if (temp >= 36) {
            List<BodyMovies> results = template()
                    .getForEntity(urlFinalMovie, Poster.class)
                    .getBody()
                    .getResults()
                    .stream()
                    .filter(movie -> movie.getGenre_ids().contains(35))
                    .collect(Collectors.toList());
            log.info("The temp returned was {}", temp);
            return results;
        } else if (temp >= 20) {
            List<BodyMovies> results = template()
                    .getForEntity(urlFinalMovie, Poster.class)
                    .getBody()
                    .getResults()
                    .stream()
                    .filter(movie -> movie.getGenre_ids().contains(16))
                    .collect(Collectors.toList());
            log.info("The temp returned was {}", temp);
            return results;
        } else if (temp >= 0) {
            List<BodyMovies> results = template()
                    .getForEntity(urlFinalMovie, Poster.class)
                    .getBody()
                    .getResults()
                    .stream()
                    .filter(movie -> movie.getGenre_ids().contains(53))
                    .collect(Collectors.toList());
            log.info("The temp returned was {}", temp);
            return results;
        } else {
            List<BodyMovies> results = template()
                    .getForEntity(urlFinalMovie, Poster.class)
                    .getBody()
                    .getResults()
                    .stream()
                    .filter(movie -> movie.getGenre_ids().contains(99))
                    .collect(Collectors.toList());
            log.info("The temp returned was {}", temp);
            return results;
        }
    }

    public BodyMovies save(BodyMovies movie) {
        String urlFinalMovie = getUrlMovie();
        BodyMovies MovieToSave = this.movieRepository.save(movie);

        return MovieToSave;
    }

    // URL - MOVIE | method
    private String getUrlMovie() {
        String apiUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
        String apiKey = "43690bf9a399137442f8bb73b262f447";
        String language = "pt-BR";

        StringBuilder builder = new StringBuilder();
        String urlFinal = builder
                .append(apiUrl)
                .append(apiKey)
                .append("&language=")
                .append(language)
                .toString();
        return urlFinal;
    }

    //    URL - WEATHER | method
    private String getUrlWeather(String city) {
        String apiKey = "bb85471d2221957c640336916cec2bf7";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
        String units = "metric";

        StringBuilder builder = new StringBuilder();
        String urlFinal = builder
                .append(apiUrl)
                .append(city)
                .append("&appid=")
                .append(apiKey)
                .append("&units=")
                .append(units)
                .toString();
        return urlFinal;
    }
}
