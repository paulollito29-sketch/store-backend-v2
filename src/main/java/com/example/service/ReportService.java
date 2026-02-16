package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.ReportMapper;
import com.example.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<ReportFindAll> findAll() {
        return reportRepository.findAllByEnabledIsTrueOrderByIdReportDesc().stream()
                .map(ReportMapper::toFindAll)
                .toList();
    }

    public ReportCreated create(ReportCreate dto) {
        if (reportRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this report already exists");
        }
        var reportSaved = reportRepository.save(ReportMapper.toEntityCreated(dto));
        return ReportMapper.toCreated(reportSaved);
    }

    public ReportFindOne findOne(Long id) {
        var report = reportRepository.findFirstByEnabledIsTrueAndIdReport(id)
                .orElseThrow(() -> new ResourceNotFoundException("report not found"));
        return ReportMapper.toFindOne(report);
    }

    public ReportUpdated update(Long id, ReportUpdate dto) {
        var report = reportRepository.findFirstByEnabledIsTrueAndIdReport(id)
                .orElseThrow(() -> new ResourceNotFoundException("report not found"));
        if (reportRepository.existsByEnabledIsTrueAndIdReportNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this report already exists");
        }
        var reportSaved = reportRepository.save(ReportMapper.toEntityUpdated(report, dto));
        return ReportMapper.toUpdated(reportSaved);
    }

    public void delete(Long id) {
        var report = reportRepository.findFirstByEnabledIsTrueAndIdReport(id)
                .orElseThrow(() -> new ResourceNotFoundException("report not found"));
        reportRepository.save(ReportMapper.toEntityDeleted(report));
    }
}
