package com.learnjava.libraraymanagementsystemv7.controller;

import com.learnjava.libraraymanagementsystemv7.dto.AcceptLibrarianInvitationRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LibrarianInvitationRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LibrarianInvitationResponse;
import com.learnjava.libraraymanagementsystemv7.service.LibrarianInvitationService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarian-invitations")
public class LibrarianInvitationController {

    private final LibrarianInvitationService invitationService;

    public LibrarianInvitationController(
            LibrarianInvitationService invitationService) {

        this.invitationService = invitationService;
    }
    @PostMapping
    public ResponseEntity<LibrarianInvitationResponse> createInvitation(
            @Valid @RequestBody LibrarianInvitationRequest request) {

        LibrarianInvitationResponse response =
                invitationService.createInvitation(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @PostMapping("/accept")
    public ResponseEntity<String> acceptInvitation(
            @Valid @RequestBody AcceptLibrarianInvitationRequest request) {

        invitationService.acceptInvitation(request);

        return ResponseEntity.ok("Librarian invitation accepted successfully");
    }

}
