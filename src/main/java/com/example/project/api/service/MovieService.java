package com.example.project.api.service;

import com.example.project.api.model.themovie.Poster;
import com.example.project.api.model.weather.BodyWeather;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

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
