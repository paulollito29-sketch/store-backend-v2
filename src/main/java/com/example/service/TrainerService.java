package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.TrainerMapper;
import com.example.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    //method findAll obtains trainers
    public List<TrainerFindAll> findAll() {
        return trainerRepository.findAllByEnabledIsTrueOrderByIdTrainerDesc().stream()
                .map(TrainerMapper::ToTrainerFindAll)
                .toList();
    }

    public TrainerCreated create(TrainerCreate dto) {
        if (trainerRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }
        var trainerEntity = TrainerMapper.ToEntity(dto);
        var trainerSaved = trainerRepository.save(trainerEntity);
        return TrainerMapper.ToTrainerCreated(trainerSaved);
    }

    public TrainerFindOne findOne(Long id) {
        var trainer = trainerRepository.findFirstByEnabledIsTrueAndIdTrainer(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        return TrainerMapper.ToTrainerFindOne(trainer);
    }

    public TrainerUpdated update(Long id, TrainerUpdate dto) {
        var trainer = trainerRepository.findFirstByEnabledIsTrueAndIdTrainer(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        if (trainerRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }

        var trainerUpdate = TrainerMapper.toEntity(trainer, dto);
        var trainerSaved = trainerRepository.save(trainerUpdate);
        return TrainerMapper.toTrainerUpdated(trainerSaved);
    }

    public void delete(Long id) {
        var trainer = trainerRepository.findFirstByEnabledIsTrueAndIdTrainer(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));
        var trainerDeleted = TrainerMapper.toEntity(trainer);
        trainerRepository.save(trainerDeleted);
    }


}
