package com.example.repository;

import com.example.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    List<ArtistEntity> findAllByEnabledIsTrueOrderByIdArtistDesc();

    Optional<ArtistEntity> findFirstByEnabledIsTrueAndIdArtist(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdArtistNotAndNameIgnoreCase(Long id, String name);
}