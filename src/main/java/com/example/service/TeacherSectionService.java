package com.example.service;

import com.example.dto.*;
import com.example.entity.TeacherSectionEntity;
import com.example.entity.TeacherSectionPK;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.TeacherSectionMapper;
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
                .map(TeacherSectionMapper::findAll)
                .toList();
    }

    /*
    public List<TeacherSectionEntity> findAllTeachers(Long id){
        return  sectionRepository.findFirstByEnabledIsTrueAndIdSection(id)
                .map(teacherSectionRepository::findAllBySection)
                .orElseThrow(()-> new ResourceNotFoundException("section not found"));
    }
    */

    public List<TeacherSectionsFindAll> findAllSections(Long id){
        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(id)
                .orElseThrow(()-> new ResourceNotFoundException("teacher not found"));
        return teacherSectionRepository.findAllByTeacher(teacher);
    }

    public TeacherSectionEntity create(Long idSection, Long idTeacher){
        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(idTeacher)
                .orElseThrow(()-> new ResourceNotFoundException("teacher not found"));
        var section = sectionRepository.findFirstByEnabledIsTrueAndIdSection(idSection)
                .orElseThrow(()-> new ResourceNotFoundException("Section not found"));
        return teacherSectionRepository.save(TeacherSectionMapper.toEntityCreated(teacher.getIdTeacher(),section.getIdSection()));
    }

    public TeacherSectionUpdated update(Long idSection, Long idTeacher, TeacherSectionUpdate dto){
        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(idTeacher)
                .orElseThrow(()-> new ResourceNotFoundException("teacher not found"));
        var section = sectionRepository.findFirstByEnabledIsTrueAndIdSection(idSection)
                .orElseThrow(()-> new ResourceNotFoundException("Section not found"));
        var teacherSectionSaved = teacherSectionRepository.save(TeacherSectionMapper.toEntityUpdated(teacher, section, dto));
        return TeacherSectionMapper.toUpdated(teacherSectionSaved);

    }
}
