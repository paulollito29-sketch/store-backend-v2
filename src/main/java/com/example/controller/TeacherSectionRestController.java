package com.example.controller;

import com.example.dto.TeacherSectionsFindAll;
import com.example.service.TeacherSectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teachersections")

public class TeacherSectionRestController {

    private final TeacherSectionService teacherSectionService;

    public TeacherSectionRestController(TeacherSectionService teacherSectionService){

        this.teacherSectionService = teacherSectionService;

    }

    @GetMapping("/ts/{id}")
    public ResponseEntity<List<TeacherSectionsFindAll>> get(@PathVariable Long id){
        return ResponseEntity.ok().body(teacherSectionService.findAllSections(id));
    }

}
