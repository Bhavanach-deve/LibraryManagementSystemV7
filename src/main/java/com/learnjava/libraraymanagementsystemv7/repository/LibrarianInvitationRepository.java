package com.learnjava.libraraymanagementsystemv7.repository;

import com.learnjava.libraraymanagementsystemv7.entity.LibrarianInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrarianInvitationRepository
        extends JpaRepository<LibrarianInvitation, Integer> {

    Optional<LibrarianInvitation> findByToken(String token);
}