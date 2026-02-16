package com.example.repository;

import com.example.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByEnabledIsTrueOrderByIdNotificationDesc();

    Optional<NotificationEntity> findFirstByEnabledIsTrueAndIdNotification(Long id);

    boolean existsByEnabledIsTrueAndTitleIgnoreCase(String title);
    boolean existsByEnabledIsTrueAndIdNotificationNotAndTitleIgnoreCase(Long id, String title);
}