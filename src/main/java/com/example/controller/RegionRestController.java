package com.example.controller;

import com.example.dto.*;
import com.example.service.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionRestController {

    private final RegionService regionService;

    public RegionRestController(RegionService regionService){
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<RegionFindAll>> getRegions(){
        var region = regionService.findAll();
        return ResponseEntity.ok(region);
    }
    @PostMapping
    public ResponseEntity<RegionCreated> post(@RequestBody RegionCreate dto) {
        var regionCreated = regionService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(regionCreated.regionId())
                .toUri();
        return ResponseEntity.created(location).body(regionCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionUpdated> update(@PathVariable Long id,
                                                  @RequestBody RegionUpdate dto) {
        var regionUpdated = regionService.update(id, dto);
        return ResponseEntity.ok(regionUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionFindOne> findOne(@PathVariable Long id) {
        var region = regionService.findOne(id);
        return ResponseEntity.ok(region);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        regionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
