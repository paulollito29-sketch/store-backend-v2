package com.example.mapper;

import com.example.dto.*;
import com.example.entity.ContactEntity;

import java.time.LocalDateTime;

public class ContactMapper {

    private ContactMapper() {

    }

    public static ContactFindAll toContactFindAll(ContactEntity entity) {
        return new ContactFindAll(entity.getIdContact(), entity.getName(), entity.getPhone(), entity.getEmail(), entity.getAddress());
    }

    public static ContactEntity toEntity(ContactCreate dto) {
        return ContactEntity.builder()
                .name(dto.name())
                .phone(dto.phone())
                .email(dto.email())
                .address(dto.address())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ContactCreated toContactCreated(ContactEntity entity) {
        return new ContactCreated(entity.getIdContact(), entity.getName(), entity.getPhone(), entity.getEmail(), entity.getAddress());
    }

    public static ContactFindOne toContactFindOne(ContactEntity entity) {
        return new ContactFindOne(entity.getIdContact(), entity.getName(), entity.getPhone(), entity.getEmail(), entity.getAddress());
    }

    public static ContactEntity toEntity(ContactEntity entity, ContactUpdate dto) {
        entity.setName(dto.name());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setAddress(dto.address());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static ContactUpdated toContactUpdated(ContactEntity entity) {
        return new ContactUpdated(entity.getIdContact(), entity.getName(), entity.getPhone(), entity.getEmail(), entity.getAddress());
    }

    public static ContactEntity toEntity(ContactEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}