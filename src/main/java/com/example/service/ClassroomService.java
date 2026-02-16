package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.ClassroomMapper;
import com.example.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<ClassroomFindAll> findAll() {
        return classroomRepository.findAllByEnabledIsTrueOrderByIdClassroomDesc().stream()
                .map(ClassroomMapper::toFindAll)
                .toList();
    }

    public ClassroomCreated create(ClassroomCreate dto) {
        if (classroomRepository.existsByEnabledIsTrueAndCourseIgnoreCase(dto.course())) {
            throw new ResourceAlreadyExistsException("this classroom already exists");
        }
        var classroomSaved = classroomRepository.save(ClassroomMapper.toEntityCreated(dto));
        return ClassroomMapper.toCreated(classroomSaved);
    }

    public ClassroomFindOne findOne(Long id) {
        var classroom = classroomRepository.findFirstByEnabledIsTrueAndIdClassroom(id)
                .orElseThrow(() -> new ResourceNotFoundException("classroom not found"));
        return ClassroomMapper.toFindOne(classroom);
    }

    public ClassroomUpdated update(Long id, ClassroomUpdate dto) {
        var classroom = classroomRepository.findFirstByEnabledIsTrueAndIdClassroom(id)
                .orElseThrow(() -> new ResourceNotFoundException("classroom not found"));
        if (classroomRepository.existsByEnabledIsTrueAndIdClassroomNotAndCourseIgnoreCase(id, dto.course())) {
            throw new ResourceAlreadyExistsException("this classroom already exists");
        }
        var classroomSaved = classroomRepository.save(ClassroomMapper.toEntityUpdated(classroom, dto));
        return ClassroomMapper.toUpdated(classroomSaved);
    }

    public void delete(Long id) {
        var classroom = classroomRepository.findFirstByEnabledIsTrueAndIdClassroom(id)
                .orElseThrow(() -> new ResourceNotFoundException("classroom not found"));
        classroomRepository.save(ClassroomMapper.toEntityDeleted(classroom));
    }
}
