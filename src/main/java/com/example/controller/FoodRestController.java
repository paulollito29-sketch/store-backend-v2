package com.example.controller;

import com.example.dto.*;
import com.example.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodRestController {

    private final FoodService foodService;

    public FoodRestController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity<List<FoodFindAll>> get() {
        var foods = foodService.FindALl();
        return ResponseEntity.ok(foods);
    }

    @PostMapping
    public ResponseEntity<FoodCreated> post(@Valid @RequestBody FoodCreate dto) {
        var foods = foodService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(foods.foodId()).toUri();
        return ResponseEntity.created(location).body(foods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodFindOne> FindOne(@PathVariable Long id) {
        var food = foodService.findOne(id);
        return ResponseEntity.ok(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodUpdated> post(@Valid @RequestBody FoodUpdate dto,
                                            @PathVariable Long id) {
        var foods = foodService.update(id,dto);
        return ResponseEntity.ok(foods);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        foodService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
