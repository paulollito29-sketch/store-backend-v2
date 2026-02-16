package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.CycleMapper;
import com.example.repository.CycleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CycleService {

    private final CycleRepository cycleRepository;

    public CycleService(CycleRepository cycleRepository) {
        this.cycleRepository = cycleRepository;
    }

    public List<CycleFindAll> findAll() {
        return cycleRepository.findAllByEnabledIsTrueOrderByIdCycleDesc().stream()
                .map(CycleMapper::toCycleFindAll)
                .toList();
    }

    public CycleCreated create(CycleCreate dto) {
        if (cycleRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name is already taken");
        }

        var cycleEntity = CycleMapper.toEntity(dto);
        var cycleSaved = cycleRepository.save(cycleEntity);
        return CycleMapper.toCycleCreated(cycleSaved);
    }

    public CycleFindOne findOne(Long id) {
        var cycle = cycleRepository.findFirstByEnabledIsTrueAndIdCycle(id)
                .orElseThrow(() -> new ResourceNotFoundException("cycle not found"));
        return CycleMapper.toCycleFindOne(cycle);
    }

    public CycleUpdated update(Long id, CycleUpdate dto) {
        var cycle = cycleRepository.findFirstByEnabledIsTrueAndIdCycle(id)
                .orElseThrow(() -> new ResourceNotFoundException("cycle not found"));

        if (cycleRepository.existsByEnabledIsTrueAndIdCycleNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }

        var cycleUpdated = CycleMapper.toEntity(cycle, dto);
        var cycleSaved = cycleRepository.save(cycleUpdated);
        return CycleMapper.toCycleUpdated(cycleSaved);
    }

    public void delete(Long id) {
        var cycle = cycleRepository.findFirstByEnabledIsTrueAndIdCycle(id)
                .orElseThrow(() -> new ResourceNotFoundException("cycle not found"));

        var cycleDeleted = CycleMapper.toEntity(cycle);
        cycleRepository.save(cycleDeleted);
    }
}