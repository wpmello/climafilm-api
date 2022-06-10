package com.example.project.api.service;

import com.example.project.api.model.BodyWeather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    public Long getTempCity(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String urlFinal = getUrlWeather(city);
        return restTemplate
                .getForObject(urlFinal, BodyWeather.class)
                .getMain()
                .getTemp();
    }

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
