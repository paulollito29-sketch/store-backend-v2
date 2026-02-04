package com.example.service;

import com.example.dto.*;
import com.example.entity.BookEntity;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.StockInvalidException;
import com.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRespository){
        this.bookRepository = bookRespository;
    }

    public List<BookFindAll> findAll() {
        return bookRepository.findAllByActiveIsTrueOrderByIdBookDesc().stream()
                .map(item -> new BookFindAll(item.getIdBook(),item.getTitle(),item.getDescription(),item.getPrice(),item.getStock()))
                .toList();
    }

    public BookCreated create(BookCreate dto) {
        if (bookRepository.existsByActiveIsTrueAndTitleIgnoreCase(dto.title())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.title() + " already exists");
        }
        var bookEntity = BookEntity.builder()
                .title(dto.title())
                .description(dto.description())
                .price(dto.price())
                .stock(dto.stock())
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();
        var bookSaved = bookRepository.save(bookEntity);
        return new BookCreated(bookSaved.getIdBook(), bookSaved.getTitle(),bookSaved.getDescription(),bookSaved.getPrice(),bookSaved.getStock());
    }

    public BookFindOne findOne(Long id) {
        var book = bookRepository.findFirstByActiveIsTrueAndIdBook(id)
                .orElseThrow(() -> new RuntimeException("book not found"));
        return new BookFindOne(book.getIdBook(), book.getTitle(),book.getDescription(),book.getPrice(),book.getStock());
    }

    public BookUpdated update(long id, BookUpdate dto) {
        var bookUpdated = bookRepository.findFirstByActiveIsTrueAndIdBook(id)
                .orElseThrow(() -> new RuntimeException("book not found"));
        if (bookRepository.existsByActiveIsTrueAndIdBookNotAndTitleIgnoreCase(id, bookUpdated.getTitle())) {
            throw new RuntimeException("this title" + dto.title() + " already exists");
        }
        bookUpdated.setTitle(dto.title());
        bookUpdated.setDescription(dto.description());
        bookUpdated.setPrice(dto.price());
        bookUpdated.setStock(dto.stock());
        bookUpdated.setUpdatedAt(LocalDateTime.now());
        var bookSaved = bookRepository.save(bookUpdated);
        return new BookUpdated(bookSaved.getIdBook(), bookSaved.getTitle(),bookSaved.getDescription(),bookSaved.getPrice(),bookSaved.getStock());
    }

    public void delete(Long id) {
        var book = bookRepository.findFirstByActiveIsTrueAndIdBook(id)
                .orElseThrow(() -> new RuntimeException("book not found"));
        book.setActive(false);
        book.setDeletedAt(LocalDateTime.now());
        bookRepository.save(book);
    }

}
