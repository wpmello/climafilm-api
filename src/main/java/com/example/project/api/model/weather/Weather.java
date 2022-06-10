package com.example.project.api.model.weather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Weather {
    private Long id;
    private String main;
    private String description;
    private String icon;
}