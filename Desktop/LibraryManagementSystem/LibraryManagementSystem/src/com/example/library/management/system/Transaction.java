package com.example.library.management.system;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private LocalDateTime dateTime;
    private int userId;
    private int bookId;
    private String type; // "borrow" or "return"

    public Transaction(int transactionId, LocalDateTime dateTime, int userId, int bookId, String type) {
        this.transactionId = transactionId;
        this.dateTime = dateTime;
        this.userId = userId;
        this.bookId = bookId;
        this.type = type;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getType() {
        return type;
    }
}
