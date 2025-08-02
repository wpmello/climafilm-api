package com.example.project.api.model.themovie;

import java.util.List;

public record MovieDetail(
    boolean adult,
    String backdrop_path,
    Object belongs_to_collection,
    int budget,
    List<Genre> genres,
    String homepage,
    int id,
    String imdb_id,
    String original_language,
    String original_title,
    String overview,
    double popularity,
    String poster_path,
    List<ProductionCompany> production_companies,
    List<ProductionCountry> production_countries,
    String release_date,
    Long revenue,
    int runtime,
    List<SpokenLanguage> spoken_languages,
    String status,
    String tagline,
    String title,
    boolean video,
    double vote_average,
    int vote_count
) {}

