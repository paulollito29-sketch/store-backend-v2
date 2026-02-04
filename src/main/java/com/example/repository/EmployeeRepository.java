package com.example.repository;

import com.example.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findAllByEnabledIsTrueOrderByIdEmployeeDesc();

    boolean existsByEnabledIsTrueAndFirstNameIgnoreCase(
            String firstName);

    Optional<EmployeeEntity> findFirstByEnabledIsTrueAndIdEmployee (Long id);

    boolean existsByEnabledIsTrueAndIdEmployeeNotAndFirstNameIgnoreCaseAndLastNameIgnoreCase(Long id, String firstName, String lastName);
}
