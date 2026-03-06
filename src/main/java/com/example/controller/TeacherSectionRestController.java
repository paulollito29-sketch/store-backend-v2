package com.example.controller;

import com.example.dto.*;
import com.example.service.TeacherSectionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ts")

public class TeacherSectionRestController {

    private final TeacherSectionService teacherSectionService;

    public TeacherSectionRestController(TeacherSectionService teacherSectionService){

        this.teacherSectionService = teacherSectionService;

    }

    @GetMapping
    public ResponseEntity<List<TeacherSectionUpdated>> findAll() {
        return ResponseEntity.ok(teacherSectionService.findAll());
    }

    @GetMapping("/fots/{teacherId}/{sectionId}")
    public ResponseEntity<TeacherSectionFindOne> findOne(@PathVariable Long teacherId,
                                                         @PathVariable Long sectionId) {
        return ResponseEntity.ok(teacherSectionService.findOne(teacherId, sectionId));
    }

    @PostMapping
    public ResponseEntity<TeacherSectionCreated> create(@Valid @RequestBody TeacherSectionCreate dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherSectionService.create(dto));
    }

    @PutMapping("/uts/{teacherId}/{sectionId}")
    public ResponseEntity<TeacherSectionUpdated> update(@PathVariable Long teacherId,
                                                        @PathVariable Long sectionId,
                                                        @Valid @RequestBody TeacherSectionUpdate dto) {
        return ResponseEntity.ok(teacherSectionService.update(teacherId, sectionId, dto));
    }

    @DeleteMapping("/dts/{teacherId}/{sectionId}")
    public ResponseEntity<Void> delete(@PathVariable Long teacherId,
                                       @PathVariable Long sectionId) {
        teacherSectionService.delete(teacherId, sectionId);
        return ResponseEntity.noContent().build();
    }

        @GetMapping("/ss/{teacherId}")
        public ResponseEntity<List<SectionFindAll>> findSectionsByTeacher(@PathVariable Long teacherId) {
            return ResponseEntity.ok(teacherSectionService.findSectionsByTeacher(teacherId));
        }

        @GetMapping("/ts/{sectionId}")
        public ResponseEntity<List<TeacherFullFindAll>> findTeachersBySection(@PathVariable Long sectionId) {
            return ResponseEntity.ok(teacherSectionService.findTeachersBySection(sectionId));
        }
    }
