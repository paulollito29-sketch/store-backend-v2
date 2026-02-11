package com.example.service;

import com.example.dto.*;
import com.example.exception.InvalidDateException;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.EventMapper;
import com.example.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        var eventDate = LocalDate.parse(dto.eventDate());
        var now = LocalDate.now();
        if(eventDate.isBefore(now)){
            throw new InvalidDateException("Invalid date. Must be after date now");
        }
        var eventCreated = EventMapper.toEntityCreated(dto);

        var eventSaved = eventRepository.save(eventCreated);
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
        var eventDate = LocalDate.parse(dto.eventDate());
        var now = LocalDate.now();
        if(eventDate.isBefore(now)){
            throw new InvalidDateException("Invalid date. Must be after date now");
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