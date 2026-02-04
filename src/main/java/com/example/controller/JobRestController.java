package com.example.controller;

import com.example.dto.*;
import com.example.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobRestController {

    private final JobService jobService;

    public JobRestController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobFindAll>> get(){
        var jobs = jobService.findAll();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<JobCreated> post(@Valid @RequestBody JobCreate dto){
        var jobCreated = jobService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(jobCreated.JobId())
                .toUri();
        return ResponseEntity.created(location).body(jobCreated);

    }
    @PutMapping("/{id}")
    public ResponseEntity<JobUpdated> put(@PathVariable Long id,
                                          @RequestBody JobUpdate dto){
        var jobUpdated = jobService.update(id, dto);
        return ResponseEntity.ok(jobUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobFindOne> findOne(@PathVariable Long id) {
        var job = jobService.findOne(id);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
