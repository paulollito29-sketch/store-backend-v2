package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.SpecialityMapper;
import com.example.repository.FacultyRepository;
import com.example.repository.SpecialityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {

    private final FacultyRepository facultyRepository;
    private final SpecialityRepository specialityRepository;

    public SpecialityService(FacultyRepository facultyRepository, SpecialityRepository specialityRepository){
        this.facultyRepository = facultyRepository;
        this.specialityRepository = specialityRepository;
    }
    public List<SpecialityFindAll> findAll(){
        return specialityRepository.findAllByEnabledIsTrueOrderByIdSpecialityDesc()
                .stream()
                .map(SpecialityMapper::toFindAll)
                .toList();
    }

    public SpecialityCreated create(SpecialityCreate dto){
        if (specialityRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())){
            throw new ResourceAlreadyExistsException("this speciality already exists");
        }
        var faculty = facultyRepository.findByEnabledIsTrueAndIdFaculty(dto.facultyId())
                .orElseThrow(()-> new ResourceNotFoundException("no faculty found"));

        var speciality = SpecialityMapper.toEntityCreated(dto, faculty);
        var specialitySaved = specialityRepository.save(speciality);
        System.out.println(specialitySaved);
        return SpecialityMapper.toSpecialityCreated(specialitySaved);
    }

    public SpecialityFindOne findOne(Long id){
        var speciality = specialityRepository.findFirstByEnabledIsTrueAndIdSpeciality(id)
                .orElseThrow(() -> new ResourceNotFoundException("no speciality found"));

        return SpecialityMapper.toFindOne(speciality);
    }

    public SpecialityUpdated update(SpecialityUpdate dto, Long id){
        var speciality = specialityRepository.findFirstByEnabledIsTrueAndIdSpeciality(id)
                .orElseThrow(() -> new ResourceNotFoundException("no speciality found"));

        if (specialityRepository.existsByEnabledIsTrueAndIdSpecialityNotAndNameIgnoreCase(id, dto.name())){
            throw new ResourceAlreadyExistsException("this speciality already exists");
        }

        var faculty = facultyRepository.findFirstByEnabledIsTrueAndIdFaculty(dto.facultyId())
                .orElseThrow(() -> new ResourceNotFoundException("no faculty found"));

        var specialityUpdated = SpecialityMapper.toEntityUpdated(speciality, dto, faculty);
        var specialitySaved = specialityRepository.save(specialityUpdated);
        return SpecialityMapper.toSpecialityUpdated(specialitySaved);
    }

    public void delete(Long id){
        var speciality = specialityRepository.findFirstByEnabledIsTrueAndIdSpeciality(id)
                .orElseThrow(() -> new ResourceNotFoundException("no speciality found"));

        var specialityDeleted = SpecialityMapper.toEntityDeleted(speciality);
        specialityRepository.save(specialityDeleted);
    }



}
