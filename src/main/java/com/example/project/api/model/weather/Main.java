package com.example.project.api.model.weather;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Main {

    private Long temp;
    private Long feels_like;
    private Long temp_min;
    private Long temp_max;
    private Integer pressure;
    private Integer humidity;
}
