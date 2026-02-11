package com.example.service;

import com.example.dto.*;
import com.example.entity.MovieEntity;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.MovieMapper;
import com.example.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public List<MovieFindAll> findAll(){
        return movieRepository.findAllByEnabledIsTrueOrderByCreatedAtDesc().stream().map(MovieMapper::toFindAll).toList();
    }

    public MovieCreated create(MovieCreate dto){
        if(movieRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())){
            throw new ResourceAlreadyExistsException("movie already exists");
        }
        var movieCreated = MovieMapper.toEntityCreated(dto);
        var movieSaved = movieRepository.save(movieCreated);
        return MovieMapper.toMovieCreated(movieSaved);
    }

    public MovieFindOne findOne(String id) {
        var movie = movieRepository.findFirstByEnabledIsTrueAndIdMovie(id)
    .orElseThrow(()-> new ResourceNotFoundException("movie not found"));
        return MovieMapper.toFindOne(movie);
    }

    public MovieUpdated update(String id, MovieUpdate dto){
        var movie = movieRepository.findFirstByEnabledIsTrueAndIdMovie(id)
                .orElseThrow(()-> new ResourceNotFoundException("movie not found"));
        if(movieRepository.existsByEnabledIsTrueAndIdMovieNotAndNameIgnoreCase(id, dto.name())){
            throw new ResourceAlreadyExistsException("movie name already exists");
        }
        var movieUpdated = MovieMapper.toEntityUpdated(movie, dto);
        var movieSaved = movieRepository.save(movieUpdated);
        return MovieMapper.toMovieUpdated(movieSaved);
    }

    public void delete(String id){
        var movie = movieRepository.findFirstByEnabledIsTrueAndIdMovie(id)
                .orElseThrow(()-> new ResourceNotFoundException("movie not found"));
        var movieDeleted= MovieMapper.deleted(movie);
        movieRepository.save(movieDeleted);
    }
}
