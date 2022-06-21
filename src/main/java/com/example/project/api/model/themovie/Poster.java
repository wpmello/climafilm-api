package com.example.project.api.model.themovie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
