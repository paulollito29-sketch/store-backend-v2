package com.example.mapper;

import com.example.dto.*;
import com.example.entity.TrainerEntity;

import java.time.LocalDateTime;

public class TrainerMapper {
    private TrainerMapper() {
    }

    public static TrainerFindAll ToTrainerFindAll(TrainerEntity entity) {
        return new TrainerFindAll(entity.getIdTrainer(),
                entity.getName(),
                entity.getSalary(),
                entity.getTrainee());

    }

    public static TrainerEntity ToEntity(TrainerCreate dto){
        return TrainerEntity.builder()
                .name(dto.name())
                .salary(dto.salary())
                .trainee(dto.trainee())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TrainerCreated ToTrainerCreated(TrainerEntity entity){
        return new TrainerCreated(entity.getIdTrainer(),entity.getName(),entity.getSalary(), entity.getTrainee());
    }

    public static TrainerFindOne ToTrainerFindOne(TrainerEntity entity){
        return new TrainerFindOne(entity.getIdTrainer(), entity.getName(), entity.getSalary(), entity.getTrainee());
    }

    public static TrainerEntity toEntity(TrainerEntity entity, TrainerUpdate dto){
        entity.setName(dto.name());
        entity.setSalary(dto.salary());
        entity.setTrainee(dto.trainee());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static TrainerEntity toEntity(TrainerEntity entity){
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }

    public static TrainerUpdated toTrainerUpdated(TrainerEntity entity){
        return new TrainerUpdated(entity.getIdTrainer(), entity.getName(), entity.getSalary(), entity.getTrainee());
    }
}
