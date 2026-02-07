package com.library.src.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    private final String id;
    private final LocalDateTime loanDate;

    public Book(String id, LocalDateTime loanDate) {
        this.id = id;
        this.loanDate = loanDate;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(loanDate, book.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loanDate);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", loanDate=" + loanDate +
                '}';
    }
}
