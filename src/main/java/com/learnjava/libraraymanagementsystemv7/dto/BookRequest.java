package com.learnjava.libraraymanagementsystemv7.dto;

import jakarta.validation.constraints.NotBlank;

public class BookRequest
{
    @NotBlank(message="Title must not be blank")
    private String title;

    @NotBlank(message="Author must not be blank")
    private String author;

    private boolean available;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
