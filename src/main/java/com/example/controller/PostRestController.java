package com.example.controller;

import com.example.dto.*;
import com.example.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostFindAll>> get() {
        var posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<PostCreated> post(@Valid @RequestBody PostCreate dto) {
        var post = postService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.postId()).toUri();
        return ResponseEntity.created(location).body(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostFindOne> findOne(@PathVariable Long id) {
        var post = postService.findOne(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostUpdated> put(@PathVariable Long id,
                                           @Valid @RequestBody PostUpdate dto ) {
        var posts = postService.update(id, dto);
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
