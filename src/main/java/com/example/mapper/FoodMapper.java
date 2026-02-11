package com.example.mapper;

import com.example.dto.*;
import com.example.entity.FoodEntity;

import java.time.LocalDateTime;

public class FoodMapper {

    private FoodMapper(){}

    public static FoodFindAll toFoodFindAll(FoodEntity entity){
        return new FoodFindAll(entity.getIdFood(),entity.getType(), entity.getPrice(),entity.getName(),entity.getIngredients());
    }

    public static FoodEntity toEntityCreated(FoodCreate dto) {
        return FoodEntity.builder()
                .type(dto.type())
                .price(dto.price())
                .name(dto.name())
                .ingredients(dto.ingredients())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static FoodCreated toFoodCreated(FoodEntity entity){
        return new FoodCreated(entity.getIdFood(), entity.getType(), entity.getPrice(), entity.getName(), entity.getIngredients());
    }

    public static FoodFindOne toFoodFindOne(FoodEntity entity){
        return new FoodFindOne(entity.getIdFood(),entity.getType(), entity.getPrice(),entity.getName(),entity.getIngredients());
    }

    public static FoodEntity toEntityUpdated(FoodEntity entity, FoodUpdate dto){
        entity.setType(dto.type());
        entity.setName(dto.name());
        entity.setPrice(dto.price());
        entity.setIngredients(dto.ingredients());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static FoodUpdated toFoodUpdated(FoodEntity entity){
        return new FoodUpdated(entity.getIdFood(), entity.getType(), entity.getPrice(), entity.getName(), entity.getIngredients());
    }

    public static FoodEntity toEntityDeleted(FoodEntity entity){
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}
