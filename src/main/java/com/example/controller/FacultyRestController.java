package com.example.controller;

import com.example.dto.*;
import com.example.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/faculties")
public class FacultyRestController {

    private final FacultyService facultyService;

    public FacultyRestController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<FacultyFindAll>> get() {
        return ResponseEntity.ok(facultyService.findAll());
    }

    @PostMapping
    public ResponseEntity<FacultyCreated> post(@Valid @RequestBody FacultyCreate dto) {
        var facultyCreated = facultyService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(facultyCreated.facultyId())
                .toUri();

        return ResponseEntity.created(location).body(facultyCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyFindOne> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyUpdated> post(@Valid @RequestBody FacultyUpdate dto,
                                               @PathVariable Long id) {
        return ResponseEntity.ok(facultyService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facultyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
