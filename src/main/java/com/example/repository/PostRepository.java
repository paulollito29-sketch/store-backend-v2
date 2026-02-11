package com.example.repository;

import com.example.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByEnabledIsTrueOrderByIdPostDesc();

    Optional<PostEntity> findFirstByEnabledIsTrueAndIdPost(Long id);

    Boolean existsByEnabledIsTrueAndTitleIgnoreCase(String title);

    Boolean existsByEnabledIsTrueAndIdPostNotAndTitleIgnoreCase(Long id, String title);
}
