package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.JobMapper;
import com.example.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobFindAll> findAll() {
        return jobRepository.findAllByEnabledIsTrueOrderByIdJobDesc().stream()
                .map(JobMapper::toJobFindAll)
                .toList();
    }

    public JobCreated create(JobCreate dto) {
        if (jobRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }
        var jobEntity = JobMapper.ToEntity(dto);
        var jobSaved = jobRepository.save(jobEntity);
        return JobMapper.toJobCreated(jobSaved);
    }

    public JobFindOne findOne(Long id) {
        var job = jobRepository.findFirstByEnabledIsTrueAndIdJob(id).
                orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        return JobMapper.ToJobFindOne(job);
    }

    public JobUpdated update(Long id, JobUpdate dto) {
        var job = jobRepository.findFirstByEnabledIsTrueAndIdJob(id).
                orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        if (jobRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }

        var jobEntity = JobMapper.ToEntity(job, dto);
        var jobSaved = jobRepository.save(jobEntity);
        return JobMapper.ToJobUpdated(jobSaved);

    }

    public void delete(Long id){
        var job = jobRepository.findFirstByEnabledIsTrueAndIdJob(id).
                orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        var jobEntity = JobMapper.ToEntity(job);
        var jobSaved = jobRepository.save(jobEntity);
    }
}
