package com.example.mapper;

import com.example.dto.*;
import com.example.entity.JobEntity;
import java.time.LocalDateTime;

public class JobMapper {
    JobMapper(){

    }

    public static JobFindAll toJobFindAll(JobEntity entity){
        return new JobFindAll(entity.getIdJob(), entity.getName(), entity.getMinSalary(), entity.getMaxSalary(), entity.getMaxEmployee());
    }
    public static JobEntity ToEntity(JobCreate dto){
        return JobEntity.builder()
                .name(dto.name())
                .minSalary(dto.minSalary())
                .maxSalary(dto.maxSalary())
                .maxEmployee(dto.maxEmployee())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static JobCreated toJobCreated(JobEntity entity){
        return new JobCreated(entity.getIdJob(), entity.getName(), entity.getMinSalary(), entity.getMaxSalary(), entity.getMaxEmployee());
    }

    public static JobFindOne ToJobFindOne(JobEntity entity){
        return new JobFindOne(entity.getIdJob(), entity.getName(), entity.getMinSalary(), entity.getMaxSalary(), entity.getMaxEmployee());
    }

    public static JobEntity ToEntity(JobEntity entity, JobUpdate dto){
        entity.setName(dto.name());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static JobUpdated ToJobUpdated(JobEntity entity){
        return new JobUpdated(entity.getIdJob(), entity.getName(), entity.getMinSalary(), entity.getMaxSalary(), entity.getMaxEmployee());
    }

    public static JobEntity ToEntity(JobEntity entity){
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}
