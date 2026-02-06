package com.example.controller;

import com.example.dto.TrainerFindAll;
import com.example.dto.*;
import com.example.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerRestController {

    private final TrainerService trainerService;

    public TrainerRestController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public ResponseEntity<List<TrainerFindAll>> get() {
        var trainers = trainerService.findAll();
        return ResponseEntity.ok(trainers);
    }

    @PostMapping
    public ResponseEntity<TrainerCreated> post(@Valid @RequestBody TrainerCreate dto) {
        var trainer = trainerService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(trainer.trainerId())
                .toUri();
        return ResponseEntity.created(location).body(trainer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerUpdated> update(@PathVariable Long id,
                                                 @RequestBody TrainerUpdate dto) {
        var trainerUpdated = trainerService.update(id, dto);
        return ResponseEntity.ok(trainerUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerFindOne> findOne(@PathVariable Long id) {
        var trainer = trainerService.findOne(id);
        return ResponseEntity.ok(trainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trainerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
