package com.example.controller;

import com.example.dto.*;
import com.example.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactRestController {

    private final ContactService contactService;

    public ContactRestController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactFindAll>> get() {
        var contacts = contactService.findAll();
        return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<ContactCreated> post(@Valid @RequestBody ContactCreate dto) {
        var contactCreated = contactService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contactCreated.contactId())
                .toUri();
        return ResponseEntity.created(location).body(contactCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactUpdated> put(@PathVariable Long id,
                                              @Valid @RequestBody ContactUpdate dto) {
        var contactUpdated = contactService.update(id, dto);
        return ResponseEntity.ok(contactUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactFindOne> findOne(@PathVariable Long id) {
        var contact = contactService.findOne(id);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}