package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.AnimalMapper;
import com.example.repository.AnimalRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    public List<AnimalFindAll> findAll(){
        return animalRepository.findAllByEnabledIsTrueOrderByIdAnimalDesc().stream()
                .map(AnimalMapper::toFindAll)
                .toList();
    }

    public AnimalCreated create(AnimalCreate dto){
        if (animalRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }
        var animalCreated = AnimalMapper.toEntity(dto) ;
        var animalSaved = animalRepository.save(animalCreated);
        return AnimalMapper.toAnimalCreated(animalSaved);
    }

    public AnimalFindOne findOne(Long id){
        var animal = animalRepository.findFirstByEnabledIsTrueAndIdAnimal(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));
        return AnimalMapper.toAnimalFindOne(animal);
    }

    public AnimalUpdated update(AnimalUpdate dto, Long id){
        if (animalRepository.existsByEnabledIsTrueAndIdAnimalNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }
        var animal = animalRepository.findFirstByEnabledIsTrueAndIdAnimal(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));
        var animalUpdated = AnimalMapper.toEntityUpdated(animal, dto);
        var animalSaved = animalRepository.save(animalUpdated);
        return AnimalMapper.toAnimalUpdated(animalSaved);
    }

    public void delete(Long id){
        var animal = animalRepository.findFirstByEnabledIsTrueAndIdAnimal(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));
        var animalDeleted= AnimalMapper.toEntityDeleted(animal);
        animalRepository.save(animalDeleted);
    }
}
