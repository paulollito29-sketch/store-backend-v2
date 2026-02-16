package com.example.controller;

import com.example.dto.*;
import com.example.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportRestController {

    private final ReportService reportService;

    public ReportRestController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<ReportFindAll>> get() {
        return ResponseEntity.ok(reportService.findAll());
    }

    @PostMapping
    public ResponseEntity<ReportCreated> post(@Valid @RequestBody ReportCreate dto) {
        var reportCreated = reportService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reportCreated.idReport())
                .toUri();
        return ResponseEntity.created(location).body(reportCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportFindOne> get(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportUpdated> put(@PathVariable Long id,
                                             @Valid @RequestBody ReportUpdate dto) {
        return ResponseEntity.ok(reportService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reportService.delete(id);
        return ResponseEntity.noContent().build();
    }
}