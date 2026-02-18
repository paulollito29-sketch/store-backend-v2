package com.example.repository;

import com.example.dto.SpecialityFindAll;
import com.example.entity.SpecialityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Long> {

    List<SpecialityEntity> findAllByEnabledIsTrueOrderByIdSpecialityDesc();

    Optional<SpecialityEntity> findFirstByEnabledIsTrueAndIdSpeciality(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);
    boolean existsByEnabledIsTrueAndIdSpecialityNotAndNameIgnoreCase(Long id, String name);

    @Query(value = """
        SELECT *
        FROM specialities
        WHERE enabled = true
          AND faculty_id = :facultyId
        ORDER BY id_speciality DESC
        """, nativeQuery = true)
    List<SpecialityEntity> findAllByFacultyId(@Param("facultyId") Long facultyId);
}
