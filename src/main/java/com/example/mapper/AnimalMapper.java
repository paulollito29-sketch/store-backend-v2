package com.example.mapper;

import com.example.dto.*;
import com.example.entity.AnimalEntity;

import java.time.LocalDateTime;

public class AnimalMapper {

    private AnimalMapper() {
    }

    public static AnimalFindAll toFindAll(AnimalEntity entity) {
        return new AnimalFindAll(entity.getIdAnimal(), entity.getRaza(), entity.getName(), entity.getAge(), entity.getWeight());
    }

    public static AnimalEntity toEntity(AnimalCreate dto){
        return AnimalEntity.builder()
                .name(dto.name())
                .age(dto.age())
                .raza(dto.raza())
                .weight(dto.weight())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static AnimalCreated toAnimalCreated(AnimalEntity entity){
        return new AnimalCreated(entity.getIdAnimal(), entity.getRaza(), entity.getName(), entity.getAge(), entity.getWeight());
    }

    public static AnimalFindOne toAnimalFindOne(AnimalEntity entity){
        return new AnimalFindOne(entity.getIdAnimal(), entity.getName(), entity.getRaza(), entity.getAge(), entity.getWeight());
    }

    public static AnimalEntity toEntityUpdated(AnimalEntity entity, AnimalUpdate dto){
        entity.setName(dto.name());
        entity.setAge(dto.age());
        entity.setRaza(dto.raza());
        entity.setWeight(dto.weight());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
    public static AnimalUpdated toAnimalUpdated(AnimalEntity entity){
        return new AnimalUpdated(entity.getIdAnimal(), entity.getName(), entity.getRaza(), entity.getAge(), entity.getWeight());
    }

    public static AnimalEntity toEntityDeleted(AnimalEntity entity){
        entity.setDeletedAt(LocalDateTime.now());
        entity.setEnabled(false);
        return entity;
    }
}
