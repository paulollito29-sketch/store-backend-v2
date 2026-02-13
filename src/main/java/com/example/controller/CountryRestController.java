package com.example.controller;

import com.example.dto.*;
import com.example.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryFindAll>> get() {
        var countries = countryService.findAll();
        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<CountryCreated> post(@Valid @RequestBody CountryCreate dto) {
        var countryCreated = countryService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(countryCreated.countryId())
                .toUri();
        return ResponseEntity.created(location).body(countryCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryUpdated> put(@PathVariable Long id,
                                              @RequestBody CountryUpdate dto) {
        var countryUpdated = countryService.update(id, dto);
        return ResponseEntity.ok(countryUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryFindOne> findOne(@PathVariable Long id) {
        var country = countryService.findOne(id);
        return ResponseEntity.ok(country);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}