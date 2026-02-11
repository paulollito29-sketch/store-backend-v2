package com.example.mapper;

import com.example.dto.*;
import com.example.entity.SupplierEntity;

import java.time.LocalDateTime;

public class SupplierMapper {

    private SupplierMapper() {
    }

    public static SupplierFindAll toFindAll(SupplierEntity entity) {
        return new SupplierFindAll(entity.getIdSupplier(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress());
    }

    public static SupplierEntity toEntityCreated(SupplierCreate dto) {
        return SupplierEntity.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .address(dto.address())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static SupplierCreated toCreated(SupplierEntity entity) {
        return new SupplierCreated(entity.getIdSupplier(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress());
    }

    public static SupplierFindOne toFindOne(SupplierEntity entity) {
        return new SupplierFindOne(entity.getIdSupplier(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress());
    }

    public static SupplierEntity toEntityUpdated(SupplierEntity entity, SupplierUpdate dto) {
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPhone(dto.phone());
        entity.setAddress(dto.address());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static SupplierUpdated toUpdated(SupplierEntity entity) {
        return new SupplierUpdated(entity.getIdSupplier(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress());
    }

    public static SupplierEntity toEntityDeleted(SupplierEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}