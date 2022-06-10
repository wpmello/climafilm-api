package com.example.project.api.controller;

import com.example.project.api.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherController {

    private WeatherService weatherService;

    @GetMapping("/{city}")
    public Long getCity(@PathVariable String city) {
        return this.weatherService.getTempCity(city);
    }
}
