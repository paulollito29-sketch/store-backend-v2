package com.example.mapper;

import com.example.dto.*;
import com.example.entity.FacultyEntity;
import com.example.repository.FacultyRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class FacultyMapper {

    private FacultyMapper(){

    }

    public static List<SpecialityFindAll> toSpecialities(FacultyEntity entity){
        if (entity.getSpeciality() == null){
            return Collections.emptyList();
        }
        return entity.getSpeciality().stream()
                .map(SpecialityMapper::toFindAll)
                .toList();
    }

    public static FacultyFindAll toFindAll(FacultyEntity entity ){
        return new FacultyFindAll(entity.getIdFaculty(), entity.getName(), toSpecialities(entity));
    }

    public static FacultyEntity toEntityCreate(FacultyCreate dto){
        return FacultyEntity.builder()
                .name(dto.name())
                .createdAt(LocalDateTime.now())
                .enabled(true)
                .build();
    }

    public static FacultyCreated toFacultyCreated(FacultyEntity entity){
        return new FacultyCreated(entity.getIdFaculty(), entity.getName());
    }

    public static FacultyFindOne toFindOne(FacultyEntity entity){
        return new FacultyFindOne(entity.getIdFaculty(), entity.getName(), toSpecialities(entity));
    }



    public static FacultyEntity toFacultyUpdate(FacultyEntity entity, FacultyUpdate dto){
        entity.setName(dto.name());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static FacultyUpdated toFacultyUpdated(FacultyEntity entity){
        return new FacultyUpdated(entity.getIdFaculty(), entity.getName());
    }

    public static FacultyEntity toEntityDeleted(FacultyEntity entity){
        entity.setDeletedAt(LocalDateTime.now());
        entity.setEnabled(false);
        return (entity);
    }

}
