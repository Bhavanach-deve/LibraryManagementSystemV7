package com.learnjava.libraraymanagementsystemv7.controller;

import com.learnjava.libraraymanagementsystemv7.dto.BookRequest;
import com.learnjava.libraraymanagementsystemv7.dto.BookResponse;
import com.learnjava.libraraymanagementsystemv7.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<BookResponse>> getAllBooks()
    {
        List<BookResponse>books=bookService.getAllBooks();
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
