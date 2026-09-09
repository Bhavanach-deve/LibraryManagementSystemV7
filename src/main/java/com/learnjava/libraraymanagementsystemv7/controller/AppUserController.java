package com.learnjava.libraraymanagementsystemv7.controller;

import com.learnjava.libraraymanagementsystemv7.dto.AppUserRequest;
import com.learnjava.libraraymanagementsystemv7.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(
            @Valid @RequestBody AppUserRequest request) {

        appUserService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
