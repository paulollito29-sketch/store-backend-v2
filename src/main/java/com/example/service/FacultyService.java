package com.example.service;

import com.example.dto.*;
import com.example.entity.FacultyEntity;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.FacultyMapper;
import com.example.repository.FacultyRepository;
import com.example.repository.SpecialityRepository;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final SpecialityRepository specialityRepository;

    public FacultyService(FacultyRepository facultyRepository, SpecialityRepository specialityRepository){
        this.facultyRepository = facultyRepository;
        this.specialityRepository = specialityRepository;
    }

    public List<FacultyFindAll> findAll(){
        return facultyRepository.findAllByEnabledIsTrueOrderByIdFacultyDesc()
                .stream()
                .map(FacultyMapper::toFindAll)
                .toList();
    }

    public FacultyCreated create(FacultyCreate dto){
        if (facultyRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())){
            throw new ResourceAlreadyExistsException("this faculty already exists");
        }

        var facultyEntity = FacultyMapper.toEntityCreate(dto);
        var facultySaved = facultyRepository.save(facultyEntity);
        return FacultyMapper.toFacultyCreated(facultySaved);
    }

    public FacultyFindOne findOne(Long id){
        var faculty = facultyRepository.findFirstByEnabledIsTrueAndIdFaculty(id)
                .orElseThrow(()-> new ResourceNotFoundException("no faculty found"));
        faculty.setSpeciality(specialityRepository.findAllByFacultyId(id));
        return FacultyMapper.toFindOne(faculty);
    }

    public FacultyUpdated update(FacultyUpdate dto, Long id){
        var faculty = facultyRepository.findFirstByEnabledIsTrueAndIdFaculty(id)
                .orElseThrow(()-> new ResourceNotFoundException("no faculty found"));
        if (facultyRepository.existsByEnabledIsTrueAndIdFacultyNotAndNameIgnoreCase(id, dto.name())){
            throw new ResourceAlreadyExistsException("this faculty already exists");
        }

        var facultyCreate = FacultyMapper.toFacultyUpdate(faculty, dto);
        var facultySaved = facultyRepository.save(facultyCreate);
        return FacultyMapper.toFacultyUpdated(facultySaved);
    }

    public void delete(Long id){
        var faculty = facultyRepository.findFirstByEnabledIsTrueAndIdFaculty(id)
                .orElseThrow(()-> new ResourceNotFoundException("no faculty found"));
        facultyRepository.save(FacultyMapper.toEntityDeleted(faculty));
    }

}
