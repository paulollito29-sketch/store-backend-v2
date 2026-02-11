package com.example.mapper;

import com.example.dto.*;
import com.example.entity.CourseEntity;

import java.time.LocalDateTime;
public class CourseMapper {
    private CourseMapper(){}

    public static CourseFindAll toFindAll(CourseEntity entity){
        return new CourseFindAll(entity.getIdCourse(), entity.getName(), entity.getCredits(), entity.getHours(), entity.getModality());
    }

    public static CourseEntity toEntityCreated(CourseCreate dto){
        return CourseEntity.builder()
                .name(dto.name())
                .credits(dto.credits())
                .hours(dto.hours())
                .modality(dto.modality())
                .createdAt(LocalDateTime.now())
                .enabled(true)
                .build();
    }

    public static CourseCreated toCourseCreated(CourseEntity entity){
        return new CourseCreated(entity.getIdCourse(), entity.getName(), entity.getCredits(), entity.getHours(), entity.getModality());
    }

    public static CourseFindOne toFindOne(CourseEntity entity){
        return new CourseFindOne(entity.getIdCourse(), entity.getName(), entity.getCredits(), entity.getHours(), entity.getModality());
    }

    public static CourseEntity toEntityUpdated(CourseEntity entity, CourseUpdate dto){
        entity.setName(dto.name());
        entity.setHours(dto.hours());
        entity.setCredits(entity.getCredits());
        entity.setModality(dto.modality());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static CourseUpdated toCourseUpdated(CourseEntity entity){
        return new CourseUpdated(entity.getIdCourse(), entity.getName(), entity.getCredits(), entity.getHours(), entity.getModality());
    }

    public static CourseEntity toEntityDeleted(CourseEntity entity){
        entity.setDeletedAt(LocalDateTime.now());
        entity.setEnabled(false);
        return entity;
    }


}
