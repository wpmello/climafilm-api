package com.example.project.api.model.weather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BodyWeather {

    private Coord coord;
    private List<Weather> weather = new ArrayList<>();
    private Clouds clouds;
    private Main main;
    private Rain rain;
    private Sys sys;
    private Wind wind;

    private String base;
    private float visibility;
    private float dt;
    private float timezone;
    private float id;
    private String name;
    private Integer cod;
}
