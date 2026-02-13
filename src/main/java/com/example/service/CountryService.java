package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.CountryMapper;
import com.example.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryFindAll> findAll() {
        return countryRepository.findAllByEnabledIsTrueOrderByIdCountryDesc().stream()
                .map(CountryMapper::toCountryFindAll)
                .toList();
    }

    public CountryCreated create(CountryCreate dto) {
        if (countryRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }

        var countryEntity = CountryMapper.toEntity(dto);
        var countrySaved = countryRepository.save(countryEntity);
        return CountryMapper.toCountryCreated(countrySaved);
    }

    public CountryFindOne findOne(Long id) {
        var country = countryRepository.findFirstByEnabledIsTrueAndIdCountry(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found"));

        return CountryMapper.toCountryFindOne(country);
    }

    public CountryUpdated update(Long id, CountryUpdate dto) {
        var country = countryRepository.findFirstByEnabledIsTrueAndIdCountry(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found"));

        if (countryRepository.existsByEnabledIsTrueAndIdCountryNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }

        var countryEntity = CountryMapper.toEntity(country, dto);
        var countrySaved = countryRepository.save(countryEntity);
        return CountryMapper.toCountryUpdated(countrySaved);
    }

    public void delete(Long id) {
        var country = countryRepository.findFirstByEnabledIsTrueAndIdCountry(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found"));

        var countryEntity = CountryMapper.toEntity(country);
        countryRepository.save(countryEntity);
    }
}