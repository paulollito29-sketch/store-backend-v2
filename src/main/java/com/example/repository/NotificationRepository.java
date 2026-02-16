package com.example.repository;

import com.example.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    boolean existsByTitleIgnoreCase(String title);

    boolean existsByIdNotificationNotAndTitleIgnoreCase(Long id, String title);
}