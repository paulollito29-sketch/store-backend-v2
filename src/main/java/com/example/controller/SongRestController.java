package com.example.controller;

import com.example.dto.*;
import com.example.service.SongService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongRestController {

    private final SongService songService;

    public SongRestController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<List<SongFindAll>> get() {
        var songs = songService.findAll();
        return ResponseEntity.ok(songs);
    }

    @PostMapping
    public ResponseEntity<SongCreated> post(@Valid @RequestBody SongCreate dto) {
        var songCreated = songService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(songCreated.songId())
                .toUri();
        return ResponseEntity.created(location).body(songCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongUpdated> put(@PathVariable Long id,
                                           @RequestBody SongUpdate dto) {
        var songUpdated = songService.update(id, dto);
        return ResponseEntity.ok(songUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongFindOne> findOne(@PathVariable Long id) {
        var song = songService.findOne(id);
        return ResponseEntity.ok(song);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        songService.delete(id);
        return ResponseEntity.noContent().build();
    }
}