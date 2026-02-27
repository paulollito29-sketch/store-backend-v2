package com.example.controller;

import com.example.dto.*;
import com.example.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionRestController {

    private final SectionService sectionService;

    public SectionRestController(SectionService sectionService){
        this.sectionService = sectionService;
    }

    @GetMapping
    public ResponseEntity<List<SectionFindAll>> get() {
        var sections = sectionService.findAll();
        return ResponseEntity.ok(sections);
    }

    @PostMapping
    public ResponseEntity<SectionCreated> post(@Valid @RequestBody SectionCreate dto) {
        var sectionCreated = sectionService.create(dto);
        //return ResponseEntity.created(location).body(sectionCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(sectionCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionFindOne> findOne(@PathVariable Long id) {
        var section = sectionService.findOne(id);
        return ResponseEntity.ok(section);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionUpdated> update(@PathVariable Long id,
                                                 @Valid @RequestBody SectionUpdate dto) {
        var sectionUpdated = sectionService.update(id, dto);
        return ResponseEntity.ok(sectionUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sectionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
