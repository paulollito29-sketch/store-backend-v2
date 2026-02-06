package com.example.repository;

import com.example.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

    List<TrainerEntity> findAllByEnabledIsTrueOrderByIdTrainerDesc();

    Optional<TrainerEntity> findFirstByEnabledIsTrueAndIdTrainer(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdTrainerNotAndNameIgnoreCase(Long Id, String name);
}
