package com.example.project.api.service;

import com.example.project.api.model.themovie.BodyMovie;
import com.example.project.api.model.themovie.MovieDetail;
import com.example.project.api.model.themovie.Poster;
import com.example.project.api.model.weather.BodyWeather;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {
    // @Value("${movie.api.key}")
    private String movieApiKey;

    // @Value("${weather.api.key}")
    private String weatherApiKey;

    private final RestTemplate restTemplate;

    private final String NOW_PLAYING = "now_playing";
    private final String POPULAR = "popular";
    private final String TOP_RATED = "top_rated";
    private final String UPCOMING = "upcoming";
    private final String SEARCH = "search";
    private final String QUERY = "query";
    
    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getUrlMovie(String param1, String param2, String queryValue) {
        String baseUrl = "https://api.themoviedb.org/3";

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .pathSegment(param1)
                .pathSegment("movie");

        if (param2 != null && !param2.isEmpty()) {
            builder.pathSegment(param2);
        }
        
        if (queryValue != null && !queryValue.isEmpty()) {
            builder.queryParam(QUERY, queryValue);
        }

        builder.queryParam("api_key", "10df5ff760bcd7462684bd43487b0922")
            .queryParam("language", "pt-BR");

        return builder.toUriString();
    }

    private String getUrlWeather(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
        String units = "metric";

        return apiUrl +
                city +
                "&appid=" +
                "0e433d585f942cb93013e4f1341260bd" +
                "&units=" +
                units;
    }

    private Map<Long, List<BodyMovie>> getMoviesByTemperatureAndGenre(Long temp) {
        List<BodyMovie> results = restTemplate
                .getForEntity(getUrlMovie("", NOW_PLAYING, ""), Poster.class)
                .getBody()
                .results();

        int genreId = getGenreIdByTemperature(temp);

        List<BodyMovie> filteredMovies = results.stream()
                .filter(move -> move.genre_ids().contains(genreId))
                .collect(Collectors.toList());

        log.info("The temp returned was {}", temp);
        return Collections.singletonMap(temp, filteredMovies);
    }

    private int getGenreIdByTemperature(Long temp) {
        // TODO: remove magic numbers
        if (temp >= 38) {
            return 16;
        } else if (temp > 30) {
            return 28;
        } else if (temp > 20) {
            return 35;
        } else if (temp >= 0) {
            return 53;
        } else {
            return 99;
        }
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
        Poster poster = restTemplate.getForObject(getUrlMovie("", NOW_PLAYING, ""), Poster.class);
        return poster;
    }

    public Poster getPopularMovies() {
        Poster poster = restTemplate.getForObject(getUrlMovie("", POPULAR, ""), Poster.class);
        return poster;
    }

    public Poster getTopRatedMovies() {
        Poster poster = restTemplate.getForObject(getUrlMovie("", TOP_RATED, ""), Poster.class);
        return poster;
    }

    public Poster getUpcomingMovies() {
        Poster poster = restTemplate.getForObject(getUrlMovie("", UPCOMING, ""), Poster.class);
        return poster;
    }

    public MovieDetail getMovieByIdAPI(int id) {
        MovieDetail movieDetal = restTemplate
            .getForObject(getUrlMovie("", String.valueOf(id), ""), MovieDetail.class);
        return movieDetal;
    }

    public Poster getSearchMovies(String movieName) {
        Poster poster = restTemplate.getForObject(getUrlMovie(SEARCH, "", movieName), Poster.class);
        return poster;
    }

    public Map<Long, List<BodyMovie>> getMovieOnPlayingNowPerCity(String city) {
        Long temp = restTemplate
                .getForObject(getUrlWeather(city), BodyWeather.class)
                .getMain()
                .getTemp();
        return getMoviesByTemperatureAndGenre(temp);
    }
}
