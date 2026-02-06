package com.example.service;

import com.example.dto.*;
import com.example.entity.RegionEntity;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.RegionMapper;
import com.example.repository.RegionRepository;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.Region;
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
                .map(RegionMapper::toRegionFindAll)
                .toList();
    }

    public RegionCreated create(RegionCreate dto) {
        if (regionRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name is already taken");
        }
        var regionEntity = RegionMapper.toEntity(dto);
        var regionSaved = regionRepository.save(regionEntity);
        return RegionMapper.toRegionCreated(regionSaved);
    }

    public RegionFindOne findOne(Long id) {
        var region = regionRepository.findFirstByEnabledIsTrueAndIdRegion(id)
                .orElseThrow(() -> new ResourceNotFoundException("region not found"));
        return RegionMapper.toRegionFindOne(region);
    }

    public RegionUpdated update(Long id, RegionUpdate dto) {
        var region = regionRepository.findFirstByEnabledIsTrueAndIdRegion(id)
                .orElseThrow(() -> new ResourceNotFoundException("region not found"));

        if (regionRepository.existsByEnabledIsTrueAndIdRegionNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }
        var regionUpdated = RegionMapper.toEntity(region, dto);
        var regionSaved = regionRepository.save(regionUpdated);
        return RegionMapper.toRegionUpdated(regionSaved);
    }

    public void delete(Long id) {
        var region = regionRepository.findFirstByEnabledIsTrueAndIdRegion(id)
                .orElseThrow(() -> new ResourceNotFoundException("region not found"));
        var regionDeleted = RegionMapper.toEntity(region);
        regionRepository.save(regionDeleted);
    }
}
