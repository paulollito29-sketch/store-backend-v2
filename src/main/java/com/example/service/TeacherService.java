package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.TeacherMapper;
import com.example.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherFindAll> findAll() {
        return teacherRepository.findAllByEnabledIsTrueOrderByIdTeacherDesc().stream().map(TeacherMapper::toFindAll).toList();
    }

    public TeacherCreated create(TeacherCreate dto) {
        if (teacherRepository.existsByEnabledIsTrueAndEmailIgnoreCase(dto.email())) {
            throw new ResourceAlreadyExistsException("this teacher email already exists");
        }
        var teacherSaved = teacherRepository.save(TeacherMapper.toEntityCreated(dto));
        return TeacherMapper.toCreated(teacherSaved);
    }

    public TeacherFindOne findOne(Long id) {
        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(id)
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found"));
        return TeacherMapper.toFindOne(teacher);
    }

    public TeacherUpdated update(Long id, TeacherUpdate dto) {
        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(id)
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found"));
        if (teacherRepository.existsByEnabledIsTrueAndIdTeacherNotAndEmailIgnoreCase(id, dto.email())) {
            throw new ResourceAlreadyExistsException("this teacher email already exists");
        }
        var teacherSaved = teacherRepository.save(TeacherMapper.toEntityUpdated(teacher, dto));
        return TeacherMapper.toUpdated(teacherSaved);
    }

    public void delete(Long id) {
        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(id)
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found"));
        teacherRepository.save(TeacherMapper.toEntityDeleted(teacher));
    }
}