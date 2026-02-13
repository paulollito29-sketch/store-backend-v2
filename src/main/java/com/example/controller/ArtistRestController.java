package com.example.controller;

import com.example.dto.*;
import com.example.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistRestController {

    private final ArtistService artistService;

    public ArtistRestController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistFindAll>> get() {
        var artists = artistService.findAll();
        return ResponseEntity.ok(artists);
    }

    @PostMapping
    public ResponseEntity<ArtistCreated> post(@Valid @RequestBody ArtistCreate dto) {
        var artistCreated = artistService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(artistCreated.artistId())
                .toUri();
        return ResponseEntity.created(location).body(artistCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistUpdated> put(@PathVariable Long id,
                                             @RequestBody ArtistUpdate dto) {
        var artistUpdated = artistService.update(id, dto);
        return ResponseEntity.ok(artistUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistFindOne> findOne(@PathVariable Long id) {
        var artist = artistService.findOne(id);
        return ResponseEntity.ok(artist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}