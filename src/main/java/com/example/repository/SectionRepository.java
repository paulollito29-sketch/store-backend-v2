package com.example.repository;

import com.example.entity.SectionEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    List<SectionEntity> findAllByEnabledIsTrueOrderByIdSectionDesc();

    Optional<SectionEntity> findFirstByEnabledIsTrueAndIdSection(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String Name);

    boolean existsByEnabledIsTrueAndIdSectionNotAndNameIgnoreCase(Long id, String name);

    //methods for teacherSection

    boolean existsByEnabledIsTrueAndIdSection(Long id);



}
