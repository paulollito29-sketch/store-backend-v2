package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.SupplierMapper;
import com.example.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierFindAll> findAll() {
        return supplierRepository.findAllByEnabledIsTrueOrderByIdSupplierDesc().stream().map(SupplierMapper::toFindAll).toList();
    }

    public SupplierCreated create(SupplierCreate dto) {
        if (supplierRepository.existsByEnabledIsTrueAndEmailIgnoreCase(dto.email())) {
            throw new ResourceAlreadyExistsException("this supplier email already exists");
        }
        var supplierSaved = supplierRepository.save(SupplierMapper.toEntityCreated(dto));
        return SupplierMapper.toCreated(supplierSaved);
    }

    public SupplierFindOne findOne(Long id) {
        var supplier = supplierRepository.findFirstByEnabledIsTrueAndIdSupplier(id)
                .orElseThrow(() -> new ResourceNotFoundException("supplier not found"));
        return SupplierMapper.toFindOne(supplier);
    }

    public SupplierUpdated update(Long id, SupplierUpdate dto) {
        var supplier = supplierRepository.findFirstByEnabledIsTrueAndIdSupplier(id)
                .orElseThrow(() -> new ResourceNotFoundException("supplier not found"));
        if (supplierRepository.existsByEnabledIsTrueAndIdSupplierNotAndEmailIgnoreCase(id, dto.email())) {
            throw new ResourceAlreadyExistsException("this supplier email already exists");
        }
        var supplierSaved = supplierRepository.save(SupplierMapper.toEntityUpdated(supplier, dto));
        return SupplierMapper.toUpdated(supplierSaved);
    }

    public void delete(Long id) {
        var supplier = supplierRepository.findFirstByEnabledIsTrueAndIdSupplier(id)
                .orElseThrow(() -> new ResourceNotFoundException("supplier not found"));
        supplierRepository.save(SupplierMapper.toEntityDeleted(supplier));
    }
}