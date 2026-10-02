package com.learnjava.libraraymanagementsystemv7.controller;

import com.learnjava.libraraymanagementsystemv7.dto.BorrowRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LoanResponse;
import com.learnjava.libraraymanagementsystemv7.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<LoanResponse> borrowBook(
            @Valid @RequestBody BorrowRequest request) {

        LoanResponse response =
                loanService.borrowBook(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{loanId}/return")
    public ResponseEntity<LoanResponse> returnBook(
            @PathVariable int loanId) {

        LoanResponse response =
                loanService.returnBook(loanId);

        return ResponseEntity.ok(response);
    }
}