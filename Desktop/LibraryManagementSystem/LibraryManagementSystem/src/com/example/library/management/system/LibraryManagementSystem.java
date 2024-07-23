package com.example.library.management.system;

import java.util.Scanner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {
    private List<Book> books = new ArrayList<>();
    private List<LibraryUser> users = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private int nextUserId = 1;
    private int nextTransactionId = 1;

    public void addBook(int bookId, String title, String author) {
        Book newBook = new Book(bookId, title, author);
        books.add(newBook);
    }

    public void registerUser(String name) {
        LibraryUser newUser = new LibraryUser(nextUserId++, name);
        users.add(newUser);
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        System.out.println("================");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Book ID: " + book.getBookId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
        System.out.println("================");
    }

    public void displayUserDetails(int userId) {
        for (LibraryUser user : users) {
            if (user.getUserId() == userId) {
                System.out.println("User ID: " + user.getUserId() + ", Name: " + user.getName());
                System.out.println("Borrowed Books:");
                for (int bookId : user.getBorrowedBooks()) {
                    System.out.println("Book ID: " + bookId);
                }
                return;
            }
        }
        System.out.println("User not found.");
    }

    public void issueBook(int userId, int bookId) {
        // Check if the book is available
        for (Book book : books) {
            if (book.getBookId() == bookId && book.isAvailable()) {
                book.setAvailable(false);
                // Record the transaction
                transactions.add(new Transaction(nextTransactionId++, LocalDateTime.now(), userId, bookId, "borrow"));
                // Update user's borrowed books
                for (LibraryUser user : users) {
                    if (user.getUserId() == userId) {
                        user.borrowBook(bookId);
                        break;
                    }
                }
                System.out.println("Book issued successfully!");
                return;
            }
        }
        System.out.println("Book not available or does not exist.");
    }

    public void returnBook(int userId, int bookId) {
        // Check if the user has borrowed the book
        for (LibraryUser user : users) {
            if (user.getUserId() == userId && user.getBorrowedBooks().contains(bookId)) {
                // Mark the book as available
                for (Book book : books) {
                    if (book.getBookId() == bookId) {
                        book.setAvailable(true);
                        break;
                    }
                }
                // Record the transaction
                transactions.add(new Transaction(nextTransactionId++, LocalDateTime.now(), userId, bookId, "return"));
                // Update user's borrowed books
                user.returnBook(bookId);
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("Book ID invalid or not borrowed by the user.");
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        System.out.println("====================");
        for (Transaction transaction : transactions) {
            String type = transaction.getType().equals("borrow") ? "Borrowed" : "Returned";
            System.out.println("Transaction ID: " + transaction.getTransactionId() + ", Date: " + transaction.getDateTime()
                    + ", User ID: " + transaction.getUserId() + ", Book ID: " + transaction.getBookId() + ", Type: " + type);
        }
        System.out.println("====================");
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Adding some initial books
        library.addBook(1, "Java Programming", "John Doe");
        library.addBook(2, "Python Programming", "Jane Smith");

        // Adding some initial users
        library.registerUser("Alice");
        library.registerUser("Bob");

        while (true) {
            System.out.println("\n==== Library Management System Menu ====");
            System.out.println("1. Display all available books");
            System.out.println("2. Register new user");
            System.out.println("3. Issue a book");
            System.out.println("4. Return a book");
            System.out.println("5. Display user details");
            System.out.println("6. Display transaction history");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter new user name: ");
                    scanner.nextLine(); // Consume newline
                    String userName = scanner.nextLine();
                    library.registerUser(userName);
                    System.out.println("User registered successfully!");
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter book ID to issue: ");
                    int issueBookId = scanner.nextInt();
                    library.issueBook(userId, issueBookId);
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    int returnUserId = scanner.nextInt();
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();
                    library.returnBook(returnUserId, returnBookId);
                    break;
                case 5:
                    System.out.print("Enter user ID to display details: ");
                    int userDetailsId = scanner.nextInt();
                    library.displayUserDetails(userDetailsId);
                    break;
                case 6:
                    library.displayTransactionHistory();
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }
}
