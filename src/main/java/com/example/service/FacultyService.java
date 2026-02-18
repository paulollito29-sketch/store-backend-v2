package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.FacultyMapper;
import com.example.repository.FacultyRepository;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository){
        this.facultyRepository = facultyRepository;
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
