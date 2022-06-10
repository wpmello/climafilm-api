package com.example.project.api.model.themovie;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Poster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer page;
    @Transient
    private List<BodyMovies> results = new ArrayList<>();
    private Integer total_pages;
    private Integer total_results;
}
