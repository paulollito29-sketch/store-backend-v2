package com.example.repository;

import com.example.entity.SectionEntity;
import com.example.entity.TeacherEntity;
import com.example.entity.TeacherSectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherSectionRepository extends JpaRepository<TeacherSectionEntity, Long> {

    List<TeacherSectionEntity> findAllBySection(SectionEntity section);

    List<TeacherSectionEntity> findAllByTeacher(TeacherEntity teacher);

}
