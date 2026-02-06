package com.example.mapper;

import com.example.dto.*;
import com.example.entity.EmployeeEntity;

import java.time.LocalDateTime;

public class EmployeeMapper {

    private EmployeeMapper(){

    }
    public static EmployeeFindAll toEmployeeFindAll(EmployeeEntity entity){
        return new EmployeeFindAll(entity.getIdEmployee(), entity.getFirstName(), entity.getLastName(),entity.getAge(), entity.getSalary());
    }

    public static EmployeeEntity toEntityCreate(EmployeeCreate dto){
        return EmployeeEntity.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .age(dto.age())
                .salary(dto.salary())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static EmployeeCreated toEmployeeCreated(EmployeeEntity entity){
        return new EmployeeCreated(entity.getIdEmployee(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getSalary());
    }

    public static EmployeeFindOne toEmployeeFindOne(EmployeeEntity entity){
        return new EmployeeFindOne(entity.getIdEmployee(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getSalary());
    }

    public static EmployeeEntity toEntityUpdate(EmployeeEntity entity, EmployeeUpdate dto){
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setSalary(dto.salary());
        entity.setAge(dto.age());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static EmployeeUpdated toEmployeeUpdated(EmployeeEntity entity){
        return new EmployeeUpdated(entity.getIdEmployee(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getSalary());
    }

    public static EmployeeEntity toEntityDeleted(EmployeeEntity entity){
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }


}
