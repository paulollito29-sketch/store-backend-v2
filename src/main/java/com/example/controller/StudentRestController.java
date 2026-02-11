package com.example.controller;

import com.example.dto.*;
import com.example.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentFindAll>> get() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping
    public ResponseEntity<StudentCreated> post(@Valid @RequestBody StudentCreate dto) {
        var studentCreated = studentService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(studentCreated.studentId())
                .toUri();
        return ResponseEntity.created(location).body(studentCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentFindOne> get(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentUpdated> put(@PathVariable Long id,
                                              @Valid @RequestBody StudentUpdate dto) {
        return ResponseEntity.ok(studentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}