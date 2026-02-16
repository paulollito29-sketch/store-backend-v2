package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.NotificationMapper;
import com.example.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationFindAll> findAll() {
        return notificationRepository.findAll().stream()
                .map(NotificationMapper::toFindAll)
                .toList();
    }

    public NotificationCreated create(NotificationCreate dto) {
        if (notificationRepository.existsByTitleIgnoreCase(dto.title())) {
            throw new ResourceAlreadyExistsException("this notification already exists");
        }
        var notificationSaved = notificationRepository.save(NotificationMapper.toEntityCreated(dto));
        return NotificationMapper.toCreated(notificationSaved);
    }

    public NotificationFindOne findOne(Long id) {
        var notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("notification not found"));
        return NotificationMapper.toFindOne(notification);
    }

    public NotificationUpdated update(Long id, NotificationUpdate dto) {
        var notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("notification not found"));
        if (notificationRepository.existsByIdNotificationNotAndTitleIgnoreCase(id, dto.title())) {
            throw new ResourceAlreadyExistsException("this notification already exists");
        }
        var notificationSaved = notificationRepository.save(NotificationMapper.toEntityUpdated(notification, dto));
        return NotificationMapper.toUpdated(notificationSaved);
    }

    public void delete(Long id) {
        var notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("notification not found"));
        notificationRepository.delete(notification);
    }
}
