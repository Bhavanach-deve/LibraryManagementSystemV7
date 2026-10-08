package com.learnjava.libraraymanagementsystemv7.exception;

public class MaximumActiveLoansExceededException
        extends RuntimeException {

    public MaximumActiveLoansExceededException(String message) {
        super(message);
    }
}
