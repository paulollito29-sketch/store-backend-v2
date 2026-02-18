package com.example.repository;

import com.example.entity.SpecialityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Long> {

    List<SpecialityEntity> findAllByEnabledIsTrueOrderByIdSpecialityDesc();

    Optional<SpecialityEntity> findFirstByEnabledIsTrueAndIdSpeciality(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);
    boolean existsByEnabledIsTrueAndIdSpecialityNotAndNameIgnoreCase(Long id, String name);
}
