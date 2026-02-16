package com.example.mapper;

import com.example.dto.*;
import com.example.entity.ReportEntity;

import java.time.LocalDateTime;

public class ReportMapper {

    private ReportMapper() {
    }

    public static ReportFindAll toFindAll(ReportEntity entity) {
        return new ReportFindAll(entity.getIdReport(), entity.getType(), entity.getName(), entity.getDescription());
    }

    public static ReportEntity toEntityCreated(ReportCreate dto) {
        return ReportEntity.builder()
                .type(dto.type())
                .name(dto.name())
                .description(dto.description())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ReportCreated toCreated(ReportEntity entity) {
        return new ReportCreated(entity.getIdReport(), entity.getType(), entity.getName(), entity.getDescription());
    }

    public static ReportFindOne toFindOne(ReportEntity entity) {
        return new ReportFindOne(entity.getIdReport(), entity.getType(), entity.getName(), entity.getDescription());
    }

    public static ReportEntity toEntityUpdated(ReportEntity entity, ReportUpdate dto) {
        entity.setType(dto.type());
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static ReportUpdated toUpdated(ReportEntity entity) {
        return new ReportUpdated(entity.getIdReport(), entity.getType(), entity.getName(), entity.getDescription());
    }

    public static ReportEntity toEntityDeleted(ReportEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}