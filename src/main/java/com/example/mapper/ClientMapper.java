package com.example.mapper;

import com.example.dto.*;
import com.example.entity.CategoryEntity;
import com.example.entity.ClientEntity;

import java.time.LocalDateTime;

public class ClientMapper {

    private ClientMapper(){

    }

    public static ClientFindAll ToClientFindAll(ClientEntity entity) {
        return new ClientFindAll(entity.getIdClient(), entity.getFirstName(), entity.getLastName());
    }

    public static ClientEntity ToEntity(ClientCreate dto){
        return ClientEntity.builder()
                .firstName(dto.firstName())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ClientCreated ToClientCreated(ClientEntity entity){
        return new ClientCreated(entity.getIdClient(), entity.getFirstName(), entity.getLastName());
    }

    public static ClientFindOne ToClientFindOne(ClientEntity entity){
        return new ClientFindOne(entity.getIdClient(), entity.getFirstName(), entity.getLastName());
    }

    public static ClientEntity ToEntity(ClientEntity entity, ClientUpdate dto){
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static ClientUpdated ToClientUpdated(ClientEntity entity){
        return new ClientUpdated(entity.getIdClient(), entity.getFirstName(), entity.getLastName());
    }

    public static ClientEntity ToEntity(ClientEntity entity){
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}
