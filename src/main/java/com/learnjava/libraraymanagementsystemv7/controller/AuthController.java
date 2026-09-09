package com.learnjava.libraraymanagementsystemv7.controller;

import com.learnjava.libraraymanagementsystemv7.dto.LoginRequest;
import com.learnjava.libraraymanagementsystemv7.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequest request)
    {
        System.out.println("LOGIN CONTROLLER REACHED");

        String token = authService.login(request);

        System.out.println("TOKEN GENERATED");

        return ResponseEntity.ok(token);

    }
}

