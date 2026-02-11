package com.example.repository;

import com.example.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<SongEntity, Long> {

    List<SongEntity> findAllByEnabledIsTrueOrderByIdSongDesc();

    Optional<SongEntity> findFirstByEnabledIsTrueAndIdSong(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdSongNotAndNameIgnoreCase(Long id, String name);
}