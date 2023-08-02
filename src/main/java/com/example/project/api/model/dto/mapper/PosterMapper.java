package com.example.project.api.model.dto.mapper;

import com.example.project.api.model.dto.PosterDTO;
import com.example.project.api.model.themovie.Poster;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PosterMapper {

    PosterMapper INSTANCE = Mappers.getMapper(PosterMapper.class);

    Poster toModel(PosterDTO dto);

    PosterDTO toDTO(Poster model);
}