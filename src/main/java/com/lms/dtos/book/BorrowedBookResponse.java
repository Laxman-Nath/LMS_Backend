package com.lms.dtos.book;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BorrowedBookResponse {
    private long bookId;
    private String title;
    private Double fineAmount;
    private LocalDate borrowedDate;
    private LocalDate renewalDate;

    public BorrowedBookResponse(long bookId,String title, Double fineAmount, LocalDate borrowedDate, LocalDate renewalDate) {
        this.bookId=bookId;
        this.borrowedDate = borrowedDate;
        this.fineAmount = fineAmount;
        this.title = title;
        this.renewalDate = renewalDate;
    }
}
