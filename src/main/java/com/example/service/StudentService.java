package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.StudentMapper;
import com.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentFindAll> findAll() {
        return studentRepository.findAllByEnabledIsTrueOrderByIdStudentDesc().stream().map(StudentMapper::toFindAll).toList();
    }

    public StudentCreated create(StudentCreate dto) {
        if (studentRepository.existsByEnabledIsTrueAndFirstNameIgnoreCaseAndLastNameIgnoreCase(dto.firstName(), dto.lastName())) {
            throw new ResourceAlreadyExistsException("this student already exists");
        }
        var studentSaved = studentRepository.save(StudentMapper.toEntityCreated(dto));
        return StudentMapper.toCreated(studentSaved);
    }

    public StudentFindOne findOne(Long id) {
        var student = studentRepository.findFirstByEnabledIsTrueAndIdStudent(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not found"));
        return StudentMapper.toFindOne(student);
    }

    public StudentUpdated update(Long id, StudentUpdate dto) {
        var student = studentRepository.findFirstByEnabledIsTrueAndIdStudent(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not found"));
        if (studentRepository.existsByEnabledIsTrueAndIdStudentNotAndFirstNameIgnoreCaseAndLastNameIgnoreCase(id, dto.firstName(), dto.lastName())) {
            throw new ResourceAlreadyExistsException("this student already exists");
        }
        var studentSaved = studentRepository.save(StudentMapper.toEntityUpdated(student, dto));
        return StudentMapper.toUpdated(studentSaved);
    }

    public void delete(Long id) {
        var student = studentRepository.findFirstByEnabledIsTrueAndIdStudent(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not found"));
        studentRepository.save(StudentMapper.toEntityDeleted(student));
    }
}