package com.library.src;

import com.library.src.model.Book;
import com.library.src.service.LoanService;

import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome to the library!");

        LoanService loanService = new LoanService();
        System.out.println("=== Library Loan Management System ===\n");

        // Loan some books
        loanService.loanBook("B001");
        loanService.loanBook("B002");
        loanService.loanBook("B003");
        loanService.loanBook("B004");
        loanService.loanBook("B005");

        // Print currently loaned books
        System.out.println();
        Set<Book> test = loanService.printLoanedBooks();
        System.out.println("\nTotal loaned books: " + test);

        // Try to loan a book that's already loaned
        loanService.loanBook("B003");
        System.out.println();

        // Return some books
        loanService.returnBook("B002");
        loanService.returnBook("B004");
        System.out.println();
        loanService.printLoanedBooks();

        // Try to return a book that wasn't loaned
        System.out.println();
        loanService.returnBook("B999");

        // Loan more books
        System.out.println();
        loanService.loanBook("B006");
        loanService.loanBook("B007");

        System.out.println();
        loanService.printLoanedBooks();
    }
}