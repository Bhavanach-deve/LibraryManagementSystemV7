package com.learnjava.libraraymanagementsystemv7.controller;

import com.learnjava.libraraymanagementsystemv7.dto.LibrarianRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LibrarianResponse;
import com.learnjava.libraraymanagementsystemv7.service.LibrarianService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    private final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @GetMapping
    public ResponseEntity<List<LibrarianResponse>> getAllLibrarians() {

        List<LibrarianResponse> librarians =
                librarianService.getAllLibrarians();

        return ResponseEntity.ok(librarians);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LibrarianResponse> getLibrarianById(
            @PathVariable int id) {

        LibrarianResponse response =
                librarianService.getLibrarianById(id);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LibrarianResponse> updateLibrarian(
            @PathVariable int id,
            @Valid @RequestBody LibrarianRequest librarianRequest) {

        LibrarianResponse response =
                librarianService.updateLibrarian(id, librarianRequest);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrarianById(
            @PathVariable int id) {

        librarianService.deleteLibrarianById(id);

        return ResponseEntity.noContent().build();
    }
}
