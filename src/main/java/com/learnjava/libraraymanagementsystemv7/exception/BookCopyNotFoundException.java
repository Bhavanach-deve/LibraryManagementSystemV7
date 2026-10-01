package com.learnjava.libraraymanagementsystemv7.exception;

public class BookCopyNotFoundException extends RuntimeException {

    public BookCopyNotFoundException(String message) {
        super(message);
    }
}