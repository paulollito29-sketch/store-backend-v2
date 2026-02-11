package com.example.controller;

import com.example.dto.*;
import com.example.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventFindAll>> get() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @PostMapping
    public ResponseEntity<EventCreated> post(@Valid @RequestBody EventCreate dto) {
        var eventCreated = eventService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventCreated.eventId())
                .toUri();
        return ResponseEntity.created(location).body(eventCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventFindOne> get(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventUpdated> put(@Valid @PathVariable Long id,
                                            @Valid @RequestBody EventUpdate dto) {
        return ResponseEntity.ok(eventService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}