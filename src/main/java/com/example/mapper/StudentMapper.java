package com.example.mapper;

import com.example.dto.*;
import com.example.entity.StudentEntity;

import java.time.LocalDateTime;

public class StudentMapper {

    private StudentMapper() {
    }

    public static StudentFindAll toFindAll(StudentEntity entity) {
        return new StudentFindAll(entity.getIdStudent(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getGrade());
    }

    public static StudentEntity toEntityCreated(StudentCreate dto) {
        return StudentEntity.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .age(dto.age())
                .grade(dto.grade())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StudentCreated toCreated(StudentEntity entity) {
        return new StudentCreated(entity.getIdStudent(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getGrade());
    }

    public static StudentFindOne toFindOne(StudentEntity entity) {
        return new StudentFindOne(entity.getIdStudent(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getGrade());
    }

    public static StudentEntity toEntityUpdated(StudentEntity entity, StudentUpdate dto) {
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setAge(dto.age());
        entity.setGrade(dto.grade());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static StudentUpdated toUpdated(StudentEntity entity) {
        return new StudentUpdated(entity.getIdStudent(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getGrade());
    }

    public static StudentEntity toEntityDeleted(StudentEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}