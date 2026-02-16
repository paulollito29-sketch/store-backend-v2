package com.example.repository;

import com.example.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    List<ReportEntity> findAllByEnabledIsTrueOrderByIdReportDesc();

    Optional<ReportEntity> findFirstByEnabledIsTrueAndIdReport(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdReportNotAndNameIgnoreCase(Long id, String name);
}
