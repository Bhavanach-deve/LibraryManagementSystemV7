
package com.learnjava.libraraymanagementsystemv7.controller;

import com.learnjava.libraraymanagementsystemv7.dto.BookCopyRequest;
import com.learnjava.libraraymanagementsystemv7.dto.BookCopyResponse;
import com.learnjava.libraraymanagementsystemv7.service.BookCopyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-copies")
public class BookCopyController {

    private final BookCopyService bookCopyService;

    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @PostMapping
    public ResponseEntity<BookCopyResponse> addBookCopy(
            @Valid @RequestBody BookCopyRequest request) {

        BookCopyResponse response =
                bookCopyService.addBookCopy(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}