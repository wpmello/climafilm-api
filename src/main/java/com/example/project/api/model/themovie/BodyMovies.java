package com.example.project.api.model.themovie;

import lombok.Data;

@Data
public class BodyMovies {
    private boolean adult;
    private String backdrop_path;
    private Integer[] genre_ids;
    private Integer id;
    private String original_language;
    private String original_title;
    private String overview;
    private Long popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private Long vote_average;
    private Integer vote_count;
}
