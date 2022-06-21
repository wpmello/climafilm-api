package com.example.project.api.util;

import com.example.project.api.model.weather.BodyWeather;
import com.example.project.api.model.weather.Main;

public class WeatherCreator {

    public static BodyWeather createValidWeather() {
        return BodyWeather
                .builder()
                .name("sã paulo")
                .main(new Main())
                .build();
    }
}
