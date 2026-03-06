package com.example.mapper;

import com.example.dto.TeacherFullFindAll;
import com.example.dto.TeacherSectionCreate;
import com.example.dto.TeacherSectionUpdate;
import com.example.dto.TeacherSectionUpdated;
import com.example.entity.SectionEntity;
import com.example.entity.TeacherEntity;
import com.example.entity.TeacherSectionEntity;
import jdk.dynalink.beans.StaticClass;

import java.util.List;

public class TeacherSectionMapper {

    private TeacherSectionMapper() {}

    public static TeacherFullFindAll findAll(TeacherSectionEntity entity) {
        return new TeacherFullFindAll(entity.getTeacher().getIdTeacher(), entity.getTeacher().getFirstName());
    }

    public static TeacherSectionEntity toEntityCreated(Long teacherId, Long sectionId){
        return TeacherSectionEntity.builder()
                .teacher(TeacherEntity.builder().idTeacher(teacherId).build())
                .section(SectionEntity.builder().idSection(sectionId).build())
                .build();
    }

    public static TeacherSectionEntity toEntityUpdated(TeacherEntity teacher, SectionEntity section, TeacherSectionUpdate dto){

        return new TeacherSectionEntity( );
    }

    public static TeacherSectionUpdated toUpdated(TeacherSectionEntity entity){
    return new TeacherSectionUpdated(entity.getTeacher().getIdTeacher(), entity.getSection().getIdSection());
    }



}

