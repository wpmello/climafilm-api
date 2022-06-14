package com.example.project.api.service;

import com.example.project.api.model.themovie.Poster;
import com.example.project.api.util.PosterBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    Poster poster = PosterBuilder
            .builder()
            .build()
            .posterBuilder();

//    @Test
//    void someTest() {
//        Assertions
//    }
}