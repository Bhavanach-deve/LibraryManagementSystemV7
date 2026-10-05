package com.learnjava.libraraymanagementsystemv7.controller;

import com.learnjava.libraraymanagementsystemv7.dto.BorrowRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LoanResponse;
import com.learnjava.libraraymanagementsystemv7.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponse> getLoanById(
            @PathVariable int loanId) {

        LoanResponse response =
                loanService.getLoanById(loanId);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<LoanResponse>> getLoansByMemberId(
            @PathVariable int memberId) {

        List<LoanResponse> responses =
                loanService.getLoansByMemberId(memberId);

        return ResponseEntity.ok(responses);
    }
    @GetMapping("/member/{memberId}/active")
    public ResponseEntity<List<LoanResponse>> getActiveLoansByMemberId(
            @PathVariable int memberId) {

        List<LoanResponse> responses =
                loanService.getActiveLoansByMemberId(memberId);

        return ResponseEntity.ok(responses);
    }
}