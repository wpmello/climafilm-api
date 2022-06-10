package com.example.project.api.model.themovie;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Poster {
    private Dates DatesObject;
    private Integer page;
    private List<BodyMovies> results = new ArrayList<>();
    private Integer total_pages;
    private Integer total_results;
}
