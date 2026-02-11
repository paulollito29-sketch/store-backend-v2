package com.example.repository;

import com.example.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    List<TeacherEntity> findAllByEnabledIsTrueOrderByIdTeacherDesc();

    Optional<TeacherEntity> findFirstByEnabledIsTrueAndIdTeacher(Long id);

    boolean existsByEnabledIsTrueAndEmailIgnoreCase(String email);

    boolean existsByEnabledIsTrueAndIdTeacherNotAndEmailIgnoreCase(Long id, String email);
}