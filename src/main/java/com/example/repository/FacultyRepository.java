package com.example.repository;

import com.example.entity.FacultyEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Long> {

    @EntityGraph(attributePaths = "speciality")
    List<FacultyEntity> findAllByEnabledIsTrueOrderByIdFacultyDesc();

    @EntityGraph(attributePaths = "speciality")
    Optional<FacultyEntity> findFirstByEnabledIsTrueAndIdFaculty(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdFacultyNotAndNameIgnoreCase(Long id, String name);

    // methods for speciality service

   Optional<FacultyEntity> findByEnabledIsTrueAndIdFaculty(Long id);
}