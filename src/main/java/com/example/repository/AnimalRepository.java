package com.example.repository;

import com.example.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
    List<AnimalEntity> findAllByEnabledIsTrueOrderByIdAnimalDesc();

    Optional<AnimalEntity> findFirstByEnabledIsTrueAndIdAnimal(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdAnimalNotAndNameIgnoreCase(Long id, String name);

}
