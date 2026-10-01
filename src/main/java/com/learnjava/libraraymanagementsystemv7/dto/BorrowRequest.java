package com.learnjava.libraraymanagementsystemv7.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BorrowRequest {

    @NotNull
    @Min(1)
    private Integer memberId;

    @NotNull
    @Min(1)
    private Integer bookCopyId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Integer bookCopyId) {
        this.bookCopyId = bookCopyId;
    }
}