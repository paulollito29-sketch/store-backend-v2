package com.example.mapper;

import com.example.dto.*;
import com.example.entity.ClassroomEntity;

import java.time.LocalDateTime;

public class ClassroomMapper {

    private ClassroomMapper() {
    }

    public static ClassroomFindAll toFindAll(ClassroomEntity entity) {
        return new ClassroomFindAll(entity.getIdClassroom(), entity.getCourse(), entity.getBlock());
    }

    public static ClassroomEntity toEntityCreated(ClassroomCreate dto) {
        return ClassroomEntity.builder()
                .course(dto.course())
                .block(dto.block())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ClassroomCreated toCreated(ClassroomEntity entity) {
        return new ClassroomCreated(entity.getIdClassroom(), entity.getCourse(), entity.getBlock());
    }

    public static ClassroomFindOne toFindOne(ClassroomEntity entity) {
        return new ClassroomFindOne(entity.getIdClassroom(), entity.getCourse(), entity.getBlock());
    }

    public static ClassroomEntity toEntityUpdated(ClassroomEntity entity, ClassroomUpdate dto) {
        entity.setCourse(dto.course());
        entity.setBlock(dto.block());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static ClassroomUpdated toUpdated(ClassroomEntity entity) {
        return new ClassroomUpdated(entity.getIdClassroom(), entity.getCourse(), entity.getBlock());
    }

    public static ClassroomEntity toEntityDeleted(ClassroomEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}