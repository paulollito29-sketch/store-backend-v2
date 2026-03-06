package com.example.mapper;

import com.example.dto.*;
import com.example.entity.SectionEntity;
import com.example.entity.TeacherEntity;
import com.example.entity.TeacherSectionEntity;
import com.example.entity.TeacherSectionPK;


import java.util.ArrayList;
import java.util.List;


public class TeacherSectionMapper {

    private TeacherSectionMapper() {}

    public static TeacherSectionUpdated toFindAll(TeacherSectionEntity entity) {
        return new TeacherSectionUpdated(entity.getTeacher().getIdTeacher(), entity.getSection().getIdSection());
    }

    public static TeacherSectionEntity toEntity(TeacherEntity teacher, SectionEntity section) {
        return TeacherSectionEntity.builder()
                .id(new TeacherSectionPK(teacher.getIdTeacher(), section.getIdSection()))
                .teacher(teacher)
                .section(section)
                .build();
    }

    public static List<TeacherSecctionsDTO> toList(List<SectionEntity> sections, List<TeacherEntity> teachers, List<TeacherSectionEntity> teachersSections ){

        List<TeacherSecctionsDTO> lista = new ArrayList<>();

        for (TeacherEntity teacher : teachers) {

            TeacherSecctionsDTO dto = new TeacherSecctionsDTO();
            dto.setTeacherId(teacher.getIdTeacher());
            dto.setNameComplete(teacher.getFirstName() + " " +teacher.getLastName());
            List<String> teacherSectionsList = new ArrayList<>();
            for (TeacherSectionEntity teacherSection : teachersSections) {
                if (teacherSection.getTeacher().getIdTeacher().equals(teacher.getIdTeacher())) {
                    for (SectionEntity section : sections) {
                        if (section.getIdSection().equals(teacherSection.getSection().getIdSection())) {
                            teacherSectionsList.add(section.getName());
                        }
                    }
                }
            }

            dto.setSections(teacherSectionsList);
            lista.add(dto);
        }
        return lista;
    }

    public static TeacherSectionCreated toEntityCreated(TeacherSectionEntity entity) {
        return new TeacherSectionCreated(entity.getTeacher().getIdTeacher(), entity.getSection().getIdSection());
    }

    public static TeacherSectionFindOne toFindOne(TeacherSectionEntity entity) {
        return new TeacherSectionFindOne(entity.getTeacher().getIdTeacher(), entity.getSection().getIdSection());
    }

    public static TeacherSectionUpdated toUpdated(TeacherSectionEntity entity) {
        return new TeacherSectionUpdated(entity.getTeacher().getIdTeacher(), entity.getSection().getIdSection());
    }

    public static TeacherFullFindAll toTeacherFindAll(TeacherSectionEntity entity) {
        return new TeacherFullFindAll(entity.getTeacher().getIdTeacher(), entity.getTeacher().getFirstName());
    }

    public static SectionFindAll toSectionFindAll(TeacherSectionEntity entity) {
        return new SectionFindAll(entity.getSection().getIdSection(), entity.getSection().getName());
    }

}

