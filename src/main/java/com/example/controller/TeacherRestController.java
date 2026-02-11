package com.example.controller;

import com.example.dto.*;
import com.example.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherRestController {

    private final TeacherService teacherService;

    public TeacherRestController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherFindAll>> get() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @PostMapping
    public ResponseEntity<TeacherCreated> post(@Valid @RequestBody TeacherCreate dto) {
        var teacherCreated = teacherService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teacherCreated.teacherId())
                .toUri();
        return ResponseEntity.created(location).body(teacherCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherFindOne> get(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherUpdated> put(@PathVariable Long id,
                                              @Valid @RequestBody TeacherUpdate dto) {
        return ResponseEntity.ok(teacherService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}