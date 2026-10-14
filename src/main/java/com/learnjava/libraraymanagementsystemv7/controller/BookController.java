package com.learnjava.libraraymanagementsystemv7.controller;

import com.learnjava.libraraymanagementsystemv7.dto.BookRequest;
import com.learnjava.libraraymanagementsystemv7.dto.BookResponse;
import com.learnjava.libraraymanagementsystemv7.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Validated
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse>addBook(
            @Valid @RequestBody BookRequest bookRequest)
    {
        BookResponse response = bookService.addBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBooks(
            @RequestParam(required = false) String author,

            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page number cannot be negative")
            int page,

            @RequestParam(defaultValue = "20")
            @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 100, message = "Page size cannot exceed 100")
            int size,

            Sort sort) {

        Pageable pageable = PageRequest.of(page, size, sort);

        if (author != null && !author.isBlank()) {
            Page<BookResponse> books =
                    bookService.getBooksByAuthor(author.trim(), pageable);

            return ResponseEntity.ok(books);
        }

        Page<BookResponse> books =
                bookService.getAllBooks(pageable);

        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable int id) {
        BookResponse response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable int id){
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable int id,
            @Valid @RequestBody BookRequest bookRequest) {

        BookResponse response = bookService.updateBook(id, bookRequest);

        return ResponseEntity.ok(response);
    }
}
