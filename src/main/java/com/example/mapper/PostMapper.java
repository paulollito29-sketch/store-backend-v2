package com.example.mapper;

import com.example.dto.*;
import com.example.entity.PostEntity;

import java.time.LocalDateTime;

public class PostMapper {

    private PostMapper(){}

    public static PostFindAll toFindAll(PostEntity entity){
        return new PostFindAll(entity.getIdPost(), entity.getTitle(), entity.getReactions(), entity.getRating());
    }

    public static PostEntity toEntityCreated(PostCreate dto){
        return PostEntity.builder()
                .title(dto.title())
                .rating(dto.rating())
                .reactions(dto.reactions())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static PostCreated toPostCreated(PostEntity entity){
        return new PostCreated(entity.getIdPost(), entity.getTitle(), entity.getReactions(), entity.getRating());
    }

    public static PostFindOne toFindOne(PostEntity entity){
        return new PostFindOne(entity.getIdPost(), entity.getTitle(), entity.getReactions(), entity.getRating());
    }

    public static PostEntity toEntityUpdated(PostEntity entity, PostUpdate dto){
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setRating(dto.rating());
        entity.setTitle(dto.title());
        entity.setReactions(dto.reactions());
        return entity ;
    }

    public static PostUpdated toPostUpdated(PostEntity entity){
        return new PostUpdated(entity.getIdPost(), entity.getTitle(), entity.getReactions(), entity.getRating());
    }

    public static PostEntity toPostDeleted(PostEntity entity){
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity ;
    }
}
