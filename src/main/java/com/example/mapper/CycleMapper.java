package com.example.mapper;

import com.example.dto.*;
import com.example.entity.CycleEntity;

import java.time.LocalDateTime;

public class CycleMapper {

    private CycleMapper() {

    }

    public static CycleFindAll toCycleFindAll(CycleEntity entity) {
        return new CycleFindAll(entity.getIdCycle(), entity.getName());
    }

    public static CycleEntity toEntity(CycleCreate dto) {
        return CycleEntity.builder()
                .name(dto.name())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CycleCreated toCycleCreated(CycleEntity entity) {
        return new CycleCreated(entity.getIdCycle(), entity.getName());
    }

    public static CycleFindOne toCycleFindOne(CycleEntity entity) {
        return new CycleFindOne(entity.getIdCycle(), entity.getName());
    }

    public static CycleEntity toEntity(CycleEntity entity, CycleUpdate dto) {
        entity.setName(dto.name());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static CycleUpdated toCycleUpdated(CycleEntity entity) {
        return new CycleUpdated(entity.getIdCycle(), entity.getName());
    }

    public static CycleEntity toEntity(CycleEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}