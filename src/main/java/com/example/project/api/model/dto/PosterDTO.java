package com.example.project.api.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PosterDTO(List<BodyMovieDTO> results, Integer total_results) { }