package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.PostMapper;
import com.example.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository  postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<PostFindAll> findAll(){
        return postRepository. findAllByEnabledIsTrueOrderByIdPostDesc().stream().map(PostMapper::toFindAll).toList();
    }

    public PostCreated create(PostCreate dto){
        if(postRepository.existsByEnabledIsTrueAndTitleIgnoreCase(dto.title())){
            throw new ResourceAlreadyExistsException("Post already exists");
        }
        var postEntity = PostMapper.toEntityCreated(dto);
        var postSaved = postRepository.save(postEntity);
        return PostMapper.toPostCreated(postSaved);
    }

    public PostFindOne findOne(Long id){
        var post = postRepository.findFirstByEnabledIsTrueAndIdPost(id)
                .orElseThrow(()-> new ResourceNotFoundException("post doesnt exists"));
        return PostMapper.toFindOne(post);
    }

    public PostUpdated update(Long id, PostUpdate dto){
        var post = postRepository.findFirstByEnabledIsTrueAndIdPost(id)
                .orElseThrow(()-> new ResourceNotFoundException("post doesnt exists"));
        if(postRepository.existsByEnabledIsTrueAndIdPostNotAndTitleIgnoreCase(id, dto.title())){
            throw new ResourceAlreadyExistsException("Post already exists");
        }
        var postUpdated = PostMapper.toEntityUpdated(post, dto);
        var postSaved = postRepository.save(postUpdated);
        return PostMapper.toPostUpdated(postSaved);
    }

    public void delete(Long id){
        var post = postRepository.findFirstByEnabledIsTrueAndIdPost(id)
                .orElseThrow(()-> new ResourceNotFoundException("post doesnt exists"));
        var postDeleted = PostMapper.toPostDeleted(post);
        postRepository.save(postDeleted);
    }
}
