package com.example.mapper;

import com.example.dto.*;
import com.example.entity.ArtistEntity;

import java.time.LocalDateTime;

public class ArtistMapper {

    private ArtistMapper() {

    }

    public static ArtistFindAll toArtistFindAll(ArtistEntity entity) {
        return new ArtistFindAll(entity.getIdArtist(), entity.getName(), entity.getCountry(), entity.getGenre(), entity.getDebutYear());
    }

    public static ArtistEntity toEntity(ArtistCreate dto) {
        return ArtistEntity.builder()
                .name(dto.name())
                .country(dto.country())
                .genre(dto.genre())
                .debutYear(dto.debutYear())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ArtistCreated toArtistCreated(ArtistEntity entity) {
        return new ArtistCreated(entity.getIdArtist(), entity.getName(), entity.getCountry(), entity.getGenre(), entity.getDebutYear());
    }

    public static ArtistFindOne toArtistFindOne(ArtistEntity entity) {
        return new ArtistFindOne(entity.getIdArtist(), entity.getName(), entity.getCountry(), entity.getGenre(), entity.getDebutYear());
    }

    public static ArtistEntity toEntity(ArtistEntity entity, ArtistUpdate dto) {
        entity.setName(dto.name());
        entity.setCountry(dto.country());
        entity.setGenre(dto.genre());
        entity.setDebutYear(dto.debutYear());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static ArtistUpdated toArtistUpdated(ArtistEntity entity) {
        return new ArtistUpdated(entity.getIdArtist(), entity.getName(), entity.getCountry(), entity.getGenre(), entity.getDebutYear());
    }

    public static ArtistEntity toEntity(ArtistEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}