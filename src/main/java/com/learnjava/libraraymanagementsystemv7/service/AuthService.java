package com.learnjava.libraraymanagementsystemv7.service;

import com.learnjava.libraraymanagementsystemv7.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    public String login(LoginRequest request) {

        System.out.println("Starting authentication...");

        try {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()
                            )
                    );

            System.out.println("Authentication successful!");

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            String role = authentication.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();

            System.out.println("Authenticated role: " + role);

            return jwtService.generateToken(
                    request.getEmail(),
                    role
            );

        } catch (Exception e) {

            System.out.println("AUTHENTICATION FAILED!");
            System.out.println("Exception: " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());

            throw e;
        }
    }
}