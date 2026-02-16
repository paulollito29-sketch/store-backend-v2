package com.example.mapper;

import com.example.dto.*;
import com.example.entity.NotificationEntity;

public class NotificationMapper {

    private NotificationMapper() {
    }

    public static NotificationFindAll toFindAll(NotificationEntity entity) {
        return new NotificationFindAll(entity.getIdNotification(), entity.getBody(), entity.getTitle());
    }

    public static NotificationEntity toEntityCreated(NotificationCreate dto) {
        return NotificationEntity.builder()
                .body(dto.body())
                .title(dto.title())
                .build();
    }

    public static NotificationCreated toCreated(NotificationEntity entity) {
        return new NotificationCreated(entity.getIdNotification(), entity.getBody(), entity.getTitle());
    }

    public static NotificationFindOne toFindOne(NotificationEntity entity) {
        return new NotificationFindOne(entity.getIdNotification(), entity.getBody(), entity.getTitle());
    }

    public static NotificationEntity toEntityUpdated(NotificationEntity entity, NotificationUpdate dto) {
        entity.setBody(dto.body());
        entity.setTitle(dto.title());
        return entity;
    }

    public static NotificationUpdated toUpdated(NotificationEntity entity) {
        return new NotificationUpdated(entity.getIdNotification(), entity.getBody(), entity.getTitle());
    }
}
