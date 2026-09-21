package com.learnjava.libraraymanagementsystemv7.service;

import com.learnjava.libraraymanagementsystemv7.dto.LibrarianRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LibrarianResponse;
import com.learnjava.libraraymanagementsystemv7.entity.Librarian;
import com.learnjava.libraraymanagementsystemv7.exception.LibrarianNotFoundException;
import com.learnjava.libraraymanagementsystemv7.repository.LibrarianRepository;
import org.springframework.stereotype.Service;
import com.learnjava.libraraymanagementsystemv7.entity.AppUser;
import com.learnjava.libraraymanagementsystemv7.repository.AppUserRepository;
import org.springframework.transaction.annotation.Transactional;
import com.learnjava.libraraymanagementsystemv7.exception.AppUserNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibrarianService
{
    private final LibrarianRepository librarianRepository;
    private final AppUserRepository appUserRepository;

    public LibrarianService(LibrarianRepository librarianRepository,AppUserRepository appUserRepository) {
        this.librarianRepository = librarianRepository;
        this.appUserRepository=appUserRepository;
    }
  
    public List<LibrarianResponse> getAllLibrarians() {

        List<Librarian> librarians = librarianRepository.findAll();

        List<LibrarianResponse> responses = new ArrayList<>();

        for (Librarian librarian : librarians) {
            responses.add(toLibrarianResponse(librarian));
        }

        return responses;
    }
    public LibrarianResponse getLibrarianById(int id) {

        Librarian librarian = findLibrarianById(id);

        return toLibrarianResponse(librarian);

    }
    public LibrarianResponse updateLibrarian(
            int id,
            LibrarianRequest librarianRequest) {

        Librarian librarian = findLibrarianById(id);

        librarian.setName(librarianRequest.getName());

        Librarian updatedLibrarian =
                librarianRepository.save(librarian);

        return toLibrarianResponse(updatedLibrarian);
    }
    @Transactional
    public void deleteLibrarianById(int id)
    {
        Librarian librarian = findLibrarianById(id);

        AppUser appUser = appUserRepository
                .findByEmail(librarian.getEmail())
                .orElseThrow(() ->
                        new AppUserNotFoundException(
                                "AppUser not found for librarian: "
                                        + librarian.getEmail()));

        appUser.setEnabled(false);

        appUserRepository.save(appUser);
    }
    private LibrarianResponse toLibrarianResponse(Librarian librarian) {

        LibrarianResponse response = new LibrarianResponse();

        response.setId(librarian.getId());
        response.setName(librarian.getName());
        response.setEmail(librarian.getEmail());

        return response;
    }
    private Librarian findLibrarianById(int id) {

        return librarianRepository.findById(id)
                .orElseThrow(() ->
                        new LibrarianNotFoundException(
                                "Librarian with id " + id + " not found"));
    }
}
