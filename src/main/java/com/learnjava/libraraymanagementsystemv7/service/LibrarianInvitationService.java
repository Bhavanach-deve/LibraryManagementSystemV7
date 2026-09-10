package com.learnjava.libraraymanagementsystemv7.service;

import com.learnjava.libraraymanagementsystemv7.dto.AcceptLibrarianInvitationRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LibrarianInvitationRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LibrarianInvitationResponse;
import com.learnjava.libraraymanagementsystemv7.entity.AppUser;
import com.learnjava.libraraymanagementsystemv7.entity.LibrarianInvitation;
import com.learnjava.libraraymanagementsystemv7.repository.AppUserRepository;
import com.learnjava.libraraymanagementsystemv7.repository.LibrarianInvitationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.learnjava.libraraymanagementsystemv7.entity.Librarian;
import com.learnjava.libraraymanagementsystemv7.repository.LibrarianRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LibrarianInvitationService {

    private final LibrarianInvitationRepository invitationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final LibrarianRepository librarianRepository;

    public LibrarianInvitationService(
            LibrarianInvitationRepository invitationRepository,
            PasswordEncoder passwordEncoder,
            AppUserRepository appUserRepository,
            LibrarianRepository librarianRepository) {

        this.invitationRepository = invitationRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
        this.librarianRepository = librarianRepository;
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
    @Transactional
    public void acceptInvitation(
            AcceptLibrarianInvitationRequest request) {

        LibrarianInvitation invitation =
                invitationRepository.findByToken(request.getToken())
                        .orElseThrow(() ->
                                new RuntimeException("Invalid invitation token"));

        if (invitation.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invitation has expired");
        }
        if (invitation.isUsed()) {
            throw new RuntimeException("Invitation has already been used");
        }
        AppUser appUser = new AppUser();

        appUser.setEmail(invitation.getEmail());

        appUser.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        appUser.setRole("LIBRARIAN");
        appUserRepository.save(appUser);

        Librarian librarian = new Librarian();

        librarian.setName(request.getName());
        librarian.setEmail(invitation.getEmail());

        librarianRepository.save(librarian);
        invitation.setUsed(true);

        invitationRepository.save(invitation);

    }

}