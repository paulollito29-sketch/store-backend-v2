package com.example.controller;

import com.example.dto.*;
import com.example.service.AnimalService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
//new
//new new comment

@RestController
@RequestMapping("/api/animals")
public class AnimalRestController {
    private final AnimalService animalService;

    public AnimalRestController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalFindAll>> get() {
        var animals = animalService.findAll();
        return ResponseEntity.ok(animals);
    }

    @PostMapping
    public ResponseEntity<AnimalCreated> post(@Valid @RequestBody AnimalCreate dto) {
        var animals = animalService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(animals)
                .toUri();
        return ResponseEntity.created(location).body(animals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalFindOne> findOne(@PathVariable Long id) {
        var animal = animalService.findOne(id);
        return ResponseEntity.ok(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalUpdated> update(@PathVariable Long id,
                                                @Valid @RequestBody AnimalUpdate dto) {
        var animalUpdated = animalService.update(dto, id);
        return ResponseEntity.ok(animalUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
