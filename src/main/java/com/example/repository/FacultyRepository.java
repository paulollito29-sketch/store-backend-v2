package com.example.repository;

import com.example.entity.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Long> {

    List<FacultyEntity> findAllByEnabledIsTrueOrderByIdFacultyDesc();

    Optional<FacultyEntity> findFirstByEnabledIsTrueAndIdFaculty(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdFacultyNotAndNameIgnoreCase(Long id, String name);

    // methods for speciality service

   Optional<FacultyEntity> findByEnabledIsTrueAndIdFaculty(Long id);
}