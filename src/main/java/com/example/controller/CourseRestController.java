package com.example.controller;

import com.example.dto.*;
import com.example.repository.CourseRepository;
import com.example.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController( CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseFindAll>> get(){
        var courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<CourseCreated> post(@Valid @RequestBody CourseCreate dto){
        var courseCreated = courseService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(courseCreated).toUri();
        return ResponseEntity.created(location).body(courseCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseFindOne> get(@PathVariable Long id){
        var course = courseService.findOne(id);
        return ResponseEntity.ok(course);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseUpdated> put(@Valid @RequestBody CourseUpdate dto,
                                             @PathVariable Long id) {
        var courseUpdated = courseService.update(dto, id);
        return ResponseEntity.ok(courseUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
