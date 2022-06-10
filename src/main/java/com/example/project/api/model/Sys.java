package com.example.project.api.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Sys {

    private Integer type;
    private Long id;
    private Long message;
    private String country;
    private Long sunrise;
    private Long sunset;
}
