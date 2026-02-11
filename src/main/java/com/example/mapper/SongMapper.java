package com.example.mapper;

import com.example.dto.*;
import com.example.entity.SongEntity;

import java.time.LocalDateTime;

public class SongMapper {

    private SongMapper() {

    }

    public static SongFindAll toSongFindAll(SongEntity entity) {
        return new SongFindAll(entity.getIdSong(), entity.getName(), entity.getArtist(), entity.getGenre(), entity.getDuration());
    }

    public static SongEntity toEntity(SongCreate dto) {
        return SongEntity.builder()
                .name(dto.name())
                .artist(dto.artist())
                .genre(dto.genre())
                .duration(dto.duration())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static SongCreated toSongCreated(SongEntity entity) {
        return new SongCreated(entity.getIdSong(), entity.getName(), entity.getArtist(), entity.getGenre(), entity.getDuration());
    }

    public static SongFindOne toSongFindOne(SongEntity entity) {
        return new SongFindOne(entity.getIdSong(), entity.getName(), entity.getArtist(), entity.getGenre(), entity.getDuration());
    }

    public static SongEntity toEntity(SongEntity entity, SongUpdate dto) {
        entity.setName(dto.name());
        entity.setArtist(dto.artist());
        entity.setGenre(dto.genre());
        entity.setDuration(dto.duration());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static SongUpdated toSongUpdated(SongEntity entity) {
        return new SongUpdated(entity.getIdSong(), entity.getName(), entity.getArtist(), entity.getGenre(), entity.getDuration());
    }

    public static SongEntity toEntity(SongEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}