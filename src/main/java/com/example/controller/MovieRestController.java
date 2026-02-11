package com.example.controller;

import com.example.dto.*;
import com.example.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    private final MovieService movieService;

    public MovieRestController(MovieService movieService){this.movieService = movieService;}

    @GetMapping
    public ResponseEntity<List<MovieFindAll>> getAll(){
        var movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<MovieCreated> post(@Valid @RequestBody MovieCreate dto) {
        var movie = movieService.create(dto);
        var location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movie)
                .toUri();
        return ResponseEntity.created(location).body(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieFindOne> findOne(@PathVariable String id){
        var movie = movieService.findOne(id);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieUpdated> post(@PathVariable String id,
                                             @Valid @RequestBody MovieUpdate dto) {
        var movie = movieService.update(id,dto);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
