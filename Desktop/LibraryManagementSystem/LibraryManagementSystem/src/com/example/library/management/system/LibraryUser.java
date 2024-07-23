package com.example.library.management.system;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class LibraryUser {
    private int userId;
    private String name;
    private List<Integer> borrowedBooks;

    public LibraryUser(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(int bookId) {
        borrowedBooks.add(bookId);
    }

    public void returnBook(int bookId) {
        borrowedBooks.remove(Integer.valueOf(bookId));
    }
}
