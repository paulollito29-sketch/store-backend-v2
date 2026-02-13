package com.example.repository;

import com.example.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    List<CountryEntity> findAllByEnabledIsTrueOrderByIdCountryDesc();

    Optional<CountryEntity> findFirstByEnabledIsTrueAndIdCountry(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdCountryNotAndNameIgnoreCase(Long id, String name);
}