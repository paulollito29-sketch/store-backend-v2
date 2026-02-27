package com.example.mapper;

import com.example.dto.*;
import com.example.entity.SectionEntity;

import java.time.LocalDateTime;

public class SectionMapper {

    private SectionMapper() {
    }

    public static SectionFindAll ToSectionFindAll(SectionEntity entity) {
        return new SectionFindAll(entity.getIdSection(), entity.getName());
    }

    public static SectionEntity ToEntity(SectionCreate dto) {
        return SectionEntity.builder()
                .name(dto.name())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static SectionCreated ToSectionCreated(SectionEntity entity) {
        return new SectionCreated(entity.getIdSection(), entity.getName());
    }

    public static SectionFindOne ToSectionFindOne(SectionEntity entity) {
        return new SectionFindOne(entity.getIdSection(), entity.getName());
    }

    public static SectionEntity ToEntity(SectionEntity entity, SectionUpdate dto) {
        entity.setName(dto.name());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static SectionUpdated ToSectionUpdated(SectionEntity entity) {
        return new SectionUpdated(entity.getIdSection(), entity.getName());
    }

    public static SectionEntity ToEntity(SectionEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}