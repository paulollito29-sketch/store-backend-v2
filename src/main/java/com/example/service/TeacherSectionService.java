package com.example.service;

import com.example.dto.TeacherFullFindAll;
import com.example.entity.TeacherSectionEntity;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.SectionRepository;
import com.example.repository.TeacherRepository;
import com.example.repository.TeacherSectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherSectionService {
    private final TeacherSectionRepository teacherSectionRepository;
    private final TeacherRepository teacherRepository;
    private final SectionRepository sectionRepository;

    public TeacherSectionService(TeacherSectionRepository teacherSectionRepository, TeacherRepository teacherRepository, SectionRepository sectionRepository) {
        this.teacherSectionRepository = teacherSectionRepository;
        this.teacherRepository = teacherRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<TeacherFullFindAll> findAllTeachers(Long id){
        var section = sectionRepository.findFirstByEnabledIsTrueAndIdSection(id)
                .orElseThrow(()-> new ResourceNotFoundException("section not found"));
        return teacherSectionRepository.findAllBySection(section)
                .stream()
                .map(item -> new TeacherFullFindAll(item.getTeacher().getIdTeacher(), item.getTeacher().getFirstName() ))
                .toList();
    }

    /*
    public List<TeacherSectionEntity> findAllTeachers(Long id){
        return  sectionRepository.findFirstByEnabledIsTrueAndIdSection(id)
                .map(teacherSectionRepository::findAllBySection)
                .orElseThrow(()-> new ResourceNotFoundException("section not found"));
    }
    */

    public List<TeacherSectionEntity> findAllSections(Long id){
        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(id)
                .orElseThrow(()-> new ResourceNotFoundException("teacher not found"));
        return teacherSectionRepository.findAllByTeacher(teacher);
    }

}
