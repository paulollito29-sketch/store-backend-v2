package com.example.controller;

import com.example.dto.*;
import com.example.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/api/clients")
public class ClientRestController {
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientFindAll>> get(){
        var clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<ClientCreated> post(@Valid @RequestBody ClientCreate dto) {
        var clients = clientService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand((clients))
                .toUri();
        return ResponseEntity.created(location).body(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientUpdated> put(@PathVariable Long id,
                                             @RequestBody ClientUpdate dto){
        var clientUpdated = clientService.update(id, dto);
        return ResponseEntity.ok(clientUpdated);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientFindOne> FindOne(@PathVariable Long id){
        var client = clientService.findOne(id);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
