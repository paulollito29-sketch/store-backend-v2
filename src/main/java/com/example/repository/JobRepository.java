package com.example.repository;

import com.example.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<JobEntity,Long> {

    List<JobEntity> findAllByEnabledIsTrueOrderByIdJobDesc();

    Optional<JobEntity> findFirstByEnabledIsTrueAndIdJob(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdJobNotAndNameIgnoreCase(Long id, String name);


}
