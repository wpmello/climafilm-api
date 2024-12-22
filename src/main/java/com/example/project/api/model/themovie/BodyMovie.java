package com.example.project.api.model.themovie;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.List;

public record BodyMovie(
        Integer id,
        boolean adult,
        @Transient
        List<Integer> genre_ids,
        String original_title,
        String overview,
        Long popularity,
        String poster_path,
        String release_date,
        @NotBlank
        String title,
        boolean video,
        double vote_average,
        int vote_count) { }