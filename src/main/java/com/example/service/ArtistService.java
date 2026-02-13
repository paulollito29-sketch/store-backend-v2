package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.ArtistMapper;
import com.example.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<ArtistFindAll> findAll() {
        return artistRepository.findAllByEnabledIsTrueOrderByIdArtistDesc().stream()
                .map(ArtistMapper::toArtistFindAll)
                .toList();
    }

    public ArtistCreated create(ArtistCreate dto) {
        if (artistRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }

        var artistEntity = ArtistMapper.toEntity(dto);
        var artistSaved = artistRepository.save(artistEntity);
        return ArtistMapper.toArtistCreated(artistSaved);
    }

    public ArtistFindOne findOne(Long id) {
        var artist = artistRepository.findFirstByEnabledIsTrueAndIdArtist(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));

        return ArtistMapper.toArtistFindOne(artist);
    }

    public ArtistUpdated update(Long id, ArtistUpdate dto) {
        var artist = artistRepository.findFirstByEnabledIsTrueAndIdArtist(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));

        if (artistRepository.existsByEnabledIsTrueAndIdArtistNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }

        var artistEntity = ArtistMapper.toEntity(artist, dto);
        var artistSaved = artistRepository.save(artistEntity);
        return ArtistMapper.toArtistUpdated(artistSaved);
    }

    public void delete(Long id) {
        var artist = artistRepository.findFirstByEnabledIsTrueAndIdArtist(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));

        var artistEntity = ArtistMapper.toEntity(artist);
        artistRepository.save(artistEntity);
    }
}