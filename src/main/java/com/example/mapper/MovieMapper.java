package com.example.mapper;

import com.example.dto.*;
import com.example.entity.MovieEntity;

import java.time.LocalDateTime;

public class MovieMapper {

    private MovieMapper() {
    }

    public static MovieFindAll toFindAll(MovieEntity entity) {
        return new MovieFindAll(entity.getIdMovie(), entity.getName(), entity.getDirector(), entity.getDuration(), entity.getYear(), entity.getGenre());
    }

    public static MovieEntity toEntityCreated(MovieCreate dto) {
        return MovieEntity.builder()
                .name(dto.name())
                .director(dto.director())
                .year(dto.year())
                .duration(dto.duration())
                .genre(dto.genre())
                .createdAt(LocalDateTime.now())
                .enabled(true)
                .build();
    }

    public static MovieCreated toMovieCreated(MovieEntity entity) {
        return new MovieCreated(entity.getIdMovie(), entity.getName(), entity.getDirector(), entity.getDuration(), entity.getYear(), entity.getGenre());

    }

    public static MovieFindOne toFindOne(MovieEntity entity) {
        return new MovieFindOne(entity.getIdMovie(), entity.getName(), entity.getDirector(), entity.getDuration(), entity.getYear(), entity.getGenre());
    }

    public static MovieEntity toEntityUpdated(MovieEntity entity, MovieUpdate dto) {
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setDirector(dto.director());
        entity.setName(dto.name());
        entity.setDuration(dto.duration());
        entity.setYear(dto.year());
        entity.setGenre(dto.genre());
        return entity;
    }

    public static MovieUpdated toMovieUpdated(MovieEntity entity) {
        return new MovieUpdated(
                entity.getIdMovie(),
                entity.getName(),
                entity.getDirector(),
                entity.getDuration(),
                entity.getYear(),
                entity.getGenre()
        );
    }
    public static MovieEntity deleted(MovieEntity entity){
        entity.setDeletedAt(LocalDateTime.now());
        entity.setEnabled(false);
        return entity;
    }
}
