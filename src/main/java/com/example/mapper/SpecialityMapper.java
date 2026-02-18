package com.example.mapper;

import com.example.dto.SpecialityCreate;
import com.example.dto.SpecialityFindAll;
import com.example.entity.SpecialityEntity;

import java.time.LocalDateTime;

public class SpecialityMapper {

    private SpecialityMapper(){}

    public static SpecialityFindAll toFindAll(SpecialityEntity entity){
        return new SpecialityFindAll(entity.getIdSpeciality(), entity.getFaculty().getName(), entity.getName());
    }

    public static SpecialityEntity toEntityCreated(SpecialityEntity entity, SpecialityCreate dto){
        entity.setCreatedAt(LocalDateTime.now());
        entity.setName(dto.name());
        entity.setFaculty();


    }
}
