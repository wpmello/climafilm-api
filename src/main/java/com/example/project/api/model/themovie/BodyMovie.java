package com.example.project.api.model.themovie;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class BodyMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean adult;
    private String backdrop_path;
    @Transient
    private List<Integer> genre_ids = new ArrayList<>();
    private String original_language;
    private String original_title;
    private String overview;
    private Long popularity;
    private String poster_path;
    private String release_date;
    @NotBlank
    private String title;
    private boolean video;
    private Long vote_average;
    private Integer vote_count;
}
