package com.example.controller;

import com.example.dto.*;
import com.example.service.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomRestController {

    private final ClassroomService classroomService;

    public ClassroomRestController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<ClassroomFindAll>> get() {
        return ResponseEntity.ok(classroomService.findAll());
    }

    @PostMapping
    public ResponseEntity<ClassroomCreated> post(@Valid @RequestBody ClassroomCreate dto) {
        var classroomCreated = classroomService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(classroomCreated.idClassroom())
                .toUri();
        return ResponseEntity.created(location).body(classroomCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomFindOne> get(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomUpdated> put(@PathVariable Long id,
                                                @Valid @RequestBody ClassroomUpdate dto) {
        return ResponseEntity.ok(classroomService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classroomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
