package com.learnjava.libraraymanagementsystemv7.exception;

public class ActiveLoanAlreadyExistsException extends RuntimeException {

    public ActiveLoanAlreadyExistsException(String message) {
        super(message);
    }
}