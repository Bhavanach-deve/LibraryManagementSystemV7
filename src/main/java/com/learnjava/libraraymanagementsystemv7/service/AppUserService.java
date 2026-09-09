package com.learnjava.libraraymanagementsystemv7.service;

import com.learnjava.libraraymanagementsystemv7.dto.AppUserRequest;
import com.learnjava.libraraymanagementsystemv7.entity.AppUser;
import com.learnjava.libraraymanagementsystemv7.repository.AppUserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository,
                          PasswordEncoder passwordEncoder) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        System.out.println("Searching user with email: " + username);

        AppUser appUser = appUserRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + username));

        System.out.println("User found: " + appUser.getEmail());

        return User.builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(appUser.getRole())
                .build();
    }

    public void registerUser(AppUserRequest request) {

        AppUser appUser = new AppUser();

        appUser.setEmail(request.getEmail());

        appUser.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        appUser.setRole("USER");

        appUserRepository.save(appUser);
    }

}
