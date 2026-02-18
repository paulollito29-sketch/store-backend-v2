package com.example.service;

import com.example.dto.SpecialityFindAll;
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



}
