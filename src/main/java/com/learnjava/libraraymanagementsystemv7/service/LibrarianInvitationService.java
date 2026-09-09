package com.learnjava.libraraymanagementsystemv7.service;

import com.learnjava.libraraymanagementsystemv7.dto.LibrarianInvitationRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LibrarianInvitationResponse;
import com.learnjava.libraraymanagementsystemv7.entity.LibrarianInvitation;
import com.learnjava.libraraymanagementsystemv7.repository.LibrarianInvitationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LibrarianInvitationService {

    private final LibrarianInvitationRepository invitationRepository;

    public LibrarianInvitationService(
            LibrarianInvitationRepository invitationRepository) {

        this.invitationRepository = invitationRepository;
    }
    public LibrarianInvitationResponse createInvitation(
            LibrarianInvitationRequest request) {

        LibrarianInvitation invitation = new LibrarianInvitation();

        invitation.setEmail(request.getEmail());

        invitation.setToken(UUID.randomUUID().toString());

        invitation.setExpiresAt(
                LocalDateTime.now().plusHours(24)
        );

        invitation.setUsed(false);

        LibrarianInvitation savedInvitation =
                invitationRepository.save(invitation);

        LibrarianInvitationResponse response =
                new LibrarianInvitationResponse();

        response.setId(savedInvitation.getId());
        response.setEmail(savedInvitation.getEmail());
        response.setToken(savedInvitation.getToken());
        response.setExpiresAt(savedInvitation.getExpiresAt());

        return response;
    }
}