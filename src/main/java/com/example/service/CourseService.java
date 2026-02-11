package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.CourseMapper;
import com.example.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<CourseFindAll> findAll(){
        return courseRepository.findAllByEnabledIsTrueOrderByIdCourseDesc().stream().map(CourseMapper::toFindAll).toList();
    }

    public CourseCreated create(CourseCreate dto){
        if (courseRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())){
            throw new ResourceAlreadyExistsException("this course already exists");
        }
        var course = CourseMapper.toEntityCreated(dto);
        var courseSaved = courseRepository.save(course);
        return CourseMapper.toCourseCreated(courseSaved);
    }

    public CourseFindOne findOne(Long id){
        var course = courseRepository.findFirstByEnabledIsTrueAndIdCourse(id)
                .orElseThrow(()-> new ResourceNotFoundException("course not found"));

        return CourseMapper.toFindOne(course);
    }

    public CourseUpdated update(CourseUpdate dto, Long id ){
        var course = courseRepository.findFirstByEnabledIsTrueAndIdCourse(id)
                .orElseThrow(()-> new ResourceNotFoundException("course not found"));
        if (courseRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())){
            throw new ResourceAlreadyExistsException("this course already exists");
        }
        var courseUpdated = CourseMapper.toEntityUpdated(course, dto);
        var courseSaved = courseRepository.save(courseUpdated);
        return CourseMapper.toCourseUpdated(courseSaved);

    }

    public void delete(Long id){
        var course = courseRepository.findFirstByEnabledIsTrueAndIdCourse(id)
                .orElseThrow(()-> new ResourceNotFoundException("course not found"));
        var courseDeleted = CourseMapper.toEntityDeleted(course);
        courseRepository.save(courseDeleted);
    }

}
