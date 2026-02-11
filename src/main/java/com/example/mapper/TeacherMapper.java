package com.example.mapper;

import com.example.dto.*;
import com.example.entity.TeacherEntity;

import java.time.LocalDateTime;

public class TeacherMapper {

    private TeacherMapper() {
    }

    public static TeacherFindAll toFindAll(TeacherEntity entity) {
        return new TeacherFindAll(entity.getIdTeacher(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getSubject());
    }

    public static TeacherEntity toEntityCreated(TeacherCreate dto) {
        return TeacherEntity.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .subject(dto.subject())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TeacherCreated toCreated(TeacherEntity entity) {
        return new TeacherCreated(entity.getIdTeacher(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getSubject());
    }

    public static TeacherFindOne toFindOne(TeacherEntity entity) {
        return new TeacherFindOne(entity.getIdTeacher(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getSubject());
    }

    public static TeacherEntity toEntityUpdated(TeacherEntity entity, TeacherUpdate dto) {
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setSubject(dto.subject());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static TeacherUpdated toUpdated(TeacherEntity entity) {
        return new TeacherUpdated(entity.getIdTeacher(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getSubject());
    }

    public static TeacherEntity toEntityDeleted(TeacherEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}