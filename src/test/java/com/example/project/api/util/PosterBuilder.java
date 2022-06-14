package com.example.project.api.util;

import com.example.project.api.model.themovie.BodyMovies;
import com.example.project.api.model.themovie.Poster;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class PosterBuilder {

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private List<BodyMovies> results = new ArrayList<>();

    @Builder.Default
    private Integer total_pages = 1;

    @Builder.Default
    private Integer total_results = 10;

    public Poster posterBuilder() {
        return new Poster(page, results, total_pages, total_results);
    }
}
