package com.example.project.api.model.dto.mapper;

import com.example.project.api.model.dto.BodyMovieDTO;
import com.example.project.api.model.themovie.BodyMovies;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BodyMovieMapper {

    BodyMovieMapper INSTANCE = Mappers.getMapper(BodyMovieMapper.class);

    BodyMovies toModel(BodyMovieDTO dto);

    BodyMovieDTO toDTO(BodyMovies model);
}