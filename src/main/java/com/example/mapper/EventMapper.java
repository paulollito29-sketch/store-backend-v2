package com.example.mapper;

import com.example.dto.*;
import com.example.entity.EventEntity;

import java.time.LocalDateTime;

public class EventMapper {

    private EventMapper() {
    }

    public static EventFindAll toFindAll(EventEntity entity) {
        return new EventFindAll(entity.getIdEvent(), entity.getName(), entity.getDescription(), entity.getLocation(), entity.getEventDate());
    }

    public static EventEntity toEntityCreated(EventCreate dto) {
        return EventEntity.builder()
                .name(dto.name())
                .description(dto.description())
                .location(dto.location())
                .eventDate(dto.eventDate())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static EventCreated toCreated(EventEntity entity) {
        return new EventCreated(entity.getIdEvent(), entity.getName(), entity.getDescription(), entity.getLocation(), entity.getEventDate());
    }

    public static EventFindOne toFindOne(EventEntity entity) {
        return new EventFindOne(entity.getIdEvent(), entity.getName(), entity.getDescription(), entity.getLocation(), entity.getEventDate());
    }

    public static EventEntity toEntityUpdated(EventEntity entity, EventUpdate dto) {
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setLocation(dto.location());
        entity.setEventDate(dto.eventDate());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static EventUpdated toUpdated(EventEntity entity) {
        return new EventUpdated(entity.getIdEvent(), entity.getName(), entity.getDescription(), entity.getLocation(), entity.getEventDate());
    }

    public static EventEntity toEntityDeleted(EventEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}