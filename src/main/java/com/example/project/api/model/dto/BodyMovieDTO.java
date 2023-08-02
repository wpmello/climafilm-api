package com.example.project.api.model.dto;

import lombok.Builder;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
public record BodyMovieDTO(
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
        String title) { }