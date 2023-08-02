package com.example.project.api.model.dto.mapper;

import com.example.project.api.model.dto.BodyMovieDTO;
import com.example.project.api.model.themovie.BodyMovie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BodyMovieMapper {

    BodyMovieMapper INSTANCE = Mappers.getMapper(BodyMovieMapper.class);

    BodyMovie toModel(BodyMovieDTO dto);

    BodyMovieDTO toDTO(BodyMovie model);
}