package com.example.project.api.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(Integer id) {
        super(String.format("Movie with id %d not found. Try again!", id));
    }
}
