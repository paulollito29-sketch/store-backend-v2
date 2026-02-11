package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.EventMapper;
import com.example.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventFindAll> findAll() {
        return eventRepository.findAllByEnabledIsTrueOrderByIdEventDesc().stream().map(EventMapper::toFindAll).toList();
    }

    public EventCreated create(EventCreate dto) {
        if (eventRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this event already exists");
        }
        var eventSaved = eventRepository.save(EventMapper.toEntityCreated(dto));
        return EventMapper.toCreated(eventSaved);
    }

    public EventFindOne findOne(Long id) {
        var event = eventRepository.findFirstByEnabledIsTrueAndIdEvent(id)
                .orElseThrow(() -> new ResourceNotFoundException("event not found"));
        return EventMapper.toFindOne(event);
    }

    public EventUpdated update(Long id, EventUpdate dto) {
        var event = eventRepository.findFirstByEnabledIsTrueAndIdEvent(id)
                .orElseThrow(() -> new ResourceNotFoundException("event not found"));
        if (eventRepository.existsByEnabledIsTrueAndIdEventNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this event already exists");
        }
        var eventSaved = eventRepository.save(EventMapper.toEntityUpdated(event, dto));
        return EventMapper.toUpdated(eventSaved);
    }

    public void delete(Long id) {
        var event = eventRepository.findFirstByEnabledIsTrueAndIdEvent(id)
                .orElseThrow(() -> new ResourceNotFoundException("event not found"));
        eventRepository.save(EventMapper.toEntityDeleted(event));
    }
}