package com.example.controller;

import com.example.dto.*;
import com.example.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierRestController {

    private final SupplierService supplierService;

    public SupplierRestController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierFindAll>> get() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    @PostMapping
    public ResponseEntity<SupplierCreated> post(@Valid @RequestBody SupplierCreate dto) {
        var supplierCreated = supplierService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(supplierCreated.supplierId())
                .toUri();
        return ResponseEntity.created(location).body(supplierCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierFindOne> get(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierUpdated> put(@PathVariable Long id,
                                               @Valid @RequestBody SupplierUpdate dto) {
        return ResponseEntity.ok(supplierService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}