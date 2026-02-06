package com.example.mapper;

import com.example.dto.*;
import com.example.entity.RegionEntity;

import java.time.LocalDateTime;

public class RegionMapper {
    private RegionMapper() {

    }

    public static RegionFindAll toRegionFindAll(RegionEntity entity) {
        return new RegionFindAll(entity.getIdRegion(), entity.getName());
    }

    public static RegionEntity toEntity(RegionCreate dto) {
        return RegionEntity.builder().name(dto.name()).enabled(true).createdAt(LocalDateTime.now())
                .build();
    }

    public static RegionCreated toRegionCreated(RegionEntity entity){
        return new RegionCreated(entity.getIdRegion(), entity.getName());
    }

    public static RegionFindOne toRegionFindOne(RegionEntity entity){
        return new RegionFindOne(entity.getIdRegion(), entity.getName());
    }

    public static RegionEntity toEntity(RegionEntity entity, RegionUpdate dto){
        entity.setName(dto.name());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static RegionEntity toEntity(RegionEntity entity){
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }

    public static RegionUpdated toRegionUpdated(RegionEntity entity){
        return new RegionUpdated(entity.getIdRegion(), entity.getName());
    }
}
