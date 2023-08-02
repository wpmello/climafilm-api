package com.example.project.api.util;

import com.example.project.api.model.dto.PosterDTO;

public class PosterCreator {
    public static PosterDTO createValidPoster() {
        return PosterDTO
                .builder()
                .build();
    }
}
