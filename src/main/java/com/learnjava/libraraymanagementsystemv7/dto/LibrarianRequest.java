package com.learnjava.libraraymanagementsystemv7.dto;

import jakarta.validation.constraints.NotBlank;

public class LibrarianRequest {

    @NotBlank(message = "Name must not be blank")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}