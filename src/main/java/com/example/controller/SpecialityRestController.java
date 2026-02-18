package com.example.controller;

import com.example.dto.*;
import com.example.entity.SpecialityEntity;
import com.example.service.SpecialityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/specialities")
public class SpecialityRestController {

    private final SpecialityService specialityService;

    public SpecialityRestController(SpecialityService specialityService) {this.specialityService = specialityService;}

    @GetMapping
    public ResponseEntity<List<SpecialityFindAll>> findAll(){
        var specialities = specialityService.findAll();
        return ResponseEntity.ok(specialities);
    }

    @PostMapping
    public ResponseEntity<SpecialityCreated> post(@Valid @RequestBody SpecialityCreate dto){
        var specialityCreated = specialityService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(specialityCreated.specialityId())
                .toUri();
        return ResponseEntity.created(location).body(specialityCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityFindOne> get(@Valid @PathVariable Long id) {
        var speciality = specialityService.findOne(id);
        return ResponseEntity.ok(speciality);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialityUpdated> put(@Valid @PathVariable Long id,
                                                 @Valid @RequestBody SpecialityUpdate dto){
        var specialityUpdated = specialityService.update(dto, id);
        return ResponseEntity.ok(specialityUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        specialityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
