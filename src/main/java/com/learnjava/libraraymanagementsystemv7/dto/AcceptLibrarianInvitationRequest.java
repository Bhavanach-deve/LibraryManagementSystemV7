package com.learnjava.libraraymanagementsystemv7.dto;

import jakarta.validation.constraints.NotBlank;

public class AcceptLibrarianInvitationRequest {

    @NotBlank(message = "Token must not be blank")
    private String token;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Password must not be blank")
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}