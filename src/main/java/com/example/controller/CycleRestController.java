package com.example.controller;

import com.example.dto.*;
import com.example.service.CycleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/cycles")
public class CycleRestController {

    private final CycleService cycleService;

    public CycleRestController(CycleService cycleService) {
        this.cycleService = cycleService;
    }

    @GetMapping
    public ResponseEntity<List<CycleFindAll>> get() {
        var cycles = cycleService.findAll();
        return ResponseEntity.ok(cycles);
    }

    @PostMapping
    public ResponseEntity<CycleCreated> post(@RequestBody CycleCreate dto) {
        var cycleCreated = cycleService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cycleCreated.cycleId())
                .toUri();
        return ResponseEntity.created(location).body(cycleCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CycleUpdated> put(@PathVariable Long id,
                                            @RequestBody CycleUpdate dto) {
        var cycleUpdated = cycleService.update(id, dto);
        return ResponseEntity.ok(cycleUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CycleFindOne> findOne(@PathVariable Long id) {
        var cycle = cycleService.findOne(id);
        return ResponseEntity.ok(cycle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cycleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}