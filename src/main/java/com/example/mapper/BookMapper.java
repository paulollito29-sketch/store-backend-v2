package com.example.mapper;

import com.example.dto.*;
import com.example.entity.BookEntity;

import java.awt.print.Book;
import java.time.LocalDateTime;

public class BookMapper {

    private BookMapper(){

    }
    public static BookFindAll toBookFIndall(BookEntity entity){
        return new BookFindAll(entity.getIdBook(), entity.getTitle(), entity.getDescription(), entity.getPrice(), entity.getStock());
    }

    public static BookEntity toEntity (BookCreate dto){
        return BookEntity.builder()
                .title(dto.title())
                .price(dto.price())
                .description(dto.description())
                .stock(dto.stock())
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static BookCreated toEntity(BookEntity entity){
        return new BookCreated(entity.getIdBook(), entity.getTitle(), entity.getDescription(), entity.getPrice(), entity.getStock());
    }

    public static BookFindOne toEntityFindOne(BookEntity entity){
        return new BookFindOne(entity.getIdBook(), entity.getTitle(), entity.getDescription(), entity.getPrice(), entity.getStock());
    }

    public static BookEntity toEntity(BookEntity entity, BookUpdate dto){
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setStock(dto.stock());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
    public static BookUpdated toUpdated(BookEntity entity){
        return new BookUpdated(entity.getIdBook(), entity.getTitle(), entity.getDescription(), entity.getPrice(), entity.getStock());
    }

    public static BookEntity toEntityDeleted(BookEntity entity){
        entity.setActive(false);
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
}
