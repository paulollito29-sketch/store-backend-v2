package com.example.repository;

import com.example.dto.TeacherSectionsFindAll;
import com.example.entity.SectionEntity;
import com.example.entity.TeacherEntity;
import com.example.entity.TeacherSectionEntity;
import com.example.entity.TeacherSectionPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherSectionRepository extends JpaRepository<TeacherSectionEntity, TeacherSectionPK> {

    List<TeacherSectionEntity> findAllByTeacher(TeacherEntity teacher);

    List<TeacherSectionEntity> findAllBySection(SectionEntity section);

}
