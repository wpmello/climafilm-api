package com.example.project.api.util;

import com.example.project.api.model.themovie.Poster;

public class PosterCreator {
    public static Poster createValidPoster() {
        return Poster
                .builder()
                .build();
    }
}
