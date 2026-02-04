package com.example.service;

import com.example.dto.*;
import com.example.entity.RegionEntity;
import com.example.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<RegionFindAll> findAll() {
        return regionRepository.findAllByEnabledIsTrueOrderByIdRegionDesc().stream()
                .map(item -> new RegionFindAll(item.getIdRegion(), item.getName()))
                .toList();
    }

    public RegionCreated create(RegionCreate dto) {
        if (regionRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new RuntimeException("this name is already taken");
        }
        var regionEntity = RegionEntity.builder()
                .name(dto.name())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
        var regionSaved = regionRepository.save(regionEntity);
        return new RegionCreated(regionSaved.getIdRegion(), regionSaved.getName());
    }

    public RegionFindOne findOne(Long id) {
        var region = regionRepository.findFirstByEnabledIsTrueAndIdRegion(id)
                .orElseThrow(() -> new RuntimeException("region not found"));
        return new RegionFindOne(region.getIdRegion(), region.getName());
    }

    public RegionUpdated update(Long id, RegionUpdate dto) {
        var region = regionRepository.findFirstByEnabledIsTrueAndIdRegion(id)
                .orElseThrow(() -> new RuntimeException("region not found"));

        if (regionRepository.existsByEnabledIsTrueAndIdRegionNotAndNameIgnoreCase(id, dto.name())) {
            throw new RuntimeException("this name: " + dto.name() + " already exists");
        }
        region.setName(dto.name());
        region.setUpdatedAt(LocalDateTime.now());
        var regionSaved = regionRepository.save(region);
        return new RegionUpdated(region.getIdRegion(), region.getName());
    }

    public void delete(Long id) {
        var region = regionRepository.findFirstByEnabledIsTrueAndIdRegion(id)
                .orElseThrow(() -> new RuntimeException("region not found"));
        region.setEnabled(false);
        region.setDeletedAt(LocalDateTime.now());
        regionRepository.save(region);
    }
}
