package com.library.src.repository;

import com.library.src.model.Book;

import java.util.LinkedHashSet;
import java.util.Set;

public class LoanRepository {

    private final LinkedHashSet<Book> loanedBooks;

    public LoanRepository() {
        this.loanedBooks = new LinkedHashSet<>();
    }

    public boolean addLoan(Book book) {
        return loanedBooks.add(book);
    }

    public int getLoanCount() {
        return loanedBooks.size();
    }

    public boolean isBookLoaned(String bookId) {
        return loanedBooks.stream()
                .anyMatch(book -> book.getId().equals(bookId));
    }

    public boolean returnBook(String bookId) {
        return loanedBooks.removeIf(book -> book.getId().equals(bookId));
    }


    public Set<Book> getLoanedBooks() {
        return loanedBooks;
    }
}
