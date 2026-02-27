package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.SectionMapper;
import com.example.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<SectionFindAll> findAll() {
        return sectionRepository.findAllByEnabledIsTrueOrderByIdSectionDesc().stream()
                .map(SectionMapper::ToSectionFindAll)
                .toList();
    }

    public SectionCreated create(SectionCreate dto) {
        if (sectionRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this nombre: " + dto.name() + " already exists");
        }

        var sectionEntity = SectionMapper.ToEntity(dto);
        var sectionSaved = sectionRepository.save(sectionEntity);
        return SectionMapper.ToSectionCreated(sectionSaved);
    }

    public SectionFindOne findOne(Long id) {
        var section = sectionRepository.findFirstByEnabledIsTrueAndIdSection(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found"));
        return SectionMapper.ToSectionFindOne(section);
    }

    public SectionUpdated update(Long id, SectionUpdate dto) {
        var section = sectionRepository.findFirstByEnabledIsTrueAndIdSection(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found"));

        if (sectionRepository.existsByEnabledIsTrueAndIdSectionNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this nombre: " + dto.name() + " already exists");
        }

        var sectionUpdated = SectionMapper.ToEntity(section, dto);
        var sectionSaved = sectionRepository.save(sectionUpdated);
        return SectionMapper.ToSectionUpdated(sectionSaved);
    }

    public void delete(Long id) {
        var section = sectionRepository.findFirstByEnabledIsTrueAndIdSection(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found"));

        var sectionDeleted = SectionMapper.ToEntity(section);
        sectionRepository.save(sectionDeleted);
    }
}