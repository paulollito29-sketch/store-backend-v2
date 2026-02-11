package com.example.repository;

import com.example.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findAllByEnabledIsTrueOrderByIdEventDesc();

    Optional<EventEntity> findFirstByEnabledIsTrueAndIdEvent(Long id);

    boolean existsByEnabledIsTrueAndNameIgnoreCase(String name);

    boolean existsByEnabledIsTrueAndIdEventNotAndNameIgnoreCase(Long id, String name);
}