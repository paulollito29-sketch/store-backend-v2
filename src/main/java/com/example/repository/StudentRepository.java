package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findAllByEnabledIsTrueOrderByIdStudentDesc();

    Optional<StudentEntity> findFirstByEnabledIsTrueAndIdStudent(Long id);

    boolean existsByEnabledIsTrueAndFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    boolean existsByEnabledIsTrueAndIdStudentNotAndFirstNameIgnoreCaseAndLastNameIgnoreCase(Long id, String firstName, String lastName);


}
