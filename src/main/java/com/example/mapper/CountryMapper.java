package com.example.mapper;

import com.example.dto.*;
import com.example.entity.CountryEntity;

import java.time.LocalDateTime;

public class CountryMapper {

    private CountryMapper() {

    }

    public static CountryFindAll toCountryFindAll(CountryEntity entity) {
        return new CountryFindAll(entity.getIdCountry(), entity.getName(), entity.getCode());
    }

    public static CountryEntity toEntity(CountryCreate dto) {
        return CountryEntity.builder()
                .name(dto.name())
                .code(dto.code())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CountryCreated toCountryCreated(CountryEntity entity) {
        return new CountryCreated(entity.getIdCountry(), entity.getName(), entity.getCode());
    }

    public static CountryFindOne toCountryFindOne(CountryEntity entity) {
        return new CountryFindOne(entity.getIdCountry(), entity.getName(), entity.getCode());
    }

    public static CountryEntity toEntity(CountryEntity entity, CountryUpdate dto) {
        entity.setName(dto.name());
        entity.setCode(dto.code());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static CountryUpdated toCountryUpdated(CountryEntity entity) {
        return new CountryUpdated(entity.getIdCountry(), entity.getName(), entity.getCode());
    }

    public static CountryEntity toEntity(CountryEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}