package com.example.mapper;

import com.example.dto.*;
import com.example.entity.FacultyEntity;
import com.example.entity.SpecialityEntity;

import java.time.LocalDateTime;

public class SpecialityMapper {

    private SpecialityMapper(){}

    public static SpecialityFindAll toFindAll(SpecialityEntity entity){
        return new SpecialityFindAll(entity.getIdSpeciality(), entity.getFaculty().getName(), entity.getName());
    }

    public static SpecialityEntity toEntityCreated(SpecialityCreate dto, FacultyEntity faculty){
        return SpecialityEntity.builder()
                .name(dto.name())
                .faculty(faculty)
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static SpecialityCreated toSpecialityCreated(SpecialityEntity entity){
        return new SpecialityCreated(entity.getIdSpeciality(), entity.getFaculty().getIdFaculty(), entity.getName());
    }

    public static SpecialityFindOne toFindOne(SpecialityEntity entity){
        return new SpecialityFindOne(entity.getIdSpeciality(), entity.getFaculty().getIdFaculty(), entity.getName());
    }
    public static SpecialityEntity toEntityUpdated(SpecialityEntity entity, SpecialityUpdate dto, FacultyEntity faculty){
        entity.setName(dto.name());
        entity.setFaculty(faculty);
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static SpecialityUpdated toSpecialityUpdated(SpecialityEntity entity){
        return new SpecialityUpdated(entity.getIdSpeciality(), entity.getFaculty().getIdFaculty(), entity.getName());
    }

    public static SpecialityEntity toEntityDeleted(SpecialityEntity entity){
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}
