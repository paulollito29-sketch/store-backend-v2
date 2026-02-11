package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findAllByEnabledIsTrueOrderByIdCourseDesc();

    Optional<CourseEntity> findFirstByEnabledIsTrueAndIdCourse(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdCourseNotAndNameIgnoreCase(Long id, String name);
}
