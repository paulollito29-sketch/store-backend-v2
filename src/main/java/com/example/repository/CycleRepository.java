package com.example.repository;

import com.example.entity.CycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CycleRepository extends JpaRepository<CycleEntity, Long> {

    List<CycleEntity> findAllByEnabledIsTrueOrderByIdCycleDesc();

    Optional<CycleEntity> findFirstByEnabledIsTrueAndIdCycle(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdCycleNotAndNameIgnoreCase(Long id, String name);
}