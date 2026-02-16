package com.example.controller;

import com.example.dto.*;
import com.example.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationRestController {

    private final NotificationService notificationService;

    public NotificationRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationFindAll>> get() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @PostMapping
    public ResponseEntity<NotificationCreated> post(@Valid @RequestBody NotificationCreate dto) {
        var notificationCreated = notificationService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(notificationCreated.idNotification())
                .toUri();
        return ResponseEntity.created(location).body(notificationCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationFindOne> get(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationUpdated> put(@PathVariable Long id,
                                                   @Valid @RequestBody NotificationUpdate dto) {
        return ResponseEntity.ok(notificationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}