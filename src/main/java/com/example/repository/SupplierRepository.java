package com.example.repository;

import com.example.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {

    List<SupplierEntity> findAllByEnabledIsTrueOrderByIdSupplierDesc();

    Optional<SupplierEntity> findFirstByEnabledIsTrueAndIdSupplier(Long id);

    boolean existsByEnabledIsTrueAndEmailIgnoreCase(String email);

    boolean existsByEnabledIsTrueAndIdSupplierNotAndEmailIgnoreCase(Long id, String email);
}