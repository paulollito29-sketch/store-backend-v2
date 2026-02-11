package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.SongMapper;
import com.example.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongFindAll> findAll() {
        return songRepository.findAllByEnabledIsTrueOrderByIdSongDesc().stream()
                .map(SongMapper::toSongFindAll)
                .toList();
    }

    public SongCreated create(SongCreate dto) {
        if (songRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }

        var songEntity = SongMapper.toEntity(dto);
        var songSaved = songRepository.save(songEntity);
        return SongMapper.toSongCreated(songSaved);
    }

    public SongFindOne findOne(Long id) {
        var song = songRepository.findFirstByEnabledIsTrueAndIdSong(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found"));

        return SongMapper.toSongFindOne(song);
    }

    public SongUpdated update(Long id, SongUpdate dto) {
        var song = songRepository.findFirstByEnabledIsTrueAndIdSong(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found"));

        if (songRepository.existsByEnabledIsTrueAndIdSongNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }

        var songEntity = SongMapper.toEntity(song, dto);
        var songSaved = songRepository.save(songEntity);
        return SongMapper.toSongUpdated(songSaved);
    }

    public void delete(Long id) {
        var song = songRepository.findFirstByEnabledIsTrueAndIdSong(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found"));

        var songEntity = SongMapper.toEntity(song);
        songRepository.save(songEntity);
    }
}