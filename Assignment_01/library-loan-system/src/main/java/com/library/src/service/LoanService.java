package com.library.src.service;

import com.library.src.model.Book;
import com.library.src.repository.LoanRepository;

import java.util.Set;

public class LoanService {
    private final LoanRepository repository;

    public LoanService() {
        this.repository = new LoanRepository();
    }

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public void loanBook(String bookId) {
        if (bookId==null || bookId.isEmpty()) {
            System.out.println("Error: Book ID cannot be empty");
            return;
        }
        if (repository.isBookLoaned(bookId)) {
            System.out.println("Warning: Book " + bookId + " is already loaned out");
            return;
        }
        Book book = new Book(bookId);
        if (repository.addLoan(book)) {
            System.out.println("Book " + bookId + " has been loaned out successfully");
        }
        else {
            System.out.println("Error: Failed to loan book " + bookId);
        }
    }

    public void returnBook(String bookId) {
        if (bookId==null || bookId.isEmpty()) {
            System.out.println("Error: Book ID cannot be empty");
            return;
        }
        if (!repository.isBookLoaned(bookId)) {
            System.out.println("Warning: Book " + bookId + " is not currently loaned out");
            return;
        }
        if (repository.returnBook(bookId)) {
            System.out.println("Book " + bookId + " has been returned successfully");
        }
        else {
            System.out.println("Error: Failed to return book " + bookId);
        }
    }

    public Set<Book> printLoanedBooks() {
        Set<Book> loanedBooks = repository.getLoanedBooks();
        if (loanedBooks.isEmpty()) {
            System.out.println("No books are currently loaned out");
        }
        else {
            System.out.println("Currently loaned books:");
            loanedBooks.forEach(book -> System.out.println("- " + book.getId() + " (loaned on " + book.getLoanDate() + ")"));
        }
        return loanedBooks;
    }
}
