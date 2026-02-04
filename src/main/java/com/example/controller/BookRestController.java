package com.example.controller;

import com.example.dto.*;
import com.example.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private BookService bookService;

    public BookRestController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookFindAll>> get() {
        var Books = bookService.findAll();
        return ResponseEntity.ok(Books);
    }

    @PostMapping
    public ResponseEntity<BookCreated> post(@Valid @RequestBody BookCreate dto) {
        var bookCreated = bookService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookCreated.idBook())
                .toUri();
        return ResponseEntity.created(location).body(bookCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookUpdated> update(@PathVariable Long id,
                                                  @RequestBody BookUpdate dto) {
        var bookUpdated = bookService.update(id, dto);
        return ResponseEntity.ok(bookUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookFindOne> findOne(@PathVariable Long id) {
        var book = bookService.findOne(id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
