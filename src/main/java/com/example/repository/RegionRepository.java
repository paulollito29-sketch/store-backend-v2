package com.example.repository;

import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

    List<RegionEntity> findAllByEnabledIsTrueOrderByIdRegionDesc();

    Optional<RegionEntity> findFirstByEnabledIsTrueAndIdRegion(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdRegionNotAndNameIgnoreCase(Long id, String name);

    Optional<RegionEntity> findFirstByEnabledIsFalseAndIdRegion(Long id);
}
