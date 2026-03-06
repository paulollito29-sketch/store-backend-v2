package com.example.service;

import com.example.dto.*;
import com.example.entity.SectionEntity;
import com.example.entity.TeacherEntity;
import com.example.entity.TeacherSectionEntity;
import com.example.entity.TeacherSectionPK;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.TeacherSectionMapper;
import com.example.repository.SectionRepository;
import com.example.repository.TeacherRepository;
import com.example.repository.TeacherSectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherSectionService {
    private final TeacherSectionRepository teacherSectionRepository;
    private final TeacherRepository teacherRepository;
    private final SectionRepository sectionRepository;

    public TeacherSectionService(TeacherSectionRepository teacherSectionRepository,
                                 TeacherRepository teacherRepository,
                                 SectionRepository sectionRepository) {
        this.teacherSectionRepository = teacherSectionRepository;
        this.teacherRepository = teacherRepository;
        this.sectionRepository = sectionRepository;
    }


    public List<TeacherSecctionsDTO> findAll() {

        List<TeacherSecctionsDTO> lista = new ArrayList<>();

        var sections = sectionRepository.findAllByEnabledIsTrue();
        var teachers = teacherRepository.findAllByEnabledIsTrue();
        var teachersSections = teacherSectionRepository.findAll();

        for (TeacherEntity teacher : teachers) {

            TeacherSecctionsDTO dto = new TeacherSecctionsDTO();
            dto.setTeacherId(teacher.getIdTeacher());
            dto.setNameComplete(teacher.getFirstName() + " " +teacher.getLastName());
            List<String> teacherSectionsList = new ArrayList<>();
            for (TeacherSectionEntity teacherSection : teachersSections) {
                if (teacherSection.getTeacher().getIdTeacher().equals(teacher.getIdTeacher())) {
                    for (SectionEntity section : sections) {
                        if (section.getIdSection().equals(teacherSection.getSection().getIdSection())) {
                            teacherSectionsList.add(section.getName());
                        }
                    }
                }
            }

            dto.setSections(teacherSectionsList);
            lista.add(dto);
        }

        return lista;
    }

    public TeacherSectionCreated create(TeacherSectionCreate dto) {
        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(dto.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found"));

        var section = sectionRepository.findFirstByEnabledIsTrueAndIdSection(dto.sectionId())
                .orElseThrow(() -> new ResourceNotFoundException("section not found"));

        var pk = new TeacherSectionPK(teacher.getIdTeacher(), section.getIdSection());
        if (teacherSectionRepository.existsById(pk)) {
            throw new ResourceAlreadyExistsException("teacher-section relation already exists");
        }

        var created = teacherSectionRepository.save(TeacherSectionMapper.toEntity(teacher, section));
        return TeacherSectionMapper.toEntityCreated(created);
    }
    /*
    public List<TeacherSectionEntity> findAllTeachers(Long id){
        return  sectionRepository.findFirstByEnabledIsTrueAndIdSection(id)
                .map(teacherSectionRepository::findAllBySection)
                .orElseThrow(()-> new ResourceNotFoundException("section not found"));
    }
    */

    public TeacherSectionFindOne findOne(Long teacherId, Long sectionId) {
        var teacherSection = teacherSectionRepository.findById(new TeacherSectionPK(teacherId, sectionId))
                .orElseThrow(() -> new ResourceNotFoundException("teacher-section not found"));

        return TeacherSectionMapper.toFindOne(teacherSection);
    }

    public TeacherSectionUpdated update(Long teacherId, Long sectionId, TeacherSectionUpdate dto) {
        teacherSectionRepository.findById(new TeacherSectionPK(teacherId, sectionId))
                .orElseThrow(() -> new ResourceNotFoundException("teacher-section not found"));

        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(dto.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found"));

        var section = sectionRepository.findFirstByEnabledIsTrueAndIdSection(dto.sectionId())
                .orElseThrow(() -> new ResourceNotFoundException("section not found"));

        var updatedEntity = TeacherSectionMapper.toEntity(teacher, section);
        if (teacherId.longValue() != dto.teacherId() || sectionId.longValue() != dto.sectionId()) {
            teacherSectionRepository.deleteById(new TeacherSectionPK(teacherId, sectionId));
        }

        var saved = teacherSectionRepository.save(updatedEntity);
        return TeacherSectionMapper.toUpdated(saved);
    }

    public List<TeacherFullFindAll> findTeachersBySection(Long sectionId) {
        var section = sectionRepository.findFirstByEnabledIsTrueAndIdSection(sectionId)
                .orElseThrow(() -> new ResourceNotFoundException("section not found"));

        return teacherSectionRepository.findAllBySection(section).stream()
                .map(TeacherSectionMapper::toTeacherFindAll)
                .toList();
    }

    public List<SectionFindAll> findSectionsByTeacher(Long teacherId) {
        var teacher = teacherRepository.findFirstByEnabledIsTrueAndIdTeacher(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("teacher not found"));

        return teacherSectionRepository.findAllByTeacher(teacher).stream()
                .map(TeacherSectionMapper::toSectionFindAll)
                .toList();
    }

    public void delete(Long teacherId, Long sectionId) {
        var pk = new TeacherSectionPK(teacherId, sectionId);
        if (!teacherSectionRepository.existsById(pk)) {
            throw new ResourceNotFoundException("teacher-section not found");
        }
        teacherSectionRepository.deleteById(pk);
    }



}
