package com.example.project.api.model.themovie;

import java.util.List;

public record Poster(List<BodyMovie> results, Integer total_results) { }