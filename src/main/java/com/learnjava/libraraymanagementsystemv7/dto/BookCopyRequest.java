package com.learnjava.libraraymanagementsystemv7.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BookCopyRequest {

    @NotNull
    @Min(1)
    private Integer bookId;

    @NotNull
    @Min(1)
    private Integer copyNumber;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(Integer copyNumber) {
        this.copyNumber = copyNumber;
    }
}