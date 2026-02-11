package com.example.repository;

import com.example.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, String> {

    List<MovieEntity> findAllByEnabledIsTrueOrderByCreatedAtDesc();

    Optional<MovieEntity> findFirstByEnabledIsTrueAndIdMovie(String id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdMovieNotAndNameIgnoreCase(String id, String name);
}
